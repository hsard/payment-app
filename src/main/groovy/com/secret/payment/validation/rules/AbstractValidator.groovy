package com.secret.payment.validation.rules
/**
 *  Abstract implementation of ChainValidator
 *  adding abstract support of methods for condition validation, success and failure callbacks*/

abstract class AbstractValidator<T> implements ChainValidator<T> {
    private AbstractValidator nextValidator

    @Override
    void validate(T entity) {
        if (conditionToValidate(entity)) {
            onSuccess(entity)
        } else {
            onFailure(entity)
        }
    }

    @Override
    ChainValidator setNextValidator(ChainValidator nextValidator) {
        this.nextValidator = (AbstractValidator) nextValidator
        return this.nextValidator
    }

    @Override
    AbstractValidator getNextValidator() {
        return nextValidator
    }

    /**
     * Success case callback method*/
    abstract void onSuccess(T entity)

    /**
     * Failure case callback method*/
    abstract void onFailure(T entity)

    /**
     * Logic of validation method*/
    abstract boolean conditionToValidate(T entity)
}