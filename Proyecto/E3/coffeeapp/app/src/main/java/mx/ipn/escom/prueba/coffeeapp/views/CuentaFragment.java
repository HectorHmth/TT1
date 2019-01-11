package mx.ipn.escom.prueba.coffeeapp.views;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tfb.fbtoast.FBToast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import mx.ipn.escom.prueba.coffeeapp.R;
import mx.ipn.escom.prueba.coffeeapp.constants.Constants;
import mx.ipn.escom.prueba.coffeeapp.databinding.FragmentCuentaBinding;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.CuentaViewModel;


public class CuentaFragment extends androidx.fragment.app.Fragment {


    public CuentaViewModel cuentaViewModel;
    public Integer idCuenta;

    public CuentaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentCuentaBinding fragmentCuentaBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_cuenta, container, false);
        View view = fragmentCuentaBinding.getRoot();
        cuentaViewModel = ViewModelProviders.of(this).get(CuentaViewModel.class);
        fragmentCuentaBinding.setViewModel(cuentaViewModel);
        fragmentCuentaBinding.setLifecycleOwner(this);
        Bundle bundle = getArguments();
        idCuenta = bundle.getInt("CUENTA_ID");

        cuentaViewModel.setCuentaBusiness(getActivity().getApplication());
        cuentaViewModel.getPersona(idCuenta);

        cuentaViewModel.getErrorGeneral().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer){
                    case Constants.CUENTA_NOT_FOUND:
                        FBToast.errorToast(getContext(),getString(R.string.MSG19),FBToast.LENGTH_SHORT);
                        break;
                    default:
                        break;
                }
            }
        });


        return view;
    }


}
