package mx.ipn.escom.prueba.coffeeapp.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.ConnectException;

import mx.ipn.escom.prueba.coffeeapp.entities.Cuenta;
import mx.ipn.escom.prueba.coffeeapp.services.CuentaService;
import retrofit2.Response;

public class NetAsyncTask extends AsyncTask<String , Void, Void> {
    private CuentaService cuentaService;
    public Cuenta cuenta;

    public NetAsyncTask(CuentaService cuentaService){
        this.cuentaService = cuentaService;
    }

    @Override
    protected Void doInBackground(String... strings){
        Log.d("--->NetAsyncTask", strings[0] + "-" + strings[1]);
        Response<Cuenta> response = null;
        try {
            response = cuentaService.getLogin(strings[0],strings[1]).execute();
            cuenta = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
