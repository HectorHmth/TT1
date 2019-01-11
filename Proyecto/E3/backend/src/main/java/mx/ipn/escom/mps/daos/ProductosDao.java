package mx.ipn.escom.mps.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.ipn.escom.mps.entities.Disponibilidad;
import mx.ipn.escom.mps.entities.EstadoProductoLocal;
import mx.ipn.escom.mps.entities.Producto;
import mx.ipn.escom.mps.entities.ProductoLocal;

@Service("productosDao")
@Scope(value=BeanDefinition.SCOPE_SINGLETON)
public class ProductosDao {
	//select idLocal,idProducto,precioVenta,estadoProductoLocal.nombreEstado, producto.nombreProducto,producto.tmEstimadoPrep,producto.nuRating,producto.descripcion from ProductoLocal where producto.id not in (from Paquete) and local.nombreLocal = ?
	private static final String QUERY_1="from ProductoLocal where producto.id not in (from Paquete) and local.nombreLocal = ?";
	private static final String QUERY_2 ="from ProductoLocal where producto.id in (from Paquete) and local.nombreLocal = ?";
	private static final String QUERY_3 ="from ProductoLocal where idLocal = ? and idProducto = ?";
	private static final String QUERY_4 = "from Disponibilidad where idLocal = ? and idProducto = ?";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<ProductoLocal> getProductosByNombreLocal(String nombreLocal){
		Query query = entityManager.createQuery(QUERY_1,ProductoLocal.class);
		query.setParameter(0, nombreLocal);
		List<ProductoLocal> resultado = query.getResultList();
		return resultado;
	}
	
	public List<ProductoLocal> getPaquetesByNombreLocal(String nombreLocal){
		Query query = entityManager.createQuery(QUERY_2,ProductoLocal.class);
		query.setParameter(0, nombreLocal);
		List<ProductoLocal> resultList = query.getResultList();
		return resultList;
		
	}
	
	public ProductoLocal getProductoLocalById(Integer idLocal, Integer idProducto) {
		Query query = entityManager.createQuery(QUERY_3,ProductoLocal.class);
		query.setParameter(0, idLocal);
		query.setParameter(1, idProducto);
		return (ProductoLocal)query.getSingleResult();
	}
	
	
	public Boolean isProductoDisponible(Integer idLocal, Integer idProducto, Integer nuCount) {
		ProductoLocal productoLocal = getProductoLocalById(idLocal, idProducto);
		Query query = entityManager.createQuery(QUERY_4,Disponibilidad.class);
		query.setParameter(0, productoLocal.getIdLocal());
		query.setParameter(1, productoLocal.getIdProducto());
		Disponibilidad disponibilidad = (Disponibilidad)query.getSingleResult();
		if(disponibilidad.getNuExistenciaActual() > nuCount && disponibilidad.getNuExistenciaActual() > 0) {
			return true;
		}else {
			return false;
		}	
	}
	

}
