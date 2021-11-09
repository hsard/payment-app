package com.secret.payment

class Account {

    String name
    String iban
    String email
    BigDecimal balance

    static constraints = {
        name blank: false
        iban blank: false
        email blank: false, email: true, unique: true
        balance min: BigDecimal.ZERO
    }

    static Account of(def parametersMap) {
        new Account(parametersMap)
    }
}
