package qvision.util.config;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import qvision.util.interfaces.config.driver.OpcionesDriver;

import java.util.HashMap;
import java.util.Map;

public class SustituirOpcionesChrome implements OpcionesDriver<ChromeOptions> {

    @Override
    public WebDriver implementarOpciones(String directorioDeDescarga) {

        ChromeOptions opcionesProveedorChrome = new ChromeOptions();

        // Crear y retornar una instancia local del navegador Chrome
        return new ChromeDriver(obtenerOpciones(opcionesProveedorChrome, directorioDeDescarga));
    }

    @NotNull
    @Override
    public ChromeOptions obtenerOpciones(ChromeOptions proveedor, String directorioDeDescarga) {

        // Ajustar preferencias de Chrome
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", directorioDeDescarga);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("profile.managed_default_content_settings.ads", 0);

        proveedor.setExperimentalOption("prefs", prefs);
        //Ajustar otras opciones del navegador
        proveedor.addArguments("--start-maximized", "--disable-infobars", "--disable-popup-blocking");

        return OpcionesDriver.super.obtenerOpciones(proveedor, directorioDeDescarga);
    }
}
