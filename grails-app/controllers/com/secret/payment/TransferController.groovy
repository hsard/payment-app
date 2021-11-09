package com.secret.payment

import com.secret.payment.business.TransferService

import static org.springframework.http.HttpStatus.NOT_FOUND

class TransferController {

    TransferService transferService

    def create() {
        respond new Transfer(params)
    }

    def save(Transfer transfer) {
        if (transfer == null) {
            notFound()
            return
        }

        if (transferService.processRequest(transfer)) {
            flash.message = message(code: 'transfer.succesfull.message', default: 'Successfull transfer')
            redirect controller: "account", action: "index"
        } else {
            respond transfer.errors, view: 'create'
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'transfer.label', default: 'Transfer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
