package sura.exception;

import sura.util.RegistrarInformacion;

import java.io.Serializable;

/*
 * @(#) ExcepcionDeHost.java 1.0 15/04/2025
 *
 * Copyright 2024 Sura Afore, Inc. Todos los derechos reservados.
 */

/**
 * La clase {@code ExcepcionDeHost} es heredada de {@code RuntimeException} y representa una excepción personalizada
 * que se lanza cuando ocurre un error al intentar determinar la dirección IP de un host especificado.
 *
 * <p>
 * Esta clase permite:
 * <ul>
 *   <li>Identificar el tipo de error ocurrido.</li>
 *   <li>Especificar el lugar donde ocurrió el error.</li>
 *   <li>Proporcionar una descripción detallada del error.</li>
 *   <li>Registrar la información del error en la consola para facilitar el diagnóstico.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Ejemplo de uso:
 * <pre>{@code
 * throw new ExcepcionDeHost("Error de red", ObtenerHost.class.getName(), "No se pudo resolver la dirección IP");
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
public class ExcepcionDeHost extends RuntimeException implements Serializable {

    public final String tipoError;
    public final String dondeOcurre;
    public final transient Object descripcionDelError;

    /*
     * @ExcepcionDeHost
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Constructor para la clase {@code ExcepcionDeHost}.
     *
     * @param tipoError           El tipo de error ocurrido.
     * @param dondeOcurre         El lugar donde ocurrió el error.
     * @param descripcionDelError Una descripción detallada del error.
     */
    public ExcepcionDeHost(String tipoError, String dondeOcurre, Object descripcionDelError) {
        super(String.format("¡Ha ocurrido un error al intentar determinar la dirección IP del host especificado!%n %s, ocurrido en %s%n Verifica los detalles para más información: %s", tipoError, dondeOcurre, descripcionDelError));
        RegistrarInformacion.deConsola("¡Ha ocurrido un error al intentar determinar la dirección IP del host especificado!\n {}, ocurrido en {}\n Verifica los detalles para más información: {}", tipoError, dondeOcurre, descripcionDelError);

        this.tipoError = tipoError;
        this.dondeOcurre = dondeOcurre;
        this.descripcionDelError = descripcionDelError;
    }
}
