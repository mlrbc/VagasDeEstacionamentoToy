package exemplodaauladetestes

class Reserva {

    Date entrada
    Date saida
    static belongsTo = [vaga:Vaga]

    static constraints = {
        entrada nullable: true
        saida nullable: true
    }

}

