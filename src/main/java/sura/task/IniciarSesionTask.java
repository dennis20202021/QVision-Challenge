package sura.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.*;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import static sura.ui.AforePortalAccesoLoginUI.CAMPO_CONTRASENIA;
import static sura.ui.AforePortalAccesoLoginUI.CAMPO_USUARIO;
import static sura.util.Constantes.VAR_AMBIENTE;

public class IniciarSesionTask implements Task {

    private final String usuario;
    private final String contrasenia;

    public IniciarSesionTask(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CAMPO_USUARIO, isCurrentlyVisible()),
                Enter.theValue(VAR_AMBIENTE.get(this.usuario)).into(CAMPO_USUARIO).thenHit(Keys.TAB),
                Enter.theValue(VAR_AMBIENTE.get(this.contrasenia)).into(CAMPO_CONTRASENIA).thenHit(Keys.ENTER)
        );
    }

    public static IniciarSesionTask conCredenciales(String usuario, String contrasenia) {
        return Tasks.instrumented(IniciarSesionTask.class, usuario, contrasenia);
    }
}
