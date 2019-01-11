package mx.ipn.escom.prueba.coffeeapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import mx.ipn.escom.prueba.coffeeapp.NetworkBroadcastReceiver;
import mx.ipn.escom.prueba.coffeeapp.R;
import mx.ipn.escom.prueba.coffeeapp.databinding.ActivityLocalDetailBinding;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.LocalDetailViewModel;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

public class LocalDetailActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        broadcastReceiver = new NetworkBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(broadcastReceiver,intentFilter);


        ActivityLocalDetailBinding localDetailBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_local_detail);

        LocalDetailViewModel localDetailViewModel = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(this.getApplication())
                .create(LocalDetailViewModel.class);

        localDetailViewModel.setLocalDetailBusiness(this.getApplication());
        localDetailBinding.setViewModel(localDetailViewModel);
        localDetailBinding.setLifecycleOwner(this);
        localDetailViewModel.getLocal(this.getIntent().getStringExtra("LOCAL"));
        setUI();
    }


    public void verEnElMapa(View view){
        try{
            unregisterReceiver(broadcastReceiver);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        try{
            unregisterReceiver(broadcastReceiver);
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onDestroy();
    }

    private void setUI(){
        toolbar = (Toolbar) findViewById(R.id.localDetailToolbar);
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
