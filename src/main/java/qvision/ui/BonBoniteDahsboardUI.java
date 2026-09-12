package qvision.ui;

import net.serenitybdd.screenplay.targets.Target;

public class BonBoniteDahsboardUI {

    private BonBoniteDahsboardUI() {
        throw new IllegalStateException("Utility class");
    }

    public static final Target MSJ_BIENVENIDA = Target.the("Mensaje de bienvenida")
            .locatedBy("//h3[contains(text(), 'Hola,')]/span");

}
