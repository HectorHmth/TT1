package mx.ipn.escom.mau.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tau07_rol_recurso")
public class RolRecurso implements Serializable{

	@EmbeddedId
	private RolRecursoId id;

	@Column(name = "id_rol", insertable = false, updatable = false)
	private Integer idRol;

	@Column(name = "id_metodo", insertable = false, updatable = false)
	private Integer idMetodo;

	@Column(name = "id_recurso", insertable = false, updatable = false)
	private Integer idRecurso;

	public RolRecursoId getId() {
		return id;
	}

	public void setId(RolRecursoId id) {
		this.id = id;
	}

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
