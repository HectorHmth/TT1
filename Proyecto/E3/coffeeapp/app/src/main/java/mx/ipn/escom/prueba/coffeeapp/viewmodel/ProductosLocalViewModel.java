package mx.ipn.escom.prueba.coffeeapp.viewmodel;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import mx.ipn.escom.prueba.coffeeapp.entities.ProductoLocal;
import mx.ipn.escom.prueba.coffeeapp.business.ProductosLocalBussines;

public class ProductosLocalViewModel extends ViewModel{
    public MutableLiveData<Integer> errorGlobal;
    private ProductosLocalBussines productosLocalBussines;
    private Integer progress;
    private MutableLiveData<List<ProductoLocal>> productosLocal;

    public ProductosLocalViewModel(){

        this.productosLocal=new MutableLiveData<>();
    }

    public void setProductoLocalBussines(Application application){
        this.productosLocalBussines=new ProductosLocalBussines(application);
    }

    public ProductosLocalBussines getProductoLocalBussines(){
        return productosLocalBussines;
    }

    public MutableLiveData<List<ProductoLocal>> getProductosLocal(){
        return productosLocal;
    }

    public void setProductosLocal(MutableLiveData<List<ProductoLocal>> productosLocal){
        this.productosLocal=productosLocal;
    }



    public ArrayList<ProductoLocalViewModel>getListaDeProductosLocal(String nombreLocal){
        try{
            List<ProductoLocal> productosObtenidos = productosLocalBussines.getProductos(nombreLocal);
            return bindResult(productosObtenidos);
        }catch (Exception e){
            e.printStackTrace();
            Log.d("--->","ERROR");
        }
        return null;
    }

    public ArrayList<ProductoLocalViewModel>bindResult(List<ProductoLocal> listaProductosLocal){
        ArrayList<ProductoLocalViewModel> productosLocalViewModel = new ArrayList<>();
        Log.d("-->bind Result","Entre"+listaProductosLocal.size());

        for(ProductoLocal productoLocal: listaProductosLocal){
            Log.d("-->","-"+productoLocal.getProducto().getNombreProducto()
                    + "-" + productoLocal.getProducto().getDescripcion()
                    + "-" + productoLocal.getProducto().getNuRating()
                    + "-" + productoLocal.getPrecioVenta());
            ProductoLocalViewModel productoLocalViewModel= new ProductoLocalViewModel();
            productoLocalViewModel.nombreProductoLocal=productoLocal.getProducto().getNombreProducto();
            productoLocalViewModel.descripcionProductoLocal=productoLocal.getProducto().getDescripcion();
            productoLocalViewModel.precioProductoLocal="$"+String.valueOf(productoLocal.getPrecioVenta());
            productoLocalViewModel.calificacionProductoLocal=String.valueOf(productoLocal.getProducto().getNuRating());
            productoLocalViewModel.idLocal=productoLocal.getIdLocal();
            productoLocalViewModel.idProducto=productoLocal.getIdProducto();
            productosLocalViewModel.add(productoLocalViewModel);
        }
        return productosLocalViewModel;
    }
}
