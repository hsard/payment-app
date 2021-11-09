package com.secret.payment.validation.rules

import com.secret.payment.Account
import com.secret.payment.Transfer
import com.secret.payment.validation.rules.impl.AccountsValidator
import spock.lang.Specification
import spock.lang.Unroll

import static com.secret.payment.utils.TestHelperUtils.getDESTINATION_ACCOUNT_PROPERTIES_MAP
import static com.secret.payment.utils.TestHelperUtils.getSOURCE_ACCOUNT_PROPERTIES_MAP
import static com.secret.payment.utils.TestHelperUtils.validateConstraints

class AccountsValidatorTest extends Specification {
    @Unroll
    def "Validate"() {
        given:
        Transfer transfer = new Transfer(sourceAccount: sourceAccount, destinationAccount: destinationAccount, amount: 200)

        when:
        new AccountsValidator().validate(transfer)

        then:
        validateConstraints(transfer, field, error, false)

        where:
        sourceAccount                             | destinationAccount                             | error                       | field
        Account.of(SOURCE_ACCOUNT_PROPERTIES_MAP) | Account.of(SOURCE_ACCOUNT_PROPERTIES_MAP)      | 'transfer.matching.account' | 'destinationAccount'
        Account.of(SOURCE_ACCOUNT_PROPERTIES_MAP) | Account.of(DESTINATION_ACCOUNT_PROPERTIES_MAP) | ''                          | ''
    }
}
