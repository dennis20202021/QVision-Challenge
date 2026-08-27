# language: es
@Regresión
@PruebaDeHumo
Característica: Iniciar sesión

  Regla: Como usuario quiero poder ingresar al portal Bon-Bonite

    Antecedentes:
      Dado que el usuario de pruebas navega al portal Bon-Bonite

    @RegistroExitoso
    Esquema del escenario: Registro exitoso de usuario
      Cuando realiza click en el botón de iniciar sesión
      Y realiza click en el botón de registrarse
      Y ingresa los datos de "<Cedula>", "<Nombres>", "<Apellidos>", "<Correo>", "<Contrasena>" e "<InfoAdicional>" de registro válidos

      Ejemplos:

        | Cedula | Nombres | Apellidos | Correo | Contrasena | InfoAdicional |
        #@data:data/PTP_Daniel_Valderrama27082026.xlsx#@sheetName:registroExitoso
