package mx.ipn.escom.prueba.coffeeapp.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.RatingBar;

import java.util.Date;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;
import mx.ipn.escom.prueba.coffeeapp.business.LocalDetailBusiness;
import mx.ipn.escom.prueba.coffeeapp.entities.Local;

public class LocalDetailViewModel extends ViewModel {

    public String nombreLocal;
    public String estadoActualLocal;
    public String fullHorario;
    public String direccion;
    public Float nuRating;

    private LocalDetailBusiness localDetailBusiness;

    public LocalDetailViewModel(){ }


    public void setLocalDetailBusiness(Application application) {
        this.localDetailBusiness = new LocalDetailBusiness(application);
    }


    public Local getLocal(String nombreLocal){
        try{
            Local local = localDetailBusiness.getLocal(nombreLocal);
            if (local != null){
                this.nombreLocal = local.getNombreLocal();
                this.estadoActualLocal = local.getEstado();
                this.fullHorario = local.getHorarioCompleto();
                this.nuRating = local.getNuRating().floatValue();
                this.direccion = local.getDireccion();
                return local;
            }else{
                Log.d("---->","ERROR_GENERAL");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
