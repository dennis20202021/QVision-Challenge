package sura.util;


import sura.model.Rectangulo;

public class UtilidadesRectangulo {

    private UtilidadesRectangulo() {
        throw new IllegalStateException("Utility class");
    }

    // Método auxiliar para comprobar si dos rectángulos se superponen
    public static boolean estanLosRectangulosSuperpuestos(Rectangulo rect1, Rectangulo rect2) {
        // Comprobar si los rectángulos no se superponen
        return rect1.getX() + rect1.getAncho() > rect2.getX() && rect2.getX() + rect2.getAncho() > rect1.getX() && rect1.getY() + rect1.getAlto() > rect2.getY() && rect2.getY() + rect2.getAlto() > rect1.getY();
    }

}
