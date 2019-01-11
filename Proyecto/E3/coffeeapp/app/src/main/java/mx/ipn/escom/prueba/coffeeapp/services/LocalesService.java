package mx.ipn.escom.prueba.coffeeapp.services;

import java.util.List;

import mx.ipn.escom.prueba.coffeeapp.entities.Local;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocalesService {

    @GET("locales")
    Call<List<Local>> getLocales();

    @GET("locales/local/{nombreLocal}")
    Call<Local> getLocal(@Path("nombreLocal") String nombreLocal);

    @GET("locales/findById/{idLocal}")
    Call<Local> getLocalById(@Path("idLocal") Integer idLocal);
}
