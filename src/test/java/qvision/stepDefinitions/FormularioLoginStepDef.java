package qvision.stepDefinitions;

import io.cucumber.java.es.*;
import net.serenitybdd.screenplay.actors.OnStage;
import qvision.task.*;

public class FormularioLoginStepDef {

    @Y("realiza click en el botón de registrarse")
    public void realizaClickEnElBotonDeRegistrarse() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                IngresarAPaginaRegistroTask.actual()
        );
    }

    @Y("ingresa los datos de {string}, {string} de inicio de sesión válidos")
    public void ingresaLosDatosDeDeInicioDeSesiónValidos(String cedula, String contrasena) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                IniciarSesionTask.conDatos(cedula, contrasena)
        );
    }
}
