package com.secret.payment.domains

import com.secret.payment.Transfer
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

import static com.secret.payment.utils.TestHelperUtils.validateConstraints

class TransferTest extends Specification implements DomainUnitTest<Transfer> {

    @Unroll
    void "Test Transfer constraints #error - #field"() {
        when:
        Transfer transfer = new Transfer("$field": value)

        then:
        validateConstraints(transfer, field, error)

        where:
        error        | field                | value
        'nullable'   | 'sourceAccount'      | null

        'nullable'   | 'destinationAccount' | null

        'nullable'   | 'amount'             | null
        'min.notmet' | 'amount'             | -11.5
    }
}
