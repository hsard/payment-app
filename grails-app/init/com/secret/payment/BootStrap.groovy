package com.secret.payment

class BootStrap {

    def init = { servletContext ->
        bootstrapAccounts()
    }

    def destroy = {
    }

    private void bootstrapAccounts() {
        Account.withNewTransaction {
            [[name: "Jeff Bezos", iban: "DE123", balance: 200, email: "jeff.bezos@bbbb.com"],
             [name: "Bill Gates", iban: "BG456", balance: 200, email: "bill.gates@bbbb.com"],
             [name: "Elon Mask", iban: "US789", balance: 200, email: "elon.mask@bbbb.com"],
            ].each {
                new Account(it).save(flush: true)
            }
        }
    }
}
