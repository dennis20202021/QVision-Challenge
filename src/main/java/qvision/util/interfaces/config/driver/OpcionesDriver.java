package qvision.util.interfaces.config.driver;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

public interface OpcionesDriver<T> {

    WebDriver implementarOpciones(String directorioDeDescarga);

    @NotNull
    default T obtenerOpciones(T proveedor, String directorioDeDescarga) {
        return proveedor;
    }
}
