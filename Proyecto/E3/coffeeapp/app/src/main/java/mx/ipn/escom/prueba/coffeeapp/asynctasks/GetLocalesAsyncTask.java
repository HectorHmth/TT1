package mx.ipn.escom.prueba.coffeeapp.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DiffUtil;
import mx.ipn.escom.prueba.coffeeapp.entities.Local;
import mx.ipn.escom.prueba.coffeeapp.services.LocalesService;
import retrofit2.Response;

public class GetLocalesAsyncTask extends AsyncTask<Void, Void, Void> {
    private List<Local> localesList;
    private LocalesService localesService;
    private Integer progress;

    public GetLocalesAsyncTask(LocalesService localesService){
        this.localesService = localesService;
        this.localesList = new ArrayList<>();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Response<List<Local>> response;
        try{
            response = localesService.getLocales().execute();
            if (response.isSuccessful()){
                Log.d("--->","Se obtuvieron los locales");
                localesList = response.body();
            }else{
                Log.d("--->", "ERROR:" + response.message());
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d("--->","Error en la petición");
        }
        return null;
    }

    public List<Local> getLocalesList() {
        return localesList;
    }

    public void setLocalesList(List<Local> localesList) {
        this.localesList = localesList;
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
