package mx.ipn.escom.prueba.coffeeapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.tfb.fbtoast.FBToast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import mx.ipn.escom.prueba.coffeeapp.databinding.OrdenProductoItemViewBinding;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.OrdenProductoViewModel;
import mx.ipn.escom.prueba.coffeeapp.views.CarritoFragment;

public class OrdenProductosAdapter extends RecyclerView.Adapter<OrdenProductosAdapter.OrdenProductoViewHolder>{

    private Context mContext;
    private ArrayList<OrdenProductoViewModel> mList;
    private LayoutInflater layoutInflater;
    private Integer idCuenta;
    private OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
        void onAddCantidad(int position);
        void onSubstractCantidad(int position);
        void onDeleteProductoDeOrden(int position);
    }

    public void setOnItemClickListener(OnItemClickListener mListener){
         this.mListener = mListener;
    }

    public OrdenProductosAdapter(CarritoFragment mContext, ArrayList<OrdenProductoViewModel> mList, Integer idCuenta){
        this.mContext = mContext.getContext();
        this.mList = mList;
        this.idCuenta = idCuenta;
    }

    @NonNull
    @Override
    public OrdenProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        OrdenProductoItemViewBinding dataBinding =  OrdenProductoItemViewBinding.inflate(layoutInflater, parent, false);
        return new OrdenProductoViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdenProductoViewHolder holder, int position) {
        OrdenProductoViewModel ordenProductoViewModel = mList.get(position);
        holder.bind(ordenProductoViewModel);

    }

    @Override
    public int getItemCount() {
        if (mList == null){
            return 0;
        }
        return mList.size();
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<OrdenProductoViewModel> getmList() {
        return mList;
    }

    public void setmList(ArrayList<OrdenProductoViewModel> mList) {
        this.mList = mList;
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    class OrdenProductoViewHolder extends RecyclerView.ViewHolder {
        private OrdenProductoItemViewBinding ordenProductoItemViewBinding;

        public OrdenProductoViewHolder(OrdenProductoItemViewBinding ordenProductoItemViewBinding){
            super(ordenProductoItemViewBinding.getRoot());
            this.ordenProductoItemViewBinding = ordenProductoItemViewBinding;
        }

        public void bind(OrdenProductoViewModel ordenProductoViewModel){
            this.ordenProductoItemViewBinding.setViewModel(ordenProductoViewModel);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
            ordenProductoItemViewBinding.moreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                                mListener.onAddCantidad(position);
                        }
                    }
                }
            });
            ordenProductoItemViewBinding.lessButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onSubstractCantidad(position);
                        }
                    }
                }
            });
            ordenProductoItemViewBinding.eliminarProductoDeOrden.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onDeleteProductoDeOrden(position);
                        }
                    }
                }
            });
        }

        public OrdenProductoItemViewBinding getDataBinding(){
            return this.ordenProductoItemViewBinding;
        }

    }
}
