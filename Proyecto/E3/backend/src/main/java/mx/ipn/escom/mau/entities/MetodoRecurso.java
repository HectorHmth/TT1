package mx.ipn.escom.mau.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tau06_metodo_recurso")
public class MetodoRecurso implements Serializable {

	@EmbeddedId
	private MetodoRecursoId id;

	@Column(name = "id_metodo", insertable = false, updatable = false)
	private Integer idMetodo;

	@Column(name = "id_recurso", insertable = false, updatable = false)
	private Integer idRecurso;

	@ManyToOne
	@JoinColumn(name = "id_metodo", referencedColumnName = "id_metodo", insertable = false, updatable = false)
	private Metodo metodo;

	@ManyToOne
	@JoinColumn(name = "id_recurso", referencedColumnName = "id_recurso", insertable = false, updatable = false)
	private Recurso recurso;

	public MetodoRecursoId getId() {
		return id;
	}

	public void setId(MetodoRecursoId id) {
		this.id = id;
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

	public Metodo getMetodo() {
		return metodo;
	}

	public void setMetodo(Metodo metodo) {
		this.metodo = metodo;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}
	
	
}
