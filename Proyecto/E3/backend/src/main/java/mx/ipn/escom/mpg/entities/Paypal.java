package mx.ipn.escom.mpg.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tpg03_paypal")
public class Paypal extends FormaPago implements Serializable {
/**
 * 
 */
	@Column(name = "nb_usuario")
	private String nombre;
	
	@Column(name = "tx_contrasena")
	private String contrasena;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	
	
}
