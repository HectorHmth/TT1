package mx.ipn.escom.prueba.coffeeapp.viewmodel;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import mx.ipn.escom.prueba.coffeeapp.business.ProductoDetailBusiness;
import mx.ipn.escom.prueba.coffeeapp.constants.Constants;
import mx.ipn.escom.prueba.coffeeapp.constants.NetWorkConstants;
import mx.ipn.escom.prueba.coffeeapp.entities.PostResponse;
import mx.ipn.escom.prueba.coffeeapp.entities.ProductoLocal;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.ProductoAgotadoException;
import mx.ipn.escom.prueba.coffeeapp.exception.ProductoExisteEnOrdenException;
import mx.ipn.escom.prueba.coffeeapp.exception.ProductoLocalNoEncontradoException;
import mx.ipn.escom.prueba.coffeeapp.exception.ProductoNoAgregadoException;

public class ProductoDetailViewModel extends ViewModel {

    public ProductoLocal productoLocal;
    public String precioVenta;
    public Float nuRating;
    public MutableLiveData<String> notaAlCocinero;
    public MutableLiveData<Integer> errorNotalAlCocinero;
    public MutableLiveData<String> cantidadText;
    public MutableLiveData<String> errorGeneralText;
    public MutableLiveData<Integer> errorGeneral;
    private Integer cantidad;
    private Integer idCuenta;
    private ProductoDetailBusiness productoDetailBusiness;

    public ProductoDetailViewModel(){
        this.nuRating = Float.valueOf(0);
        notaAlCocinero = new MutableLiveData<>();
        errorNotalAlCocinero = new MutableLiveData<>();
        errorGeneral = new MutableLiveData<>();
        errorGeneralText = new MutableLiveData<>();
    }


    public void setIdCuenta(Integer idCuenta){
        this.idCuenta = idCuenta;
    }

    public void setProductoDetailBusiness(Application application){
        productoDetailBusiness = new ProductoDetailBusiness(application);
        cantidadText = new MutableLiveData<>();
        cantidadText.setValue("1");
    }

    public ProductoLocal getProductoLocal() {
        return productoLocal;
    }

    public LiveData<String> getCantidadText(){
        if (cantidadText == null)
            cantidadText = new MutableLiveData<>();
        return cantidadText;
    }

    public LiveData<Integer> getErrorNotaAlCocinero(){
        if (errorNotalAlCocinero == null)
            errorNotalAlCocinero = new MutableLiveData<>();
        return errorNotalAlCocinero;
    }

    public LiveData<Integer> getErrorGeneral(){
        if (errorGeneral == null)
            errorGeneral = new MutableLiveData<>();
        return errorGeneral;
    }


    public boolean addCantidad(){
        if (cantidadText == null){
            cantidadText = new MutableLiveData<>();
            cantidadText.setValue("1");
        }
        cantidad = Integer.valueOf(cantidadText.getValue());
        try{
            if (productoDetailBusiness.isProductoDisponible(productoLocal, cantidad)){
                cantidad++;
                cantidadText.setValue(cantidad.toString());
            }else{
                errorGeneral.setValue(Constants.PRODUCTOLOCAL_CANTIDAD_MAXIMA);
            }
        }catch (Exception e){
            e.printStackTrace();
            cantidadText.setValue("0");
        }
        return true;
    }

    public boolean subtractCantidad(){
        if (cantidadText == null){
            cantidadText = new MutableLiveData<>();
            cantidadText.setValue("1");
        }
        cantidad = Integer.valueOf(cantidadText.getValue());
        if (cantidad > 1){
            cantidad--;
            cantidadText.setValue(cantidad.toString());
        }else{
            errorGeneral.setValue(Constants.PRODUCTOLOCAL_CANTIDAD_MINIMA);
        }
        return true;
    }


    public boolean getProductoDetail(Integer idLocal, Integer idProducto){
        try{
            this.productoLocal = productoDetailBusiness.getProductoLocalById(idLocal,idProducto);
            this.nuRating = this.productoLocal.getProducto().getNuRating().floatValue();
            this.precioVenta = "$"+this.productoLocal.getPrecioVenta();
        }catch (ExecutionException
                | SocketTimeoutException
                | InterruptedException
                | ConexionNoDisponibleException e){
            e.printStackTrace();
            errorGeneral.setValue(NetWorkConstants.CONEXION_NO_DISPONIBLE);
        }catch (ProductoLocalNoEncontradoException e){
            e.printStackTrace();
            errorGeneral.setValue(Constants.PRODUCTOLOCAL_NO_EXISTE);
        }catch (Exception e){
            e.printStackTrace();
            Log.d("--->E",e.toString());
        }
        return true;
    }

    public boolean agregarProducto(){
        try{
            this.productoLocal.setCantidad(Integer.valueOf(cantidadText.getValue()));
           PostResponse postResponse = productoDetailBusiness.agregarProducto(this.productoLocal,notaAlCocinero.getValue(),this.idCuenta,null);
           Log.d("--->",".."+postResponse.getResultado());
           if (postResponse.getId().equals(Constants.PRODUCTO_AGREGADO_EXITOSAMENTE))
               errorGeneral.setValue(Constants.PRODUCTO_AGREGADO_EXITOSAMENTE);
           else
               errorGeneral.setValue(Constants.PRODUCTO_NO_AGREGADO);
        }catch (ConexionNoDisponibleException | InterruptedException | ExecutionException e){
            e.printStackTrace();
            errorGeneral.setValue(NetWorkConstants.CONEXION_NO_DISPONIBLE);
        }catch (ProductoAgotadoException e){
            e.printStackTrace();
            errorGeneral.setValue(Constants.PRODUCTO_AGOTADO);
        }catch (ProductoExisteEnOrdenException e){
            e.printStackTrace();
            errorGeneral.setValue(Constants.PRODUCTO_EXISTE_EN_LA_ORDEN);
        }catch (ProductoNoAgregadoException e){
            e.printStackTrace();
            errorGeneral.setValue(Constants.PRODUCTO_NO_AGREGADO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

}