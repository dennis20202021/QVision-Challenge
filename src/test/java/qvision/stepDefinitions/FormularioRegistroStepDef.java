package qvision.stepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.es.*;
import net.serenitybdd.screenplay.actors.OnStage;
import qvision.task.RegistrarUsuarioTask;

public class FormularioRegistroStepDef {

    @Y("ingresa los datos de {string}, {string}, {string}, {string}, {string} e {string} de registro válidos")
    public void ingresaLosDatosDeEDeRegistroVálidos(String cedula, String nombres, String apellidos, String correo, String contrasena, String infoAdicional) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                RegistrarUsuarioTask.conDatos(cedula, nombres, apellidos, correo, contrasena, infoAdicional)
        );
    }
}
