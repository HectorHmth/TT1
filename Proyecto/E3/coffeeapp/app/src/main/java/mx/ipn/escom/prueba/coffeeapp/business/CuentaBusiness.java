package mx.ipn.escom.prueba.coffeeapp.business;

import android.app.Application;

import java.net.ConnectException;
import java.util.concurrent.ExecutionException;

import mx.ipn.escom.prueba.coffeeapp.entities.Persona;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.repositories.CuentaRepository;

public class CuentaBusiness {
    private CuentaRepository cuentaRepository;

    public CuentaBusiness(Application application){
        this.cuentaRepository = new CuentaRepository(application);
    }

    public Persona getPersona(Integer idCuenta)
    throws ConexionNoDisponibleException
            ,InterruptedException
            ,ExecutionException {
        return cuentaRepository.getPersona(idCuenta);
    }


}
