package mx.ipn.escom.prueba.coffeeapp.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import mx.ipn.escom.prueba.coffeeapp.entities.ProductoLocal;
import mx.ipn.escom.prueba.coffeeapp.services.ProductosService;
import retrofit2.Response;

public class GetProductoDetailAsyncTask extends AsyncTask<Integer, Void, Void> {
    private Response<ProductoLocal> response;
    private ProductosService productosService;

    public GetProductoDetailAsyncTask(ProductosService productosService){
        this.productosService = productosService;
    }


    @Override
    protected Void doInBackground(Integer... integers) {
        try{
            this.response = productosService.getProductoLocalById(integers[0], integers[1]).execute();
            if (response.isSuccessful()){
                if (response.body() != null){
                    Log.d("--->","SE ENCONTRO INFO. PRODUCTO");
                }else{
                    Log.d("--->","NO EXISTE EL PRODUCTO");
                }
            }else{
                Log.d("--->","ERROR EN LA CONEXION");
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("--->","ERROR");
        }
        return null;
    }

    public ProductoLocal getResponseBody(){
        return this.response.body();
    }
}
