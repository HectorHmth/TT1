package mx.ipn.escom.prueba.coffeeapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import mx.ipn.escom.prueba.coffeeapp.NetworkBroadcastReceiver;
import mx.ipn.escom.prueba.coffeeapp.constants.RequestCodes;
import mx.ipn.escom.prueba.coffeeapp.databinding.LocalItemViewBinding;
import mx.ipn.escom.prueba.coffeeapp.entities.Orden;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.LocalViewModel;
import mx.ipn.escom.prueba.coffeeapp.views.LocalProductosActivity;
import mx.ipn.escom.prueba.coffeeapp.views.LocalesFragment;


public class LocalesAdapter extends RecyclerView.Adapter<LocalesAdapter.LocalViewHolder> {

    private Context mContext;
    private ArrayList<LocalViewModel> mList;
    private LayoutInflater layoutInflater;
    private Integer idCuenta;

    public LocalesAdapter(LocalesFragment mContext, ArrayList<LocalViewModel> mList, Integer idCuenta) {
        this.mContext = mContext.getContext();
        this.mList = mList;
        this.idCuenta = idCuenta;
    }

    @NonNull
    @Override
    public LocalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        LocalItemViewBinding dataBinding = LocalItemViewBinding.inflate(layoutInflater, parent, false);
        return new LocalViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalViewHolder holder, int position) {
        LocalViewModel model = mList.get(position);
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

    public void setmList(ArrayList<LocalViewModel> mList) {
        this.mList = mList;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public Context getmContext() {
        return mContext;
    }

    public ArrayList<LocalViewModel> getmList() {
        return mList;
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    class LocalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private LocalItemViewBinding localItemViewBinding;


        public LocalViewHolder(LocalItemViewBinding localItemViewBinding) {
            super(localItemViewBinding.getRoot());
            this.localItemViewBinding = localItemViewBinding;
            localItemViewBinding.getRoot().setOnClickListener(this);
        }

        public void bind(LocalViewModel localViewModel){
            this.localItemViewBinding.setLocalViewModel(localViewModel);
        }

        public LocalItemViewBinding getDataBinding(){
            return this.localItemViewBinding;
        }


        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), LocalProductosActivity.class);
            intent.putExtra("LOCAL", localItemViewBinding.getLocalViewModel().nombreLocal);
            intent.putExtra("CUENTA_ID", idCuenta);
            view.getContext().startActivity(intent);
        }
    }
}
