package com.secret.payment.validation.chains

import com.secret.payment.Account
import com.secret.payment.Transfer
import com.secret.payment.validation.chains.impl.TransferChainValidationGroup
import spock.lang.Specification
import spock.lang.Unroll

import static com.secret.payment.utils.TestHelperUtils.*

class TransferChainValidationGroupTest extends Specification {
    @Unroll
    def "Validate"() {
        given:
        Transfer transfer = new Transfer(sourceAccount: sourceAccount, destinationAccount: destinationAccount, amount: amount)

        when:
        new TransferChainValidationGroup().validate(transfer)

        then:
        validateConstraints(transfer, field, error, false)

        where:
        sourceAccount                             | destinationAccount                             | amount | error                       | field
        Account.of(SOURCE_ACCOUNT_PROPERTIES_MAP) | Account.of(SOURCE_ACCOUNT_PROPERTIES_MAP)      | 200    | 'transfer.matching.account' | 'destinationAccount'
        Account.of(SOURCE_ACCOUNT_PROPERTIES_MAP) | Account.of(DESTINATION_ACCOUNT_PROPERTIES_MAP) | 250    | 'transfer.exceeding.amount' | 'amount'
    }
}
