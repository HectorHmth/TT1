package mx.ipn.escom.prueba.coffeeapp.business;

import android.app.Application;

import java.net.ConnectException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import mx.ipn.escom.prueba.coffeeapp.entities.Orden;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.NoExistenOrdenesException;
import mx.ipn.escom.prueba.coffeeapp.repositories.OrdenesRepository;

public class OrdenesBusiness {

    private OrdenesRepository ordenesRepository;

    public OrdenesBusiness(Application application){
        this.ordenesRepository = new OrdenesRepository(application);
    }

    public List<Orden> getOrdenes(Integer idCuenta) throws ConexionNoDisponibleException
            , NoExistenOrdenesException
            , ConnectException
            , InterruptedException
            , ExecutionException {
        return ordenesRepository.getOrdenes(idCuenta);
    }

    public List<Orden> getOrdenesActivas(Integer idCuenta) throws ConexionNoDisponibleException
            , NoExistenOrdenesException
            , ConnectException
            , InterruptedException
            , ExecutionException {
        return ordenesRepository.getOrdenesActivas(idCuenta);
    }
}
