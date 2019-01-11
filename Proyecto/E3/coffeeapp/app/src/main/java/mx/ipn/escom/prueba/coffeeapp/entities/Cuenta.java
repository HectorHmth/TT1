package mx.ipn.escom.prueba.coffeeapp.entities;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 *  Define la entidad de negocio Cuenta con la que el cliente podrá iniciar sesión
 *  en la aplicación móvil.
 * */
@Entity(tableName = "tml02_cuenta")
public class Cuenta {
    @SerializedName("idCuenta")
    @Expose
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_cuenta")
    private Integer id;
    @SerializedName("nombreUsuario")
    @Expose
    @ColumnInfo(name = "tx_nombreUsuario")
    private String nombreUsuario;
    @SerializedName("contrasena")
    @Expose
    @ColumnInfo(name = "tx_contrasena")
    private String contrasena;

    @SerializedName("tx_correo")
    @Expose
    private String correo;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return Regresa el nombre de usuario
     * */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario Setea el nombre de usuario de una cuenta
     * */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }


    /**
     * @return Regresa la contraseña de una cuenta
     * */
    public String getContrasena() {
        return contrasena;
    }

    /**
     *
     * */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "id=" + id +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
}
