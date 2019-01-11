package mx.ipn.escom.prueba.coffeeapp.viewmodel;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import mx.ipn.escom.prueba.coffeeapp.business.CarritoBusiness;
import mx.ipn.escom.prueba.coffeeapp.constants.Constants;
import mx.ipn.escom.prueba.coffeeapp.constants.NetWorkConstants;
import mx.ipn.escom.prueba.coffeeapp.entities.FormaPago;
import mx.ipn.escom.prueba.coffeeapp.entities.Local;
import mx.ipn.escom.prueba.coffeeapp.entities.Orden;
import mx.ipn.escom.prueba.coffeeapp.entities.OrdenProducto;
import mx.ipn.escom.prueba.coffeeapp.entities.PostResponse;
import mx.ipn.escom.prueba.coffeeapp.exception.NoExisteOrdenSinConfirmarException;
import mx.ipn.escom.prueba.coffeeapp.exception.ProductoExisteEnOrdenException;

public class CarritoViewModel extends ViewModel {

    private MutableLiveData<Boolean> togglePedido;
    private MutableLiveData<Boolean> toggleParaCuando;
    private MutableLiveData<Boolean> toggleFormaDePago;
    private Integer paraCuando;
    private MutableLiveData<Integer> tipoFormaPagoImagen;
    private MutableLiveData<Integer> errorGeneral;
    private List<OrdenProducto> listaProductosEnOrden;
    private Integer idCuenta;
    private MutableLiveData<Boolean> pedidoConfirmado;

    public Local local;
    private FormaPago formaPago;

    private CarritoBusiness carritoBusiness;
    private Double totalOrden;

    public String totalOrdenString;
    public String tipoFormaPago;
    public MutableLiveData<String> totalOrdenMutable;

    /**
     * Constructor
    **/
    public CarritoViewModel(){
        togglePedido = new MutableLiveData<>();
        togglePedido.setValue(false);
        toggleParaCuando = new MutableLiveData<>();
        toggleParaCuando.setValue(false);
        toggleFormaDePago = new MutableLiveData<>();
        toggleFormaDePago.setValue(false);
        paraCuando = 0;
        local = new Local();
        totalOrden = 0.00;
        errorGeneral = new MutableLiveData<>();
        tipoFormaPagoImagen = new MutableLiveData<>();
        pedidoConfirmado = new MutableLiveData<>();
        pedidoConfirmado.setValue(true);
    }

    public Integer getParaCuando(){
        return paraCuando;
    }

    public void setParaCuando(Integer paraCuando){
        this.paraCuando = paraCuando;
    }

    public LiveData<Boolean> getPedidoConfirmado(){
        if (pedidoConfirmado == null)
            pedidoConfirmado = new MutableLiveData<>();
        return pedidoConfirmado;
    }


    public LiveData<Integer> getTipoFormaPagoImagen() {
        if (tipoFormaPagoImagen == null)
            tipoFormaPagoImagen = new MutableLiveData<>();
        return tipoFormaPagoImagen;
    }

    public void setCarritoBusiness(Application application){
        this.carritoBusiness = new CarritoBusiness(application);
    }

    public LiveData<Boolean> getTogglePedido() {
        if (togglePedido == null)
            togglePedido = new MutableLiveData<>();
        return togglePedido;
    }

    public LiveData<Boolean> getToggleParaCuando(){
        if (toggleParaCuando == null)
            toggleParaCuando = new MutableLiveData<>();
        return toggleParaCuando;
    }

    public LiveData<Boolean> getToggleFormaDePago(){
        if (toggleFormaDePago == null)
            toggleFormaDePago = new MutableLiveData<>();
        return toggleFormaDePago;
    }


    /**
     * Trigger para activar la lista de los pedidos
     * @return true
     * */
    public boolean displayPedido(){
        if (togglePedido == null){
            togglePedido = new MutableLiveData<>();
            togglePedido.setValue(false);
        }else{
            togglePedido.setValue(!togglePedido.getValue());
        }
        return true;
    }


    /**
     * Trigger para activar el radioGroup para seleccionar la hora del pedido
     * @return true
     * */
    public boolean displayParaCuando(){
        if (toggleParaCuando == null){
            toggleParaCuando = new MutableLiveData<>();
            toggleParaCuando.setValue(false);
        }else{
            toggleParaCuando.setValue(!toggleParaCuando.getValue());
        }
        return true;
    }


