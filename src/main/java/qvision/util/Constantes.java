package qvision.util;

import io.github.cdimascio.dotenv.Dotenv;
import net.datafaker.Faker;
import qvision.util.file.propiedades.*;
import qvision.util.interfaces.propiedades.ControlArchivoPropiedades;

public class Constantes {

    private Constantes() {
        throw new IllegalStateException("Utility class");
    }

    public static final Dotenv VAR_AMBIENTE = Dotenv.load();

    private static final ControlArchivoPropiedades UTILIDAD_PROPIEDADES = new ActualizadorArchivoPropiedades();

    public static final ModificarArchivoPropiedades GESTION_ARCHIVO_SERENITY_PROPIEDADES = UTILIDAD_PROPIEDADES.gestionarPropiedad("serenity.properties");

    public static final ModificarArchivoPropiedades GESTION_ARCHIVO_JUNIT_PROPIEDADES = UTILIDAD_PROPIEDADES.gestionarPropiedad(Cargar.recursoComoCadena("junit-platform.properties"));

    public static final String CANTIDAD_DE_CONCURRENCIAS = GESTION_ARCHIVO_JUNIT_PROPIEDADES.obtenerPropiedad("cucumber.execution.parallel.config.fixed.parallelism");

    public static final String RUTA_ABSOLUTA_DESCARGAS = String.format("%s%s", System.getProperty("user.dir"), "\\src\\test\\resources\\almacenamientoDocumentos");

    public static final String EXTENSION_EXCEL_MODERNO = ".xlsx";

    public static final String EXTENSION_TEMP_CRDOWNLOAD = ".crdownload";

    public static final String EXTENSION_TEMP_PART = ".part";

    public static final Faker GENERADOR_DE_DATOS = new Faker();

}
