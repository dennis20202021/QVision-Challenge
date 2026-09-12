# language: es
@Regresión
@PruebaDeHumo
Característica: Registro de usuario en el portal Bon-Bonite

  Regla: Como usuario quiero poder ingresar al portal Bon-Bonite

    Antecedentes:
      Dado que el usuario de pruebas navega al portal Bon-Bonite

    @RegistroExitoso
    Esquema del escenario: Registro exitoso de usuario
      Cuando realiza click en el botón de iniciar sesión
      Y realiza click en el botón de registrarse
      Y ingresa los datos de "<Cedula>", "<Nombres>", "<Apellidos>", "<Correo>", "<Contrasena>" e "<InfoAdicional>" de registro válidos o genera datos aleatorios "<DatosAleatoriosPrueba>"
      Entonces valida el correcto registro e inicio de sesión del usuario y el mensaje de bienvenida

      Ejemplos:

        | Cedula | Nombres | Apellidos | Correo | Contrasena | InfoAdicional | DatosAleatoriosPrueba |
        #@data:data/PTP_Daniel_Valderrama27082026.xlsx#@sheetName:registroExitoso
