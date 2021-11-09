package com.secret.payment.business

import com.secret.payment.Account
import com.secret.payment.Transfer
import com.secret.payment.repository.AccountRepositoryService
import com.secret.payment.repository.TransferRepositoryService
import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification
import spock.lang.Unroll

import static com.secret.payment.constants.TransactionType.CREDIT
import static com.secret.payment.constants.TransactionType.DEBIT
import static com.secret.payment.utils.TestHelperUtils.DESTINATION_ACCOUNT_PROPERTIES_MAP
import static com.secret.payment.utils.TestHelperUtils.SOURCE_ACCOUNT_PROPERTIES_MAP
import static com.secret.payment.utils.TestHelperUtils.validateConstraints

class TransferServiceTest extends Specification implements ServiceUnitTest<TransferService>, DataTest {

    def setup() {
        mockDomain(Transfer)
        service.transferRepositoryService = Mock(TransferRepositoryService)
        service.accountRepositoryService = Mock(AccountRepositoryService)
        service.notificationService = Mock(NotificationService)
    }

    @Unroll
    def "test ProcessRequest"() {
        given:
        Account sourceAccount = Account.of(SOURCE_ACCOUNT_PROPERTIES_MAP)
        Account destinationAccount = Account.of(DESTINATION_ACCOUNT_PROPERTIES_MAP)
        Transfer transfer = new Transfer(sourceAccount: sourceAccount, destinationAccount: destinationAccount, amount: amount)

        expect:
        service.processRequest(transfer) == isSuccess
        sourceAccount.balance == sourceAccountRemainingBalance
        destinationAccount.balance == destinationAccountRemainingBalance

        where:
        isSuccess | amount | sourceAccountRemainingBalance | destinationAccountRemainingBalance
        false     | 250    | 200                           | 200
        true      | 100    | 100                           | 300
        true      | 200    | 0                             | 400
    }

    @Unroll
    def "test ValidateRequest"() {
        given:
        Transfer transfer = new Transfer(sourceAccount: sourceAccount, destinationAccount: destinationAccount, amount: amount)

        expect:
        service.validateRequest(transfer) == valid
        validateConstraints(transfer, field, error, false)

        where:
        sourceAccount                             | destinationAccount                             | amount | valid | error                       | field
        Account.of(SOURCE_ACCOUNT_PROPERTIES_MAP) | Account.of(SOURCE_ACCOUNT_PROPERTIES_MAP)      | 100    | false | 'transfer.matching.account' | 'destinationAccount'
        Account.of(SOURCE_ACCOUNT_PROPERTIES_MAP) | Account.of(DESTINATION_ACCOUNT_PROPERTIES_MAP) | 250    | false | 'transfer.exceeding.amount' | 'amount'
        Account.of(SOURCE_ACCOUNT_PROPERTIES_MAP) | Account.of(DESTINATION_ACCOUNT_PROPERTIES_MAP) | 100    | true  | ''                          | ''
    }

    @Unroll
    def "test UpdateAccountBalance"() {
        given:
        Account account = Account.of(SOURCE_ACCOUNT_PROPERTIES_MAP)

        when:
        service.updateAccountBalance(account, transactionType, amount as BigDecimal)

        then:
        assert account.balance == expectedBalance

        where:
        transactionType | amount | expectedBalance
        DEBIT           | 100    | 300
        CREDIT          | 150    | 50
    }
}
