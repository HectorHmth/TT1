package mx.ipn.escom.prueba.coffeeapp.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import mx.ipn.escom.prueba.coffeeapp.AppDataBase;
import mx.ipn.escom.prueba.coffeeapp.LocationManagerService;
import mx.ipn.escom.prueba.coffeeapp.NetworkBroadcastReceiver;
import mx.ipn.escom.prueba.coffeeapp.R;
import mx.ipn.escom.prueba.coffeeapp.constants.Constants;
import mx.ipn.escom.prueba.coffeeapp.constants.ErroresGeneralesConstants;
import mx.ipn.escom.prueba.coffeeapp.constants.NetWorkConstants;
import mx.ipn.escom.prueba.coffeeapp.constants.RequestCodes;
import mx.ipn.escom.prueba.coffeeapp.entities.Cuenta;
import mx.ipn.escom.prueba.coffeeapp.observers.SimpleTextInputLayoutObserver;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.LoginViewModel;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import mx.ipn.escom.prueba.coffeeapp.databinding.ActivityLoginBinding;


/**
 * The type Login activity.
 *
 */
public class LoginActivity extends AppCompatActivity {

    TextView textView;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                AppDataBase appDataBase = AppDataBase.getDatabase(getApplicationContext());
                appDataBase.buildDatabase();
            }
        }); // Crea la base de datos.


        broadcastReceiver = new NetworkBroadcastReceiver();   // Objeto para determinar
                                                                                // si hay conexión o no al servicio web
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(broadcastReceiver,intentFilter);


        ActivityLoginBinding activityLoginBinding = DataBindingUtil
                .setContentView(this,R.layout.activity_login); //Habilita el binding de la UI

        LoginViewModel loginViewModel = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(this.getApplication())
                .create(LoginViewModel.class); // Instancia del objeto ViewModel

        loginViewModel.setLoginBusiness(this.getApplication());// Instancia el objeto bs del ViewModel
        activityLoginBinding.setViewModel(loginViewModel);//Conecta el ViewModel con el Activity
        activityLoginBinding.setLifecycleOwner(this);//Indica el Propietario del Ciclo de Vida del ViewModel


        loginViewModel.errorContrasena.observe(this,
                new SimpleTextInputLayoutObserver((TextInputLayout)findViewById(R.id.pwdError)
                        , getString(R.string.MSG1))); //Observer para el TextInputLayout de la contraseña

        loginViewModel.errorUsuario.observe(this,
                new SimpleTextInputLayoutObserver((TextInputLayout)findViewById(R.id.nombreUsuarioError)
                        ,getString(R.string.MSG1))); //Observer para el TextInputLayout del nombre de Usuario



        loginViewModel.errorGeneral.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView = (TextView) findViewById(R.id.errorGeneral);
                switch (integer){
                    case NetWorkConstants.CONEXION_NO_DISPONIBLE:
                        textView.setVisibility(View.VISIBLE);
                        textView.setText(getString(R.string.MSG2));
                        break;
                    case NetWorkConstants.TIEMPO_LIMITE:
                        textView.setVisibility(View.VISIBLE);
                        textView.setText(getString(R.string.MSG2));
                        break;
                    case ErroresGeneralesConstants.LOGIN_INVALIDO:
                        textView.setVisibility(View.VISIBLE);
                        textView.setText(getString(R.string.MSG3));
                        break;
                    case Constants.CUENTA_NOT_FOUND:
                        textView.setVisibility(View.VISIBLE);
                        textView.setText(getString(R.string.MSG3));
                        break;
                    default:
                        textView.setVisibility(View.GONE);
                        break;
                }
            }
        });// Observer para agregar el error general.

        loginViewModel.getCuenta().observe(this, new Observer<Cuenta>() {
            @Override
            public void onChanged(Cuenta cuenta) {
                if (cuenta != null){
                    Log.d("--->UI","Cuenta habilitada");
                    Intent intent = new Intent(getApplication(),PrincipalActivity.class);
                    unregisterReceiver(broadcastReceiver);
                    intent.putExtra("CUENTA_ID", cuenta.getId());
                    Log.d("--->CUENTAIDLogin", cuenta.getId().toString());
                    startActivityForResult(intent,RequestCodes.LOGIN_ACTIVITY_REQUEST);
                }else{
                    Log.d("--->UI","Cuenta inhabilatada");
                }
            }
        });

        Intent servicio = new Intent(this,LocationManagerService.class);
        startService(servicio);

    }

    public void signUp(View view){
        Intent intent = new Intent(this,SignUpActivity.class);
        try{
            this.unregisterReceiver(broadcastReceiver);
        }catch (Exception e){
            e.printStackTrace();
        }
        startActivityForResult(intent,RequestCodes.SIGNUP_ACTIVIY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        textView = (TextView) findViewById(R.id.errorGeneral);
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(broadcastReceiver,intentFilter);
        if (requestCode == RequestCodes.SIGNUP_ACTIVIY_REQUEST){
            if (resultCode == Activity.RESULT_OK){
                Integer registroExitoso = data.getIntExtra("REGISTRO_EXITOSO",Constants.REGISTRO_ERRONEO);
                if (registroExitoso == Constants.REGISTRO_EXITOSO){
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(getString(R.string.MSG5));
                }
            }else{
                textView.setVisibility(View.GONE);
            }
        }

        if (requestCode == RequestCodes.LOGIN_ACTIVITY_REQUEST){
            if (resultCode == Activity.RESULT_OK){
                Integer cierreSesionExitoso = data.getIntExtra("CIERRE_SESION", Constants.CIERRE_SESION_ERRONEO);
                if(cierreSesionExitoso == Constants.CIERRE_SESION_EXITOSO){
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(getString(R.string.MSG7));
                }
            }else{
                textView.setVisibility(View.GONE);
            }
        }
    }


    @Override
    protected void onDestroy() {
        try{
            this.unregisterReceiver(broadcastReceiver);
            super.onDestroy();
        }catch (Exception e){
            e.printStackTrace();
            super.onDestroy();
        }

    }
}
