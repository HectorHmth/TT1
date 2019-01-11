package mx.ipn.escom.prueba.coffeeapp.entities;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tml01_persona")
public class Persona {
    @SerializedName("idPersona")
    @Expose
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_persona")
    public Integer id_persona;

    @SerializedName("nombrePersona")
    @Expose
    @ColumnInfo(name = "nb_persona")
    private String nombrePersona;

    @SerializedName("primerApellido")
    @Expose
    @ColumnInfo(name = "tx_primer_apellido")
    private String primerApellido;

    @SerializedName("segundoApellido")
    @Expose
    @ColumnInfo(name = "tx_segundo_apellido")
    private String segundoApellido;

    @SerializedName("cuenta")
    @Expose
    @Embedded
    private Cuenta cuenta;

    public Integer getId_persona() {
        return id_persona;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id_persona+
                ", nombrePersona='" + nombrePersona + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", cuenta=" + cuenta +
                '}';
    }
}
