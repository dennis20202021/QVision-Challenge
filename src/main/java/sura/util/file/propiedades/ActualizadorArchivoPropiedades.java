package sura.util.file.propiedades;

import sura.util.interfaces.propiedades.ControlArchivoPropiedades;

public class ActualizadorArchivoPropiedades implements ControlArchivoPropiedades {

    @Override
    public ModificarArchivoPropiedades gestionarPropiedad(String rutaDeArchivo) {
        return new ModificarArchivoPropiedades(rutaDeArchivo);
    }
}
