package com.secret.payment.validation.rules
/**
 * Processor for validating the business entity
 * Designed for use in scope of design pattern "Chain of Responsibility", so assumes setup of the next validator in chain
 */
interface ChainValidator<T> {

    void validate(T target);

    /**
     * Method sets next validator to be used in validation chain
     * @return next validator
     */
    ChainValidator setNextValidator(ChainValidator nextValidator);

    /**
     * @return next validator to be used in validation chain
     */
    ChainValidator getNextValidator();
}