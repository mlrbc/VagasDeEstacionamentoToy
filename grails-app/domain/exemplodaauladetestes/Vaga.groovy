package exemplodaauladetestes

class Vaga {
    String descricao
    boolean ocupada
    static hasMany = [historicoDeReservas:Reserva]
    Date dataEntrada

    Vaga() {
        historicoDeReservas = []
    }

    def select() {
        if (ocupada) {
            def reserva = new Reserva(entrada: dataEntrada, saida: new Date(), vaga:this)
            historicoDeReservas.add(reserva)
            dataEntrada = null
        } else {
            dataEntrada = new Date()
        }
        ocupada = !ocupada
    }

    static constraints = {
        descricao blank: false
        dataEntrada nullable: true
    }

    @Override
    String toString() {
        return "<" + descricao + ", " + ocupada + " >"
    }
}
