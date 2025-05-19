package sura.stepDefinitions;

import io.cucumber.java.es.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import sura.interaction.SobreescribirOpciones;
import sura.task.*;

public class InicioSesionStepDef {

    @Dado("que el {actor} navega al portal Afore de Sura")
    public void queElUsuarioNavegaAlPortalAforeDeSura(Actor actor) {
        actor.wasAbleTo(
                SobreescribirOpciones.delNavegador(),
                NavegarAlTask.portalPrincipalAforeSura()
        );
    }

    @Cuando("ingresa su usuario {string} y contraseña de acceso {string}")
    public void ingresaSuYContraseniaDeAcceso(String usuario, String contrasenia) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                IniciarSesionTask.conCredenciales(usuario, contrasenia)
        );
    }
}
