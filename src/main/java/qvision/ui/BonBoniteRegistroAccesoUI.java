package qvision.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class BonBoniteRegistroAccesoUI {

    private BonBoniteRegistroAccesoUI() {
        throw new IllegalStateException("Utility class");
    }

    public static final Target TXT_CEDULA = Target.the("Campo de cédula").located(By.id("reg_username"));
    public static final Target TXT_NOMBRES = Target.the("Campo de nombres").located(By.id("first_name"));
    public static final Target TXT_APELLIDOS = Target.the("Campo de apellidos").located(By.id("last_name"));
    public static final Target TXT_CORREO = Target.the("Campo de correo").located(By.id("reg_email"));
    public static final Target TXT_CONTRASENA = Target.the("Campo de contraseña").located(By.id("reg_password"));
    public static final Target TXT_CONFIRMAR_CONTRASENA = Target.the("Campo de confirmar contraseña").located(By.id("reg_password2"));
    public static final Target CHK_TERMINOS = Target.the("Checkbox de términos y condiciones").located(By.id("privacy_policy_reg"));
    public static final Target CHK_INFO_ADICIONAL = Target.the("Campo de información adicional").located(By.id("newsletter_authorization"));
    public static final Target BTN_REGISTRAR = Target.the("Botón de registrar").located(By.name("register"));
}
