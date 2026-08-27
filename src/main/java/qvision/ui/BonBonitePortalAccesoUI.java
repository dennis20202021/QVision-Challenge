package qvision.ui;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

import java.util.Collections;
import java.util.List;

// URL de ambiente
@DefaultUrl("page:webdriver.base.url")
public class BonBonitePortalAccesoUI extends PageObject {

    public static final List<Target> TODOS_LOS_ELEMENTOS = Collections.singletonList(
            Target.the("Todos los elementos del Dashboard")
                    .locatedBy("//*")
    );

    public static final Target LNK_LOGIN = Target.the("Formulario de inicio de sesión")
            .locatedBy("//a[@href='https://www.bon-bonite.com/mi-cuenta']");
}
