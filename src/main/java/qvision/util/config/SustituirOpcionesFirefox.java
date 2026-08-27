package qvision.util.config;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import qvision.util.interfaces.config.driver.OpcionesDriver;

public class SustituirOpcionesFirefox implements OpcionesDriver<FirefoxOptions> {

    @Override
    public WebDriver implementarOpciones(String directorioDeDescarga) {

        FirefoxOptions opcionesProveedorFirefox = new FirefoxOptions();

        // Crear y retornar una instancia local del navegador Firefox
        return new FirefoxDriver(obtenerOpciones(opcionesProveedorFirefox, directorioDeDescarga));
    }

    @NotNull
    @Override
    public FirefoxOptions obtenerOpciones(FirefoxOptions proveedor, String directorioDeDescarga) {

        // Ajustar preferencias de Firefox
        proveedor.addPreference("browser.download.dir", directorioDeDescarga);
        proveedor.addPreference("browser.download.folderList", 2);
        proveedor.addPreference("browser.download.manager.showWhenStarting", false);
        proveedor.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
        proveedor.setPageLoadStrategy(PageLoadStrategy.EAGER);

        //Ajustar otras opciones del navegador
        proveedor.addArguments("--start-maximized");

        return OpcionesDriver.super.obtenerOpciones(proveedor, directorioDeDescarga);
    }
}
