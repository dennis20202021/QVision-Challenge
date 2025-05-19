package sura.exception;

import sura.util.RegistrarInformacion;

import java.io.Serializable;

/*
 * @(#) ExcepcionDeLectura.java 1.0 15/04/2025
 *
 * Copyright 2024 Sura Afore, Inc. Todos los derechos reservados.
 */

/**
 * La clase {@code ExcepcionDeLectura} es heredada de {@code RuntimeException} y representa una excepción personalizada
 * que se lanza cuando ocurre un error al intentar leer un objeto del sistema de archivos.
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
 * throw new ExcepcionDeLectura("Error de formato", LeerArchivo.class.getName(), "El archivo no tiene el formato esperado");
 * }</pre>
 * </p>
 *
 * <p>
 * Nota: El campo {@code descripcionDelError} es transitorio y no será serializado.
 * </p>
 *
 * <p>
 * Esta clase es útil para manejar errores relacionados con la lectura de archivos o datos en el sistema.
 * </p>
 *
 * @author Equipo de Automatizaciones SQA S.A
 * @version 1.0
 * @since 15/04/2025
 */
public class ExcepcionDeLectura extends RuntimeException implements Serializable {

    public final String tipoError;
    public final String dondeOcurre;
    public final transient Object descripcionDelError;

    /*
     * @ExcepcionDeLectura
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Constructor para la clase {@code ExcepcionDeLectura}.
     *
     * @param tipoError           El tipo de error ocurrido.
     * @param dondeOcurre         El lugar donde ocurrió el error.
     * @param descripcionDelError Una descripción detallada del error.
     */
    public ExcepcionDeLectura(String tipoError, String dondeOcurre, Object descripcionDelError) {
        super(String.format("¡Ha ocurrido un error al intentar leer el objeto del sistema de archivos!%n %s, ocurrido en %s%n Verifica los detalles para más información: %s", tipoError, dondeOcurre, descripcionDelError));
        RegistrarInformacion.deConsola("¡Ha ocurrido un error al intentar leer el objeto del sistema de archivos!\n {}, ocurrido en {}\n Verifica los detalles para más información: {}", tipoError, dondeOcurre, descripcionDelError);
        this.tipoError = tipoError;
        this.dondeOcurre = dondeOcurre;
        this.descripcionDelError = descripcionDelError;
    }
}
