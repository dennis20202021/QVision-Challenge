package sura.exception;

import sura.util.RegistrarInformacion;

import java.io.Serializable;

/*
 * @(#) ExcepcionInterrumpida.java 1.0 15/04/2025
 *
 * Copyright 2024 Sura Afore, Inc. Todos los derechos reservados.
 */

/**
 * La clase {@code ExcepcionInterrumpida} es heredada de {@code RuntimeException} y representa una excepción personalizada
 * que se lanza cuando una operación es interrumpida inesperadamente.
 *
 * <p>
 * Esta clase permite:
 * <ul>
 *   <li>Identificar la operación que fue interrumpida.</li>
 *   <li>Especificar el lugar donde ocurrió la interrupción.</li>
 *   <li>Proporcionar una descripción detallada del motivo de la interrupción.</li>
 *   <li>Registrar la información del error en la consola para facilitar el diagnóstico.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Ejemplo de uso:
 * <pre>{@code
 * throw new ExcepcionInterrumpida("Proceso de cálculo", Calcular.class.getName(), "El hilo fue interrumpido");
 * }</pre>
 * </p>
 *
 * <p>
 * Nota: El campo {@code descripcionDelError} es transitorio y no será serializado.
 * </p>
 *
 * @author Equipo de Automatizaciones SQA S.A
 * @version 1.0
 * @since 15/04/2025
 */
public class ExcepcionInterrumpida extends RuntimeException implements Serializable {

    public final String tipoError;
    public final String dondeOcurre;
    public final transient Object descripcionDelError;

    /*
     * @ExcepcionInterrumpida
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Constructor para la clase {@code ExcepcionInterrumpida}.
     *
     * @param operacion El nombre de la operación que fue interrumpida.
     * @param lugar     El lugar donde ocurrió la interrupción.
     * @param detalle   Una descripción detallada del motivo de la interrupción.
     */
    public ExcepcionInterrumpida(String operacion, String lugar, Object detalle) {
        super(String.format("¡La operación ha sido interrumpida inesperadamente!%nOperación: %s, Lugar: %s%nDetalle: %s", operacion, lugar, detalle));
        RegistrarInformacion.deConsola("¡La operación ha sido interrumpida inesperadamente!\n Operación: {}, Lugar: {}\n Detalle: {}", operacion, lugar, detalle);
        this.tipoError = operacion;
        this.dondeOcurre = lugar;
        this.descripcionDelError = detalle;
    }
}
