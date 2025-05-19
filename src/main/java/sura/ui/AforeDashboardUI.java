package sura.ui;

import net.serenitybdd.screenplay.targets.Target;

public class AforeDashboardUI {

    private AforeDashboardUI() {
        throw new IllegalStateException("Utility class");
    }

    public static final Target ETIQUETA_BIENVENIDA = Target.the("Etiqueta Bienvenida")
            .locatedBy("#menu_contenedor_sup");

    public static final Target CERRAR_SESION_BTN = Target.the("Enlace de Cerrar Sesión")
            .locatedBy("//img[contains(@src, 'bt_cerrar_cesion')]/parent::a");

    public static final Target BTN_MENU_PORTAL_DE_ACCESO = Target.the("Botón Menú Portal de Acceso")
            .locatedBy("#hierarchybreadcrumb");

    public static final Target OPCION_AF_026_GESTION_PENSION = Target.the("Opción AF-026 Global")
            .locatedBy("//span[contains(text(), '{0}')]/parent::a");

    public static final Target OPCION_DE_PROYECCION_DE_PENSION = Target.the("Opción de Proyección de la Pensión Global")
            .locatedBy("//li[@role='menuitem']/a[contains(text(), '{0}')]");

    public static final Target OPCION_DE_AUTOSERVICIO = Target.the("Opción de AutoServicio")
            .locatedBy("(//a[contains(text(), '{0}')])[2]");
}
