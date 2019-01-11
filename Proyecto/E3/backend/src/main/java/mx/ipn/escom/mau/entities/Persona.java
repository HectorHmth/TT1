package mx.ipn.escom.mau.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mx.ipn.escom.mor.entities.Cliente;

@Entity
@Table(name = "tau09_persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name = "tau09_persona_seq", sequenceName = "tau09_persona_seq", allocationSize = 1)
	@GeneratedValue(generator = "tau09_persona_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id_persona")
	private Integer id_persona;

	@Column(name = "nb_persona")
	private String nombrePersona;

	@Column(name = "tx_primer_ap")
	private String primerApellido;

	@Column(name = "tx_segundo_ap")
	private String segundoApellido;

	@JsonIgnore
	@Column(name = "by_foto")
	private byte[] foto;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "id_persona", referencedColumnName = "id_cuenta",insertable=false,updatable=false)
	private Cuenta cuenta;
	
	@JsonIgnore
	@OneToOne(mappedBy = "persona")
	@JoinColumn(name = "id_persona", referencedColumnName = "id_persona",insertable = false, updatable=false)
	private Cliente cliente;
	

	public Integer getId_persona() {
		return id_persona;
	}

	public void setId_persona(Integer id_persona) {
		this.id_persona = id_persona;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	

}
