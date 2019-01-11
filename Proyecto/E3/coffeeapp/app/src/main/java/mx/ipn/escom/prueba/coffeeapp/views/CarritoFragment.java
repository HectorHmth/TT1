package mx.ipn.escom.prueba.coffeeapp.views;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.button.MaterialButton;
import com.tfb.fbtoast.FBToast;

import org.w3c.dom.Text;

import java.sql.Time;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mx.ipn.escom.prueba.coffeeapp.R;
import mx.ipn.escom.prueba.coffeeapp.adapters.OrdenProductosAdapter;
import mx.ipn.escom.prueba.coffeeapp.constants.Constants;
import mx.ipn.escom.prueba.coffeeapp.constants.NetWorkConstants;
import mx.ipn.escom.prueba.coffeeapp.databinding.FragmentCarritoBinding;
import mx.ipn.escom.prueba.coffeeapp.entities.Cuenta;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.CarritoViewModel;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.OrdenProductoViewModel;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.PromocionesViewModel;

public class CarritoFragment extends androidx.fragment.app.Fragment {

    private Integer idCuenta;
    private RecyclerView recyclerView;
    private CarritoViewModel mViewModel;
    private OrdenProductosAdapter ordenProductosAdapter;
    private TimePicker timePicker;
    private RadioGroup radioGroup;
    private AlertDialog alertDialog;

