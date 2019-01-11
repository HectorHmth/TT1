package mx.ipn.escom.prueba.coffeeapp.repositories;

import android.app.Application;
import android.util.Log;

import java.net.ConnectException;
import java.util.concurrent.ExecutionException;

import mx.ipn.escom.prueba.coffeeapp.AppDataBase;
import mx.ipn.escom.prueba.coffeeapp.NetworkBroadcastReceiver;
import mx.ipn.escom.prueba.coffeeapp.RetrofitManager;
import mx.ipn.escom.prueba.coffeeapp.asynctasks.SignUpNetAsyncTask;
import mx.ipn.escom.prueba.coffeeapp.constants.Constants;
import mx.ipn.escom.prueba.coffeeapp.daos.CuentaDao;
import mx.ipn.escom.prueba.coffeeapp.daos.PersonaDao;
import mx.ipn.escom.prueba.coffeeapp.entities.Cuenta;
import mx.ipn.escom.prueba.coffeeapp.entities.Persona;
import mx.ipn.escom.prueba.coffeeapp.entities.PostResponse;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.CuentaExisteException;
import mx.ipn.escom.prueba.coffeeapp.services.SignUpService;

public class SignUpRepository {
    private PersonaDao personaDao;
    private CuentaDao cuentaDao;
    private SignUpService signUpService;


    public SignUpRepository(Application application){
        personaDao = AppDataBase.getDatabase(application.getApplicationContext()).personaDao();
        cuentaDao = AppDataBase.getDatabase(application.getApplicationContext()).cuentaDao();
        signUpService = RetrofitManager.getRetrofitManager().create(SignUpService.class);
    }

    /**
     * Registra una persona en el sistema por medio del servicio web.
     *
     * @param nombrePersona Nombre de la persona que se va a registrar
     * @param primerApellido Primer apellido de la persona que se va a registrar
     * @param segundoApellido Segundo apellido de la persona que se va a registrar
     * @param correoElectronico Correo Electrónico de la persona que se va a registrar
     * @param nombreUsuario Nombre de Usuario seleccionado por la persona para iniciar sesión en el sistema
     * @param contrasena Contraseña seleccionada por la persona para iniciar sesión en el sistema
     *
     * @return Regresa el código de resultado de registrar a un nuevo cliente en el sistema.
     * */
   public PostResponse onSignUp(String nombrePersona
                            , String primerApellido
                            , String segundoApellido
                            , String correoElectronico
                            , String nombreUsuario
                            , String contrasena
   ) throws
            ConexionNoDisponibleException
           ,InterruptedException
           ,ExecutionException
           ,CuentaExisteException
           ,ConnectException
   {
        //Aun discutir si se debe guardar a la persona, que yo creo que si
        if (NetworkBroadcastReceiver.isConnected()){
            SignUpNetAsyncTask signUpNetAsyncTask = new SignUpNetAsyncTask(signUpService);
            signUpNetAsyncTask.execute(nombrePersona
                    ,primerApellido
                    ,segundoApellido
                    ,correoElectronico
                    ,nombreUsuario
                    ,contrasena).get();

            switch (signUpNetAsyncTask.getPostResponse().getId()){
                case Constants.CUENTA_EXISTE:
                    throw new CuentaExisteException();
                default:
                    return signUpNetAsyncTask.getPostResponse();
            }
        }else{
            throw new ConexionNoDisponibleException();
        }
   }
}
