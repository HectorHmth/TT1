package mx.ipn.escom.prueba.coffeeapp.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import mx.ipn.escom.prueba.coffeeapp.entities.Persona;
import mx.ipn.escom.prueba.coffeeapp.services.CuentaService;
import retrofit2.Response;

public class GetPersonaAsyncTask extends AsyncTask<Integer,Void,Void> {
    private CuentaService cuentaService;
    private Response<Persona> response;

    public GetPersonaAsyncTask(CuentaService cuentaService){
        this.cuentaService = cuentaService;
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        try{
            response = cuentaService.getPersona(integers[0]).execute();
            if (response.isSuccessful()){
                Log.d("--->","PERSONA_OBTENIDA");
            }else{
                Log.d("--->","ERROR");
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("--->","ERROR_FATAL");
        }
        return null;
    }

    public Persona getResponseBody(){
        return response.body();
    }
}
