package mx.ipn.escom.prueba.coffeeapp.services;

import android.app.Person;

import mx.ipn.escom.prueba.coffeeapp.entities.Cuenta;
import mx.ipn.escom.prueba.coffeeapp.entities.Persona;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CuentaService {

    @GET(value = "cuentas/{id}")
    Call<Cuenta> getCuenta(@Path("id") Integer id);

    @GET(value = "cuentas/{nombreUsuario}")
    Call<Cuenta> getCuentaByNombreUsuario(@Path("nombreUsuario") String nombreUsuario);

    @GET(value = "login")
    Call<Cuenta> getLogin(@Query("username")String nombreUsuario, @Query("password") String constrasena);

    @GET(value = "cuentas/getPersona/{idCuenta}")
    Call<Persona> getPersona(@Path("idCuenta") Integer idCuenta);

}
