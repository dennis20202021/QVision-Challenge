package qvision.question;

import net.serenitybdd.screenplay.*;
import net.serenitybdd.screenplay.targets.Target;

public class ExtraerTexto implements Question<String> {

    private final Target elementoWeb;

    public ExtraerTexto(Target elementoWeb) {
        this.elementoWeb = elementoWeb;
    }

    @Override
    public String answeredBy(Actor actor) {
        return elementoWeb.resolveFor(actor).getText();
    }

    public static Question<String> de(Target elementoWeb) {
        return new ExtraerTexto(elementoWeb);
    }
}


