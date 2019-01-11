package mx.ipn.escom.prueba.coffeeapp.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import mx.ipn.escom.prueba.coffeeapp.entities.OrdenProducto;
import mx.ipn.escom.prueba.coffeeapp.services.OrdenesService;
import retrofit2.Response;

public class GetOrdenProductosAsyncTask extends AsyncTask<Integer,Void,Void>{
    private OrdenesService ordenesService;
    private Response<List<OrdenProducto>> response;

    public GetOrdenProductosAsyncTask(OrdenesService ordenesService){
        this.ordenesService = ordenesService;
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        try{
            response = ordenesService.getOrdenProductosSinConfirmar(integers[0]).execute();
            if (response.isSuccessful()){
                Log.d("--->","SE OBTUVIERON DATOS");
            }else {
                Log.d("--->","ERROR");
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("--->","ERROR_FATAL");
        }
        return null;
    }

    public List<OrdenProducto> getResponseBody(){
        return response.body();
    }
}
