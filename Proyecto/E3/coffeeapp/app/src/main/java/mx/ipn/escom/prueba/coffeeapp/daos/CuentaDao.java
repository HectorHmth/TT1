package mx.ipn.escom.prueba.coffeeapp.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import mx.ipn.escom.prueba.coffeeapp.entities.Cuenta;
/**
 * Define la interfaz para obtener los datos de la base de datos local
 * */
@Dao
public interface CuentaDao {

    @Insert
    public void insert(Cuenta cuenta);

    @Update
    public void update(Cuenta cuenta);

    @Delete
    public void delete(Cuenta cuenta);

    @Query("SELECT * FROM TML02_CUENTA WHERE tx_nombreUsuario = (:nombreUsuario)")
    public Cuenta getByNombreUsuario(String nombreUsuario);

    @Query("DELETE FROM TML02_CUENTA")
    public void deleteAll();
}
