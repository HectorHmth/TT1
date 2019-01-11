package mx.ipn.escom.prueba.coffeeapp.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.EOFException;
import java.io.IOException;

import mx.ipn.escom.prueba.coffeeapp.constants.NetWorkConstants;
import mx.ipn.escom.prueba.coffeeapp.entities.PostResponse;
import mx.ipn.escom.prueba.coffeeapp.services.SignUpService;
import retrofit2.Response;

public class SignUpNetAsyncTask extends AsyncTask<String, Void, Void> {
    private PostResponse postResponse;
    private SignUpService signUpService;

    public SignUpNetAsyncTask(SignUpService signUpService){
        this.signUpService = signUpService;
    }

    @Override
    protected Void doInBackground(String... strings) {
        Response<PostResponse> response;
        try{
            Log.d("--->SUNAT","ENTRANDO");
            response = signUpService.registrarCuenta(strings[0]
                                                    ,strings[1]
                                                    ,strings[2]
                                                    ,strings[3]
                                                    ,strings[4]
                                                    ,strings[5]).execute();
            if (response.isSuccessful()){
                Log.d("--->SUNAT","Resultado:" + response.body().getId() +"-"+  response.body().getResultado());
                postResponse = response.body();
            }
            else{
                Log.d("--->SUNAT","Conexión sin éxito:");
                postResponse.setId(NetWorkConstants.CONEXION_NO_DISPONIBLE);
            }
        }catch (IOException e){
            e.printStackTrace();
            postResponse.setId(NetWorkConstants.CONEXION_NO_DISPONIBLE);
        }
        return null;
    }

    public PostResponse getPostResponse() {
        return postResponse;
    }
}
