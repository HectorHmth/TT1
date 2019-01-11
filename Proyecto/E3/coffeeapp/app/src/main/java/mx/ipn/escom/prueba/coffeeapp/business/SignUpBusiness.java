package mx.ipn.escom.prueba.coffeeapp.business;

import android.app.Application;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutionException;

import mx.ipn.escom.prueba.coffeeapp.entities.Persona;
import mx.ipn.escom.prueba.coffeeapp.entities.PostResponse;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.CuentaExisteException;
import mx.ipn.escom.prueba.coffeeapp.repositories.SignUpRepository;

public class SignUpBusiness {
    private SignUpRepository signUpRepository;

    public SignUpBusiness(Application application){
        this.signUpRepository = new SignUpRepository(application);
    }

    public PostResponse onSignUp(String nombrePersona
                            , String primerApellido
                            , String segundoApellido
                            , String correoElectronico
                            , String nombreUsuario
                            , String contrasena)
    throws
            SocketTimeoutException
            ,ConexionNoDisponibleException
            ,ExecutionException
            ,InterruptedException
            ,CuentaExisteException
            ,ConnectException
    {
            return signUpRepository.onSignUp(nombrePersona
                                                        , primerApellido
                                                        , segundoApellido
                                                        , correoElectronico
                                                        , nombreUsuario
                                                        , contrasena);

    }
}
