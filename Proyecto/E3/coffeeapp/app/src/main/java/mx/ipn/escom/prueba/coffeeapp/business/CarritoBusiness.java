package mx.ipn.escom.prueba.coffeeapp.business;

import android.app.Application;
import android.content.Intent;

import java.net.ConnectException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import mx.ipn.escom.prueba.coffeeapp.entities.Local;
import mx.ipn.escom.prueba.coffeeapp.entities.OrdenProducto;
import mx.ipn.escom.prueba.coffeeapp.entities.PostResponse;
import mx.ipn.escom.prueba.coffeeapp.entities.Producto;
import mx.ipn.escom.prueba.coffeeapp.entities.ProductoLocal;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.NoExisteOrdenSinConfirmarException;
import mx.ipn.escom.prueba.coffeeapp.exception.ProductoAgotadoException;
import mx.ipn.escom.prueba.coffeeapp.exception.ProductoExisteEnOrdenException;
import mx.ipn.escom.prueba.coffeeapp.exception.ProductoNoAgregadoException;
import mx.ipn.escom.prueba.coffeeapp.repositories.CarritoRepository;
import mx.ipn.escom.prueba.coffeeapp.repositories.ProductoDetailRepository;



public class CarritoBusiness {
    private CarritoRepository carritoRepository;
    private ProductoDetailRepository productoDetailRepository;


    public CarritoBusiness(Application application){
        this.carritoRepository = new CarritoRepository(application);
        this.productoDetailRepository = new ProductoDetailRepository(application);
    }

    public Local getLocalByOrdenSinConfirmar(Integer idCuenta)
    throws  ConexionNoDisponibleException
            , InterruptedException
            , ExecutionException
            , ConnectException
            , NoExisteOrdenSinConfirmarException {
        return carritoRepository.getLocalOfOrdenSinConfirmar(idCuenta);
    }

    public List<OrdenProducto> getOrdenProductosSinConfirmar(Integer idCuenta)
    throws  ConexionNoDisponibleException
            ,InterruptedException
            ,ExecutionException
            ,NoExisteOrdenSinConfirmarException{
        return carritoRepository.getOrdenProductosSinConfirmar(idCuenta);
    }


    public Boolean onAddCantidad(Integer idLocal, Integer idProductoLocal, Integer nuCount)
    throws  ConexionNoDisponibleException
            ,InterruptedException
            ,ConnectException
            ,ExecutionException{
        return productoDetailRepository.isProductoDisponible(idLocal,idProductoLocal,nuCount);
    }


    public PostResponse addCantidad(ProductoLocal productoLocal, String comentario, Integer idCuenta)
    throws ConexionNoDisponibleException
            ,InterruptedException
            ,ConnectException
            ,ExecutionException
            ,ProductoAgotadoException
            ,ProductoExisteEnOrdenException
            ,ProductoNoAgregadoException {
        return productoDetailRepository.agregarProducto(productoLocal,comentario,idCuenta,1);
    }


    public PostResponse substractCantidad(ProductoLocal productoLocal, String comentario, Integer idCuenta)
    throws ConexionNoDisponibleException
            ,InterruptedException
            ,ConnectException
            ,ExecutionException
            ,ProductoAgotadoException
            ,ProductoExisteEnOrdenException
            ,ProductoNoAgregadoException {
        return productoDetailRepository.agregarProducto(productoLocal,comentario,idCuenta,2);
    }


    public PostResponse deleteProductoDeOrden(OrdenProducto ordenProducto, ProductoLocal productoLocal, Integer idCuenta)
    throws  ConexionNoDisponibleException
            ,ConnectException
            ,ExecutionException
            ,InterruptedException{
        return carritoRepository.deleteProductoDeOrden(ordenProducto, productoLocal, idCuenta);
    }

    public PostResponse finalizarCompraNoProgramada(Integer idOrden, Integer idCuenta)
    throws ConexionNoDisponibleException
            ,InterruptedException
            ,ExecutionException{
        return carritoRepository.finalizarCompraNoProgramada(idOrden,idCuenta);
    }
}
