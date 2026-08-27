package qvision.task;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import qvision.ui.BonBonitePortalAccesoUI;

public class NavegarAlTask {

    private NavegarAlTask() {
        throw new IllegalStateException("Utility class");
    }

    public static Performable portalPrincipalBonBonite() {
        return Task.where("{0} abre la página principal del portal Bon-Bonite",
                Open.browserOn().the(BonBonitePortalAccesoUI.class));
    }
}
