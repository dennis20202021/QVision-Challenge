package qvision.exception;

import qvision.util.RegistrarInformacion;

import java.io.Serializable;

public class ExcepcionInterrumpida extends RuntimeException implements Serializable {

    public final String tipoError;
    public final String dondeOcurre;
    public final transient Object descripcionDelError;

    public ExcepcionInterrumpida(String operacion, String lugar, Object detalle) {
        super(String.format("¡La operación ha sido interrumpida inesperadamente!%nOperación: %s, Lugar: %s%nDetalle: %s", operacion, lugar, detalle));
        RegistrarInformacion.deConsola("¡La operación ha sido interrumpida inesperadamente!\n Operación: {}, Lugar: {}\n Detalle: {}", operacion, lugar, detalle);
        this.tipoError = operacion;
        this.dondeOcurre = lugar;
        this.descripcionDelError = detalle;
    }
}


