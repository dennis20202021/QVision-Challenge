package qvision.stepDefinitions;

import io.cucumber.java.es.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import qvision.interaction.SobreescribirOpciones;
import qvision.task.*;

public class MainDashboardStepDef {

    @Dado("que el {actor} navega al portal Bon-Bonite")
    public void queElUsuarioNavegaAlPortalAforeDeSura(Actor actor) {
        actor.wasAbleTo(
                SobreescribirOpciones.delNavegador(),
                NavegarAlTask.portalPrincipalBonBonite()
        );
    }

    @Cuando("realiza click en el botón de iniciar sesión")
    public void realizaClickEnElBotonDeIniciarSesion() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                IngresarAPaginaLoginTask.actual()
        );
    }

}
