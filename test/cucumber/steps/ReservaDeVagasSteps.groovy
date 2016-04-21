package steps

import exemplodaauladetestes.VagaController
import exemplodaauladetestes.Vaga

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

vaga = null

Given(~'^todas as vagas estão ocupadas$') { ->
    def controlador = new VagaController()
    controlador.params << [descricao:"e1",ocupado:false]
    controlador.create()
    controlador.save()
    controlador.response.reset()
    assert Vaga.findByDescricao("e1")
    controlador.select(Vaga.findByDescricao("e1").id)
    assert Vaga.findByOcupada(false) == null
}

When(~'^Eu tento reservar uma vaga$') { ->
    def controlador = new VagaController()
    vaga = Vaga.findByOcupada(true)
    assert vaga != null
    controlador.book()
    controlador.response.reset()
    controlador.select(1)
    controlador.response.reset()
    controlador.select(1)
    controlador.response.reset()
}

Then(~'^O sistema não faz nenhuma reserva$') { ->
    assert vaga.historicoDeReservas.size() == 0
    def vaga1 = Vaga.findByOcupada(true)
    assert vaga1.historicoDeReservas.size() == 1
    assert vaga.equals(vaga1)
}
