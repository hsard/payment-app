package com.secret.payment.validation.chains

import com.secret.payment.validation.rules.ChainValidator

/**
 * abstract Chain validation group
 */
abstract class ChainValidationGroup<T> {

    /**
     * first validator of the chain
     */
    ChainValidator chainStartValidator

    /**
     * starts execution of the validation chain for T
     *
     * @param entity to be validated
     * @return diffResult result object containing validation results
     */
    boolean validate(T entity) {
        chainStartValidator.validate(entity)
        !entity.hasErrors()
    }
}