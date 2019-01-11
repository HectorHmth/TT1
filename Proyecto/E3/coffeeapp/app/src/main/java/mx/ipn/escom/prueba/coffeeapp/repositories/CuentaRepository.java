package mx.ipn.escom.prueba.coffeeapp.repositories;

import android.app.Application;

import java.net.ConnectException;
import java.util.concurrent.ExecutionException;

import mx.ipn.escom.prueba.coffeeapp.NetworkBroadcastReceiver;
import mx.ipn.escom.prueba.coffeeapp.RetrofitManager;
import mx.ipn.escom.prueba.coffeeapp.asynctasks.GetPersonaAsyncTask;
import mx.ipn.escom.prueba.coffeeapp.entities.Persona;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.services.CuentaService;

public class CuentaRepository {
    private CuentaService cuentaService;

    public CuentaRepository(Application application){
        cuentaService = RetrofitManager.getRetrofitManager().create(CuentaService.class);
    }


    public Persona getPersona(Integer idCuenta) throws
            ConexionNoDisponibleException
            ,InterruptedException
            ,ExecutionException {
        if (NetworkBroadcastReceiver.isConnected()){
            GetPersonaAsyncTask getPersonaAsyncTask = new GetPersonaAsyncTask(cuentaService);
            getPersonaAsyncTask.execute(idCuenta).get();
            return getPersonaAsyncTask.getResponseBody();
        }else{
            throw new ConexionNoDisponibleException();
        }
    }
}
