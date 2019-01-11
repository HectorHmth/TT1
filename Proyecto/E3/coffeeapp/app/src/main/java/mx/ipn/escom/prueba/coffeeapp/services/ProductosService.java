package mx.ipn.escom.prueba.coffeeapp.services;

import java.util.List;

import mx.ipn.escom.prueba.coffeeapp.entities.ProductoLocal;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductosService {

    @GET("productos/productos/{nombreLocal}")
    Call<List<ProductoLocal>> getProductosByNombreLocal(@Path("nombreLocal") String nombreLocal);

    @GET("productos/paquetes/{nombreLocal}")
    Call<List<ProductoLocal>> getPaquetesByNombreLocal(@Path("nombreLocal") String nombreLocal);

    @GET("productos/producto/{idLocal}/{idProducto}")
    Call<ProductoLocal> getProductoLocalById(@Path("idLocal") Integer idLocal, @Path("idProducto") Integer idProducto);

    @GET("productos/disponibilidad/{idLocal}/{idProducto}")
    Call<Boolean> isProductoDisponible(@Path("idLocal") Integer idLocal, @Path("idProducto") Integer idProducto, @Query("nuCount") Integer nuCount);
}
