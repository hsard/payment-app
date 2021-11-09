package com.secret.payment.repository

import com.secret.payment.Account
import com.secret.payment.Transfer
import grails.gorm.services.Service

@Service(Transfer)
interface TransferRepositoryService {

    Transfer get(Serializable id)

    Transfer save(Transfer transfer)

    List<Transfer> findAllBySourceAccountOrDestinationAccount(Account sourceAccount, Account destinationAccount);
}