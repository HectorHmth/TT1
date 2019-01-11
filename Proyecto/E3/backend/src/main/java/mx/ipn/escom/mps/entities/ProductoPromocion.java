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
@Table(name="tps14_producto_promocion")
public class ProductoPromocion implements Serializable {
	
	@EmbeddedId
	private ProductoPromocionId id;
	
	@Column(name="id_promocion",insertable=false,updatable=false)
	private Integer idPromocion;

	@Column(name="id_local",insertable=false,updatable=false)
	private Integer idLocal;
	
	@Column(name="id_producto",insertable=false,updatable=false)
	private Integer idProducto;
	
	@Column(name="nu_precio_final")
	private Double precioFinal;
	
	@ManyToOne
	@JoinColumn(name="id_promocion", referencedColumnName="id_promocion",insertable=false, updatable=false)
	private Promocion promocion;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="id_local", referencedColumnName="id_local", insertable=false, updatable=false),
		@JoinColumn(name="id_producto", referencedColumnName="id_producto", insertable=false, updatable=false)})
	private ProductoLocal productoLocal;
	
	

}
