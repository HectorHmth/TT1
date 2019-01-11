package mx.ipn.escom.prueba.coffeeapp.repositories;

import android.app.Application;
import android.content.Intent;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutionException;

import androidx.annotation.Nullable;
import mx.ipn.escom.prueba.coffeeapp.NetworkBroadcastReceiver;
import mx.ipn.escom.prueba.coffeeapp.RetrofitManager;
import mx.ipn.escom.prueba.coffeeapp.asynctasks.AgregarProductoSyncTask;
import mx.ipn.escom.prueba.coffeeapp.asynctasks.GetProductoDetailAsyncTask;
import mx.ipn.escom.prueba.coffeeapp.asynctasks.IsDisponibleAsyncTask;
import mx.ipn.escom.prueba.coffeeapp.constants.Constants;
import mx.ipn.escom.prueba.coffeeapp.entities.PostResponse;
import mx.ipn.escom.prueba.coffeeapp.entities.ProductoLocal;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.ProductoAgotadoException;
import mx.ipn.escom.prueba.coffeeapp.exception.ProductoExisteEnOrdenException;
import mx.ipn.escom.prueba.coffeeapp.exception.ProductoLocalNoEncontradoException;
import mx.ipn.escom.prueba.coffeeapp.exception.ProductoNoAgregadoException;
import mx.ipn.escom.prueba.coffeeapp.services.OrdenesService;
import mx.ipn.escom.prueba.coffeeapp.services.ProductosService;

public class ProductoDetailRepository {
    private ProductosService productosService;
    private OrdenesService ordenesService;

    public ProductoDetailRepository(Application application){
        productosService = RetrofitManager.getRetrofitManager().create(ProductosService.class);
        ordenesService = RetrofitManager.getRetrofitManager().create(OrdenesService.class);
    }

    public ProductoLocal getProductoDetailById(Integer idLocal, Integer idProducto)
    throws  ConexionNoDisponibleException
            , ProductoLocalNoEncontradoException
            , SocketTimeoutException
            , InterruptedException
            , ExecutionException {
        if (NetworkBroadcastReceiver.isConnected()){
            GetProductoDetailAsyncTask getProductoDetailAsyncTask = new GetProductoDetailAsyncTask(productosService);
            getProductoDetailAsyncTask.execute(idLocal,idProducto).get();
            if (getProductoDetailAsyncTask.getResponseBody() != null){
                return getProductoDetailAsyncTask.getResponseBody();
            }else{
                throw new ProductoLocalNoEncontradoException();
            }
        }else{
            throw new ConexionNoDisponibleException();
        }
    }

    public Boolean isProductoDisponible(Integer idLocal, Integer idProducto, Integer nuCount)
    throws ConexionNoDisponibleException
            , InterruptedException
            , ConnectException
            , ExecutionException{
        if (NetworkBroadcastReceiver.isConnected()){
            IsDisponibleAsyncTask isDisponibleAsyncTask = new IsDisponibleAsyncTask(productosService);
            isDisponibleAsyncTask.execute(idLocal, idProducto,nuCount).get();
            return isDisponibleAsyncTask.getResponseBody();
        }else{
            throw new ConexionNoDisponibleException();
        }
    }


    /**
     * Agrega un producto a la base de datos
     * @param ui Si este parámetro es nulo se agrega el producto desde la interfaz de consultar producto
     *           Si este parametro no es nulo se agrega el producto desde el carrito de compras y se e actualizar la cantidad
     * */
    public PostResponse agregarProducto(ProductoLocal productoLocal, String comentario, Integer idCuenta, @Nullable Integer ui)
    throws  ConexionNoDisponibleException
            , ExecutionException
            , InterruptedException
            , ProductoAgotadoException
            , ProductoNoAgregadoException
            , ProductoExisteEnOrdenException{
        if (NetworkBroadcastReceiver.isConnected()){
            AgregarProductoSyncTask agregarProductoSyncTask = new AgregarProductoSyncTask(ordenesService);
            agregarProductoSyncTask.execute(productoLocal.getIdLocal()
                                            ,productoLocal.getIdProducto()
                                            ,productoLocal.getCantidad()
                                            ,idCuenta
                                            ,comentario
                                            ,ui).get();
            switch (agregarProductoSyncTask.getBodyResponse().getId()){
                case Constants.PRODUCTO_AGOTADO:
                    throw new ProductoAgotadoException();
                case Constants.PRODUCTO_NO_AGREGADO:
                    throw new ProductoNoAgregadoException();
                case Constants.PRODUCTO_EXISTE_EN_LA_ORDEN:
                    throw new ProductoExisteEnOrdenException();
                default:
                    return agregarProductoSyncTask.getBodyResponse();
            }

        }else {
            throw new ConexionNoDisponibleException();
        }
    }

}
