package mx.ipn.escom.prueba.coffeeapp.views;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import mx.ipn.escom.prueba.coffeeapp.NetworkBroadcastReceiver;
import mx.ipn.escom.prueba.coffeeapp.R;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.widget.Toolbar;
import mx.ipn.escom.prueba.coffeeapp.RetrofitManager;
import mx.ipn.escom.prueba.coffeeapp.entities.Orden;
import mx.ipn.escom.prueba.coffeeapp.entities.ProductoLocal;
import mx.ipn.escom.prueba.coffeeapp.services.ProductosService;
import retrofit2.Response;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class LocalProductosActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private Toolbar toolbar;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_productos);
        broadcastReceiver = new NetworkBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(broadcastReceiver,intentFilter);
        setUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_local_productos,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            default:
                intent = new Intent(this, LocalDetailActivity.class);
                intent.putExtra("LOCAL",getLocalNameFromIntent());
                try{
                    unregisterReceiver(broadcastReceiver);
                }catch (Exception e){
                    e.printStackTrace();
                }
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setUI(){
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.container);
        toolbar = findViewById(R.id.toolBar);
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getLocalNameFromIntent());
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



    class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            Fragment fragment = null;
            bundle.putString("LOCAL", getLocalNameFromIntent());
            bundle.putInt("CUENTA_ID", getIntent().getIntExtra("CUENTA_ID",1));
            switch (position){
                case 0:
                    fragment = new PromocionesFragment();
                    break;
                case 1:
                    fragment = new ProductosFragment();
                    break;
                default:
                    fragment = new MenuDiaFragment();
            }
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Ofertas";
                case 1:
                    return "Productos";
                case 2:
                    return "Menu del Día";
                default:
                    return null;
            }
        }
    }

    /*
    private Boolean cargarFragment(Fragment fragment){
        if (fragment!=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.,fragment)
                    .commit();
            return true;
        }
        return false;
    }
    */

    public String getLocalNameFromIntent(){
        Intent intent = this.getIntent();
        return intent.getStringExtra("LOCAL");
    }

}
