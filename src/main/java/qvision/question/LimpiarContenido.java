package qvision.question;

import net.serenitybdd.screenplay.*;
import net.serenitybdd.screenplay.targets.Target;
import org.jsoup.Jsoup;
import qvision.util.RegistrarInformacion;

public class LimpiarContenido implements Question<String> {

    private final Target elementoWeb;

    public LimpiarContenido(Target elementoWeb) {
        this.elementoWeb = elementoWeb;
    }

    @Override
    public String answeredBy(Actor actor) {
        String textoLimpio = Jsoup.parse(actor.asksFor(ExtraerTexto.de(this.elementoWeb))).text();
        RegistrarInformacion.deConsola("Texto limpio: {}", textoLimpio);
        return textoLimpio;
    }

    public static Question<String> htmlSinProcesar(Target elementoWeb) {
        return new LimpiarContenido(elementoWeb);
    }
}


