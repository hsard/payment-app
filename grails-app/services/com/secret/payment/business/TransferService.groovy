package com.secret.payment.business

import com.secret.payment.Account
import com.secret.payment.Transfer
import com.secret.payment.constants.TransactionType
import com.secret.payment.repository.AccountRepositoryService
import com.secret.payment.repository.TransferRepositoryService
import com.secret.payment.validation.chains.impl.TransferChainValidationGroup
import grails.gorm.transactions.Transactional

import static com.secret.payment.constants.TransactionType.CREDIT
import static com.secret.payment.constants.TransactionType.DEBIT

@Transactional(readOnly = true)
class TransferService {
    TransferRepositoryService transferRepositoryService
    AccountRepositoryService accountRepositoryService
    NotificationService notificationService

    @Transactional(rollbackFor = Exception.class)
    boolean processRequest(Transfer transfer) {
        log.debug("Processing transfer request, properties: ${transfer.properties}")

        boolean isProcessedSuccesfully = false

        try {
            if (validateRequest(transfer)) {
                transferRepositoryService.save(transfer)
                updateAccountBalance(transfer.sourceAccount, CREDIT, transfer.amount)
                updateAccountBalance(transfer.destinationAccount, DEBIT, transfer.amount)
                notificationService.notifyTransfer(transfer)
                isProcessedSuccesfully = true
            }
        } catch (Exception e) {
            log.error("Exception thrown while processing transfer request", e)
        }
        isProcessedSuccesfully
    }

    boolean validateRequest(Transfer transfer) {
        transfer.validate() && TransferChainValidationGroup.newInstance().validate(transfer)
    }

    void updateAccountBalance(Account account, TransactionType transactionType, BigDecimal amount) {
        log.debug("Updating account balance: account: ${account.id}, type: ${transactionType.name()}, amount: $amount")
        account.balance = DEBIT.equals(transactionType) ? account.balance + amount : account.balance - amount
        accountRepositoryService.save(account)
    }
}
