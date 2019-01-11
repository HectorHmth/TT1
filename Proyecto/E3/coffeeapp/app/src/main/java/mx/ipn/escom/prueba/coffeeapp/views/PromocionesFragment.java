package mx.ipn.escom.prueba.coffeeapp.views;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mx.ipn.escom.prueba.coffeeapp.R;
import mx.ipn.escom.prueba.coffeeapp.adapters.LocalesAdapter;
import mx.ipn.escom.prueba.coffeeapp.adapters.PromocionesAdapter;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.PromocionesViewModel;

public class PromocionesFragment extends Fragment {

    private RecyclerView recyclerView;
    private PromocionesViewModel promocionesViewModel;
    private PromocionesAdapter promocionesAdapter;

    public PromocionesFragment(){
        super();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("--->","ENTRE");
        //Here we inflate the layout for this fragment
        promocionesViewModel = new PromocionesViewModel();
        promocionesViewModel.setPromocionesBusiness(getActivity().getApplication());

        View view = inflater.inflate(R.layout.promociones_fragment, container, false);
        //Attach elements to the view (In this case we attach to the view the RecyclerView)
        recyclerView = view.findViewById(R.id.listaPromociones);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            String local = bundle.getString("LOCAL");
            Integer idCuenta = bundle.getInt("CUENTA_ID");
            recyclerView.setAdapter(new PromocionesAdapter(this, promocionesViewModel.getListaDePromociones(local),idCuenta));
            promocionesAdapter = (PromocionesAdapter) recyclerView.getAdapter();
            if (promocionesAdapter.getmList() == null){
                promocionesAdapter.setmList(promocionesViewModel.getListaDePromociones(local));
            }else{
                ProgressBar progressBar = view.findViewById(R.id.promociones_progress);
                progressBar.setVisibility(View.GONE);
            }
        }
    }
}
