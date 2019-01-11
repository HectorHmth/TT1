package mx.ipn.escom.prueba.coffeeapp.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import mx.ipn.escom.prueba.coffeeapp.entities.PostResponse;
import mx.ipn.escom.prueba.coffeeapp.services.OrdenesService;
import retrofit2.Response;

public class AgregarProductoSyncTask extends AsyncTask<Object, Void, Void> {

    private Response<PostResponse> response;
    private OrdenesService ordenesService;

    public AgregarProductoSyncTask(OrdenesService ordenesService) {
        this.ordenesService = ordenesService;
    }

    @Override
    protected Void doInBackground(Object ... params) {
        try{
            response = ordenesService.agregarProducto((Integer) params[0]
                                                        ,(Integer) params[1]
                                                        ,(Integer) params[2]
                                                        ,(Integer) params[3]
                                                        ,(String) params[4]
                                                        , (Integer)params[5]).execute();
            if (response.isSuccessful()){
                Log.d("--->EXITO", response.body().getId()+"-"+response.body().getResultado());
            }else {
                Log.d("--->ERROR", "-"+response.body().getResultado());
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d("--->ERRORFATAL", e.toString());
        }
        return null;
    }

    public PostResponse getBodyResponse(){
        return response.body();
    }
}
