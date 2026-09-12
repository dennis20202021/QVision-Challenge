# language: es
@Regresión
@PruebaDeHumo
Característica: Inicio de sesión en el portal Bon-Bonite

  Regla: Como usuario quiero poder ingresar al portal Bon-Bonite

    Antecedentes:
      Dado que el usuario de pruebas navega al portal Bon-Bonite

    @InicioSesiónExitoso
    Esquema del escenario: Inicio de sesión exitoso
      Cuando realiza click en el botón de iniciar sesión
      Y ingresa los datos de "<Cedula>", "<Contrasena>" de inicio de sesión válidos
      Entonces valida el correcto registro e inicio de sesión del usuario y el mensaje de bienvenida

      Ejemplos:

        | Cedula | Contrasena |
        #@data:data/PTP_Daniel_Valderrama27082026.xlsx#@sheetName:iniciSesionExitoso