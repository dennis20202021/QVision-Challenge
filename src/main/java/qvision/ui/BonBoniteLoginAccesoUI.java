package qvision.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class BonBoniteLoginAccesoUI {

    private BonBoniteLoginAccesoUI() {
        throw new IllegalStateException("Utility class");
    }

    public static final Target LNK_REGISTRARSE = Target.the("Enlace de Registrarse")
            .locatedBy("//span[contains(text(), 'Regístrate')]");

    public static final Target TXT_CEDULA = Target.the("Campo de cédula")
            .located(By.id("username"));

    public static final Target TXT_CONTRASENA = Target.the("Campo de contraseña")
            .located(By.id("password"));

}
