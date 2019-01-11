package mx.ipn.escom.prueba.coffeeapp.views;


import android.location.LocationListener;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mx.ipn.escom.prueba.coffeeapp.R;
import mx.ipn.escom.prueba.coffeeapp.adapters.LocalesAdapter;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.LocalesViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocalesFragment extends androidx.fragment.app.Fragment {


    private RecyclerView recyclerView;
    private LocalesViewModel localesViewModel;
    private LocalesAdapter localesAdapter;
    private LocationListener locationListener;

    public LocalesFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        localesViewModel = new LocalesViewModel();
        localesViewModel.setLocalesBusiness(getActivity().getApplication());
        View v = inflater.inflate(R.layout.fragment_locales, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.listaLocales);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        Log.d("--->CUENTAID", String.valueOf(bundle.getInt("CUENTA_ID")));
        recyclerView.setAdapter(new LocalesAdapter(this, localesViewModel.getListaDeLocales(), bundle.getInt("CUENTA_ID")));
        localesAdapter = (LocalesAdapter) recyclerView.getAdapter();
        if (localesAdapter.getmList() == null) {
            localesAdapter.setmList(localesViewModel.getListaDeLocales());
        } else {
            ProgressBar progressBar = view.findViewById(R.id.locales_progress);
            progressBar.setVisibility(View.GONE);
        }
    }

}
