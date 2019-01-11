package mx.ipn.escom.mps.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tps09_estado_local")
public class EstadoLocal implements Serializable {
	
	@Id
	@Column(name="id_estado")
	private Integer id;
	
	@Column(name="nb_estado")
	private String nombreEstado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	
}
