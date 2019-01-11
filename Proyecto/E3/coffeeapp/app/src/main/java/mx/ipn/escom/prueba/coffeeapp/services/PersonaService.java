package mx.ipn.escom.prueba.coffeeapp.services;

import mx.ipn.escom.prueba.coffeeapp.entities.Persona;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PersonaService {

    @GET(value = "personas/{id}")
    Call<Persona> getPersona(@Path("id") Integer id);

}
