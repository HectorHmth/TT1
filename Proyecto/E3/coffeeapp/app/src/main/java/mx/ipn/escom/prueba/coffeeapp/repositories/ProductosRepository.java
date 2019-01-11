package mx.ipn.escom.prueba.coffeeapp.repositories;

import android.app.Application;

import java.net.ConnectException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import mx.ipn.escom.prueba.coffeeapp.NetworkBroadcastReceiver;
import mx.ipn.escom.prueba.coffeeapp.RetrofitManager;
import mx.ipn.escom.prueba.coffeeapp.asynctasks.GetProductosAsyncTask;
import mx.ipn.escom.prueba.coffeeapp.asynctasks.GetPromocionesAsyncTask;
import mx.ipn.escom.prueba.coffeeapp.entities.ProductoLocal;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.ProductoLocalNoEncontradoException;
import mx.ipn.escom.prueba.coffeeapp.services.ProductosService;

public class ProductosRepository {
    private ProductosService productosService;

    public ProductosRepository(Application application){
        productosService = RetrofitManager.getRetrofitManager().create(ProductosService.class);
    }

    public List<ProductoLocal> getProductos(String nombreLocal) throws ConexionNoDisponibleException
            , ProductoLocalNoEncontradoException
            , ConnectException
            , InterruptedException
            , ExecutionException {
        if(NetworkBroadcastReceiver.isConnected()){
            GetProductosAsyncTask getProductosAsyncTask = new GetProductosAsyncTask(productosService);
            getProductosAsyncTask.execute(nombreLocal).get();
            List<ProductoLocal> productos = getProductosAsyncTask.getProductosLocalList();

            if (productos != null){
                return productos;
            }
            else{
                throw new ProductoLocalNoEncontradoException();
            }
        }
        else{
            throw new ConexionNoDisponibleException();
        }
    }


    public List<ProductoLocal> getPromociones(String nombreLocal) throws ConexionNoDisponibleException
            , ProductoLocalNoEncontradoException
            , ConnectException
            , InterruptedException
            , ExecutionException {
        if(NetworkBroadcastReceiver.isConnected()){
            GetPromocionesAsyncTask getPromocionesAsyncTask = new GetPromocionesAsyncTask(productosService);
            getPromocionesAsyncTask.execute(nombreLocal).get();
            List<ProductoLocal> promociones = getPromocionesAsyncTask.getPromocionesLocalList();

            if (promociones != null){
                return promociones;
            }
            else{
                throw new ProductoLocalNoEncontradoException();
            }
        }
        else{
            throw new ConexionNoDisponibleException();
        }
    }

}