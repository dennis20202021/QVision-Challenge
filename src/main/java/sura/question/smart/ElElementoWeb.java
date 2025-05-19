package sura.question.smart;

import net.serenitybdd.screenplay.*;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebElement;
import sura.model.Rectangulo;
import sura.util.RegistrarInformacion;
import sura.util.UtilidadesRectangulo;

import java.util.List;
import java.util.stream.Collectors;

import static sura.ui.AforePortalAccesoLoginUI.TODOS_LOS_ELEMENTOS;

public class ElElementoWeb implements Question<Boolean> {

    private final Target elemento;

    public ElElementoWeb(Target elemento) {
        this.elemento = elemento;
    }


    @Override
    public Boolean answeredBy(Actor actor) {
        // Obtener el elemento Target
        WebElement targetElement = this.elemento.resolveFor(actor);

        // Obtener la ubicación y el tamaño del elemento Target
        int targetX = targetElement.getLocation().getX();
        int targetY = targetElement.getLocation().getY();
        int targetWidth = targetElement.getSize().getWidth();
        int targetHeight = targetElement.getSize().getHeight();

        // Construir rectángulo del elemento Target
        Rectangulo rect1 = new Rectangulo(targetX, targetY, targetWidth, targetHeight);

        // Encontrar todos los elementos en la página (excluyendo el elemento Target previo)
        List<WebElement> allElements = TODOS_LOS_ELEMENTOS.stream()
                .map(target -> target.resolveFor(actor))
                .collect(Collectors.toList());

        // Filtrar el elemento Target
        allElements.remove(targetElement);

        // Verificar superposición por cada elemento
        for (WebElement otherElement : allElements) {

            try {

                // Obtener la ubicación y el tamaño del otro elemento
                int otherX = otherElement.getLocation().getX();
                int otherY = otherElement.getLocation().getY();
                int otherWidth = otherElement.getSize().getWidth();
                int otherHeight = otherElement.getSize().getHeight();

                // Construir otro rectángulo del otro elemento
                Rectangulo rect2 = new Rectangulo(otherX, otherY, otherWidth, otherHeight);

                // Comprobar superposición
                if (UtilidadesRectangulo.estanLosRectangulosSuperpuestos(rect1, rect2)) {
                    return true; // Superposición encontrada
                }

            } catch (Exception e) {
                // Controlar excepciones, por ejemplo, si un elemento no es visible o no se puede acceder a él
                RegistrarInformacion.deConsola("Error al comprobar superposición con el elemento: {} ", e.getMessage());
            }
        }
        return false; // Ninguna superposición encontrada
    }

    public static ElElementoWeb estaSuperpuesto(Target element) {
        return new ElElementoWeb(element);
    }
}
