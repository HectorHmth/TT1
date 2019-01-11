package mx.ipn.escom.prueba.coffeeapp.business;

import android.app.Application;
import android.util.Log;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutionException;

import mx.ipn.escom.prueba.coffeeapp.entities.Cuenta;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.CuentaNotFoundException;
import mx.ipn.escom.prueba.coffeeapp.repositories.LoginRepository;

public class LoginBusiness {

    private LoginRepository loginRepository;

    public LoginBusiness(Application application){
        loginRepository= new LoginRepository(application);
    }


    public Cuenta onLogin(String nombreUsuario, String contrasena) throws
            SocketTimeoutException
            ,ConexionNoDisponibleException
            ,CuentaNotFoundException
            ,ExecutionException
            ,InterruptedException
            ,ConnectException {
            Cuenta cuenta = loginRepository.loginCuenta(nombreUsuario,contrasena);
            if(cuenta != null){
                return cuenta;
            }else{
                throw new CuentaNotFoundException();
            }

    }


}
