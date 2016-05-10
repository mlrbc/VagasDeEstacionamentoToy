package pages

import geb.Page

class CreatePage extends Page {
    static url = "/ExemploDaAulaDeTestes/vaga/create/"

    static at =  {
        title ==~ /Criar Vaga/
    }

    boolean criarVaga(vaga) {
        $("form").descricao = vaga
        $("input", name: "create").click()
    }
}

