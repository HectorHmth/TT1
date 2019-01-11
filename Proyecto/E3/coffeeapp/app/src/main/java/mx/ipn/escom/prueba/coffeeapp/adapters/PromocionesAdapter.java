package mx.ipn.escom.prueba.coffeeapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import mx.ipn.escom.prueba.coffeeapp.databinding.PromocionItemViewBinding;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.PromocionViewModel;
import mx.ipn.escom.prueba.coffeeapp.views.LocalProductosActivity;
import mx.ipn.escom.prueba.coffeeapp.views.ProductoDetailActivity;
import mx.ipn.escom.prueba.coffeeapp.views.PromocionesFragment;


public class PromocionesAdapter extends RecyclerView.Adapter<PromocionesAdapter.PromocionViewHolder> {
    private Context mContext;
    private ArrayList<PromocionViewModel> mList;
    private LayoutInflater layoutInflater;
    private Integer idCuenta;

    public PromocionesAdapter(PromocionesFragment mContext, ArrayList<PromocionViewModel> mList,Integer idCuenta) {
        this.mContext = mContext.getContext();
        this.mList = mList;
        this.idCuenta = idCuenta;
    }

    @NonNull
    @Override
    public PromocionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        PromocionItemViewBinding dataBinding = PromocionItemViewBinding.inflate(layoutInflater, parent, false);
        return new PromocionViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PromocionViewHolder holder, int position) {
        PromocionViewModel model = mList.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        if (mList != null){
            return mList.size();
        }else
            return 0;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public void setmList(ArrayList<PromocionViewModel> mList) {
        this.mList = mList;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public Context getmContext() {
        return mContext;
    }

    public ArrayList<PromocionViewModel> getmList() {
        return mList;
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    class PromocionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private PromocionItemViewBinding promocionItemViewBinding;

        public PromocionViewHolder(PromocionItemViewBinding promocionItemViewBinding) {
            super(promocionItemViewBinding.getRoot());
            this.promocionItemViewBinding = promocionItemViewBinding;
            promocionItemViewBinding.getRoot().setOnClickListener(this);
        }

        public void bind(PromocionViewModel promocionViewModel){
            this.promocionItemViewBinding.setPromocionViewModel(promocionViewModel);
        }

        public PromocionItemViewBinding getDataBinding(){
            return this.promocionItemViewBinding;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ProductoDetailActivity.class);

            intent.putExtra("ID_LOCAL", promocionItemViewBinding.getPromocionViewModel().idLocal);
            intent.putExtra("ID_PRODUCTO", promocionItemViewBinding.getPromocionViewModel().idProducto);
            intent.putExtra("ID_CUENTA", idCuenta);

            view.getContext().startActivity(intent);
        }
    }
}
