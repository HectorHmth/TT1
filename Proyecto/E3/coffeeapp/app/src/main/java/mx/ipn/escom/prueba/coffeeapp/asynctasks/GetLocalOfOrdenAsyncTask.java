package mx.ipn.escom.prueba.coffeeapp.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import mx.ipn.escom.prueba.coffeeapp.entities.Local;
import mx.ipn.escom.prueba.coffeeapp.services.OrdenesService;
import retrofit2.Response;

public class GetLocalOfOrdenAsyncTask extends AsyncTask<Integer,Void,Void> {
    private OrdenesService ordenesService;
    private Response<Local> response;

    public GetLocalOfOrdenAsyncTask(OrdenesService ordenesService) {
        this.ordenesService = ordenesService;
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        try{
            response = ordenesService.getLocalOfOrden(integers[0]).execute();
            if (response.isSuccessful()){
                Log.d("--->","SE OBTUVO EL LOCAL");
            }else{
                Log.d("--->","ERROR_FATAL");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Local getResponseBody(){
        return response.body();
    }
}
