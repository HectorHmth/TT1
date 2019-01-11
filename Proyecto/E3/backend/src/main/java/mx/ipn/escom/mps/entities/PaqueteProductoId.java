package mx.ipn.escom.mps.entities;

import java.io.Serializable;

import javax.persistence.Column;

public class PaqueteProductoId implements Serializable {
	
	@Column(name="id_paquete",nullable=false)
	private Integer idPaquete;

	@Column(name="id_local", nullable=false)
	private Integer idLocal;
	
	@Column(name="id_producto", nullable=false)
	private Integer idProducto;
	

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

	public Integer getIdPaquete() {
		return idPaquete;
	}

	public void setIdPaquete(Integer idPaquete) {
		this.idPaquete = idPaquete;
	}
	
	

}
