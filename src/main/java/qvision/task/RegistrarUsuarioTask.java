package qvision.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.actions.ScrollTo;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import static qvision.ui.BonBoniteRegistroAccesoUI.*;

public class RegistrarUsuarioTask implements Task {

    private final String cedula;
    private final String nombres;
    private final String apellidos;
    private final String correo;
    private final String contrasena;
    private final String infoAdicional;

    public RegistrarUsuarioTask(String cedula, String nombres, String apellidos, String correo, String contrasena, String infoAdicional) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasena = contrasena;
        this.infoAdicional = infoAdicional;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(TXT_CEDULA, isCurrentlyVisible()).forNoMoreThan(30).seconds(),
                Enter.theValue(this.cedula).into(TXT_CEDULA),
                Enter.theValue(this.nombres).into(TXT_NOMBRES),
                Enter.theValue(this.apellidos).into(TXT_APELLIDOS),
                Enter.theValue(this.correo).into(TXT_CORREO),
                Enter.theValue(this.contrasena).into(TXT_CONTRASENA),
                Enter.theValue(this.contrasena).into(TXT_CONFIRMAR_CONTRASENA),
                Click.on(CHK_TERMINOS)
        );

        if (this.infoAdicional.equalsIgnoreCase("si")) {
            actor.attemptsTo(
                    Click.on(CHK_INFO_ADICIONAL)
            );
        }

        actor.attemptsTo(
                Click.on(BTN_REGISTRAR)
        );
    }

    public static RegistrarUsuarioTask conDatos(String cedula, String nombres, String apellidos, String correo, String contrasena, String infoAdicional) {
        return Tasks.instrumented(RegistrarUsuarioTask.class, cedula, nombres, apellidos, correo, contrasena, infoAdicional);
    }
}
