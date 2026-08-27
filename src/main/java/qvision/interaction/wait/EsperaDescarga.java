package qvision.interaction.wait;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import qvision.util.RegistrarInformacion;
import qvision.util.config.wait.EsperarArchivo;

import java.time.Duration;

public class EsperaDescarga implements Interaction {

    private final String rutaDeArchivo;
    private final Duration timeout;

    public EsperaDescarga(String rutaDeArchivo) {
        this.rutaDeArchivo = rutaDeArchivo;
        this.timeout = Duration.ofSeconds(30);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        new EsperarArchivo(this.rutaDeArchivo, this.timeout).deDescarga();
        RegistrarInformacion.deConsola("¡Archivo descargado correctamente!");
    }

    public static Interaction deArchivo(String rutaDeArchivo) {
        return Instrumented.instanceOf(EsperaDescarga.class).withProperties(rutaDeArchivo);
    }
}


