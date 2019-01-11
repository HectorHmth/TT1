package mx.ipn.escom.mps.entities;

import java.io.Serializable;

import javax.persistence.Column;

public class ProductoPromocionId implements Serializable{
	
	@Column(name="id_promocion", nullable=false)
	private Integer idPromocion;
	
	@Column(name="id_local", nullable=false)
	private Integer idLocal;
	
	@Column(name="id_producto", nullable=false)
	private Integer idProducto;

	public Integer getIdPromocion() {
		return idPromocion;
	}

	public void setIdPromocion(Integer idPromocion) {
		this.idPromocion = idPromocion;
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
