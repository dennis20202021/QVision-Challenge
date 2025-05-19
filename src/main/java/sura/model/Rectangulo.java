package sura.model;

/*
 * @(#) Rectangulo.java 1.0 15/04/2025
 *
 * Copyright 2024 Sura Afore, Inc. Todos los derechos reservados.
 */

/**
 * La clase {@code Rectangulo} representa un rectángulo en un plano bidimensional.
 * Contiene las coordenadas de su esquina superior izquierda, así como su ancho y alto.
 *
 * <p>
 * Esta clase permite realizar las siguientes operaciones:
 * <ul>
 *   <li>Obtener y modificar las coordenadas de la esquina superior izquierda.</li>
 *   <li>Obtener y modificar las dimensiones del rectángulo (ancho y alto).</li>
 * </ul>
 * </p>
 *
 * @author Equipo de Automatizaciones SQA S.A
 * @version 1.0
 * @since 15/04/2025
 */
public class Rectangulo {

    private final int x;
    private int y;
    private int ancho;
    private int alto;

    /*
     * @Rectangulo
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Constructor para la clase {@code Rectangulo}.
     *
     * @param x     La coordenada X de la esquina superior izquierda del rectángulo.
     * @param y     La coordenada Y de la esquina superior izquierda del rectángulo.
     * @param ancho El ancho del rectángulo.
     * @param alto  El alto del rectángulo.
     */
    public Rectangulo(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    /*
     * @getX
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Obtiene la coordenada X de la esquina superior izquierda del rectángulo.
     *
     * @return La coordenada X.
     */
    public int getX() {
        return x;
    }

    /*
     * @getY
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Obtiene la coordenada Y de la esquina superior izquierda del rectángulo.
     *
     * @return La coordenada Y.
     */
    public int getY() {
        return y;
    }

    /*
     * @setY
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Establece la coordenada Y de la esquina superior izquierda del rectángulo.
     *
     * @param y La nueva coordenada Y.
     */
    public void setY(int y) {
        this.y = y;
    }

    /*
     * @getAncho
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Obtiene el ancho del rectángulo.
     *
     * @return El ancho.
     */
    public int getAncho() {
        return ancho;
    }

    /*
     * @setAncho
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Establece el ancho del rectángulo.
     *
     * @param ancho El nuevo ancho.
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    /*
     * @getAlto
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Obtiene el alto del rectángulo.
     *
     * @return El alto.
     */
    public int getAlto() {
        return alto;
    }

    /*
     * @setAlto
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Establece el alto del rectángulo.
     *
     * @param alto El nuevo alto.
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }
}
