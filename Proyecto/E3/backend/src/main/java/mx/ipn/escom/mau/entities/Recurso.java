package mx.ipn.escom.mau.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tau08_recurso")
public class Recurso implements Serializable{
	@SequenceGenerator(name = "tau08_recurso_seq", sequenceName = "tau08_recurso_seq", allocationSize = 1)
	@GeneratedValue(generator = "tau08_recurso_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id_recurso")
	private Integer id;

	@Column(name = "nb_recurso")
	private String nombreRecurso;
	
	@Column(name = "tx_uri")
	private String uri;

	@ManyToMany
	@JoinTable(name = "tau06_metodo_recurso", joinColumns = {
			@JoinColumn(name = "id_recurso", referencedColumnName = "id_recurso", insertable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_metodo", referencedColumnName = "id_metodo", insertable = false, updatable = false) })
	private List<Metodo> metodos;

	@OneToMany
	@JoinColumn(name = "id_recurso", referencedColumnName = "id_recurso", insertable = false, updatable = false)
	private List<MetodoRecurso> metodosRecurso;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getNombreRecurso() {
		return nombreRecurso;
	}

	public void setNombreRecurso(String nombreRecurso) {
		this.nombreRecurso = nombreRecurso;
	}

	public List<Metodo> getMetodos() {
		return metodos;
	}

	public void setMetodos(List<Metodo> metodos) {
		this.metodos = metodos;
	}

	public List<MetodoRecurso> getMetodosRecurso() {
		return metodosRecurso;
	}

	public void setMetodosRecurso(List<MetodoRecurso> metodosRecurso) {
		this.metodosRecurso = metodosRecurso;
	}
	
	

}
