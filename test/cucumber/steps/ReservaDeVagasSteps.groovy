package steps

import cucumber.api.PendingException
import exemplodaauladetestes.VagaController
import exemplodaauladetestes.Vaga

import pages.Visualizacao
import pages.CreatePage
import pages.VerPage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

ocupadasESemHistorico = false

Given(~'^todas as vagas estão ocupadas$') { ->
    def controlador = new VagaController()
    criarEReservarVaga("e1",controlador)
    criarEReservarVaga("e2",controlador)
    criarEReservarVaga("e3",controlador)
    assert Vaga.findByOcupada(false) == null
}

When(~'^Eu tento reservar uma vaga$') { ->
    ocupadasESemHistorico = todasOcupadasESemHistorico(Vaga.list())
    def controlador = new VagaController()
    controlador.book()
}

Then(~'^O sistema não faz nenhuma reserva$') { ->
    assert ocupadasESemHistorico == todasOcupadasESemHistorico(Vaga.list())
}

// tratar parametros
def criarEReservarVaga(String descricao, VagaController controlador) {
    criarVaga(descricao, controlador)
    controlador.select(Vaga.findByDescricao(descricao).id)
    controlador.response.reset()
}

def criarVaga(String descricao, VagaController controlador) {
    controlador.params << [descricao: descricao, ocupada: false]
    controlador.save()
    controlador.response.reset()
}

boolean todasOcupadasESemHistorico(vagas) {
    boolean r = true
    vagas.each { vaga ->
           r = r && vaga.ocupada && (vaga.historicoDeReservas.size() == 0)
    }
    return r
}

Given(~/^eu criei as vagas "([^"]*)" e "([^"]*)"$/) { String vaga1, vaga2 ->
    to CreatePage
    at CreatePage
    page.criarVaga(vaga1)
    at VerPage
    to CreatePage
    at CreatePage
    page.criarVaga(vaga2)
}

And(~/^eu estou na página de visualização das vagas$/) { ->
    to Visualizacao
    at Visualizacao
}

And(~/^eu vejo a vaga "([^"]*)" vazia$/) { String vaga ->
    at Visualizacao
    assert page.vagaEstaVazia(vaga)
}

When(~/^eu seleciono a vaga "([^"]*)"$/) { String vaga ->
    at Visualizacao
    page.selecionarVaga(vaga)
}

Then(~/^a vaga "([^"]*)" é marcada como ocupada$/) { String vaga ->
    at Visualizacao
    assert page.vagaEstaOcupada(vaga)
}

Given(~/^algumas vagas não estão ocupadas$/) { ->
    def controlador = new VagaController()
    criarEReservarVaga("e1", controlador)
    criarEReservarVaga("e2", controlador)
    criarVaga("e3", controlador)
    assert Vaga.findByOcupada(false) != null
}

Then(~/^O sistema reserva uma das vagas desocupadas$/) { ->
    assert (!ocupadasESemHistorico) == todasOcupadasESemHistorico(Vaga.list())
    // assert Vaga.findByDescricao("e3").ocupada
}