package mx.ipn.escom.prueba.coffeeapp.business;

import android.app.Application;

import java.net.ConnectException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import mx.ipn.escom.prueba.coffeeapp.entities.ProductoLocal;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.ProductoLocalNoEncontradoException;
import mx.ipn.escom.prueba.coffeeapp.repositories.ProductosRepository;

public class PromocionesBusiness {
    private ProductosRepository productosRepository;

    public PromocionesBusiness(Application application){
        this.productosRepository  = new ProductosRepository(application);
    }

    public List<ProductoLocal> getPromociones(String nombreLocal) throws ConexionNoDisponibleException
            , ProductoLocalNoEncontradoException
            , ConnectException
            , InterruptedException
            , ExecutionException {
        return productosRepository.getPromociones(nombreLocal);
    }
}
