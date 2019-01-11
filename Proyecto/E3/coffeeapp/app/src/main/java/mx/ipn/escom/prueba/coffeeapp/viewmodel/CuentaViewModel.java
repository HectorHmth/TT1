package mx.ipn.escom.prueba.coffeeapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import mx.ipn.escom.prueba.coffeeapp.business.CuentaBusiness;
import mx.ipn.escom.prueba.coffeeapp.constants.Constants;
import mx.ipn.escom.prueba.coffeeapp.constants.NetWorkConstants;
import mx.ipn.escom.prueba.coffeeapp.entities.Cuenta;
import mx.ipn.escom.prueba.coffeeapp.entities.Persona;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;

public class CuentaViewModel extends ViewModel {
    public Persona persona;
    public String nombrePersona;
    private CuentaBusiness cuentaBusiness;
    private MutableLiveData<Integer> errorGeneral;

    public CuentaViewModel(){
        persona = new Persona();
        errorGeneral = new MutableLiveData<>();
    }

    public void setCuentaBusiness(Application application){
        this.cuentaBusiness = new CuentaBusiness(application);
    }


    public LiveData<Integer> getErrorGeneral(){
        if(errorGeneral == null){
            errorGeneral = new MutableLiveData<>();
        }
        return errorGeneral;
    }

    public void getPersona(Integer idCuenta){
        try{
            this.persona = cuentaBusiness.getPersona(idCuenta);
            if(persona != null){
                Log.d("--->","EEE");
                nombrePersona = persona.getNombrePersona() + " "
                            + persona.getPrimerApellido() + " "
                            + persona.getSegundoApellido();
            }else{
                errorGeneral.setValue(Constants.CUENTA_NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            errorGeneral.setValue(NetWorkConstants.CONEXION_NO_DISPONIBLE);
        }

    }


}
