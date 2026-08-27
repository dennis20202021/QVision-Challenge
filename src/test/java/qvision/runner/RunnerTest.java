package qvision.runner;

import org.junit.jupiter.api.Test;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.*;
import org.junit.platform.launcher.core.*;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import qvision.service.suite.ConfigurarServicioDe;

import static org.junit.jupiter.api.Assertions.*;

class RunnerTest {

    @Test
    void runTests() {

        ConfigurarServicioDe.lecturaYEscrituraDeArchivos();

        LauncherDiscoveryRequest solicitud = LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectClass(CucumberSuite.class.getName()))
                .build();

        Launcher iniciador = LauncherFactory.create();

        SummaryGeneratingListener receptor = new SummaryGeneratingListener();
        iniciador.execute(solicitud, receptor);

        assertEquals(0, receptor.getSummary().getFailures().size(), "Se espera a que todas las pruebas se completen con éxito");
    }
}
