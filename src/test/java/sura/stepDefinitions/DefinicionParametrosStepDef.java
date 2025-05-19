package sura.stepDefinitions;

import io.cucumber.java.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.*;
import sura.task.CerrarSesionTask;
import sura.util.Cargar;
import sura.util.DefinirPropiedad;
import sura.util.ObtenerIP;
import sura.util.report.UtilidadesReporte;

import static sura.util.Constantes.GESTION_ARCHIVO_SERENITY_PROPIEDADES;
import static sura.util.HiloConcurrente.desmontarHilo;

public class DefinicionParametrosStepDef {

    @ParameterType(".*")
    public Actor actor(String actorName) {
        return OnStage.theActorCalled(actorName);
    }

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @After
    public void logout() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                CerrarSesionTask.actual()
        );
        UtilidadesReporte.adjuntarArchivoAlReporte(
                Cargar.recursoComoCadena("data/input_data.xlsx"),
                "Datos de prueba"
        );
        UtilidadesReporte.adjuntarArchivoAlReporte(
                "test.log",
                "Archivo Logs"
        );
        desmontarHilo();
    }

    @AfterAll
    public static void tearDown() {
        GESTION_ARCHIVO_SERENITY_PROPIEDADES.establecerPropiedad("report.customfields.ip", ObtenerIP.delHostDeterminado());
        GESTION_ARCHIVO_SERENITY_PROPIEDADES.establecerPropiedad("report.customfields.concurrency", DefinirPropiedad.obtenerConcurrencia());
        GESTION_ARCHIVO_SERENITY_PROPIEDADES.guardarPropiedades();
    }
}
