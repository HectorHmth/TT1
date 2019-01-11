package mx.ipn.escom.prueba.coffeeapp.repositories;

import android.app.Application;

import java.net.ConnectException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import mx.ipn.escom.prueba.coffeeapp.NetworkBroadcastReceiver;
import mx.ipn.escom.prueba.coffeeapp.RetrofitManager;
import mx.ipn.escom.prueba.coffeeapp.asynctasks.DeleteProductoOrdenAsyncTask;
import mx.ipn.escom.prueba.coffeeapp.asynctasks.FinalizarCompraNoProgramadaAsyncTask;
import mx.ipn.escom.prueba.coffeeapp.asynctasks.GetLocalOfOrdenAsyncTask;
import mx.ipn.escom.prueba.coffeeapp.asynctasks.GetOrdenProductosAsyncTask;
import mx.ipn.escom.prueba.coffeeapp.entities.Local;
import mx.ipn.escom.prueba.coffeeapp.entities.OrdenProducto;
import mx.ipn.escom.prueba.coffeeapp.entities.PostResponse;
import mx.ipn.escom.prueba.coffeeapp.entities.ProductoLocal;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.NoExisteOrdenSinConfirmarException;
import mx.ipn.escom.prueba.coffeeapp.services.LocalesService;
import mx.ipn.escom.prueba.coffeeapp.services.OrdenesService;

public class CarritoRepository {
    private OrdenesService ordenesService;

    public CarritoRepository(Application application){
        ordenesService = RetrofitManager.getRetrofitManager().create(OrdenesService.class);
    }

    public Local getLocalOfOrdenSinConfirmar(Integer idCuenta)
    throws  ConexionNoDisponibleException
            , InterruptedException
            , ExecutionException
            , NoExisteOrdenSinConfirmarException{
        if (NetworkBroadcastReceiver.isConnected()){
            GetLocalOfOrdenAsyncTask getLocalOfOrdenAsyncTask = new GetLocalOfOrdenAsyncTask(ordenesService);
            getLocalOfOrdenAsyncTask.execute(idCuenta).get();
            if (getLocalOfOrdenAsyncTask.getResponseBody() != null)
                return getLocalOfOrdenAsyncTask.getResponseBody();
            else
                throw new NoExisteOrdenSinConfirmarException();
        }else{
            throw new ConexionNoDisponibleException();
        }
    }

    public List<OrdenProducto> getOrdenProductosSinConfirmar(Integer idCuenta)
    throws ConexionNoDisponibleException
            ,InterruptedException
            ,ExecutionException
            ,NoExisteOrdenSinConfirmarException{
        if (NetworkBroadcastReceiver.isConnected()){
            GetOrdenProductosAsyncTask getOrdenProductosAsyncTask = new GetOrdenProductosAsyncTask(ordenesService);
            getOrdenProductosAsyncTask.execute(idCuenta).get();
            if (getOrdenProductosAsyncTask.getResponseBody() != null){
                return getOrdenProductosAsyncTask.getResponseBody();
            }else{
                throw new NoExisteOrdenSinConfirmarException();
            }
        }else{
            throw new ConexionNoDisponibleException();
        }
    }


    public PostResponse deleteProductoDeOrden(OrdenProducto ordenProducto, ProductoLocal productoLocal, Integer idCuenta)
    throws ConexionNoDisponibleException
            ,InterruptedException
            ,ExecutionException{
        if (NetworkBroadcastReceiver.isConnected()){
            DeleteProductoOrdenAsyncTask deleteProductoOrdenAsyncTask = new DeleteProductoOrdenAsyncTask(ordenesService);
            deleteProductoOrdenAsyncTask.execute(ordenProducto.idOrden,ordenProducto.idLocal,productoLocal.getIdProducto(),idCuenta).get();
            return deleteProductoOrdenAsyncTask.getResponseBody();
        }else{
            throw new ConexionNoDisponibleException();
        }
    }

    public PostResponse finalizarCompraNoProgramada(Integer idProducto, Integer idCuenta)
    throws ConexionNoDisponibleException
            ,InterruptedException
            ,ExecutionException{
        if (NetworkBroadcastReceiver.isConnected()){
            FinalizarCompraNoProgramadaAsyncTask finalizarCompraNoProgramadaAsyncTask = new FinalizarCompraNoProgramadaAsyncTask(ordenesService);
            finalizarCompraNoProgramadaAsyncTask.execute(idProducto,idCuenta).get();
            return finalizarCompraNoProgramadaAsyncTask.getResponseBody();
        }else {
            throw new ConexionNoDisponibleException();
        }
    }
}
