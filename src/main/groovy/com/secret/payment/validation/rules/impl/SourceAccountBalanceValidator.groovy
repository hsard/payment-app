package com.secret.payment.validation.rules.impl

import com.secret.payment.Transfer
import com.secret.payment.validation.rules.DefaultAbstractValidator

/**
 *  Validating the balance sufficiency for transfer
 *  */
class SourceAccountBalanceValidator extends DefaultAbstractValidator<Transfer> {

    @Override
    boolean conditionToValidate(Transfer transfer) {
        transfer.sourceAccount.balance >= transfer.amount
    }

    @Override
    String fieldToReject() {
        return 'amount'
    }

    @Override
    String errorCodeToReject() {
        return 'transfer.exceeding.amount'
    }
}
