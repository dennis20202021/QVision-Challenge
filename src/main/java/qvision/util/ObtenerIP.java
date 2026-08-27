package qvision.util;

import qvision.exception.ExcepcionDeHost;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ObtenerIP {

    private ObtenerIP() {
        throw new IllegalStateException("Utility class");
    }

    public static String delHostDeterminado() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException error) {
            throw new ExcepcionDeHost("¡No se pudo obtener la dirección IP del host!", ObtenerIP.class.getName(), error);
        }
    }

}
