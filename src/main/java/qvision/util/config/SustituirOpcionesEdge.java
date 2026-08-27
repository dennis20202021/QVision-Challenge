package qvision.util.config;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import qvision.util.interfaces.config.driver.OpcionesDriver;

import java.util.HashMap;
import java.util.Map;

public class SustituirOpcionesEdge implements OpcionesDriver<EdgeOptions> {

    @Override
    public WebDriver implementarOpciones(String directorioDeDescarga) {

        EdgeOptions opcionesProveedorEdge = new EdgeOptions();

        // Crear y retornar una instancia local del navegador Edge
        return new EdgeDriver(obtenerOpciones(opcionesProveedorEdge, directorioDeDescarga));
    }

    @NotNull
    @Override
    public EdgeOptions obtenerOpciones(EdgeOptions proveedor, String directorioDeDescarga) {

        // Ajustar preferencias de Microsoft Edge
        Map<String, Object> edgePrefs = new HashMap<>();
        edgePrefs.put("download.default_directory", directorioDeDescarga);
        edgePrefs.put("profile.default_content_settings.popups", 0);
        edgePrefs.put("profile.managed_default_content_settings.ads", 0);

        proveedor.setExperimentalOption("prefs", edgePrefs);
        //Ajustar otras opciones del navegador
        proveedor.addArguments("--start-maximized");

        return OpcionesDriver.super.obtenerOpciones(proveedor, directorioDeDescarga);
    }
}
