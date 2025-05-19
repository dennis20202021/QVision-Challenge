package sura.ui;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

import java.util.Collections;
import java.util.List;

// URL de ambiente
@DefaultUrl("page:webdriver.base.url")
public class AforePortalAccesoLoginUI extends PageObject {

    public static final List<Target> TODOS_LOS_ELEMENTOS = Collections.singletonList(
            Target.the("Todos los elementos del Dashboard")
                    .locatedBy("//*")
    );

    public static final Target FORMULARIO_LOGIN = Target.the("Formulario de inicio de sesión")
            .locatedBy("#loginForm");

    public static final Target CAMPO_USUARIO = Target.the("Campo usuario MX")
            .locatedBy("#user");

    public static final Target CAMPO_CONTRASENIA = Target.the("Campo contraseña MX")
            .locatedBy("#password");

    public static final Target BTN_INGRESAR = Target.the("Botón de iniciar sesión (Ingresar)")
            .locatedBy("#btnSbmt2");

    public static final Target DIALOGO_ERROR = Target.the("Diálogo de error")
            .locatedBy("#errores");
}
