package mx.ipn.escom.prueba.coffeeapp.repositories;

import android.app.Application;
import android.util.Log;

import java.net.ConnectException;
import java.util.concurrent.ExecutionException;

import mx.ipn.escom.prueba.coffeeapp.AppDataBase;
import mx.ipn.escom.prueba.coffeeapp.NetworkBroadcastReceiver;
import mx.ipn.escom.prueba.coffeeapp.RetrofitManager;
import mx.ipn.escom.prueba.coffeeapp.asynctasks.GetAsyncTask;
import mx.ipn.escom.prueba.coffeeapp.asynctasks.NetAsyncTask;
import mx.ipn.escom.prueba.coffeeapp.daos.CuentaDao;
import mx.ipn.escom.prueba.coffeeapp.entities.Cuenta;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.CuentaNotFoundException;
import mx.ipn.escom.prueba.coffeeapp.services.CuentaService;

public class LoginRepository {
    private CuentaDao cuentaDao;
    private CuentaService cuentaService;
    private Boolean rememberMe;

    public LoginRepository(Application application){
        cuentaDao = AppDataBase.getDatabase(application.getApplicationContext()).cuentaDao();
        cuentaService = RetrofitManager.getRetrofitManager().create(CuentaService.class);
    }

    public Cuenta loginCuenta(String nombreUsuario,String password) throws
            ConexionNoDisponibleException
            ,ExecutionException
            ,InterruptedException
            ,CuentaNotFoundException
            ,ConnectException {
        //Buscar en la base de datos local
        Cuenta cuenta = new Cuenta();
        GetAsyncTask getAsyncTask = new GetAsyncTask(cuentaDao);
        getAsyncTask.execute(nombreUsuario).get();
        cuenta = getAsyncTask.cuenta;
        if(cuenta == null){
            if (NetworkBroadcastReceiver.isConnected()){
                //Search from net
                NetAsyncTask netAsyncTask = new NetAsyncTask(cuentaService);
                netAsyncTask.execute(nombreUsuario,password).get();
                cuenta = netAsyncTask.cuenta;
                if (cuenta == null){
                    throw new CuentaNotFoundException();
                }
                Log.d("--->","Datos:" + cuenta.getNombreUsuario() + "-" + cuenta.getContrasena());
            }else{
                throw new ConexionNoDisponibleException();
            }
        }
        return cuenta;
    }


}
