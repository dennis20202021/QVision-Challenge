package qvision.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import static qvision.ui.BonBoniteLoginAccesoUI.*;

public class IniciarSesionTask implements Task {

    private final String cedula;
    private final String contrasena;

    public IniciarSesionTask(String cedula, String contrasena) {
        this.cedula = cedula;
        this.contrasena = contrasena;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(TXT_CEDULA, isCurrentlyVisible()).forNoMoreThan(30).seconds(),
                Enter.theValue(this.cedula).into(TXT_CEDULA),
                Enter.theValue(this.contrasena).into(TXT_CONTRASENA).thenHit(Keys.ENTER)
        );
    }

    public static IniciarSesionTask conDatos(String cedula, String contrasena) {
        return Tasks.instrumented(IniciarSesionTask.class, cedula, contrasena);
    }

}
