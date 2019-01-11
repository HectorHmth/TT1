package mx.ipn.escom.prueba.coffeeapp.entities;

import android.content.Intent;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.text.WordUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import androidx.room.util.StringUtil;
import mx.ipn.escom.prueba.coffeeapp.constants.Constants;
import mx.ipn.escom.prueba.coffeeapp.converters.DateConverter;

@Entity(tableName = "tml03_local")
@TypeConverters({DateConverter.class})
public class Local {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_local")
    private Integer idLocal;

    @ColumnInfo(name = "nombre_local")
    private String nombreLocal;

    @ColumnInfo(name = "tm_inicio_local")
    private Date tmInicio;

    @ColumnInfo(name = "tm_fin_local")
    private Date tmFin;

    @ColumnInfo(name = "nu_rating")
    private Double nuRating;

    @ColumnInfo(name = "nu_longitud")
    private Double nuLongitud;

    @ColumnInfo(name = "nu_latitud")
    private Double nuLatitud;

    private String direccion;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public Date getTmInicio() {
        return tmInicio;
    }

    public void setTmInicio(Date tmInicio) {
        this.tmInicio = tmInicio;
    }

    public Date getTmFin() {
        return tmFin;
    }

    public void setTmFin(Date tmFin) {
        this.tmFin = tmFin;
    }

    public Double getNuRating() {
        return nuRating;
    }

    public void setNuRating(Double nuRating) {
        this.nuRating = nuRating;
    }

    public String getHorario(){
        StringBuilder stringBuilder = new StringBuilder();
        if (tmInicio == null&& tmFin == null)
            return stringBuilder.append("ERROR_ERROR").toString();
        stringBuilder.append(DateFormat.getTimeInstance(DateFormat.SHORT).format(tmInicio));
        stringBuilder.append("-");
        stringBuilder.append(DateFormat.getTimeInstance(DateFormat.SHORT).format(tmFin));
        return stringBuilder.toString();
    }

    public String getHorarioCompleto(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(WordUtils
                .capitalize(new SimpleDateFormat("EEEE",Locale.getDefault())
                        .format(tmInicio)));
        stringBuilder.append(" a ");
        stringBuilder.append(WordUtils
                .capitalize(new SimpleDateFormat("EEEE",Locale.getDefault())
                        .format(tmFin)));
        stringBuilder.append(" de ");
        stringBuilder.append(DateFormat.getTimeInstance(DateFormat.SHORT)
                .format(tmInicio));
        stringBuilder.append(" - ");
        stringBuilder.append(DateFormat.getTimeInstance(DateFormat.SHORT)
                .format(tmFin));
        return stringBuilder.toString();
    }

    public String getNuRatingString(){
        return String.valueOf(nuRating);
    }

    public Double getNuLongitud() {
        return nuLongitud;
    }

    public void setNuLongitud(Double nuLongitud) {
        this.nuLongitud = nuLongitud;
    }

    public Double getNuLatitud() {
        return nuLatitud;
    }

    public void setNuLatitud(Double nuLatitud) {
        this.nuLatitud = nuLatitud;
    }

    public String getEstado(){
       Date date = new Date();
       String horasInicio = new SimpleDateFormat("HH",Locale.getDefault())
               .format(tmInicio);
       String minutosInicio = new SimpleDateFormat("mm",Locale.getDefault())
               .format(tmInicio);

       String horasFin = new SimpleDateFormat("HH",Locale.getDefault())
               .format(tmFin);
       String minutosFin = new SimpleDateFormat("mm", Locale.getDefault())
               .format(tmFin);

       String horasActual =  new SimpleDateFormat("HH",Locale.getDefault())
               .format(date);
       String minutosActual = new SimpleDateFormat("mm", Locale.getDefault())
               .format(date);

        if (Integer.valueOf(horasActual) > Integer.valueOf(horasInicio)
                && Integer.valueOf(horasActual) < Integer.valueOf(horasFin)){
            if (Integer.valueOf(minutosActual) > Integer.valueOf(minutosInicio)
                    && Integer.valueOf(minutosActual) < Integer.valueOf(minutosFin)){
                return Constants.LOCAL_ABIERTO;
            }else{
                return Constants.LOCAL_CERRADO;
            }
        }else{
            return Constants.LOCAL_CERRADO;
        }
    }
}
