package mx.ipn.escom.mps.bs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.ipn.escom.bs.GenericBs;
import mx.ipn.escom.mps.daos.ProductosDao;
import mx.ipn.escom.mps.entities.ProductoLocal;

@Service("prodcutosBs")
@Scope(value=BeanDefinition.SCOPE_SINGLETON)
public class ProductosBs extends GenericBs{
	
	@Autowired
	private ProductosDao productosDao;
	
	public List<ProductoLocal> getProductosByNombreLocal(String nombreLocal){
		return productosDao.getProductosByNombreLocal(nombreLocal);
	}
	
	
	public List<ProductoLocal> getPaquetesByNombreLocal(String nombreLocal){
		return productosDao.getPaquetesByNombreLocal(nombreLocal);
	}
	
	public ProductoLocal getProductoLocalById(Integer idLocal, Integer idProducto) {
		return productosDao.getProductoLocalById(idLocal, idProducto);
	}
	
	public Boolean isProductoDisponible(Integer idLocal, Integer idProducto, Integer nuCount) {
		return productosDao.isProductoDisponible(idLocal, idProducto, nuCount);
	}
	

}
