package qvision.util.file.propiedades;

import qvision.util.interfaces.propiedades.ControlArchivoPropiedades;

public class ActualizadorArchivoPropiedades implements ControlArchivoPropiedades {

    @Override
    public ModificarArchivoPropiedades gestionarPropiedad(String rutaDeArchivo) {
        return new ModificarArchivoPropiedades(rutaDeArchivo);
    }
}
