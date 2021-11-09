package com.secret.payment.repository

import com.secret.payment.Account
import grails.gorm.services.Service

@Service(Account)
interface AccountRepositoryService {

    Account get(Serializable id)

    List<Account> list(Map args)

    Long count()

    Account save(Account account)
}