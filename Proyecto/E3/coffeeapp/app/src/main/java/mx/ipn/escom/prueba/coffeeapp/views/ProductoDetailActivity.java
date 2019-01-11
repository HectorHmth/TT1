package mx.ipn.escom.prueba.coffeeapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import mx.ipn.escom.prueba.coffeeapp.NetworkBroadcastReceiver;
import mx.ipn.escom.prueba.coffeeapp.R;
import mx.ipn.escom.prueba.coffeeapp.constants.Constants;
import mx.ipn.escom.prueba.coffeeapp.constants.NetWorkConstants;
import mx.ipn.escom.prueba.coffeeapp.databinding.ActivityProductoDetailBinding;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.LocalDetailViewModel;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.ProductoDetailViewModel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.tfb.fbtoast.FBToast;

public class ProductoDetailActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;
    private Integer idLocal;
    private Integer idProducto;
    private Integer idCuenta;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        broadcastReceiver = new NetworkBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(broadcastReceiver,intentFilter);


        ActivityProductoDetailBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_producto_detail);

        ProductoDetailViewModel productoDetailViewModel = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(getApplication())
                .create(ProductoDetailViewModel.class);

        getIntentData();

        productoDetailViewModel.setProductoDetailBusiness(getApplication());
        binding.setViewModel(productoDetailViewModel);
        binding.setLifecycleOwner(this);
        productoDetailViewModel.setIdCuenta(this.idCuenta);
        productoDetailViewModel.getProductoDetail(this.idLocal, this.idProducto);


        productoDetailViewModel.getCantidadText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView cantidad = findViewById(R.id.cantidadTV);
                cantidad.setText(s);
            }
        });

        productoDetailViewModel.getErrorGeneral().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer){
                    case Constants.PRODUCTO_AGOTADO:
                        FBToast.errorToast(ProductoDetailActivity.this , getString(R.string.MSG8),FBToast.LENGTH_SHORT,Gravity.TOP);
                        break;
                    case Constants.PRODUCTO_EXISTE_EN_LA_ORDEN:
                        FBToast.errorToast(ProductoDetailActivity.this , getString(R.string.MSG9),FBToast.LENGTH_SHORT,Gravity.TOP);
                        break;
                    case Constants.PRODUCTO_NO_AGREGADO:
                        FBToast.errorToast(ProductoDetailActivity.this , getString(R.string.MSG10), FBToast.LENGTH_SHORT,Gravity.TOP);
                        break;
                    case NetWorkConstants.CONEXION_NO_DISPONIBLE:
                        FBToast.errorToast(ProductoDetailActivity.this, getString(R.string.MSG2), FBToast.LENGTH_SHORT,Gravity.TOP);
                        break;
                    default:
                        Log.d("--->","EXITO");
                        FBToast.successToast(ProductoDetailActivity.this,getString(R.string.MSG11),FBToast.LENGTH_SHORT,Gravity.TOP);
                        break;
                }
            }
        });

        setUI();
    }


    private void getIntentData(){
        Intent intent = this.getIntent();
        this.idLocal = intent.getIntExtra("ID_LOCAL",1);
        this.idProducto = intent.getIntExtra("ID_PRODUCTO",1);
        this.idCuenta = intent.getIntExtra("ID_CUENTA", 1);
    }

    private void setUI(){
        toolbar = (Toolbar) findViewById(R.id.productoDetailToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    unregisterReceiver(broadcastReceiver);
                }catch (Exception e){
                    e.printStackTrace();
                }
                onBackPressed();
            }
        });
    }
}
