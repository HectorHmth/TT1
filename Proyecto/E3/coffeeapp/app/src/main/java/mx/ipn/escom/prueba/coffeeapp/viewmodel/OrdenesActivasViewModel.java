package mx.ipn.escom.prueba.coffeeapp.viewmodel;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.MutableLiveData;
import mx.ipn.escom.prueba.coffeeapp.business.LocalDetailBusiness;
import mx.ipn.escom.prueba.coffeeapp.business.OrdenesBusiness;
import mx.ipn.escom.prueba.coffeeapp.entities.Local;
import mx.ipn.escom.prueba.coffeeapp.entities.Orden;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.NoExisteInformacionException;

public class OrdenesActivasViewModel {
    public MutableLiveData<Integer> erroGlobal;
    private OrdenesBusiness ordenesBusiness;
    private LocalDetailBusiness localDetailBusiness;
    private Integer progress;
    private MutableLiveData<List<Orden>> ordenesActivas;

    public OrdenesActivasViewModel(){
        this.ordenesActivas = new MutableLiveData<>();
    }

    public OrdenesBusiness getOrdenesBusiness() {
        return ordenesBusiness;
    }

    public void setOrdenesBusiness(Application application) {
        this.ordenesBusiness = new OrdenesBusiness(application);
        this.localDetailBusiness = new LocalDetailBusiness(application);
    }

    public MutableLiveData<List<Orden>> getOrdenesActivas() {
        return ordenesActivas;
    }

    public ArrayList<OrdenActivaViewModel> getPedidosActivos(Integer idCuenta){
        try{
            Log.d("--->", "ENTRE AL GET DE PEDIDOS ACTIVOS");
            List<Orden> ordenesActivasObtenidas = ordenesBusiness.getOrdenesActivas(idCuenta);
            return bindResult(ordenesActivasObtenidas);
        }
        catch(Exception e){
            e.printStackTrace();
            Log.d("--->","ERROR");
        }
        return null;
    }

    private ArrayList<OrdenActivaViewModel> bindResult(List<Orden> ordenesActivasObtenidas)
            throws InterruptedException,
            ExecutionException,
            NoExisteInformacionException,
            ConexionNoDisponibleException {

        ArrayList<OrdenActivaViewModel> ordenesActivasViewModels = new ArrayList<>();
        Log.d("--->bindResult", "Entre"+ordenesActivasObtenidas.size());

        for(Orden orden: ordenesActivasObtenidas){
            OrdenActivaViewModel ordenActivaViewModel = new OrdenActivaViewModel();
            ordenActivaViewModel.ultimaActualizacion = "Hora de entrega estimada: " + orden.getFechaUltimaActualizacion().toString();
            ordenActivaViewModel.idOrden = orden.getId();

            Local local = localDetailBusiness.getLocalById(
                    orden.getProductosDeLocalEnOrden().get(0).getIdLocal()
            );

            ordenActivaViewModel.nombreLocal = local.getNombreLocal();
            ordenActivaViewModel.estadoOrden = orden.getEstadoOrden().getNombreEstado();
            ordenesActivasViewModels.add(ordenActivaViewModel);
            Log.d("--->bindResult", "-" + ordenActivaViewModel.nombreLocal
                    + "-" + ordenActivaViewModel.estadoOrden
                    + "-" + ordenActivaViewModel.ultimaActualizacion
                    + "-" + ordenActivaViewModel.idOrden
            );
        }
        return ordenesActivasViewModels;
    }
}
