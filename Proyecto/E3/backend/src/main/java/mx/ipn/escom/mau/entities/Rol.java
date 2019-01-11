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
@Table(name = "tau03_rol")
public class Rol implements Serializable{
	@SequenceGenerator(name = "tau03_rol_seq", sequenceName = "tau03_rol_seq", allocationSize = 1)
	@GeneratedValue(generator = "tau03_rol_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id_rol")
	private Integer id;

	@Column(name = "nb_rol")
	private String nombreRol;

	@Column(name = "ds_rol")
	private String descripcionRol;

	@ManyToMany
	@JoinTable(name = "tau07_rol_recurso", joinColumns = {
			@JoinColumn(name = "id_rol", referencedColumnName = "id_rol", insertable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_metodo", referencedColumnName = "id_metodo", insertable = false, updatable = false),
					@JoinColumn(name = "id_recurso", referencedColumnName = "id_recurso", insertable = false, updatable = false) })
	private List<MetodoRecurso> metodoRolRecursos;

	@OneToMany
	@JoinColumn(name = "id_rol", referencedColumnName = "id_rol", insertable = false, updatable = false)
	private List<RolRecurso> rolesRecurso;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public String getDescripcionRol() {
		return descripcionRol;
	}

	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}

	public List<MetodoRecurso> getMetodoRolRecursos() {
		return metodoRolRecursos;
	}

	public void setMetodoRolRecursos(List<MetodoRecurso> metodoRolRecursos) {
		this.metodoRolRecursos = metodoRolRecursos;
	}

	public List<RolRecurso> getRolesRecurso() {
		return rolesRecurso;
	}

	public void setRolesRecurso(List<RolRecurso> rolesRecurso) {
		this.rolesRecurso = rolesRecurso;
	}
	
	
	

}
