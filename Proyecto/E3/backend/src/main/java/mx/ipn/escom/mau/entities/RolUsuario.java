package mx.ipn.escom.mau.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tau04_rol_cuenta")
public class RolUsuario implements Serializable{

	@EmbeddedId
	private RolUsuarioId id;

	@Column(name = "id_rol", insertable = false, updatable = false)
	private Integer idRol;

	@Column(name = "id_cuenta", insertable = false, updatable = false)
	private Integer idCuenta;

	@ManyToOne
	@JoinColumn(name = "id_rol", referencedColumnName = "id_rol", insertable = false, updatable = false)
	private Rol rol;

	@ManyToOne
	@JoinColumn(name = "id_cuenta", referencedColumnName = "id_cuenta", insertable = false, updatable = false)
	private Cuenta cuenta;

	public RolUsuarioId getId() {
		return id;
	}

	public void setId(RolUsuarioId id) {
		this.id = id;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public Integer getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	
}
