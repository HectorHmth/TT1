package mx.ipn.escom.mpg.entities;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tpg04_tarjeta")
public class Tarjeta extends FormaPago{
/**
 * 
 */	
	@Column(name = "tx_nu_tarjeta")
	private String numeroTarjeta;
	
	@Column(name = "fh_exp_date")
	private Date fechaExpiracion;
	
	@ManyToOne
	@JoinColumn(name="id_tipo",referencedColumnName="id_tipo")
	private TipoTarjeta tipoTarjeta;

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public TipoTarjeta getTipoTrajeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(TipoTarjeta tipo) {
		this.tipoTarjeta = tipo;
	}
	
	
}