    /**
     * Trigger para activar la información de la forma de pago
     * @return true
     * */
    public boolean displayFormaPago(){
        if (toggleFormaDePago == null){
            toggleFormaDePago = new MutableLiveData<>();
            toggleFormaDePago.setValue(false);
        }else{
            toggleFormaDePago.setValue(!toggleFormaDePago.getValue());
        }
        return true;
    }

    public LiveData<Integer> getErrorGeneral(){
        if (errorGeneral == null)
            errorGeneral = new MutableLiveData<>();
        return errorGeneral;
    }

    /**
     *  Obtiene la información del local al que pertenecen los productos de un cliente
     *
     * @param idCuenta Es el identificador de la cuenta del cliente que ha iniciado sesión
     * @return true
     * */
    public boolean getLocalOfPedido(Integer idCuenta){
        try{
            this.idCuenta = idCuenta;
            this.local = carritoBusiness.getLocalByOrdenSinConfirmar(idCuenta);
            if (this.local == null){
                local.setNombreLocal("ERROR_ERROR");
                local.setTmInicio(new Date());
                local.setTmFin(new Date());
            }
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
            errorGeneral.setValue(NetWorkConstants.CONEXION_NO_DISPONIBLE);
        }catch (NoExisteOrdenSinConfirmarException e){
            e.printStackTrace();
            errorGeneral.setValue(Constants.NO_EXISTE_ORDEN_SIN_CONFIRMAR);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Obtiene la lista de productos de una orden de un cliente que se van a mostrar en pantalla
     * @param idCuenta Es el identificador de la cuenta del cliente
     * return Regresa una lista de tipo OrdenProductoViewModel para el adapter
     * */
    public ArrayList<OrdenProductoViewModel> getListaProductosEnOrden(Integer idCuenta){
        try{
            listaProductosEnOrden = carritoBusiness.getOrdenProductosSinConfirmar(idCuenta);
            return bindResult(listaProductosEnOrden);
        }catch (ExecutionException | InterruptedException e){
            e.printStackTrace();
            errorGeneral.setValue(NetWorkConstants.CONEXION_NO_DISPONIBLE);
        }catch (NoExisteOrdenSinConfirmarException e){
            e.printStackTrace();
            errorGeneral.setValue(Constants.NO_EXISTE_ORDEN_SIN_CONFIRMAR);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Realiza el dataBinding entre el viewModel y la lista obtenida de la base de datos
     * y asigna la forma de pago de la orden
     * @param ordenProductos Es la lista que contiene los productos que están en una orden
     * @return Regresa una lista de viewModels de ordenes para asignar al adapter
     * */
    private ArrayList<OrdenProductoViewModel> bindResult(List<OrdenProducto> ordenProductos){
        ArrayList<OrdenProductoViewModel> ordenProductoViewModels = new ArrayList<>();
        if (!ordenProductos.isEmpty()){
            setFormaPago(ordenProductos.get(0).orden.getFormaDePago());
            Log.d("--->bindResult", "Entre"+ordenProductos.size());
            for (OrdenProducto ordenProductoEnLista : ordenProductos){
                OrdenProductoViewModel ordenProductoViewModel = new OrdenProductoViewModel();
                ordenProductoViewModel.ordenProducto = ordenProductoEnLista;
                ordenProductoViewModels.add(ordenProductoViewModel);
            }
        }else {
            errorGeneral.setValue(Constants.NO_EXISTE_ORDEN_SIN_CONFIRMAR);
        }
        updateTotalOrden();
        return ordenProductoViewModels;
    }

    /**
     * Asigna la forma de pago de una orden al atributo de esta clase
     * @param formaPago Es la forma de pago de una orden
     * */
    public void setFormaPago(FormaPago formaPago){
        this.formaPago = formaPago;
        tipoFormaPago = formaPago.getTipo().getNombre();
        if (tipoFormaPago.equals("Efectivo")){
            tipoFormaPagoImagen.setValue(Constants.FORMA_PAGO_EFECTIVO);
        }else if (tipoFormaPago.equals("PayPal")){
            tipoFormaPagoImagen.setValue(Constants.FORMA_PAGO_PAYPAL);
        }else {
            tipoFormaPagoImagen.setValue(Constants.FORMA_PAGO_TARJETA);
        }
    }

    public FormaPago getFormaPago(){
       return formaPago;
    }

    /**
     * Agrega 1 a la cantidad de un producto en una orden, verificando su disponibilidad en la base de datos.
     * Actualiza el total de la orden
     * @param position Es la la fila del producto en el recycler view que se va a actualizare
     * @return Regresa true si se pudo actualizar la cantidad de un producto en la orden
     * */
    public boolean onAddCantidad(Integer position){
        OrdenProducto ordenProducto = this.listaProductosEnOrden.get(position);
        try {
            Boolean bool = carritoBusiness.onAddCantidad(ordenProducto.idLocal, ordenProducto.idProductoLocal, ordenProducto.cantidad);
            if (bool){
                ordenProducto.cantidad++;
                ordenProducto.productoLocal.setCantidad(ordenProducto.cantidad);
                PostResponse postResponse = carritoBusiness.addCantidad(ordenProducto.productoLocal,"",this.idCuenta);
                if (postResponse.getId().equals(Constants.PRODUCTO_AGREGADO_EXITOSAMENTE)){
                    updateTotalOrden();
                    return true;
                }
                else{
                    errorGeneral.setValue(Constants.PRODUCTO_EXISTE_EN_LA_ORDEN);
                    return false;
                }
            }else{
                errorGeneral.setValue(Constants.PRODUCTO_AGOTADO);
                return false;
            }
        }catch (ProductoExisteEnOrdenException e){
            e.printStackTrace();
            errorGeneral.setValue(Constants.PRODUCTO_EXISTE_EN_LA_ORDEN);
            return false;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Quita 1 a la cantidad de un producto
     * */
    public boolean onSubstractCantidad(Integer position){
        OrdenProducto ordenProducto = this.listaProductosEnOrden.get(position);
        if (ordenProducto.cantidad - 1 > 0){
            ordenProducto.cantidad--;
            try{
                ordenProducto.productoLocal.setCantidad(ordenProducto.cantidad);
                PostResponse postResponse = carritoBusiness.substractCantidad(ordenProducto.productoLocal,"",this.idCuenta);
                updateTotalOrden();
                return true;
            }catch(Exception e){
                return false;
            }
        }else{
            errorGeneral.setValue(Constants.PRODUCTO_CERO);
            return false;
        }
    }


    /**
     * Elimina un producto de la orden
     * */
    public Boolean onDeleteProductoDeOrden(Integer position){
        OrdenProducto ordenProducto = this.listaProductosEnOrden.get(position);
        try{
            PostResponse postResponse = carritoBusiness.deleteProductoDeOrden(ordenProducto, ordenProducto.productoLocal, this.idCuenta);
            if (postResponse.getId().equals(Constants.PRODUCTO_ELIMINADO_CORRECTAMENTE)){
                this.listaProductosEnOrden.remove(ordenProducto);
                updateTotalOrden();
                errorGeneral.setValue(Constants.PRODUCTO_ELIMINADO_CORRECTAMENTE);
                return true;
            }else{
                errorGeneral.setValue(Constants.NO_SE_PUEDE_ELIMINAR_PRODUCTO);
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Finalizar compra
     */
    public boolean finalizarCompra(){
        Log.d("--->","Se va a finalizar la compra");
        try{
            PostResponse postResponse;
            if (paraCuando == Constants.LO_ANTES_POSIBLE)
                postResponse = carritoBusiness.finalizarCompraNoProgramada(listaProductosEnOrden.get(0).idOrden,idCuenta);
            else
                postResponse = carritoBusiness.finalizarCompraNoProgramada(listaProductosEnOrden.get(0).idOrden,idCuenta);//Cambiar para el caso en que sea progranmada
            if (postResponse.getId().equals(Constants.ORDEN_ENVIADA)){
                errorGeneral.setValue(Constants.ORDEN_ENVIADA);
                listaProductosEnOrden.clear();
                updateTotalOrden();
                return true;
            }else{
                errorGeneral.setValue(Constants.ERROR_FINALIZAR_ORDEN);
                return false;
            }
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            errorGeneral.setValue(Constants.ORDEN_VACIA);
            return false;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Actualiza el precio total de la orden
     * */
    private void updateTotalOrden(){
        totalOrden = 0.0;
        for (OrdenProducto ordenProducto : listaProductosEnOrden){
            totalOrden += ordenProducto.cantidad * ordenProducto.productoLocal.getPrecioVenta();
        }
        totalOrdenString = "$ " + totalOrden;
        if (totalOrdenMutable == null){
            totalOrdenMutable = new MutableLiveData<>();
        }
        totalOrdenMutable.setValue(totalOrdenString);
    }




}
