package sura.util;


public class HiloConcurrente {

    private HiloConcurrente() {
        throw new IllegalStateException("Utility class");
    }

    private static final ThreadLocal<String> uniqueThreadIdentifier = ThreadLocal.withInitial(() -> "");

    public static void establecerIdUnico(String identifier) {
        uniqueThreadIdentifier.set(identifier);
    }

    public static void desmontarHilo() {
        uniqueThreadIdentifier.remove();
    }

    public static String obtenerIdUnico() {
        return uniqueThreadIdentifier.get();
    }
}
