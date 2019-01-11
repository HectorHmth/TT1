package mx.ipn.escom.prueba.coffeeapp.repositories;

import android.app.Application;
import android.util.Log;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import mx.ipn.escom.prueba.coffeeapp.NetworkBroadcastReceiver;
import mx.ipn.escom.prueba.coffeeapp.RetrofitManager;
import mx.ipn.escom.prueba.coffeeapp.asynctasks.GetOrdenesAsyncTask;
import mx.ipn.escom.prueba.coffeeapp.asynctasks.GetOrdenesProductoAsyncTask;
import mx.ipn.escom.prueba.coffeeapp.entities.Orden;
import mx.ipn.escom.prueba.coffeeapp.entities.OrdenProducto;
import mx.ipn.escom.prueba.coffeeapp.entities.Producto;
import mx.ipn.escom.prueba.coffeeapp.entities.ProductoLocal;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.NoExistenOrdenesException;
import mx.ipn.escom.prueba.coffeeapp.services.OrdenesService;

public class OrdenesRepository {
    private OrdenesService ordenesService;

    public OrdenesRepository(Application application){
        ordenesService = RetrofitManager.getRetrofitManager().create(OrdenesService.class);
    }

    public List<Orden> getOrdenesActivas(Integer idCuenta) throws ConexionNoDisponibleException
            , NoExistenOrdenesException
            , ConnectException
            , InterruptedException
            , ExecutionException {
        if(NetworkBroadcastReceiver.isConnected()){
            GetOrdenesAsyncTask getOrdenesAsyncTask = new GetOrdenesAsyncTask(ordenesService);
            getOrdenesAsyncTask.execute(idCuenta).get();

            GetOrdenesProductoAsyncTask getOrdenesProductoAsyncTask = new GetOrdenesProductoAsyncTask(ordenesService);
            getOrdenesProductoAsyncTask.execute(idCuenta).get();

            List<Orden> ordenesActivas = getOrdenesAsyncTask.getOrdenesList();
            List<OrdenProducto> ordenesProducto = getOrdenesProductoAsyncTask.getOrdenesList();

            if((ordenesActivas != null) && (ordenesProducto != null)){
                Log.d("---->","EL OBJETO NO ES NULO...");
                HashMap<Integer, List<ProductoLocal>> hashMap = new HashMap<>();

                for(OrdenProducto ordenProducto : ordenesProducto){
                    if(!hashMap.containsKey(ordenProducto.idOrden)){
                        List<ProductoLocal> list = new ArrayList<>();
                        list.add(ordenProducto.productoLocal);
                    }
                    else{
                        hashMap.get(ordenProducto.idOrden).add(ordenProducto.productoLocal);
                    }
                }

                for(Orden orden : ordenesActivas){
                    List<ProductoLocal> ordenesAgrupadas = hashMap.get(orden.getId());
                    orden.setProductosDeLocalEnOrden(ordenesAgrupadas);
                }

                return ordenesActivas;
            }
            else{
                throw new NoExistenOrdenesException();
            }

        }
        else{
            throw new ConexionNoDisponibleException();
        }
    }


    public List<Orden> getOrdenes(Integer idCuenta) throws ConexionNoDisponibleException
            , NoExistenOrdenesException
            , ConnectException
            , InterruptedException
            , ExecutionException {
        if(NetworkBroadcastReceiver.isConnected()){
            GetOrdenesAsyncTask getOrdenesAsyncTask = new GetOrdenesAsyncTask(ordenesService);
            getOrdenesAsyncTask.execute(idCuenta).get();

            GetOrdenesProductoAsyncTask getOrdenesProductoAsyncTask = new GetOrdenesProductoAsyncTask(ordenesService);
            getOrdenesProductoAsyncTask.execute(idCuenta).get();

            List<Orden> ordenes = getOrdenesAsyncTask.getOrdenesList();
            List<OrdenProducto> ordenesProducto = getOrdenesProductoAsyncTask.getOrdenesList();
            List<Orden> fullOrdenes = new ArrayList<>();

            if((ordenes != null) && (ordenesProducto != null)){
                Log.d("---->","EL OBJETO NO ES NULO...");
                HashMap<Integer, List<ProductoLocal>> hashMap = new HashMap<>();

                for(OrdenProducto ordenProducto : ordenesProducto){
                    if(!hashMap.containsKey(ordenProducto.idOrden)){
                        List<ProductoLocal> list = new ArrayList<>();
                        list.add(ordenProducto.productoLocal);
                        hashMap.put(ordenProducto.idOrden, list);
                        Log.d("---->New Array", ordenProducto.productoLocal.getProducto().getNombreProducto());
                    }
                    else{
                        hashMap.get(ordenProducto.idOrden).add(ordenProducto.productoLocal);
                        Log.d("---->Add to Array", ordenProducto.productoLocal.getProducto().getNombreProducto());
                    }
                }

                for(int i=0; i<ordenes.size(); i++){
                    Orden orden = ordenes.get(i);
                    List<ProductoLocal> ordenesAgrupadas = hashMap.get(orden.getId());
                    orden.setProductosDeLocalEnOrden(ordenesAgrupadas);
                    fullOrdenes.add(orden);
                }

                /*Debug for checking if the orders are full */
                for(int i=0; i<fullOrdenes.size(); i++){
                    Orden orden = fullOrdenes.get(i);
                    Log.d("---->Orden", orden.getId().toString());
                    Log.d("---->Orden", orden.getFecha().toString());
                    Log.d("---->Orden", String.valueOf(orden.getProductosDeLocalEnOrden().size()));
                    Log.d("---->Ordenk", orden.getProductosDeLocalEnOrden().get(0).getIdLocal().toString());
                }
                return fullOrdenes;
            }
            else{
                throw new NoExistenOrdenesException();
            }
        }
        else{
            throw new ConexionNoDisponibleException();
        }
    }
}
