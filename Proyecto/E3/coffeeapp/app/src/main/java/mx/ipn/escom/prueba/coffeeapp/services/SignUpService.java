package mx.ipn.escom.prueba.coffeeapp.services;

import mx.ipn.escom.prueba.coffeeapp.entities.PostResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SignUpService {

    @FormUrlEncoded
    @POST("signup")
    Call<PostResponse> registrarCuenta(@Field("nombrePersona") String nombrePersona
                                    , @Field("primerApellido") String primerApellido
                                    , @Field("segundoApellido") String segundoApellido
                                    , @Field("correoElectronico") String correoElectronico
                                    , @Field("nombreUsuario") String nombreUsuario
                                    , @Field("contrasena") String contrasena);
}
