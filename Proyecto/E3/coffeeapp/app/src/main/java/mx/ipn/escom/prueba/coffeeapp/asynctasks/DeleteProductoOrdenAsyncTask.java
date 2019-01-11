package mx.ipn.escom.prueba.coffeeapp.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import mx.ipn.escom.prueba.coffeeapp.R;
import mx.ipn.escom.prueba.coffeeapp.entities.PostResponse;
import mx.ipn.escom.prueba.coffeeapp.services.OrdenesService;
import retrofit2.Response;

public class DeleteProductoOrdenAsyncTask extends AsyncTask<Integer, Void, Void> {
    private OrdenesService ordenesService;
    private Response<PostResponse> postResponse;


    public DeleteProductoOrdenAsyncTask(OrdenesService ordenesService){
        this.ordenesService = ordenesService;
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        try{
            postResponse = ordenesService.deleteProductoDeOrden(integers[0]
                                                                ,integers[1]
                                                                ,integers[2]
                                                                ,integers[3]).execute();
            if (postResponse.isSuccessful()){
                Log.d("--->",postResponse.body().getResultado());
            }else {
                Log.d("--->","ERROR AL BORRAR");
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("--->","ERROR FATAL");
        }
        return null;
    }

    public PostResponse getResponseBody(){
        return postResponse.body();
    }
}
