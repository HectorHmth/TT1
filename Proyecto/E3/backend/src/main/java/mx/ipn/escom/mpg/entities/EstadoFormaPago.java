package mx.ipn.escom.mpg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="tpg07_estado_forma_pago")
public class EstadoFormaPago {

	@Id
	@Column(name = "id_estado")
	private Integer idEstado;
	
	@Column(name = "nb_estado")
	private String nombreEstado;

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	
	

}
