package mx.ipn.escom.prueba.coffeeapp.services;

import java.util.List;

import androidx.annotation.Nullable;
import mx.ipn.escom.prueba.coffeeapp.entities.Local;
import mx.ipn.escom.prueba.coffeeapp.entities.Orden;
import mx.ipn.escom.prueba.coffeeapp.entities.OrdenProducto;
import mx.ipn.escom.prueba.coffeeapp.entities.PostResponse;
import mx.ipn.escom.prueba.coffeeapp.entities.Producto;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrdenesService {

    @FormUrlEncoded
    @POST("ordenes/agregarProducto")
    Call<PostResponse> agregarProducto(@Field("idLocal") Integer idLocal
                                        , @Field("idProducto") Integer idProducto
                                        , @Field("nuCantidad") Integer nuCantidad
                                        , @Field("idCuenta") Integer idCuenta
                                        , @Field("comentario") String comentario
                                        , @Nullable @Field("ui") Integer ui);

    @GET("ordenes/getAll/{idCuenta}")
    Call<List<Orden>> getOrdenes(@Path("idCuenta") Integer idCuenta);

    @GET("ordenes/getAllOrdenesProducto/{idCuenta}")
    Call<List<OrdenProducto>> getOrdenesProducto(@Path("idCuenta") Integer idCuenta);

    @GET("ordenes/getLocalOfOrden/{idCuenta}")
    Call<Local> getLocalOfOrden(@Path("idCuenta") Integer idCuenta);

    @GET("ordenes/getOrdenProductosSinConfirmar/{idCuenta}")
    Call<List<OrdenProducto>> getOrdenProductosSinConfirmar(@Path("idCuenta") Integer idCuenta);

    @POST("ordenes/deleteProductoDeOrden/{idOrden}/{idLocal}/{idProductoLocal}/{idCuenta}")
    Call<PostResponse> deleteProductoDeOrden(@Path("idOrden")Integer idOrden
                                            , @Path("idLocal") Integer idLocal
                                            , @Path("idProductoLocal")Integer idProductoLocal
                                            , @Path("idCuenta")Integer idCuenta);

    @POST("ordenes/finalizarCompra/{idOrden}/{idCuenta}")
    Call<PostResponse> finalizarCompraNoProgramada(@Path("idOrden") Integer idOrden
                                                    ,@Path("idCuenta") Integer idCuenta);
}
