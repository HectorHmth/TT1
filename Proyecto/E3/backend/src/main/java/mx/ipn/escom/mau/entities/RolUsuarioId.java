package mx.ipn.escom.mau.entities;

import java.io.Serializable;

import javax.persistence.Column;

public class RolUsuarioId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "id_rol", nullable = false)
	private Integer idRol;

	@Column(name = "id_cuenta", nullable = false)
	private Integer idCuenta;

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

}
