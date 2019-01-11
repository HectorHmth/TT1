package mx.ipn.escom.prueba.coffeeapp.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import mx.ipn.escom.prueba.coffeeapp.entities.Local;
import mx.ipn.escom.prueba.coffeeapp.services.LocalesService;
import retrofit2.Response;

public class GetLocalDetailByIdAsyncTask extends AsyncTask<Integer,Void,Void> {
    private LocalesService localesService;
    private Response<Local> localResponse;

    public GetLocalDetailByIdAsyncTask(LocalesService localesService){
        this.localesService = localesService;
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        try{
            localResponse = localesService.getLocalById(integers[0]).execute();
            if (localResponse.isSuccessful()){
                if(localResponse.body() == null){
                    Log.d("--->","NO SE ENCONTRÓ EL LOCAL");
                }
            }else{
                Log.d("--->","NO SE ENCONTRÓ EL LOCAL");
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("--->","ERROR EN LA CONSULTA");
        }
        return null;
    }

    public Local getResponseBody() {
        return localResponse.body();
    }
}
