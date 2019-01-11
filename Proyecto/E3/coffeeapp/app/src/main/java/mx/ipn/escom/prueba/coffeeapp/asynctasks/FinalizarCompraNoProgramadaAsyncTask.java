package mx.ipn.escom.prueba.coffeeapp.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import mx.ipn.escom.prueba.coffeeapp.entities.PostResponse;
import mx.ipn.escom.prueba.coffeeapp.services.OrdenesService;
import retrofit2.Response;

public class FinalizarCompraNoProgramadaAsyncTask extends AsyncTask<Integer,Void,Void> {
    private OrdenesService ordenesService;
    private Response<PostResponse> postResponse;

    public FinalizarCompraNoProgramadaAsyncTask(OrdenesService ordenesService){
        this.ordenesService = ordenesService;
    }
    @Override
    protected Void doInBackground(Integer... integers) {
        try{
            postResponse = ordenesService.finalizarCompraNoProgramada(integers[0],integers[1]).execute();
            if (postResponse.isSuccessful()){
                Log.d("--->","TODO_FINE");
            }else{
                Log.d("--->","ERROR");
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("--->","ERROR_FATAL");
        }
        return null;
    }


    public PostResponse getResponseBody(){
        return postResponse.body();
    }
}
