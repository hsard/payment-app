package com.secret.payment.validation.chains.impl

import com.secret.payment.validation.chains.ChainValidationGroup
import com.secret.payment.validation.rules.impl.AccountsValidator
import com.secret.payment.validation.rules.impl.SourceAccountBalanceValidator

/**
 *  Custom implementation of {@link ChainValidationGroup}
 *  Defining the validation flow chain required for Transfer flow
 *  */

class TransferChainValidationGroup extends ChainValidationGroup {

    TransferChainValidationGroup() {
        chainStartValidator = new AccountsValidator()
        chainStartValidator
                .setNextValidator(new SourceAccountBalanceValidator())
    }
}