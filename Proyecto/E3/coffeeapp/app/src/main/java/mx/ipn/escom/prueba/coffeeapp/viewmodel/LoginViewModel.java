package mx.ipn.escom.prueba.coffeeapp.viewmodel;

import android.app.Application;
import android.util.Log;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import mx.ipn.escom.prueba.coffeeapp.business.LoginBusiness;
import mx.ipn.escom.prueba.coffeeapp.constants.Constants;
import mx.ipn.escom.prueba.coffeeapp.constants.ErroresGeneralesConstants;
import mx.ipn.escom.prueba.coffeeapp.constants.FieldErrorContants;
import mx.ipn.escom.prueba.coffeeapp.constants.NetWorkConstants;
import mx.ipn.escom.prueba.coffeeapp.entities.Cuenta;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.CuentaNotFoundException;

public class LoginViewModel extends ViewModel {
   public MutableLiveData<String> nombreUsuario = new MutableLiveData<>();
   public MutableLiveData<String> contrasena = new MutableLiveData<>();
   public MutableLiveData<Boolean> recuerdame = new MutableLiveData<>();

   public MutableLiveData<Integer> errorUsuario = new MutableLiveData<>();
   public MutableLiveData<Integer> errorContrasena = new MutableLiveData<>();
   public MutableLiveData<Integer>  errorGeneral = new MutableLiveData<>();

   private LoginBusiness loginBusiness;
   private MutableLiveData<Cuenta> cuentaMutableLiveData = new MutableLiveData<>();


    public LoginViewModel(){
        nombreUsuario = new MutableLiveData<>();
        contrasena = new MutableLiveData<>();
        recuerdame = new MutableLiveData<>();
        errorUsuario = new MutableLiveData<>();
        errorContrasena = new MutableLiveData<>();
        errorGeneral = new MutableLiveData<>();
    }

    public void setLoginBusiness(Application application) {
        this.loginBusiness = new LoginBusiness(application);
    }

    public LiveData<Integer> getErrorContrasena() {
        if (errorContrasena == null)
            errorContrasena = new MutableLiveData<>();
        return errorContrasena;
    }

    public LiveData<Integer> getErrorUsuario() {
        if (errorUsuario == null)
            errorUsuario = new MutableLiveData<>();
        return errorUsuario;
    }


    public LiveData<Cuenta> getCuenta(){
        if (cuentaMutableLiveData == null)
            cuentaMutableLiveData = new MutableLiveData<>();
        return cuentaMutableLiveData;
    }

    public boolean onLogin(){
        if(validateUi()){
            try{
                Cuenta cuenta = loginBusiness.onLogin(nombreUsuario.getValue(), contrasena.getValue());
                if (cuentaMutableLiveData == null)
                    cuentaMutableLiveData = new MutableLiveData<>();
                cuentaMutableLiveData.setValue(cuenta);
                errorGeneral.setValue(FieldErrorContants.SIN_ERRORES);
            }catch (SocketTimeoutException | ExecutionException | InterruptedException e){
                errorGeneral.setValue(NetWorkConstants.TIEMPO_LIMITE);
            }catch (ConexionNoDisponibleException | ConnectException e){
                errorGeneral.setValue(NetWorkConstants.CONEXION_NO_DISPONIBLE);
            }catch (CuentaNotFoundException e){
                errorGeneral.setValue(Constants.CUENTA_NOT_FOUND);
            }catch (Exception e){
                Log.d("--->",e.toString());
            }
        }else{
            errorGeneral.setValue(ErroresGeneralesConstants.LOGIN_INVALIDO);
        }
        return true;
    }

    private boolean validateUi(){
        Boolean validated = true;
        if (nombreUsuario.getValue() == null){
            validated = false;
            errorUsuario.setValue(FieldErrorContants.CAMPO_VACIO);
        }else if (nombreUsuario.getValue().equals("")){
            validated = false;
            errorUsuario.setValue(FieldErrorContants.CAMPO_VACIO);
        }

        if(contrasena.getValue() == null){
            validated = false;
            errorContrasena.setValue(FieldErrorContants.CAMPO_VACIO);
        }else if(contrasena.getValue().equals("")){
            validated = false;
            errorContrasena.setValue(FieldErrorContants.CAMPO_VACIO);
        }
        return validated;
    }

}
