package mx.ipn.escom.prueba.coffeeapp.views;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationListener;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import mx.ipn.escom.prueba.coffeeapp.LocationManagerService;
import mx.ipn.escom.prueba.coffeeapp.NetworkBroadcastReceiver;
import mx.ipn.escom.prueba.coffeeapp.R;
import mx.ipn.escom.prueba.coffeeapp.RetrofitManager;
import mx.ipn.escom.prueba.coffeeapp.constants.Constants;
import mx.ipn.escom.prueba.coffeeapp.entities.Orden;
import mx.ipn.escom.prueba.coffeeapp.entities.ProductoLocal;
import mx.ipn.escom.prueba.coffeeapp.services.ProductosService;
import retrofit2.Response;


import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Fragment currentFragment;
    private BroadcastReceiver broadcastReceiver;
    private Orden orden;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_locales:
                    Log.d("--->","Se abrirá " + R.id.navigation_locales);
                    currentFragment = new LocalesFragment();
                    return cargarFragment(currentFragment);
                case R.id.navigation_ordenes:
                    Log.d("--->","Se abrirá " + R.id.navigation_ordenes);
                    currentFragment = new OrdenesFragment();
                    return cargarFragment(currentFragment);
                case R.id.navigation_carrito:
                    Log.d("--->","Se abrirá " + R.id.navigation_carrito);
                    currentFragment = new CarritoFragment();
                    return cargarFragment(currentFragment);
                case R.id.navigation_cuenta:
                    Log.d("--->","Se abrirá " + R.id.navigation_cuenta);
                    currentFragment = new CuentaFragment();
                    return cargarFragment(currentFragment);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        broadcastReceiver = new NetworkBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(broadcastReceiver,intentFilter);
        Bundle bundle = new Bundle();
        bundle.putSerializable("CARRITO_DE_COMPRAS", new Orden());
        cargarFragment(new LocalesFragment());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private Boolean cargarFragment(Fragment fragment){
        Bundle bundle = new Bundle();
        if (fragment!=null){
            bundle.putInt("CUENTA_ID",getIntent().getIntExtra("CUENTA_ID",1));
            fragment.setArguments(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container,fragment)
                    .commit();
            return true;
        }
        return false;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        try{
            unregisterReceiver(broadcastReceiver);
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onPause();
        Log.d("--->","AQUI ESTOY");
    }


    public void cerrarSesion(View view){
        try{
            unregisterReceiver(broadcastReceiver);
        }catch (Exception e){
            e.printStackTrace();
        }
        Intent intent = new Intent();
        intent.putExtra("CIERRE_SESION",Constants.CIERRE_SESION_EXITOSO);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }




    //Sobreescribir el metodo onDestroy para que cuando la aplicación muera se guarde
    // el inicio de sesión del cliente
}
