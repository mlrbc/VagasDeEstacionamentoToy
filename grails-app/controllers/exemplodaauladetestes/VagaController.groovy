package exemplodaauladetestes

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class VagaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [vagaInstanceList: Vaga.list(params),
         vagaInstanceTotal: Vaga.count()]
    }

    def overview(Integer max) {
        list(max)
    }

    def select(Long id) {
        def vaga = Vaga.get(id)
        if (!vaga) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vaga.label', default: 'Vaga'), id])
            redirect(action: "overview")
            return
        }
        vaga.select()
        if (!vaga.save(flush: true)) {
            flash.message = "Vaga não pode ser reservada! Problema interno na gravação!\n" + vaga.errors
        }
        redirect(action: "overview")
    }

    def show(Vaga vagaInstance) {
        respond vagaInstance
    }

    def create() {
        respond new Vaga(params)       //  [vagaInstance: new Vaga(params)]
    }

    @Transactional
    def save(Vaga vagaInstance) {
        if (vagaInstance == null) {
            notFound()
            return
        }

        if (vagaInstance.hasErrors()) {
            respond vagaInstance.errors, view:'create'
            return
        }

        vagaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'vaga.label', default: 'Vaga'), vagaInstance.id])
                redirect vagaInstance
            }
            '*' { respond vagaInstance, [status: CREATED] }
        }
    }

    def edit(Vaga vagaInstance) {
        respond vagaInstance
    }

    @Transactional
    def update(Vaga vagaInstance) {
        if (vagaInstance == null) {
            notFound()
            return
        }

        if (vagaInstance.hasErrors()) {
            respond vagaInstance.errors, view:'edit'
            return
        }

        vagaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Vaga.label', default: 'Vaga'), vagaInstance.id])
                redirect vagaInstance
            }
            '*'{ respond vagaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Vaga vagaInstance) {

        if (vagaInstance == null) {
            notFound()
            return
        }

        vagaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Vaga.label', default: 'Vaga'), vagaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'vaga.label', default: 'Vaga'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
