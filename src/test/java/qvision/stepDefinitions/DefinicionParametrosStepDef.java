package qvision.stepDefinitions;

import io.cucumber.java.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.*;
import qvision.util.Cargar;
import qvision.util.DefinirPropiedad;
import qvision.util.ObtenerIP;
import qvision.util.report.UtilidadesReporte;

import static qvision.util.Constantes.GESTION_ARCHIVO_SERENITY_PROPIEDADES;
import static qvision.util.HiloConcurrente.desmontarHilo;

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
        UtilidadesReporte.adjuntarArchivoAlReporte(
                Cargar.recursoComoCadena("data/PTP_Daniel_Valderrama27082026.xlsx"),
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
