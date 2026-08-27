package qvision.exception;

import qvision.util.RegistrarInformacion;

import java.io.Serializable;

public class ExcepcionDeLectura extends RuntimeException implements Serializable {

    public final String tipoError;
    public final String dondeOcurre;
    public final transient Object descripcionDelError;

    public ExcepcionDeLectura(String tipoError, String dondeOcurre, Object descripcionDelError) {
        super(String.format("¡Ha ocurrido un error al intentar leer el objeto del sistema de archivos!%n %s, ocurrido en %s%n Verifica los detalles para más información: %s", tipoError, dondeOcurre, descripcionDelError));
        RegistrarInformacion.deConsola("¡Ha ocurrido un error al intentar leer el objeto del sistema de archivos!\n {}, ocurrido en {}\n Verifica los detalles para más información: {}", tipoError, dondeOcurre, descripcionDelError);
        this.tipoError = tipoError;
        this.dondeOcurre = dondeOcurre;
        this.descripcionDelError = descripcionDelError;
    }
}


