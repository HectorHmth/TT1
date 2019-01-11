package mx.ipn.escom.prueba.coffeeapp.viewmodel;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import mx.ipn.escom.prueba.coffeeapp.business.PromocionesBusiness;
import mx.ipn.escom.prueba.coffeeapp.entities.ProductoLocal;

public class PromocionesViewModel extends ViewModel {
    public MutableLiveData<Integer> errorGlobal;
    private PromocionesBusiness promocionesBusiness;
    private Integer progress;
    private MutableLiveData<List<ProductoLocal>> promociones;

    public PromocionesViewModel(){
        this.promociones = new MutableLiveData<>();
    }

    public PromocionesBusiness getPromocionesBusiness() {
        return promocionesBusiness;
    }

    public void setPromocionesBusiness(Application application){
        this.promocionesBusiness = new PromocionesBusiness(application);
    }

    public MutableLiveData<List<ProductoLocal>> getPromociones() {
        return promociones;
    }

    public void setPromociones(MutableLiveData<List<ProductoLocal>> promociones) {
        this.promociones = promociones;
    }

    public ArrayList<PromocionViewModel> getListaDePromociones(String nombreLocal){
        try{
            List<ProductoLocal> promocionesObtenidas = promocionesBusiness.getPromociones(nombreLocal);
            return bindResult(promocionesObtenidas);
        }
        catch(Exception e){
            e.printStackTrace();
            Log.d("--->","ERROR");
        }
        return null;
    }

    private ArrayList<PromocionViewModel> bindResult(List<ProductoLocal> listaDePromociones) {
        ArrayList<PromocionViewModel> promocionesViewModels = new ArrayList<>();
        Log.d("--->bindResult", "Entre"+listaDePromociones.size());

        for(ProductoLocal promocion: listaDePromociones){
            Log.d("--->", "-" + promocion.getProducto().getNombreProducto()
                    + "-" + promocion.getProducto().getDescripcion()
                    + "-" + promocion.getProducto().getNuRating());
            PromocionViewModel promocionViewModel = new PromocionViewModel();
            promocionViewModel.nombrePromocion = promocion.getProducto().getNombreProducto();
            promocionViewModel.descripcionPromocion = promocion.getProducto().getDescripcion();
            promocionViewModel.calificacionPromocion = String.valueOf(promocion.getProducto().getNuRating());
            promocionViewModel.precioPromocion = "$" + String.valueOf(promocion.getPrecioVenta());
            promocionViewModel.idProducto = promocion.getIdProducto();
            promocionViewModel.idLocal = promocion.getIdLocal();

            promocionesViewModels.add(promocionViewModel);
        }

        return promocionesViewModels;
    }
}