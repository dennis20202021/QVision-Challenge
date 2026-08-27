package qvision.exception;

import qvision.util.RegistrarInformacion;

import java.io.Serializable;

public class ExcepcionDeHost extends RuntimeException implements Serializable {

    public final String tipoError;
    public final String dondeOcurre;
    public final transient Object descripcionDelError;

    public ExcepcionDeHost(String tipoError, String dondeOcurre, Object descripcionDelError) {
        super(String.format("¡Ha ocurrido un error al intentar determinar la dirección IP del host especificado!%n %s, ocurrido en %s%n Verifica los detalles para más información: %s", tipoError, dondeOcurre, descripcionDelError));
        RegistrarInformacion.deConsola("¡Ha ocurrido un error al intentar determinar la dirección IP del host especificado!\n {}, ocurrido en {}\n Verifica los detalles para más información: {}", tipoError, dondeOcurre, descripcionDelError);

        this.tipoError = tipoError;
        this.dondeOcurre = dondeOcurre;
        this.descripcionDelError = descripcionDelError;
    }
}


