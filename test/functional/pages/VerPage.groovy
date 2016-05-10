package pages

import geb.Page

class VerPage extends Page {
    static url = "/ExemploDaAulaDeTestes/vaga/show/"

    static at =  {
        title ==~ /Ver Vaga/
    }

}

