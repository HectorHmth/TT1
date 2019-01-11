package mx.ipn.escom.mor.entities;
import java.io.Serializable;
import javax.persistence.Column;

public class OrdenProductoId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "id_orden", nullable = false)
	private Integer idOrden;
	
	@Column(name = "id_local", nullable = false)
	private Integer idLocal;
	
	@Column(name = "id_articulo", nullable = false)
	private Integer idArticulo;

	public Integer getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(Integer idOrden) {
		this.idOrden = idOrden;
	}

	public Integer getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(Integer idArticulo) {
		this.idArticulo = idArticulo;
	}

	public Integer getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(Integer idLocal) {
		this.idLocal = idLocal;
	}
	
	
	
	
}