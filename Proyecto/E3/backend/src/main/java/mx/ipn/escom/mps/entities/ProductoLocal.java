package mx.ipn.escom.mps.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tps09_producto_local")
public class ProductoLocal implements Serializable{
	
	@JsonIgnore
	@EmbeddedId
	private ProductoLocalId id;
	
	@Column(name="id_local",insertable=false, updatable=false)
	private Integer idLocal;
	
	@Column(name="id_producto",insertable=false, updatable=false)
	private Integer idProducto;
	
	@JsonIgnore
	@Column(name="id_estado")
	private Integer idEstadoProductoLocal;
	
	@Column(name="nu_precio_venta")
	private Double precioVenta;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_local", referencedColumnName="id_local", updatable=false, insertable=false)
	private Local local;
	
	@ManyToOne
	@JoinColumn(name="id_producto", referencedColumnName="id_producto",updatable=false, insertable=false)
	private Producto producto;
	
	
	@ManyToOne
	@JoinColumn(name="id_estado", referencedColumnName="id_estado", updatable=false, insertable=false)
	private EstadoProductoLocal estadoProductoLocal;
	
	
	@JsonIgnore
	@OneToMany
	@JoinColumns({
		@JoinColumn(name="id_local", referencedColumnName="id_local", updatable=false, insertable=false),
		@JoinColumn(name="id_producto", referencedColumnName="id_producto",updatable=false, insertable=false)
	})
	private List<Disponibilidad> disponibilidadDelProducto;
	
	
	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public EstadoProductoLocal getEstadoProductoLocal() {
		return estadoProductoLocal;
	}

	public void setEstadoProductoLocal(EstadoProductoLocal estadoProductoLocal) {
		this.estadoProductoLocal = estadoProductoLocal;
	}

	public ProductoLocalId getId() {
		return id;
	}

	public void setId(ProductoLocalId id) {
		this.id = id;
	}

	public Integer getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(Integer idLocal) {
		this.idLocal = idLocal;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public List<Disponibilidad> getDisponibilidadDelProducto() {
		return disponibilidadDelProducto;
	}

	public void setDisponibilidadDelProducto(List<Disponibilidad> disponibilidadDelProducto) {
		this.disponibilidadDelProducto = disponibilidadDelProducto;
	}

	public Integer getIdEstadoProductoLocal() {
		return idEstadoProductoLocal;
	}

	public void setIdEstadoProductoLocal(Integer idEstadoProductoLocal) {
		this.idEstadoProductoLocal = idEstadoProductoLocal;
	}
	
	
	
	
}
