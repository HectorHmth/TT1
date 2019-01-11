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
import mx.ipn.escom.prueba.coffeeapp.databinding.ProductoItemViewBinding;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.ProductoLocalViewModel;
import mx.ipn.escom.prueba.coffeeapp.views.ProductoDetailActivity;
import mx.ipn.escom.prueba.coffeeapp.views.ProductosFragment;


public class ProductosLocalesAdapter extends RecyclerView.Adapter<ProductosLocalesAdapter.ProductoLocalViewHolder> {
    private Context mContext;
    private ArrayList<ProductoLocalViewModel> mList;
    private LayoutInflater layoutInflater;
    private Integer idCuenta;

    public ProductosLocalesAdapter(ProductosFragment mContext,ArrayList<ProductoLocalViewModel> mList,Integer idCuenta){
        this.mContext=mContext.getContext();
        this.mList=mList;
        this.idCuenta = idCuenta;
    }

    @NonNull
    @Override
    public ProductoLocalViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        if(layoutInflater==null){
            layoutInflater=LayoutInflater.from(parent.getContext());
        }
        ProductoItemViewBinding dataBinding=ProductoItemViewBinding.inflate(layoutInflater,parent,false);
        return new ProductoLocalViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoLocalViewHolder holder,int position){
        ProductoLocalViewModel model=mList.get(position);
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

    public void setmList(ArrayList<ProductoLocalViewModel> mList) {
        this.mList = mList;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public Context getmContext() {
        return mContext;
    }

    public ArrayList<ProductoLocalViewModel> getmList() {
        return mList;
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    class ProductoLocalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ProductoItemViewBinding productoLocalItemViewBinding;

        public ProductoLocalViewHolder(ProductoItemViewBinding productoLocalItemViewBinding){
            super(productoLocalItemViewBinding.getRoot());
            this.productoLocalItemViewBinding=productoLocalItemViewBinding;
            productoLocalItemViewBinding.getRoot().setOnClickListener(this);
        }

        public void bind(ProductoLocalViewModel productoLocalViewModel){
            this.productoLocalItemViewBinding.setProductoLocalViewModel(productoLocalViewModel);
        }

        public ProductoItemViewBinding getDataBinding(){
            return this.productoLocalItemViewBinding;
        }

        @Override
        public void onClick(View view){
            Intent intent = new Intent(view.getContext(), ProductoDetailActivity.class);

            intent.putExtra("ID_LOCAL", productoLocalItemViewBinding.getProductoLocalViewModel().idLocal);
            intent.putExtra("ID_PRODUCTO", productoLocalItemViewBinding.getProductoLocalViewModel().idProducto);
            intent.putExtra("ID_CUENTA", idCuenta);
            view.getContext().startActivity(intent);
        }
    }
}
