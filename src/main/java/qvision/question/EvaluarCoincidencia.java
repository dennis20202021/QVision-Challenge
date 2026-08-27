package qvision.question;

import net.serenitybdd.screenplay.*;

public class EvaluarCoincidencia implements Question<Boolean> {

    private final Question<String> valorActual;
    private final String valorEsperado;

    public EvaluarCoincidencia(Question<String> valorActual, String valorEsperado) {
        this.valorActual = valorActual;
        this.valorEsperado = valorEsperado;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String actualString = this.valorActual.answeredBy(actor);
        return actualString != null && actualString.equalsIgnoreCase(this.valorEsperado);
    }

    public static Question<Boolean> deDosObjetos(Question<String> valorActual, String valorEsperado) {
        return new EvaluarCoincidencia(valorActual, valorEsperado);
    }
}


