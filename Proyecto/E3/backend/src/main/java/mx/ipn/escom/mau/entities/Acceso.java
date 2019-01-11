package mx.ipn.escom.mau.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tau10_acceso")
public class Acceso implements Serializable{

	@Id
	@Column(name="id_acceso")
	private Integer id;
	
	@Column(name = "fh_ultimo_acceso")
	private Date fechaUltimoAcceso;

	@Column(name = "nu_intentos")
	private Integer numeroIntentos;

	@Column(name="st_sesion_activa")
	private Integer sesionActiva;

	@OneToOne
	@JoinColumn(name="id_acceso", referencedColumnName="id_acceso",insertable=false,updatable=false)
	@MapsId
	private Cuenta cuenta;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaUltimoAcceso() {
		return fechaUltimoAcceso;
	}

	public void setFechaUltimoAcceso(Date fechaUltimoAcceso) {
		this.fechaUltimoAcceso = fechaUltimoAcceso;
	}

	public Integer getNumeroIntentos() {
		return numeroIntentos;
	}

	public void setNumeroIntentos(Integer numeroIntentos) {
		this.numeroIntentos = numeroIntentos;
	}

	public Integer getSesionActiva() {
		return sesionActiva;
	}

	public void setSesionActiva(Integer sesionActiva) {
		this.sesionActiva = sesionActiva;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	
	
}
