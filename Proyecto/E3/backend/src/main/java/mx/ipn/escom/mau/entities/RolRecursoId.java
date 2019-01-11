package mx.ipn.escom.mau.entities;

import java.io.Serializable;

import javax.persistence.Column;

public class RolRecursoId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "id_rol", nullable = false)
	private Integer idRol;

	@Column(name = "id_metodo", nullable = false)
	private Integer idMetodo;

	@Column(name = "id_recurso", nullable = false)
	private Integer idRecurso;

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

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
