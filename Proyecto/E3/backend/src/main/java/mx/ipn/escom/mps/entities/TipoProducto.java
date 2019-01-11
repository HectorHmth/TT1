package mx.ipn.escom.mps.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tps06_tipo_producto")
public class TipoProducto implements Serializable {
	
	@Id
	@Column(name="id_tipo")
	private Integer id;
	
	@Column(name="nb_tipo")
	private String nombreTipo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}
	

}
