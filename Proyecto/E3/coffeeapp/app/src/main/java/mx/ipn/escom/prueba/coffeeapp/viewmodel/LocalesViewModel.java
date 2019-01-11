package mx.ipn.escom.prueba.coffeeapp.viewmodel;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;
import mx.ipn.escom.prueba.coffeeapp.business.LocalesBusiness;
import mx.ipn.escom.prueba.coffeeapp.entities.Local;

public class LocalesViewModel extends ViewModel {
    private MutableLiveData<Integer> errorGlobal;
    private LocalesBusiness localesBusiness;
    private Integer progress;
    private MutableLiveData<List<Local>> locales;


    public LocalesViewModel(){
        this.locales = new MutableLiveData<>();
    }

    public LocalesBusiness getLocalesBusiness() {
        return localesBusiness;
    }

    public void setLocalesBusiness(Application application) {
        this.localesBusiness = new LocalesBusiness(application);
    }

    public MutableLiveData<List<Local>> getLocales() {
        return locales;
    }

    public void setLocales(MutableLiveData<List<Local>> locales) {
        this.locales = locales;
    }

    public ArrayList<LocalViewModel> getListaDeLocales(){
        try {
            List<Local> localesObtenidos = localesBusiness.getLocales(progress);
            return bindResult(localesObtenidos);
        }catch (Exception e){
            e.printStackTrace();
            Log.d("--->","ERROR");
        }
        return null;
    }

    private ArrayList<LocalViewModel> bindResult(List<Local> listaDeLocales){
        ArrayList<LocalViewModel> localViewModels = new ArrayList<>();
        Log.d("--->bindResult", "Entre"+listaDeLocales.size());
        for (Local local:listaDeLocales){
            Log.d("--->", "-" + local.getNombreLocal() + "-" + local.getHorario());
            LocalViewModel localViewModel = new LocalViewModel();
            localViewModel.nombreLocal = local.getNombreLocal();
            localViewModel.horarioLocal = local.getHorario();
            localViewModel.calificacion = local.getNuRatingString();
            Log.d("--->", "-" + localViewModel.nombreLocal + "-" + localViewModel.horarioLocal);
            localViewModels.add(localViewModel);
        }
        return localViewModels;
    }


}
