package mx.ipn.escom.prueba.coffeeapp.viewmodel;

import android.app.Application;
import android.util.Log;

import java.lang.reflect.Field;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import mx.ipn.escom.prueba.coffeeapp.business.SignUpBusiness;
import mx.ipn.escom.prueba.coffeeapp.constants.Constants;
import mx.ipn.escom.prueba.coffeeapp.constants.ErroresGeneralesConstants;
import mx.ipn.escom.prueba.coffeeapp.constants.FieldErrorContants;
import mx.ipn.escom.prueba.coffeeapp.constants.NetWorkConstants;
import mx.ipn.escom.prueba.coffeeapp.entities.Persona;
import mx.ipn.escom.prueba.coffeeapp.entities.PostResponse;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.CuentaExisteException;
import mx.ipn.escom.prueba.coffeeapp.ui.UiUitls;

public class SignUpViewModel extends ViewModel {
    /*Cadenas*/
    public MutableLiveData<String> nombrePersona = new MutableLiveData<>();
    public MutableLiveData<String> primerApellido = new MutableLiveData<>();
    public MutableLiveData<String> segundoApellido = new MutableLiveData<>();
    public MutableLiveData<String> correoElectronico = new MutableLiveData<>();
    public MutableLiveData<String> nombreUsuario = new MutableLiveData<>();
    public MutableLiveData<String> contrasena = new MutableLiveData<>();

    /*Errores*/
    private MutableLiveData<Integer> errorGeneral = new MutableLiveData<>();
    private MutableLiveData<Integer> errorNombrePersona = new MutableLiveData<>();
    private MutableLiveData<Integer> errorPrimerApellido = new MutableLiveData<>();
    private MutableLiveData<Integer> errorSegundoApellido = new MutableLiveData<>();
    private MutableLiveData<Integer> errorCorreoElectronico = new MutableLiveData<>();
    private MutableLiveData<Integer> errorNombreUsuario = new MutableLiveData<>();
    private MutableLiveData<Integer> errorContrasena = new MutableLiveData<>();

    private SignUpBusiness signUpBusiness;
    private MutableLiveData<PostResponse> postResponseMutableLiveData;

    public SignUpViewModel(){ }

    public void setSignUpBusiness(Application application){
        this.signUpBusiness = new SignUpBusiness(application);
    }

    public LiveData<PostResponse> getPostResponseMutableLiveData() {
        if (postResponseMutableLiveData == null)
            postResponseMutableLiveData = new MutableLiveData<>();
        return postResponseMutableLiveData;
    }

    public LiveData<Integer> getErrorGeneral() {
        if (errorGeneral == null)
            errorGeneral = new MutableLiveData<>();
        return errorGeneral;
    }

    public LiveData<Integer> getErrorNombrePersona() {
        if (errorNombrePersona == null)
            errorNombrePersona = new MutableLiveData<>();
        return errorNombrePersona;
    }

    public LiveData<Integer> getErrorPrimerApellido() {
        if (errorPrimerApellido == null)
            errorPrimerApellido = new MutableLiveData<>();
        return errorPrimerApellido;
    }

    public LiveData<Integer> getErrorSegundoApellido() {
        if (errorSegundoApellido == null)
            errorSegundoApellido = new MutableLiveData<>();
        return errorSegundoApellido;
    }

    public LiveData<Integer> getErrorCorreoElectronico() {
        if (errorCorreoElectronico== null)
            errorCorreoElectronico = new MutableLiveData<>();
        return errorCorreoElectronico;
    }

    public LiveData<Integer> getErrorNombreUsuario() {
        if (errorNombreUsuario == null)
            errorNombreUsuario = new MutableLiveData<>();
        return errorNombreUsuario;
    }

    public LiveData<Integer> getErrorContrasena() {
        if (errorContrasena == null)
            errorContrasena = new MutableLiveData<>();
        return errorContrasena;
    }

    /**
     * Realiza la inserción del registro en la BD
     *
     * @return
     * */
    public boolean onSignUp(){
        if (validateUi()){
            try{
                PostResponse postResponse = signUpBusiness.onSignUp(  nombrePersona.getValue()
                                                            , primerApellido.getValue()
                                                            , segundoApellido.getValue()
                                                            , correoElectronico.getValue()
                                                            , nombreUsuario.getValue()
                                                            , contrasena.getValue());

                postResponseMutableLiveData.setValue(postResponse);
            }catch (CuentaExisteException e){
                e.printStackTrace();
                errorGeneral.setValue(Constants.CUENTA_EXISTE);
            }
            catch (SocketTimeoutException | ExecutionException | InterruptedException e){
                e.printStackTrace();
                errorGeneral.setValue(NetWorkConstants.TIEMPO_LIMITE);
            }catch (ConnectException | ConexionNoDisponibleException e){
                e.printStackTrace();
                errorGeneral.setValue(NetWorkConstants.CONEXION_NO_DISPONIBLE);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            errorGeneral.setValue(ErroresGeneralesConstants.SIGUNP_INVALIDO);
        }
        return true;
    }

    /**
     *
     * @return Regresa true si la pantalla tiene la entrada de datos correcta,
     *         devuelve false si existe almenos un error en la pantalla.
     * */

    private boolean validateUi(){
        boolean validated = true;
        errorNombrePersona.setValue(UiUitls.stringValidate(nombrePersona.getValue(),null));
        errorPrimerApellido.setValue(UiUitls.stringValidate(primerApellido.getValue(),null));
        errorSegundoApellido.setValue(UiUitls.stringValidate(segundoApellido.getValue(),null));
        errorCorreoElectronico.setValue(UiUitls.stringValidate(correoElectronico.getValue(),null));
        errorNombreUsuario.setValue(UiUitls.stringValidate(nombreUsuario.getValue(),null));
        errorContrasena.setValue(UiUitls.stringValidate(contrasena.getValue(),null));
        if (!errorNombrePersona.getValue().equals(FieldErrorContants.SIN_ERRORES))
            validated = false;
        if (!errorPrimerApellido.getValue().equals(FieldErrorContants.SIN_ERRORES))
            validated = false;
        if (!errorSegundoApellido.getValue().equals(FieldErrorContants.SIN_ERRORES))
            validated = false;
        if (!errorContrasena.getValue().equals(FieldErrorContants.SIN_ERRORES))
            validated = false;
        if (!errorNombreUsuario.getValue().equals(FieldErrorContants.SIN_ERRORES))
            validated = false;
        if (!errorContrasena.getValue().equals(FieldErrorContants.SIN_ERRORES))
            validated = false;
        return validated;
    }



}
