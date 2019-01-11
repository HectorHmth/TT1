package mx.ipn.escom.prueba.coffeeapp.views;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import mx.ipn.escom.prueba.coffeeapp.NetworkBroadcastReceiver;
import mx.ipn.escom.prueba.coffeeapp.R;
import mx.ipn.escom.prueba.coffeeapp.adapters.OrdenesHistoricalAdapter;
import mx.ipn.escom.prueba.coffeeapp.databinding.FragmentOrdenesBinding;
import mx.ipn.escom.prueba.coffeeapp.entities.Orden;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.OrdenesHistoricalViewModel;
//import mx.ipn.escom.prueba.coffeeapp

/**
 * A simple {@link Fragment} subclass.
 */
public class OrdenesFragment extends androidx.fragment.app.Fragment {

    private RecyclerView recyclerView;
    private FragmentActivity myContext;
    private OrdenesHistoricalViewModel ordenesHistoricalViewModel;
    private OrdenesHistoricalAdapter ordenesHistoricalAdapter;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private BroadcastReceiver broadcastReceiver;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    Bundle bundle;

    public OrdenesFragment() {
        super();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        Log.d("--->","ENTRE");
        //Here we inflate the layout for this fragment
        ordenesHistoricalViewModel = new OrdenesHistoricalViewModel();
        ordenesHistoricalViewModel.setOrdenesBusiness(getActivity().getApplication());
        //Here we inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_ordenes, container, false);
        //Attach elements to the view (In this case we attach to the view the RecyclerView)
        recyclerView = view.findViewById(R.id.listaOrdenes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        bundle = this.getArguments();

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.container);
        toolbar = view.findViewById(R.id.toolBar);
        //sectionsPagerAdapter = new SectionsPagerAdapter(myContext.getSupportFragmentManager());
        //viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            Integer idCuenta = bundle.getInt("CUENTA_ID");
            if(idCuenta == null) {
                Log.d("---->", "OJO AQUI PRRO");
            }
            else{
                Log.d("---->HOLI", idCuenta.toString());
            }
            recyclerView.setAdapter(new OrdenesHistoricalAdapter(this, ordenesHistoricalViewModel.getHistorialPedidos(idCuenta), idCuenta));
            ordenesHistoricalAdapter = (OrdenesHistoricalAdapter) recyclerView.getAdapter();
            if (ordenesHistoricalAdapter.getmList() == null){
                ordenesHistoricalAdapter.setmList(ordenesHistoricalViewModel.getHistorialPedidos(idCuenta));
                Log.d("---->Result_ARRAY", String.valueOf(ordenesHistoricalAdapter.getmList().size()));
            }else{
                ProgressBar progressBar = view.findViewById(R.id.ordenes_progress);
                progressBar.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }


    class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public androidx.fragment.app.Fragment getItem(int position) {
            Bundle localBundle = new Bundle();
            androidx.fragment.app.Fragment fragment = null;
            localBundle.putInt("CUENTA_ID", bundle.getInt("CUENTA_ID"));
            switch (position){
                case 0:
                    fragment = new OrdenesFragment();
                    break;
                //case 1:
                   // fragment = new ProductosFragment();
                   // break;
                default:
                    //fragment = new MenuDiaFragment();
                    break;
            }
            fragment.setArguments(localBundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Historial de Pedidos";
                case 1:
                    return "Pedidos Activos";
                default:
                    return null;
            }
        }
    }
}
