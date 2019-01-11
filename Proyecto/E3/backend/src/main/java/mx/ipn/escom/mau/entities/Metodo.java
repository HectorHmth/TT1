package mx.ipn.escom.mau.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tau05_metodo")
public class Metodo implements Serializable {

	@Id
	@Column(name = "id_metodo")
	private Integer id;

	@Column(name = "nb_metodo")
	private String nombreMetodo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreMetodo() {
		return nombreMetodo;
	}

	public void setNombreMetodo(String nombreMetodo) {
		this.nombreMetodo = nombreMetodo;
	}
	
	
}
