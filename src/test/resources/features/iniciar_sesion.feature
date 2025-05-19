# language: es
@Regresión
@PruebaDeHumo-InicioSesión
Característica: Iniciar sesión

  Regla: Como usuario quiero poder ingresar al portal Afore de Sura

    Antecedentes:
      Dado que el usuario de pruebas navega al portal Afore de Sura

    @InicioSesiónExitoso
    Esquema del escenario: Inicio de sesión
      Cuando ingresa su usuario "<UsuarioMX>" y contraseña de acceso "<Contrasenia>"

      Ejemplos:

        | UsuarioMX | Contrasenia |
        #@data:data/input_data.xlsx#@sheetName:inicioSesiónExitoso
