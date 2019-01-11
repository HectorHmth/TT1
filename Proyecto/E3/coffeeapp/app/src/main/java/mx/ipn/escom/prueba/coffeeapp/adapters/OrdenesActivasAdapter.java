package mx.ipn.escom.prueba.coffeeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import mx.ipn.escom.prueba.coffeeapp.databinding.OrdenActivaItemViewBinding;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.OrdenActivaViewModel;
import mx.ipn.escom.prueba.coffeeapp.views.OrdenesFragment;

public class OrdenesActivasAdapter extends RecyclerView.Adapter<OrdenesActivasAdapter.OrdenActivaViewHolder> {
    private Context mContext;
    private ArrayList<OrdenActivaViewModel> mList;
    private LayoutInflater layoutInflater;
    private Integer idCuenta;

    public OrdenesActivasAdapter(OrdenesFragment mContext, ArrayList<OrdenActivaViewModel> mList, Integer idCuenta) {
        this.mContext = mContext.getContext();
        this.mList = mList;
        this.idCuenta = idCuenta;
    }

    @NonNull
    @Override
    public OrdenActivaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        OrdenActivaItemViewBinding dataBinding = OrdenActivaItemViewBinding.inflate(layoutInflater, parent, false);
        return new OrdenActivaViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdenActivaViewHolder holder, int position) {
        OrdenActivaViewModel model = mList.get(position);
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

    public void setmList(ArrayList<OrdenActivaViewModel> mList) {
        this.mList = mList;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public Context getmContext() {
        return mContext;
    }

    public ArrayList<OrdenActivaViewModel> getmList() {
        return mList;
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    class OrdenActivaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private OrdenActivaItemViewBinding ordenActivaItemViewBinding;

        public OrdenActivaViewHolder(OrdenActivaItemViewBinding ordenActivaItemViewBinding) {
            super(ordenActivaItemViewBinding.getRoot());
            this.ordenActivaItemViewBinding = ordenActivaItemViewBinding;
            ordenActivaItemViewBinding.getRoot().setOnClickListener(this);
        }

        public void bind(OrdenActivaViewModel ordenActivaViewModel){
            this.ordenActivaItemViewBinding.setOrdenActivaViewModel(ordenActivaViewModel);
        }

        public OrdenActivaItemViewBinding getDataBinding(){
            return this.ordenActivaItemViewBinding;
        }

        @Override
        public void onClick(View view) {
            /*
            Intent intent = new Intent(view.getContext(), ProductoDetailActivity.class);

            intent.putExtra("ID_LOCAL", ordenItemViewBinding.getOrdenHistoricalViewModel().idLocal);
            intent.putExtra("ID_PRODUCTO", ordenItemViewBinding.getOrdenHistoricalViewModel().idProducto);
            intent.putExtra("ID_CUENTA", idCuenta);

            view.getContext().startActivity(intent);
            */
        }
    }
}
