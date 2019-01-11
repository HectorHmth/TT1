package mx.ipn.escom.prueba.coffeeapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import mx.ipn.escom.prueba.coffeeapp.NetworkBroadcastReceiver;
import mx.ipn.escom.prueba.coffeeapp.R;
import mx.ipn.escom.prueba.coffeeapp.asynctasks.SignUpNetAsyncTask;
import mx.ipn.escom.prueba.coffeeapp.constants.Constants;
import mx.ipn.escom.prueba.coffeeapp.constants.ErroresGeneralesConstants;
import mx.ipn.escom.prueba.coffeeapp.constants.NetWorkConstants;
import mx.ipn.escom.prueba.coffeeapp.databinding.ActivityLoginBinding;
import mx.ipn.escom.prueba.coffeeapp.databinding.ActivitySignUpBinding;
import mx.ipn.escom.prueba.coffeeapp.entities.Persona;
import mx.ipn.escom.prueba.coffeeapp.entities.PostResponse;
import mx.ipn.escom.prueba.coffeeapp.observers.SimpleTextInputLayoutObserver;
import mx.ipn.escom.prueba.coffeeapp.services.SignUpService;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.SignUpViewModel;
import retrofit2.Response;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class SignUpActivity extends AppCompatActivity {
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        broadcastReceiver = new NetworkBroadcastReceiver();   // Objeto para determinar
        // si hay conexión o no al servicio web
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(broadcastReceiver,intentFilter);

        ActivitySignUpBinding activitySignUpBinding = DataBindingUtil
                .setContentView(this,R.layout.activity_sign_up); //Habilita el binding de la UI

        SignUpViewModel signUpViewModel = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(getApplication())
                .create(SignUpViewModel.class);

        signUpViewModel.setSignUpBusiness(getApplication());

        activitySignUpBinding.setViewModel(signUpViewModel);
        activitySignUpBinding.setLifecycleOwner(this);



        /*
         *  Observers
         *
         *
         * */
        signUpViewModel
                .getErrorNombrePersona()
                .observe(this
                        , new SimpleTextInputLayoutObserver((TextInputLayout)findViewById(R.id.nombrePersona)
                                ,getString(R.string.MSG1)));

        signUpViewModel
                .getErrorPrimerApellido()
                .observe(this
                        , new SimpleTextInputLayoutObserver((TextInputLayout)findViewById(R.id.primerApellido)
                                ,getString(R.string.MSG1)));

        signUpViewModel
                .getErrorSegundoApellido()
                .observe(this
                        , new SimpleTextInputLayoutObserver((TextInputLayout)findViewById(R.id.segundoApellido)
                                ,getString(R.string.MSG1)));


        signUpViewModel
                .getErrorCorreoElectronico()
                .observe(this
                        , new SimpleTextInputLayoutObserver((TextInputLayout)findViewById(R.id.correoElectronico)
                                ,getString(R.string.MSG1)));

        signUpViewModel
                .getErrorNombreUsuario()
                .observe(this
                        , new SimpleTextInputLayoutObserver((TextInputLayout)findViewById(R.id.nombreUsuario)
                                ,getString(R.string.MSG1)));

        signUpViewModel
                .getErrorContrasena()
                .observe(this
                        , new SimpleTextInputLayoutObserver((TextInputLayout)findViewById(R.id.contrasena)
                                ,getString(R.string.MSG1)));



        signUpViewModel.getErrorGeneral().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                TextView textView = findViewById(R.id.errorGeneral);
                switch (integer){
                    case Constants.CUENTA_EXISTE:
                        textView.setVisibility(View.VISIBLE);
                        textView.setText(getString(R.string.MSG4));
                        break;
                    case NetWorkConstants.CONEXION_NO_DISPONIBLE:
                        textView.setVisibility(View.VISIBLE);
                        textView.setText(getString(R.string.MSG2));
                        break;
                    case NetWorkConstants.TIEMPO_LIMITE:
                        textView.setVisibility(View.VISIBLE);
                        textView.setText(getString(R.string.MSG2));
                    case ErroresGeneralesConstants.SIGUNP_INVALIDO:
                        textView.setVisibility(View.VISIBLE);
                        textView.setText(getString(R.string.MSG6));
                    default:
                        textView.setVisibility(View.GONE);
                        break;
                }
            }
        });

        signUpViewModel.getPostResponseMutableLiveData().observe(this, new Observer<PostResponse>() {
            @Override
            public void onChanged(PostResponse postResponse) {
                if (postResponse != null){
                    unregisterReceiver(broadcastReceiver);
                    Intent intent = new Intent();
                    intent.putExtra("REGISTRO_EXITOSO",postResponse.getId());
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
            }
        });

    }

    public void regresar(View view){
        unregisterReceiver(broadcastReceiver);
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        try{
            unregisterReceiver(broadcastReceiver);
            super.onDestroy();
        }catch (Exception e){
            e.printStackTrace();
            super.onDestroy();
        }
    }
}
