package sura.task;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import sura.ui.AforePortalAccesoLoginUI;

public class NavegarAlTask {

    private NavegarAlTask() {
        throw new IllegalStateException("Utility class");
    }

    public static Performable portalPrincipalAforeSura() {
        return Task.where("{0} abre la página principal del portal Afore Sura",
                Open.browserOn().the(AforePortalAccesoLoginUI.class));
    }
}
