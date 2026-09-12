package qvision.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import qvision.util.RegistrarInformacion;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import static qvision.ui.BonBoniteRegistroAccesoUI.*;
import static qvision.util.Constantes.*;

public class RegistrarUsuarioTask implements Task {

    private final String cedula;
    private final String nombres;
    private final String apellidos;
    private final String correo;
    private final String contrasena;
    private final String infoAdicional;
    private final String datosAleatoriosPrueba;

    public RegistrarUsuarioTask(String cedula, String nombres, String apellidos, String correo, String contrasena, String infoAdicional, String datosAleatoriosPrueba) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasena = contrasena;
        this.infoAdicional = infoAdicional;
        this.datosAleatoriosPrueba = datosAleatoriosPrueba;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Wait until form is visible
        WaitUntil.the(TXT_CEDULA, isCurrentlyVisible()).forNoMoreThan(30).seconds();

        // Choose source values: generated test data or provided values
        boolean usarDatosAleatorios = this.datosAleatoriosPrueba.equalsIgnoreCase("si");

        String cedulaValor = usarDatosAleatorios ? GENERADOR_DE_DATOS.idNumber().valid() : this.cedula;
        String nombresValor = usarDatosAleatorios ? GENERADOR_DE_DATOS.name().firstName() : this.nombres;
        String apellidosValor = usarDatosAleatorios ? GENERADOR_DE_DATOS.name().lastName() : this.apellidos;
        String correoValor = usarDatosAleatorios ? GENERADOR_DE_DATOS.internet().emailAddress() : this.correo;
        String contrasenaValor = usarDatosAleatorios ? GENERADOR_DE_DATOS.internet().password() : this.contrasena;

        actor.remember("nombresSet", nombresValor);

        RegistrarInformacion.deConsola("Datos de registro: Cédula: {}, Nombres: {}, Apellidos: {}, Correo: {}, Contraseña: {}, Info Adicional: {}, Datos Aleatorios: {}",
                cedulaValor, nombresValor, apellidosValor, correoValor, contrasenaValor, infoAdicional, datosAleatoriosPrueba);

        // Fill form once with chosen values
        actor.attemptsTo(
                Enter.theValue(cedulaValor).into(TXT_CEDULA),
                Enter.theValue(nombresValor).into(TXT_NOMBRES),
                Enter.theValue(apellidosValor).into(TXT_APELLIDOS),
                Enter.theValue(correoValor).into(TXT_CORREO),
                Enter.theValue(contrasenaValor).into(TXT_CONTRASENA),
                Enter.theValue(contrasenaValor).into(TXT_CONFIRMAR_CONTRASENA),
                Click.on(CHK_TERMINOS)
        );

        if (this.infoAdicional.equalsIgnoreCase("si")) {
            actor.attemptsTo(Click.on(CHK_INFO_ADICIONAL));
        }

        actor.attemptsTo(Click.on(BTN_REGISTRAR));
    }

    public static RegistrarUsuarioTask conDatos(String cedula, String nombres, String apellidos, String correo, String contrasena, String infoAdicional, String datosAleatoriosPrueba) {
        return Tasks.instrumented(RegistrarUsuarioTask.class, cedula, nombres, apellidos, correo, contrasena, infoAdicional, datosAleatoriosPrueba);
    }
}
