package mx.ipn.escom.prueba.coffeeapp.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import mx.ipn.escom.prueba.coffeeapp.services.ProductosService;
import retrofit2.Response;

public class IsDisponibleAsyncTask extends AsyncTask<Integer,Void, Void> {
    private ProductosService productosService;
    private Response<Boolean> response;

    public IsDisponibleAsyncTask(ProductosService productosService){
        this.productosService = productosService;
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        try{
            response = productosService.isProductoDisponible(integers[0],integers[1],integers[2]).execute();
            if (response.isSuccessful()){
                Log.d("--->", "Resultado" + response.body());

            }else{
                Log.d("--->","ERROR");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Boolean getResponseBody(){
        return response.body();
    }
}
