package com.secret.payment.utils

class TestHelperUtils {
    static final SOURCE_ACCOUNT_PROPERTIES_MAP = [name: "Bill Gates", iban: "DE123", email: "bill.gates@mail.com", balance: 200]
    static final DESTINATION_ACCOUNT_PROPERTIES_MAP = [name: "Elon Mask", iban: "BG789", email: "elon.mask@mail.com", balance: 200]

    static void validateConstraints(obj, field, error, doValidate = true) {
        def validated = doValidate ? obj.validate() : !obj.hasErrors()
        if (error) {
            assert !validated
            assert obj.errors[field]
            assert obj.errors[field].code.contains(error)
        } else {
            assert !obj.errors[field]
        }
    }
}
