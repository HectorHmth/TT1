package mx.ipn.escom.mau.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tau01_cuenta")
public class Cuenta implements Serializable{
	@Id
	@Column(name = "id_cuenta")
	private Integer id;

	@Column(name = "nb_usuario")
	private String nombreUsuario;
	
	@Column(name = "id_estado")
	@JsonIgnore
	private Integer idEstado;

	@Column(name = "tx_correo")
	private String correo;

	@Column(name = "tx_contrasena")
	private String contrasena;

	@JsonIgnore
	@Column(name = "fh_creacion")
	private Date fechaCreacion;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name="id_cuenta",referencedColumnName ="id_persona", insertable =  false, updatable = false)
	@MapsId
	private Persona persona;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "id_cuenta", referencedColumnName = "id_cuenta", insertable = false, updatable = false)
	private Acceso acceso;


	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_estado", referencedColumnName = "id_estado", insertable = false, updatable = false)
	private EstadoCuenta estadoCuenta;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "tau04_rol_cuenta", joinColumns = {
			@JoinColumn(name = "id_cuenta", referencedColumnName = "id_cuenta", insertable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_rol", referencedColumnName = "id_rol", insertable = false, updatable = false) })
	private List<Rol> roles;

	@JsonIgnore
	@OneToMany
	@JoinColumn(name = "id_cuenta", referencedColumnName = "id_cuenta", insertable = false, updatable = false)
	private List<RolUsuario> rolesUsuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Acceso getAcceso() {
		return acceso;
	}

	public void setAcceso(Acceso acceso) {
		this.acceso = acceso;
	}

	public EstadoCuenta getEstadoCuenta() {
		return estadoCuenta;
	}

	public void setEstadoCuenta(EstadoCuenta estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}
	@JsonIgnore
	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	//@JsonIgnore
	public List<RolUsuario> getRolesUsuario() {
		return rolesUsuario;
	}

	public void setRolesUsuario(List<RolUsuario> rolesUsuario) {
		this.rolesUsuario = rolesUsuario;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	
	
	
	
}
