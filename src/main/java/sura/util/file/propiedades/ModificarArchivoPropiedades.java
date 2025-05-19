package sura.util.file.propiedades;

import sura.exception.ExcepcionDeLectura;
import sura.util.interfaces.propiedades.GestionArchivoPropiedades;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ModificarArchivoPropiedades implements GestionArchivoPropiedades {

    private final Properties propiedades;
    private final String rutaDeArchivo;

    public ModificarArchivoPropiedades(String rutaDeArchivo) {
        this.rutaDeArchivo = rutaDeArchivo;
        this.propiedades = new Properties();
        cargarPropiedades(this.rutaDeArchivo, this.propiedades);
    }

    public String obtenerPropiedad(String clave) {
        return this.propiedades.getProperty(clave);
    }

    public void establecerPropiedad(String clave, String valor) {
        this.propiedades.setProperty(clave, valor);
    }

    @Override
    public void cargarPropiedades(String rutaDeArchivo, Properties propiedades) {
        try (FileInputStream inputStream = new FileInputStream(rutaDeArchivo)) {
            propiedades.load(inputStream);
        } catch (IOException error) {
            throw new ExcepcionDeLectura(
                    String.format("¡Archivo propiedades no encontrado en la ruta: %s!", rutaDeArchivo),
                    ModificarArchivoPropiedades.class.getName(),
                    error
            );
        }
    }

    @Override
    public void guardarPropiedades() {
        try (FileOutputStream outputStream = new FileOutputStream(this.rutaDeArchivo)) {
            this.propiedades.store(outputStream, null);
        } catch (IOException error) {
            throw new ExcepcionDeLectura(
                    String.format("¡Error al intentar guardar los cambios en el archivo de propiedades en la ruta: %s!", this.rutaDeArchivo),
                    ModificarArchivoPropiedades.class.getName(),
                    error
            );
        }
    }
}
