package mx.ipn.escom.mor.entities;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import mx.ipn.escom.mps.entities.ProductoLocal;

@Entity
@Table(name = "tpd06_orden_producto")
public class OrdenProducto {
	
	
	@JsonIgnore
	@EmbeddedId
	private OrdenProductoId id;
	
	@Column(name="id_orden",insertable=false,updatable=false)
	private Integer idOrden;
	
	@Column(name="id_local",insertable=false,updatable=false)
	private Integer idLocal;
	
	@JsonProperty("idProductoLocal")
	@Column(name="id_articulo",insertable=false,updatable=false)
	private Integer idArticulo;	
	
	@Column(name = "tx_comentaro")
	private String comentario;
	
	@Column(name = "nu_rating")
	private Double rating;
	
	@Column(name = "nu_cantidad")
	private Integer cantidad;
	
	@ManyToOne
	@MapsId("orden")
	@JoinColumn(name="id_orden",referencedColumnName="id_orden",insertable=false, updatable=false)
	private Orden orden;
	
	@ManyToOne
	@MapsId("articulo")
	@JoinColumn(name="id_articulo", referencedColumnName="id_producto",insertable=false, updatable=false)
	private ProductoLocal articulo;
	
	

	public OrdenProductoId getId() {
		return id;
	}

	public void setId(OrdenProductoId id) {
		this.id = id;
	}

	public Integer getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(Integer idOrden) {
		this.idOrden = idOrden;
	}

	public Integer getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(Integer idLocal) {
		this.idLocal = idLocal;
	}

	public Integer getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(Integer idArticulo) {
		this.idArticulo = idArticulo;
	}

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

	@JsonProperty("productoLocal")
	public ProductoLocal getArticulo() {
		return articulo;
	}

	public void setArticulo(ProductoLocal articulo) {
		this.articulo = articulo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
	

}