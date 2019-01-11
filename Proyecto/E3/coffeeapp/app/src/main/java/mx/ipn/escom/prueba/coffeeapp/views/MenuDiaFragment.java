package mx.ipn.escom.prueba.coffeeapp.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import mx.ipn.escom.prueba.coffeeapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuDiaFragment extends Fragment {


    public MenuDiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_dia, container, false);
    }

}
