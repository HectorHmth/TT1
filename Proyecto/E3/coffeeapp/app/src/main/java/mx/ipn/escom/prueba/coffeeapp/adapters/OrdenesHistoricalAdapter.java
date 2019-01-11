package mx.ipn.escom.prueba.coffeeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import mx.ipn.escom.prueba.coffeeapp.databinding.OrdenHistoricalItemViewBinding;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.OrdenHistoricalViewModel;
import mx.ipn.escom.prueba.coffeeapp.views.OrdenesFragment;


public class OrdenesHistoricalAdapter extends RecyclerView.Adapter<OrdenesHistoricalAdapter.OrdenHistoricalViewHolder> {
    private Context mContext;
    private ArrayList<OrdenHistoricalViewModel> mList;
    private LayoutInflater layoutInflater;
    private Integer idCuenta;

    public OrdenesHistoricalAdapter(OrdenesFragment mContext, ArrayList<OrdenHistoricalViewModel> mList, Integer idCuenta) {
        this.mContext = mContext.getContext();
        this.mList = mList;
        this.idCuenta = idCuenta;
    }

    @NonNull
    @Override
    public OrdenHistoricalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        OrdenHistoricalItemViewBinding dataBinding = OrdenHistoricalItemViewBinding.inflate(layoutInflater, parent, false);
        return new OrdenHistoricalViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdenHistoricalViewHolder holder, int position) {
        OrdenHistoricalViewModel model = mList.get(position);
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

    public void setmList(ArrayList<OrdenHistoricalViewModel> mList) {
        this.mList = mList;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public Context getmContext() {
        return mContext;
    }

    public ArrayList<OrdenHistoricalViewModel> getmList() {
        return mList;
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    class OrdenHistoricalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private OrdenHistoricalItemViewBinding ordenHistoricalItemViewBinding;

        public OrdenHistoricalViewHolder(OrdenHistoricalItemViewBinding ordenHistoricalItemViewBinding) {
            super(ordenHistoricalItemViewBinding.getRoot());
            this.ordenHistoricalItemViewBinding = ordenHistoricalItemViewBinding;
            ordenHistoricalItemViewBinding.getRoot().setOnClickListener(this);
        }

        public void bind(OrdenHistoricalViewModel ordenHistoricalViewModel){
            this.ordenHistoricalItemViewBinding.setOrdenHistoricalViewModel(ordenHistoricalViewModel);
        }

        public OrdenHistoricalItemViewBinding getDataBinding(){
            return this.ordenHistoricalItemViewBinding;
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
