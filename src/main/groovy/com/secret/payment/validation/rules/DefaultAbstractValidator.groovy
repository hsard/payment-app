package com.secret.payment.validation.rules
/**
 * Default abstract implementation of {@link AbstractValidator}
 * implements success and failure callbacks
 * onSuccess calling validate method of nextvalidator in the chain
 * onFailure rejecting the failed field with the specified error code
 */
abstract class DefaultAbstractValidator<T> extends AbstractValidator<T> {

    @Override
    void onSuccess(T entity) {
        if (this.getNextValidator() != null) {
            this.getNextValidator().validate(entity)
        }
    }

    @Override
    void onFailure(T entity) {
        fieldToReject() ?
                entity.errors.rejectValue(fieldToReject(), errorCodeToReject())
                : entity.errors.reject(errorCodeToReject())
    }

    abstract String fieldToReject()

    abstract String errorCodeToReject()
}