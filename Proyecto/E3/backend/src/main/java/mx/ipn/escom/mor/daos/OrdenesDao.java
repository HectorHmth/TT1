package mx.ipn.escom.mor.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.ipn.escom.mor.entities.Cliente;
import mx.ipn.escom.mor.entities.Orden;
import mx.ipn.escom.mor.entities.OrdenProducto;
import mx.ipn.escom.mpg.entities.Efectivo;
import mx.ipn.escom.mps.entities.Disponibilidad;
import mx.ipn.escom.mps.entities.Local;
import mx.ipn.escom.mps.entities.ProductoLocal;

@Service("ordenesDao")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class OrdenesDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final String QUERY_1 = "from Cliente where idPersona = :idCuenta";
	private static final String QUERY_2 = "select ord from Orden ord join ord.ordenProducto ordProd where ordProd.idLocal = :idLocal and ord.idCliente = :idCliente and ord.estadoConfirmacion = false and ord.estadoOrden.nombreEstado = :nombreEstado";
	private static final String QUERY_3 = "from Efectivo where idCliente = :idCliente";
	private static final String QUERY_4 = "from ProductoLocal where idProducto = :idProducto and idLocal = :idLocal";
	private static final String QUERY_5 = "from OrdenProducto where idOrden = :idOrden and idArticulo = :idProducto and idLocal = :idLocal";
	private static final String QUERY_6 = "select ord.orden from OrdenProducto ord join ord.orden where ord.orden.idCliente = :idCliente";
	private static final String QUERY_7 = "from Disponibilidad where idLocal = :idLocal and idProducto = :idProducto";
	private static final String QUERY_8 = "from ProductoLocal pl where pl.idProducto in(from Paquete) and pl.idProducto = :idProducto";
	private static final String QUERY_9 = "from Local where id in (select ordProducto.idLocal from Orden ord join ord.ordenProducto ordProducto where ord.idCliente = :idCliente and ord.estadoConfirmacion = false)";
	private static final String QUERY_10 = "from OrdenProducto where orden.estadoConfirmacion = false and orden.idCliente = :idCliente";
	private static final String QUERY_11 = "from Orden where idCliente = :idCliente and estadoConfirmacion = false and estadoOrden.nombreEstado = :nombreEstado";
	private static final String QUERY_12 = "select ord from OrdenProducto ord join ord.orden where ord.orden.idCliente = :idCliente";

	
	public Cliente getClienteByIdCuenta(Integer idCuenta) {
		Query query =  entityManager.createQuery(QUERY_1, Cliente.class);
		query.setParameter("idCuenta", idCuenta);
		return (Cliente) query.getSingleResult();
	}
	
	public Orden clienteTieneOrdenSinConfirmar(Cliente cliente, Integer idLocal) {
		Query query = entityManager.createQuery(QUERY_2, Orden.class);
		query.setParameter("idCliente", cliente.getId());
		query.setParameter("nombreEstado", "Sin Confirmar");
		query.setParameter("idLocal", idLocal);
		return (Orden) query.getSingleResult();		
	}
	
	public Orden getOrdenSinConfirmar(Cliente cliente,Integer idLocal) {
		Query query = entityManager.createQuery(QUERY_2, Orden.class);
		query.setParameter("idCliente", cliente.getId());
		query.setParameter("nombreEstado", "Sin Confirmar");
		query.setParameter("idLocal", idLocal);
		return (Orden) query.getSingleResult();
	}
	
	public Efectivo getEfectivo(Cliente cliente) {
		Query query = entityManager.createQuery(QUERY_3, Efectivo.class);
		query.setParameter("idCliente", cliente.getId());
		return (Efectivo) query.getSingleResult();		
	}
	
	
	public ProductoLocal findProductoLocalById(Integer idLocal, Integer idProducto) {
		Query query = entityManager.createQuery(QUERY_4, ProductoLocal.class);
		query.setParameter("idProducto", idProducto);
		query.setParameter("idLocal", idLocal);
		return (ProductoLocal) query.getSingleResult();
	}
	
	public OrdenProducto checkProductoInListaProductos(Orden orden,ProductoLocal productoLocal) {
		Query query = entityManager.createQuery(QUERY_5);
		query.setParameter("idOrden", orden.getId());
		query.setParameter("idProducto",productoLocal.getIdProducto());
		query.setParameter("idLocal", productoLocal.getIdLocal());
		OrdenProducto ordenProducto = (OrdenProducto)query.getSingleResult();
		return ordenProducto;
	}
	
	
	public List<Orden> getAllOrdenes(Cliente cliente){
		Query query = entityManager.createQuery(QUERY_6);
		query.setParameter("idCliente", cliente.getId());
		return (List<Orden>) query.getResultList();
	}
	
	public Boolean isPaquete(ProductoLocal productoLocal) {
		Query query = entityManager.createQuery(QUERY_8);
		query.setParameter("idProducto", productoLocal.getIdProducto());
		ProductoLocal respuesta = (ProductoLocal)query.getSingleResult();
		if(respuesta != null) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public Disponibilidad getDisponibilidadProducto(ProductoLocal productoLocal) {
		Query query = entityManager.createQuery(QUERY_7);
		query.setParameter("idLocal", productoLocal.getIdLocal());
		query.setParameter("idProducto", productoLocal.getIdProducto());
		return (Disponibilidad)query.getSingleResult();
	}
	
	
	/**
	 * Obtiene el local de una cuenta que tenga una orden cuyo estado sea sin confirmar
	 * */
	public Local getLocalOfOrdenSinConfirmar(Cliente cliente) {
		Query query = entityManager.createQuery(QUERY_9);
		query.setParameter("idCliente", cliente.getId());
		return (Local) query.getSingleResult();
	}
	
	
	public List<OrdenProducto> getOrdenProductosSinConfirmar(Cliente cliente){
		Query query = entityManager.createQuery(QUERY_10,OrdenProducto.class);
		query.setParameter("idCliente", cliente.getId());
		return (List<OrdenProducto>)query.getResultList();
	}
	
	public Orden getOrdenSinConfirmar(Cliente cliente) {
		Query query = entityManager.createQuery(QUERY_11,Orden.class);
		query.setParameter("idCliente", cliente.getId());
		query.setParameter("nombreEstado", "Sin Confirmar");
		return (Orden) query.getSingleResult();
	}
	
	public List<OrdenProducto> getAllOrdenesProducto(Cliente cliente){
		Query query =  entityManager.createQuery(QUERY_12);
		query.setParameter("idCliente", cliente.getId());
		return (List<OrdenProducto>)query.getResultList();
	}


}
