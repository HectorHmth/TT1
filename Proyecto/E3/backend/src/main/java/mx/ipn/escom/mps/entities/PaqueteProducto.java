package mx.ipn.escom.mps.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tps10_paquete_producto")
public class PaqueteProducto implements Serializable{
	
	@EmbeddedId
	private PaqueteProductoId id;
	
	@Column(name="id_paquete", insertable=false, updatable=false)
	private Integer idPaquete;
	
	@Column(name="id_local",insertable=false, updatable=false)
	private Integer idLocal;
	
	@Column(name="id_producto", insertable=false, updatable=false)
	private Integer idProducto;
	
	@Column(name="st_aplica_prom")
	private Integer aplicaPromocion;
	
	@ManyToOne
	@JoinColumn(name="id_paquete",referencedColumnName="id_paquete",insertable=false, updatable=false)
	private Paquete paquete;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="id_local", referencedColumnName="id_local", insertable=false, updatable=false),
		@JoinColumn(name="id_producto", referencedColumnName="id_producto", insertable=false, updatable=false)
	})
	private ProductoLocal productoLocal;

	public PaqueteProductoId getId() {
		return id;
	}

	public void setId(PaqueteProductoId id) {
		this.id = id;
	}

	public Integer getIdPaquete() {
		return idPaquete;
	}

	public void setIdPaquete(Integer idPaquete) {
		this.idPaquete = idPaquete;
	}

	public Paquete getPaquete() {
		return paquete;
	}

	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}

	public ProductoLocal getProductoLocal() {
		return productoLocal;
	}

	public void setProductoLocal(ProductoLocal productoLocal) {
		this.productoLocal = productoLocal;
	}

	public Integer getAplicaPromocion() {
		return aplicaPromocion;
	}

	public void setAplicaPromocion(Integer aplicaPromocion) {
		this.aplicaPromocion = aplicaPromocion;
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
	
	
	

}
