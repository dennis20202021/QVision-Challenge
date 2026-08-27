package qvision.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import qvision.interaction.ClicSi;
import qvision.ui.BonBoniteLoginAccesoUI;

public class IngresarAPaginaRegistroTask implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                ClicSi.elElementoEsVisible(BonBoniteLoginAccesoUI.LNK_REGISTRARSE)
                        .oMostrarMensajeDeError("No se pudo hacer clic en el botón 'Regístrate' porque no está visible.")
                        .build()
        );
    }

    public static IngresarAPaginaRegistroTask actual() {
        return Tasks.instrumented(IngresarAPaginaRegistroTask.class);
    }
}
