package mx.ipn.escom.mor.bs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.ipn.escom.bs.GenericBs;
import mx.ipn.escom.mor.daos.OrdenesDao;
import mx.ipn.escom.mor.entities.Cliente;
import mx.ipn.escom.mor.entities.EstadoOrden;
import mx.ipn.escom.mor.entities.Orden;
import mx.ipn.escom.mor.entities.OrdenProducto;
import mx.ipn.escom.mor.entities.OrdenProductoId;
import mx.ipn.escom.mor.entities.TipoOrden;
import mx.ipn.escom.mor.exception.ProductoAgotadoException;
import mx.ipn.escom.mor.exception.ProductoExistenEnCarritoException;
import mx.ipn.escom.mpg.entities.Efectivo;
import mx.ipn.escom.mps.entities.Disponibilidad;
import mx.ipn.escom.mps.entities.EstadoProductoLocal;
import mx.ipn.escom.mps.entities.Local;
import mx.ipn.escom.mps.entities.Paquete;
import mx.ipn.escom.mps.entities.ProductoLocal;
import mx.ipn.escom.utils.Constants;

@Service("ordenesBs")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class OrdenesBs extends GenericBs {
	
	@Autowired
	private OrdenesDao ordenesDao;
	
	//TODO: Agregar el caso en el cliente no tiene una cuenta
	
	public Local getLocalOfOrdenSinConfirmar(Integer idCuenta) throws NoResultException{
		Cliente cliente = ordenesDao.getClienteByIdCuenta(idCuenta);
		Local local = ordenesDao.getLocalOfOrdenSinConfirmar(cliente);
		return local;
	}
	
	public List<OrdenProducto> getOrdenProductosSinConfirmar(Integer idCuenta) throws NoResultException{
		Cliente cliente = ordenesDao.getClienteByIdCuenta(idCuenta);
		return ordenesDao.getOrdenProductosSinConfirmar(cliente);
	}
	
	public List<OrdenProducto> getAllOrdenesProducto(Integer idCuenta){
		Cliente cliente = ordenesDao.getClienteByIdCuenta(idCuenta);
		return ordenesDao.getAllOrdenesProducto(cliente);
	}
	
	
	@Transactional(rollbackOn = Exception.class)
	public Orden agregarProducto(Integer idLocal, Integer idProducto, Integer nuCantidad, Integer idCuenta,String comentario, Integer ui)
	throws 	ProductoExistenEnCarritoException
			, ProductoAgotadoException{
		System.out.println("--->" + idCuenta);
		Cliente cliente = ordenesDao.getClienteByIdCuenta(idCuenta);
		ProductoLocal producto = ordenesDao.findProductoLocalById(idLocal, idProducto);
		if(!clienteTieneOrdenSinConfirmar(cliente,idLocal)){
			//Crear nueva orden
			System.out.println("--->Se va a crear una nueva orden");
			EstadoOrden estadoOrden = findById(EstadoOrden.class,1);
			Efectivo efectivo = ordenesDao.getEfectivo(cliente);
			TipoOrden tipoOrden = findById(TipoOrden.class,1);
			Orden orden = new Orden();
			orden.setIdCliente(cliente.getId());
			orden.setIdEstadoOrden(estadoOrden.getId());
			orden.setIdFormaPago(efectivo.getId());
			orden.setIdTipo(tipoOrden.getId());
			orden.setCliente(cliente);
			orden.setIdCliente(cliente.getId());
			orden.setFecha(new Date());
			orden.setFechaUltimaActualizacion(new Date());
			orden.setEstadoConfirmacion(false);
			orden.setOrdenProducto(new ArrayList<>());
			orden.setProductosDeLocalEnOrden(new ArrayList<>());
			save(orden);
			updateDisponibilidad(producto,nuCantidad);
			setOrdenProducto(orden, producto,nuCantidad,comentario,Constants.SAVE_ORDEN);
			return orden;
		}else {
			System.out.println("--->Existe la orden");
			Orden orden = ordenesDao.getOrdenSinConfirmar(cliente,idLocal);
			orden.setFechaUltimaActualizacion(new Date());
			System.out.println("--->Updating fecha");
			if(checkProductoInListaProductos(orden, producto)) {
				if(ui == null) {
					throw new ProductoExistenEnCarritoException();
				}else{
					update(orden);
					if(ui == 1)
						updateDisponibilidad(producto,1);//Se resta uno a la disponibilidad
					if(ui == 2)
						updateDisponibilidad(producto,-1);//Se suma uno a la disponibilidad
					updateOrdenProducto(orden,producto,nuCantidad);
					return orden;
				}
			}else {
				System.out.println("--->Actualizar");
				update(orden);
				updateDisponibilidad(producto,nuCantidad);
				setOrdenProducto(orden,producto,nuCantidad,comentario,Constants.UPDATE_ORDEN);
				return orden;
			}
		}
	}
	
	@Transactional(rollbackOn=Exception.class)
	public Orden deleteProductoDeOrden(Integer idOrden,Integer idLocal,Integer idProductoLocal,Integer idCuenta)
	throws ProductoAgotadoException{
		Cliente cliente = ordenesDao.getClienteByIdCuenta(idCuenta);
		Orden orden = ordenesDao.getOrdenSinConfirmar(cliente,idLocal);
		orden.setFechaUltimaActualizacion(new Date());
		ProductoLocal productoLocal = ordenesDao.findProductoLocalById(idLocal, idProductoLocal);
		OrdenProducto ordenProducto = ordenesDao.checkProductoInListaProductos(orden, productoLocal);
		Integer cantidadProducto = ordenProducto.getCantidad();
		delete(ordenProducto);
		updateDisponibilidad(productoLocal, (-1 * cantidadProducto));
		update(orden);
		return orden;
	}
	
	@Transactional(rollbackOn=Exception.class)
	public Orden finalizarCompra(Integer idOrden, Integer idCuenta) {
		Cliente cliente = ordenesDao.getClienteByIdCuenta(idCuenta);
		Orden orden = ordenesDao.getOrdenSinConfirmar(cliente);
		EstadoOrden estadoOrden = findById(EstadoOrden.class,2);
		orden.setFechaUltimaActualizacion(new Date());
		orden.setEstadoConfirmacion(true);
		orden.setEstadoOrden(estadoOrden);
		orden.setIdEstadoOrden(estadoOrden.getId());
		orden.setEstadoPrioridad(1);
		update(orden);
		return orden;
	}
	
	
	public List<Orden> getAllOrdenes(Integer idCuenta){
		Cliente cliente = ordenesDao.getClienteByIdCuenta(idCuenta);
		return ordenesDao.getAllOrdenes(cliente);
	}
	
	private Boolean clienteTieneOrdenSinConfirmar(Cliente cliente, Integer idLocal) {
		try{
			Orden orden = ordenesDao.clienteTieneOrdenSinConfirmar(cliente,idLocal);
			if(orden == null) {
				return false;
			}
			return true;
		}catch(NoResultException e) {
			e.printStackTrace();
			return false;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private Boolean checkProductoInListaProductos(Orden orden, ProductoLocal productoLocal) {
		try {
			OrdenProducto ordenProducto = ordenesDao.checkProductoInListaProductos(orden, productoLocal); 
			if(ordenProducto == null) {
				return false;
			}
			return true;
		}catch(NoResultException e) {
			return false;
		}catch(Exception e) {
			return false;
		}
	}
	
	
	/**
	 * Inserta un producto de un local en una orden.
	 * @param productoLocal es el producto que se va a insertar en la orden
	 * @paran nuCantidad es la cantidad con la que se va a insertar un producto en una orden
	 * */
	private void setOrdenProducto(Orden orden, ProductoLocal productoLocal, Integer nuCantidad, String comentario, Integer type) {
		System.out.println("--->SET ORDEN");
		OrdenProducto ordenProducto = new OrdenProducto();
		ordenProducto.setArticulo(productoLocal);
		ordenProducto.setOrden(orden);
		OrdenProductoId id = new OrdenProductoId();
		id.setIdArticulo(productoLocal.getIdProducto());
		id.setIdLocal(productoLocal.getIdLocal());
		id.setIdOrden(orden.getId());
		ordenProducto.setRating(0.0);
		ordenProducto.setComentario(comentario);
		ordenProducto.setId(id);
		ordenProducto.setCantidad(nuCantidad);
		save(ordenProducto);
		if(type == Constants.SAVE_ORDEN)
			save(orden);
		else
			update(orden);
		System.out.println("--->SET ORDEN SALI");
	}
	
	//TODO:Agregar excepcion de seguridad
	private void updateOrdenProducto(Orden orden,ProductoLocal productoLocal, Integer nuCantidad) {
		try{
			OrdenProducto ordenProducto = ordenesDao.checkProductoInListaProductos(orden, productoLocal);
			ordenProducto.setCantidad(nuCantidad);
			update(ordenProducto);
			update(orden);
		}catch(NoResultException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Actualiza la disponibilidad de un producto en un local.
	 * Si el producto es un paquete se debe actualizar la disponibilidad de los productos asociados.
	 * Si no si es un paquete simplemente se actualiza el producto
	 * @return void
	 * @throws ProductoAgotadoException
	 * */
	private void updateDisponibilidad(ProductoLocal productoLocal,Integer nuCantidad) throws ProductoAgotadoException{
		if(isPaquete(productoLocal)) {
			System.out.println("--->UPDATE DISPONIBILIDAD");
			Paquete paquete = findById(Paquete.class, productoLocal.getIdProducto());
			Integer disponibilidadPaquete = ordenesDao.getDisponibilidadProducto(productoLocal).getNuExistenciaActual();
			Integer disponibilidadActual;
			for(ProductoLocal productoLocalEnPaquete:paquete.getProductosEnPaquete()) {
				updateDisponibilidadProductoLocal(productoLocalEnPaquete,nuCantidad,Constants.ACTUALIZAR_EXISTENCIA_BY_RESTA);
				disponibilidadActual = ordenesDao.getDisponibilidadProducto(productoLocalEnPaquete).getNuExistenciaActual();
				if(disponibilidadActual < disponibilidadPaquete) {
					disponibilidadPaquete = disponibilidadActual;
				}
			}
			updateDisponibilidadProductoLocal(productoLocal, disponibilidadPaquete,Constants.ACTUALIZAR_EXISTENICA_BY_REEMPLAZO);
		}else {
			updateDisponibilidadProductoLocal(productoLocal,nuCantidad,Constants.ACTUALIZAR_EXISTENCIA_BY_RESTA);
		}
		System.out.println("--->UPDATE DISPONIBILIDAD SALI");
	}
	
	
	/**
	 * Actualiza la disponbilidad de un producto
	 * @param productoLocal es el producto en un local al que se le va a actualizar su existencia
	 * @param nuCantidad Es el número que representa la resta de existencia que se va hacer a la disponibildad
	 * 					 del producto o por la que se va actualizar
	 * @param type Si es 0 entonces se deberá actualizar la existencia con una resta
	 * 			   Si es 1 entonces se deberá actualizar la existencia de un producto por nuCantidad 
	 * */
	
	private void updateDisponibilidadProductoLocal(ProductoLocal productoLocal,Integer nuCantidad, Integer type) 
	throws ProductoAgotadoException{
		Disponibilidad productoDisponibilidad = ordenesDao.getDisponibilidadProducto(productoLocal);
		if(type == Constants.ACTUALIZAR_EXISTENCIA_BY_RESTA) {
			productoDisponibilidad.setNuExistenciaActual(productoDisponibilidad.getNuExistenciaActual() - nuCantidad);
		}else {
			productoDisponibilidad.setNuExistenciaActual(nuCantidad);
		}
		if(productoDisponibilidad.getNuExistenciaActual() > 0) {
			update(productoDisponibilidad);
		}else {
			productoDisponibilidad.setFechaAgotamiento(new Date());
			productoDisponibilidad.setNuExistenciaActual(0);
			update(productoDisponibilidad);
			EstadoProductoLocal estado = findById(EstadoProductoLocal.class,2);
			productoLocal.setEstadoProductoLocal(estado);
			productoLocal.setIdEstadoProductoLocal(estado.getId());
			update(productoLocal);
			throw new ProductoAgotadoException();
		}
	}

	
	/**
	 * Verifica si el producto es un producto o un paquete
	 * @param productoLocal Es el producto que se va a comprobrar
	 * @return true si es paquete false si no es paquete
	 * */
	private Boolean isPaquete(ProductoLocal productoLocal) {
		Boolean isPaquete;
		try {
			isPaquete = ordenesDao.isPaquete(productoLocal);
		}catch(NoResultException e) {
			isPaquete = false;
		}
		return isPaquete;
	}
}
