package com.secret.payment

class Transfer {

    Account sourceAccount
    Account destinationAccount
    BigDecimal amount

    static mapping = {
        version false
    }

    static constraints = {
        amount min: BigDecimal.ZERO
    }
}
