package qvision.stepDefinitions;

import io.cucumber.java.es.*;
import net.serenitybdd.screenplay.actors.OnStage;
import qvision.task.IngresarAPaginaRegistroTask;

public class FormularioLoginStepDef {

    @Y("realiza click en el botón de registrarse")
    public void realizaClickEnElBotonDeRegistrarse() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                IngresarAPaginaRegistroTask.actual()
        );
    }

}
