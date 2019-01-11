package mx.ipn.escom.mor.entities;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mx.ipn.escom.mpg.entities.FormaPago;
import mx.ipn.escom.mps.entities.ProductoLocal;

@Entity
@Table(name = "tpd02_orden")
@Inheritance(strategy = InheritanceType.JOINED)
public class Orden implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SequenceGenerator(name = "tpd02_orden_sequence", sequenceName = "tpd02_orden_sequence", allocationSize = 1)
	@GeneratedValue(generator = "tpd02_orden_sequence", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id_orden")
	private Integer id;
	
	@JsonIgnore
	@Column(name = "id_cliente")
	private Integer idCliente;
	
	@JsonIgnore
	@Column(name = "id_estado")
	private Integer idEstadoOrden;
	
	@JsonIgnore
	@Column(name = "id_forma_pago")
	private Integer idFormaPago;
	
	@JsonIgnore
	@Column(name = "id_tipo")
	private Integer idTipo;
	
	@Column(name = "fh_dia")
	private Date fecha;
	
	@Column(name = "fh_ultima_act")
	private Date fechaUltimaActualizacion;
	
	@JsonIgnore
	@Column(name = "st_prioridad")
	private Integer estadoPrioridad;
	
	@JsonIgnore
	@Column(name = "st_confirmacion")
	private boolean estadoConfirmacion;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_cliente",referencedColumnName="id_cliente",updatable=false, insertable=false)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="id_estado",referencedColumnName="id_estado",updatable=false, insertable=false)
	private EstadoOrden estadoOrden;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_cola",referencedColumnName="id_cola",updatable=false, insertable=false)
	private ColaEspera cola;
	
	
	@ManyToOne
	@JoinColumn(name="id_forma_pago",referencedColumnName="id_forma_pago",updatable=false, insertable=false)
	private FormaPago formaDePago;
	
	@ManyToOne
	@JoinColumn(name="id_tipo",referencedColumnName="id_tipo",updatable=false, insertable=false)
	private TipoOrden tipoDeOrden;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="id_orden", referencedColumnName="id_orden", updatable=false, insertable=false)
	private OrdenAtencion ordenEnAtencion;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="tpd06_orden_producto", joinColumns= {
			@JoinColumn(name="id_orden", referencedColumnName="id_orden", updatable=false, insertable=false)},
			inverseJoinColumns= {
					@JoinColumn(name="id_local", referencedColumnName="id_local", updatable=false, insertable=false),
					@JoinColumn(name="id_articulo", referencedColumnName="id_producto", updatable=false, insertable=false )})
	private List<ProductoLocal> productosDeLocalEnOrden;
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name="id_orden",referencedColumnName="id_orden", updatable=false, insertable=false)
	private List<OrdenProducto> ordenProducto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	public Integer getEstadoPrioridad() {
		return estadoPrioridad;
	}

	public void setEstadoPrioridad(Integer estadoPrioridad) {
		this.estadoPrioridad = estadoPrioridad;
	}

	public boolean isEstadoConfirmacion() {
		return estadoConfirmacion;
	}

	public void setEstadoConfirmacion(boolean estadoConfirmacion) {
		this.estadoConfirmacion = estadoConfirmacion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public EstadoOrden getEstadoOrden() {
		return estadoOrden;
	}

	public void setEstadoOrden(EstadoOrden estadoOrden) {
		this.estadoOrden = estadoOrden;
	}

	public ColaEspera getCola() {
		return cola;
	}

	public void setCola(ColaEspera cola) {
		this.cola = cola;
	}

	public FormaPago getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(FormaPago formaDePago) {
		this.formaDePago = formaDePago;
	}

	public TipoOrden getTipoDeOrden() {
		return tipoDeOrden;
	}

	public void setTipoDeOrden(TipoOrden tipoDeOrden) {
		this.tipoDeOrden = tipoDeOrden;
	}

	public OrdenAtencion getOrdenEnAtencion() {
		return ordenEnAtencion;
	}

	public void setOrdenEnAtencion(OrdenAtencion ordenEnAtencion) {
		this.ordenEnAtencion = ordenEnAtencion;
	}

	public List<ProductoLocal> getProductosDeLocalEnOrden() {
		return productosDeLocalEnOrden;
	}

	public void setProductosDeLocalEnOrden(List<ProductoLocal> productosDeLocalEnOrden) {
		this.productosDeLocalEnOrden = productosDeLocalEnOrden;
	}

	public List<OrdenProducto> getOrdenProducto() {
		return ordenProducto;
	}

	public void setOrdenProducto(List<OrdenProducto> ordenProducto) {
		this.ordenProducto = ordenProducto;
	}

	public Integer getIdEstadoOrden() {
		return idEstadoOrden;
	}

	public void setIdEstadoOrden(Integer idEstadoOrden) {
		this.idEstadoOrden = idEstadoOrden;
	}

	public Integer getIdFormaPago() {
		return idFormaPago;
	}

	public void setIdFormaPago(Integer idFormaPago) {
		this.idFormaPago = idFormaPago;
	}

	public Integer getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}
	
}
