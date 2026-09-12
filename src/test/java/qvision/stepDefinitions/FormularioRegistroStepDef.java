package qvision.stepDefinitions;

import io.cucumber.java.es.*;
import net.serenitybdd.screenplay.actors.OnStage;
import qvision.question.ExtraerTexto;
import qvision.task.RegistrarUsuarioTask;
import qvision.ui.BonBoniteDahsboardUI;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class FormularioRegistroStepDef {

    @Y("ingresa los datos de {string}, {string}, {string}, {string}, {string} e {string} de registro válidos o genera datos aleatorios {string}")
    public void ingresaLosDatosDeEDeRegistroValidos(String cedula, String nombres, String apellidos, String correo, String contrasena, String infoAdicional, String datosAleatoriosPrueba) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                RegistrarUsuarioTask.conDatos(cedula, nombres, apellidos, correo, contrasena, infoAdicional, datosAleatoriosPrueba)
        );
    }

    @Entonces("valida el correcto registro e inicio de sesión del usuario y el mensaje de bienvenida")
    public void validaElCorrectoRegistroEInicioDeSesionDelUsuarioYElMensajeDeBienvenida() {
        Object recall = OnStage.theActorInTheSpotlight().recall("nombresSet");
        String nombreEsperado = recall == null ? "" : recall.toString().trim().toLowerCase();

        OnStage.theActorInTheSpotlight().should(
                seeThat(ExtraerTexto.de(BonBoniteDahsboardUI.MSJ_BIENVENIDA),
                        texto -> texto != null && texto.trim().toLowerCase().contains(nombreEsperado)
                )
        );
    }

}
