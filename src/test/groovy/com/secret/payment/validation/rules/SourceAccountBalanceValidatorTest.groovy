package com.secret.payment.validation.rules

import com.secret.payment.Account
import com.secret.payment.Transfer
import com.secret.payment.validation.rules.impl.SourceAccountBalanceValidator
import spock.lang.Specification
import spock.lang.Unroll

import static com.secret.payment.utils.TestHelperUtils.*

class SourceAccountBalanceValidatorTest extends Specification {

    @Unroll
    def "Validate"() {
        given:
        Transfer transfer = new Transfer(
                sourceAccount: Account.of(SOURCE_ACCOUNT_PROPERTIES_MAP),
                destinationAccount: Account.of(DESTINATION_ACCOUNT_PROPERTIES_MAP),
                amount: amount)

        when:
        new SourceAccountBalanceValidator().validate(transfer)

        then:
        validateConstraints(transfer,field,error,false)

        where:
        amount | error                       | field
        250    | 'transfer.exceeding.amount' | 'amount'
        200    | ''                          | ''
    }
}
