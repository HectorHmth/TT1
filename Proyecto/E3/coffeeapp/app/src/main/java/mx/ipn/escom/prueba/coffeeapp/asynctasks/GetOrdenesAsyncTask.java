package mx.ipn.escom.prueba.coffeeapp.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mx.ipn.escom.prueba.coffeeapp.entities.Orden;
import mx.ipn.escom.prueba.coffeeapp.entities.OrdenProducto;
import mx.ipn.escom.prueba.coffeeapp.services.OrdenesService;
import retrofit2.Response;

public class GetOrdenesAsyncTask extends AsyncTask<Integer, Void, Void>{
    private List<Orden> ordenesList;
    private OrdenesService ordenesService;
    private Integer progress;

    public GetOrdenesAsyncTask(OrdenesService ordenesService){
        this.ordenesService = ordenesService;
        this.ordenesList = new ArrayList<>();
    }

    public List<Orden> getOrdenesList() {
        return ordenesList;
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        Response<List<Orden>> response;
        try{
            response = ordenesService.getOrdenes(integers[0]).execute();
            if(response.isSuccessful()){
                Log.d("--->","SE OBTUVIERON LAS ORDENES");
                Log.d("--->",response.body().toString());
                ordenesList = response.body();
            }
            else{
                Log.d("--->", "ERROR:" + response.message());
            }
        }
        catch(Exception e){
            e.printStackTrace();
            Log.d("--->","ERROR EN LA PETICIÓN");
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        Log.d("--->","EJECUTANDO CONSULTA");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("--->","Conclui");
    }
}
