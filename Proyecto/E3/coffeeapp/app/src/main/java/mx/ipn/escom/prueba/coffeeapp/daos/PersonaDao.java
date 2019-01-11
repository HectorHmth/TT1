package mx.ipn.escom.prueba.coffeeapp.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import mx.ipn.escom.prueba.coffeeapp.entities.Persona;

@Dao
public interface PersonaDao {

    @Insert
    public void insert(Persona persona);

    @Update
    public void update(Persona persona);

    @Delete
    public void delete(Persona persona);

    @Query("SELECT * FROM TML01_PERSONA WHERE id_persona = (:idUsuario)")
    public Persona getPersona(Integer idUsuario);

    @Query("SELECT * FROM TML01_PERSONA WHERE tx_nombreUsuario = (:nombreUsuario)")
    public Persona getPersonaByUserName(String nombreUsuario);

    @Query("SELECT * FROM TML01_PERSONA WHERE tx_primer_apellido = (:primerApellido)")
    public Persona getPersonaByApellido(String primerApellido);

    @Query("DELETE FROM TML01_PERSONA")
    public void deleteAll();

}