    public CarritoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentCarritoBinding carritoBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_carrito, container, false);
        final View view = carritoBinding.getRoot();
        final Context context = getContext();

        mViewModel = ViewModelProviders.of(this).get(CarritoViewModel.class);

        carritoBinding.setViewModel(mViewModel);
        carritoBinding.setLifecycleOwner(this);

        Bundle bundle =getArguments();
        idCuenta = bundle.getInt("CUENTA_ID");
        Log.d("--->CUENTAID", idCuenta.toString());

        mViewModel.setCarritoBusiness(getActivity().getApplication());
        mViewModel.getLocalOfPedido(idCuenta);

        timePicker = view.findViewById(R.id.timePicker);
        timePicker.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);

        radioGroup = view.findViewById(R.id.radio_selection);


        mViewModel.getTogglePedido().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    LinearLayout linearLayout = view.findViewById(R.id.pedidoLL);
                    TextView textView = view.findViewById(R.id.pedidoTV);
                    Drawable drawable = getContext().getDrawable(R.drawable.ic_keyboard_arrow_up_black_24dp);
                    textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable,null);
                    linearLayout.setVisibility(View.VISIBLE);
                }else{
                    LinearLayout linearLayout = view.findViewById(R.id.pedidoLL);
                    linearLayout.setVisibility(View.GONE);
                    TextView textView = view.findViewById(R.id.pedidoTV);
                    Drawable drawable = getContext().getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable,null);
                }
            }
        });

        mViewModel.getToggleParaCuando().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){

                    LinearLayout linearLayout = view.findViewById(R.id.layoutButtonsParaCuando);
                    TextView textView = view.findViewById(R.id.paraCuandoTV);
                    Drawable drawable = getContext().getDrawable(R.drawable.ic_keyboard_arrow_up_black_24dp);
                    textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable,null);
                    linearLayout.setVisibility(View.VISIBLE);

                }else{
                    LinearLayout linearLayout = view.findViewById(R.id.layoutButtonsParaCuando);
                    TextView textView = view.findViewById(R.id.paraCuandoTV);
                    linearLayout.setVisibility(View.GONE);
                    Drawable drawable = getContext().getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable,null);
                }
            }
        });

        mViewModel.getToggleFormaDePago().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    LinearLayout linearLayout = view.findViewById(R.id.pagosLL);
                    TextView textView = view.findViewById(R.id.pagoTV);
                    Drawable drawable = getContext().getDrawable(R.drawable.ic_keyboard_arrow_up_black_24dp);
                    textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable,null);
                    linearLayout.setVisibility(View.VISIBLE);
                }else{
                    LinearLayout linearLayout = view.findViewById(R.id.pagosLL);
                    TextView textView = view.findViewById(R.id.pagoTV);
                    linearLayout.setVisibility(View.GONE);
                    Drawable drawable = getContext().getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable,null);
                }
            }
        });


        mViewModel.getErrorGeneral().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer){
                    case NetWorkConstants.CONEXION_NO_DISPONIBLE:
                        FBToast.errorToast(getContext(), getString(R.string.MSG2),FBToast.LENGTH_SHORT );
                        break;
                    case Constants.NO_EXISTE_ORDEN_SIN_CONFIRMAR:
                        FBToast.errorToast(getContext(), getString(R.string.MSG12),FBToast.LENGTH_SHORT );
                        break;
                    case Constants.PRODUCTO_AGOTADO:
                        FBToast.errorToast(getContext(), getString(R.string.MSG8),FBToast.LENGTH_SHORT );
                        break;
                    case Constants.PRODUCTO_CERO:
                        FBToast.errorToast(getContext(), getString(R.string.MSG13),FBToast.LENGTH_SHORT);
                        break;
                    case Constants.PRODUCTO_EXISTE_EN_LA_ORDEN:
                        FBToast.errorToast(getContext(), getString(R.string.MSG9),FBToast.LENGTH_SHORT);
                        break;
                    case Constants.PRODUCTO_ELIMINADO_CORRECTAMENTE:
                        FBToast.successToast(getContext(),getString(R.string.MSG14),FBToast.LENGTH_SHORT);
                        break;
                    case Constants.NO_SE_PUEDE_ELIMINAR_PRODUCTO:
                        FBToast.errorToast(getContext(),getString(R.string.MSG15),FBToast.LENGTH_SHORT);
                        break;
                    case Constants.ERROR_FINALIZAR_ORDEN:
                        FBToast.errorToast(getContext(),getString(R.string.MSG16),FBToast.LENGTH_SHORT);
                        break;
                    case Constants.ORDEN_ENVIADA:
                        FBToast.successToast(getContext(),getString(R.string.MSG17),FBToast.LENGTH_SHORT);
                        break;
                    case Constants.ORDEN_VACIA:
                        FBToast.errorToast(getContext(),getString(R.string.MSG18),FBToast.LENGTH_SHORT);
                        break;
                    default:
                        break;

                }
            }
        });

        mViewModel.getTipoFormaPagoImagen().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                ImageView imageView = view.findViewById(R.id.typeFormaPagoIV);
                switch (integer){
                    case Constants.FORMA_PAGO_EFECTIVO:
                        imageView.setImageDrawable(view.getResources().getDrawable(R.drawable.ic_monetization_on_black_24dp,null));
                        break;
                    case Constants.FORMA_PAGO_PAYPAL:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.icons8_paypal_50,null));
                        break;
                    default:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_credit_card_black_24dp,null));
                        break;
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.loAntesRB){
                    timePicker.setVisibility(View.GONE);
                    mViewModel.setParaCuando(Constants.LO_ANTES_POSIBLE);
                }else{
                    timePicker.setVisibility(View.VISIBLE);
                    mViewModel.setParaCuando(Constants.PEDIDO_PROGRAMADO);
                }
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {

            }
        });

        recyclerView = view.findViewById(R.id.ordenProductosSinConfirmarRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        view.findViewById(R.id.finalizarCompraBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("--->","ENTRE AL DIALOGO");
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setCancelable(true);
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(mViewModel.finalizarCompra()){
                            Log.d("--->","FINALIZADA");
                            ordenProductosAdapter.getmList().clear();
                            ordenProductosAdapter.notifyDataSetChanged();
                            //onDestroyView();
                        }
                    }
                });
                alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("--->","Cancelado");
                    }
                });
                alert.setTitle(getString(R.string.CONFIRMAR_PEDIDO_TL)).setMessage(R.string.CONFIRMAR_PEDIDO_MSG);
                alert.setIcon(R.drawable.coffeapp);
                alertDialog = alert.create();
                alertDialog.show();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ordenProductosAdapter = new OrdenProductosAdapter(this,mViewModel.getListaProductosEnOrden(idCuenta),idCuenta);
        ordenProductosAdapter.setOnItemClickListener(new OrdenProductosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }

            @Override
            public void onAddCantidad(int position) {
                if (mViewModel.onAddCantidad(position))
                    ordenProductosAdapter.notifyItemChanged(position);
            }

            @Override
            public void onSubstractCantidad(int position) {
                if (mViewModel.onSubstractCantidad(position)){
                    ordenProductosAdapter.notifyItemChanged(position);
                }
            }

            @Override
            public void onDeleteProductoDeOrden(int position) {
                Log.d("--->","Borrar");
                if(mViewModel.onDeleteProductoDeOrden(position)) {
                    ordenProductosAdapter.getmList().remove(position);
                    ordenProductosAdapter.notifyItemRemoved(position);
                    ordenProductosAdapter.notifyDataSetChanged();
                }
            }
        });
        recyclerView.setAdapter(ordenProductosAdapter);
        if (ordenProductosAdapter.getmList() == null){
            ordenProductosAdapter.setmList(mViewModel.getListaProductosEnOrden(idCuenta));
        }else{
            ProgressBar progressBar = view.findViewById(R.id.ordenProductosSinConfirmarPB);
            progressBar.setVisibility(View.GONE);
        }



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Bundle bundle = new Bundle();
        bundle.putInt("CUENTA_ID",idCuenta);
        onSaveInstanceState(bundle);
    }

}
