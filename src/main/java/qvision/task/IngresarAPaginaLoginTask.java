package qvision.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import qvision.interaction.ClicSi;
import qvision.ui.BonBonitePortalAccesoUI;

public class IngresarAPaginaLoginTask implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                ClicSi.elElementoEsVisible(BonBonitePortalAccesoUI.LNK_LOGIN)
                        .oMostrarMensajeDeError("No se pudo hacer clic en el botón de ingresar al formulario")
                        .build()
        );
    }

    public static IngresarAPaginaLoginTask actual() {
        return Tasks.instrumented(IngresarAPaginaLoginTask.class);
    }
}
