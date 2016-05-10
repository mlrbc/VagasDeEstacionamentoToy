package pages

import geb.Page

class Visualizacao extends Page {
    static url = "/ExemploDaAulaDeTestes/vaga/overview/"

    static at =  {
        title ==~ /Vaga Listagem/
    }

    boolean vagaEstaVazia(vaga) {
        $("td", class: "green").has("a",text: vaga)
    }

    boolean vagaEstaOcupada(vaga) {
        $("td", class: "red").has("a",text: vaga)
    }

    boolean vagaEsta(ocupada,vaga) {
        def classe
        if (ocupada) {
            classe = "red"
        } else {
            classe = "green"
        }
        $("td", class: classe).has("a",text: vaga)
    }

    def selecionarVaga(vaga) {
        $("a",text: vaga).click()
    }
}

