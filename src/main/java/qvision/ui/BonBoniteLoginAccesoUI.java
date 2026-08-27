package qvision.ui;

import net.serenitybdd.screenplay.targets.Target;

public class BonBoniteLoginAccesoUI {

    private BonBoniteLoginAccesoUI() {
        throw new IllegalStateException("Utility class");
    }

    public static final Target LNK_REGISTRARSE = Target.the("Enlace de Registrarse")
            .locatedBy("//span[contains(text(), 'Regístrate')]");

}
