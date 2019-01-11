package mx.ipn.escom.prueba.coffeeapp.business;

import android.app.Application;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutionException;

import mx.ipn.escom.prueba.coffeeapp.entities.PostResponse;
import mx.ipn.escom.prueba.coffeeapp.entities.ProductoLocal;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.ProductoAgotadoException;
import mx.ipn.escom.prueba.coffeeapp.exception.ProductoExisteEnOrdenException;
import mx.ipn.escom.prueba.coffeeapp.exception.ProductoLocalNoEncontradoException;
import mx.ipn.escom.prueba.coffeeapp.exception.ProductoNoAgregadoException;
import mx.ipn.escom.prueba.coffeeapp.repositories.ProductoDetailRepository;

public class ProductoDetailBusiness {

    private ProductoDetailRepository productoDetailRepository;

    public ProductoDetailBusiness(Application application){
        productoDetailRepository = new ProductoDetailRepository(application);
    }

    public ProductoLocal getProductoLocalById(Integer idLocal, Integer idProducto)
    throws ProductoLocalNoEncontradoException
            , SocketTimeoutException
            , InterruptedException
            , ExecutionException
            ,ConexionNoDisponibleException{
        return productoDetailRepository.getProductoDetailById(idLocal, idProducto);
    }


    public Boolean isProductoDisponible(ProductoLocal productoLocal, Integer nuCount)
    throws  ConexionNoDisponibleException
            , InterruptedException
            , ConnectException
            , ExecutionException{
        return productoDetailRepository.isProductoDisponible(productoLocal.getIdLocal(), productoLocal.getIdProducto(),nuCount);

    }

    public PostResponse agregarProducto(ProductoLocal productoLocal, String comentario,Integer idCuenta,Integer ui)
    throws  ConexionNoDisponibleException
            , InterruptedException
            , ExecutionException
            , ProductoNoAgregadoException
            , ProductoExisteEnOrdenException
            , ProductoAgotadoException {
        return productoDetailRepository.agregarProducto(productoLocal,comentario,idCuenta,ui);
    }
}
