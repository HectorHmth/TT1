package mx.ipn.escom.mau.entities;

import java.io.Serializable;

import javax.persistence.Column;

public class MetodoRecursoId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "id_metodo", nullable = false)
	private Integer idMetodo;

	@Column(name = "id_recurso", nullable = false)
	private Integer idRecurso;

	public Integer getIdMetodo() {
		return idMetodo;
	}

	public void setIdMetodo(Integer idMetodo) {
		this.idMetodo = idMetodo;
	}

	public Integer getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(Integer idRecurso) {
		this.idRecurso = idRecurso;
	}
	
	

}
