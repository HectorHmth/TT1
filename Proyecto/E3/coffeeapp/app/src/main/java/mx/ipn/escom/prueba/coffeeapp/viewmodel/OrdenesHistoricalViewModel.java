package mx.ipn.escom.prueba.coffeeapp.viewmodel;

import android.app.Application;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.MutableLiveData;
import mx.ipn.escom.prueba.coffeeapp.business.LocalDetailBusiness;
import mx.ipn.escom.prueba.coffeeapp.business.OrdenesBusiness;
import mx.ipn.escom.prueba.coffeeapp.entities.Local;
import mx.ipn.escom.prueba.coffeeapp.entities.Orden;
import mx.ipn.escom.prueba.coffeeapp.entities.ProductoLocal;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.NoExisteInformacionException;

public class OrdenesHistoricalViewModel {
    public MutableLiveData<Integer> errorGlobal;
    private OrdenesBusiness ordenesBusiness;
    private LocalDetailBusiness localDetailBusiness;
    private Integer progress;
    private MutableLiveData<List<Orden>> ordenes;

    public OrdenesHistoricalViewModel(){
        this.ordenes = new MutableLiveData<>();
    }

    public OrdenesBusiness getOrdenesBusiness() {
        return ordenesBusiness;
    }

    public void setOrdenesBusiness(Application application) {
        this.ordenesBusiness = new OrdenesBusiness(application);
        this.localDetailBusiness = new LocalDetailBusiness(application);
    }

    public MutableLiveData<List<Orden>> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(MutableLiveData<List<Orden>> ordenes) {
        this.ordenes = ordenes;
    }

    public ArrayList<OrdenHistoricalViewModel> getHistorialPedidos(Integer idCuenta){
        try{
            List<Orden> ordenesObtenidas = ordenesBusiness.getOrdenes(idCuenta);
            return bindResult(ordenesObtenidas);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<OrdenHistoricalViewModel> bindResult(List<Orden> ordenesObtenidas)
            throws InterruptedException,
            ExecutionException,
            NoExisteInformacionException,
            ConexionNoDisponibleException {

        ArrayList<OrdenHistoricalViewModel> ordenesViewModels = new ArrayList<>();

        SimpleDateFormat dateFormatter = new SimpleDateFormat(
                "dd MMM yyyy"
        );

        for(Orden orden: ordenesObtenidas){
            OrdenHistoricalViewModel ordenViewModel = new OrdenHistoricalViewModel();
            ordenViewModel.fechaOrden = "Pedido hecho: " + dateFormatter.format(orden.getFecha());
            ordenViewModel.idOrden = orden.getId();

            Local local = localDetailBusiness.getLocalById(
                    orden.getProductosDeLocalEnOrden().get(0).getIdLocal()
            );

            ordenViewModel.nombreLocal = local.getNombreLocal();
            ordenViewModel.estadoOrden = orden.getEstadoOrden().getNombreEstado();
            ordenesViewModels.add(ordenViewModel);
        }
        return ordenesViewModels;
    }
}
