package com.secret.payment.validation.rules.impl

import com.secret.payment.Transfer
import com.secret.payment.validation.rules.DefaultAbstractValidator

/**
 *  Validating transfer within non-matching(different) accounts
 *  */
class AccountsValidator extends DefaultAbstractValidator<Transfer> {

    @Override
    boolean conditionToValidate(Transfer transfer) {
        transfer.sourceAccount.iban != transfer.destinationAccount.iban
    }

    @Override
    String fieldToReject() {
        return 'destinationAccount'
    }

    @Override
    String errorCodeToReject() {
        return 'transfer.matching.account'
    }
}
