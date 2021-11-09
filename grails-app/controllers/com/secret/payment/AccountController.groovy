package com.secret.payment

import com.secret.payment.business.AccountService

import static org.springframework.http.HttpStatus.NOT_FOUND

class AccountController {

    AccountService accountService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        render view: "index", model: accountService.getAccounts(params)
    }

    def show(Long id) {
        render view: "show", model: accountService.getAccount(id)
    }
}
