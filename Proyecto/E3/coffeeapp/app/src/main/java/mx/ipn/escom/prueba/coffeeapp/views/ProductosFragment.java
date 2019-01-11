package mx.ipn.escom.prueba.coffeeapp.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mx.ipn.escom.prueba.coffeeapp.R;
import mx.ipn.escom.prueba.coffeeapp.adapters.ProductosLocalesAdapter;
import mx.ipn.escom.prueba.coffeeapp.adapters.PromocionesAdapter;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.ProductosLocalViewModel;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.PromocionesViewModel;

public class ProductosFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProductosLocalViewModel productosLocalViewModel;
    private ProductosLocalesAdapter productosLocalesAdapter;
    public ProductosFragment(){
        super();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("--->","ENTRE");
        //Here we inflate the layout for this fragment
        productosLocalViewModel = new ProductosLocalViewModel();
        productosLocalViewModel.setProductoLocalBussines(getActivity().getApplication());

        View view = inflater.inflate(R.layout.productos_fragment, container, false);
        //Attach elements to the view (In this case we attach to the view the RecyclerView)
        recyclerView = view.findViewById(R.id.listaProductosLocal);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            String local = bundle.getString("LOCAL");
            Integer idCuenta = bundle.getInt("CUENTA_ID");
            Log.d("--->FRAGMENT", String.valueOf(idCuenta));
            recyclerView.setAdapter(new ProductosLocalesAdapter(this, productosLocalViewModel.getListaDeProductosLocal(local),idCuenta));
            productosLocalesAdapter = (ProductosLocalesAdapter) recyclerView.getAdapter();
            if (productosLocalesAdapter.getmList() == null){
                productosLocalesAdapter.setmList(productosLocalViewModel.getListaDeProductosLocal(local));
            }else{
                ProgressBar progressBar = view.findViewById(R.id.productosLocal_progress);
                progressBar.setVisibility(View.GONE);
            }
        }
    }
}
