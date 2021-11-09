package com.secret.payment.business

import com.secret.payment.Account
import com.secret.payment.Transfer
import com.secret.payment.repository.AccountRepositoryService
import com.secret.payment.repository.TransferRepositoryService
import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap

@Transactional(readOnly = true)
class AccountService {

    AccountRepositoryService accountRepositoryService
    TransferRepositoryService transferRepositoryService

    /**
     * @param id Id of the account
     * @return model map of account and its related transfers
     */
    def getAccount(Long id) {
        Account account = accountRepositoryService.get(id)
        [account         : account,
         accountTransfers: getTransfersForAccount(account)]
    }

    /**
     * @param paginationParams pagination parameters for listing
     * @return model map of paginated accounts list and total count
     */
    def getAccounts(GrailsParameterMap paginationParams) {
        [accountsList : accountRepositoryService.list(paginationParams),
         accountsCount: accountRepositoryService.count()]
    }

    /**
     * @param account
     * @return list of transfers related to the account
     */
    List<Transfer> getTransfersForAccount(Account account) {
        transferRepositoryService.findAllBySourceAccountOrDestinationAccount(account, account)
    }
}
