package com.secret.payment.domains

import com.secret.payment.Account
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

import static com.secret.payment.utils.TestHelperUtils.validateConstraints

class AccountTest extends Specification implements DomainUnitTest<Account> {

    @Unroll
    void "Test Account constraints #error - #field"() {
        when:
        Account account = new Account("$field": value)

        then:
        validateConstraints(account, field, error)

        where:
        error           | field     | value
        'nullable'      | 'name'    | null

        'nullable'      | 'email'   | null
        'email.invalid' | 'email'   | 'xxx@yyy'

        'nullable'      | 'balance' | null
        'min.notmet'    | 'balance' | -200
    }
}
