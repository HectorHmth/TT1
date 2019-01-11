package mx.ipn.escom.mor.entities;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mx.ipn.escom.mau.entities.Persona;
import mx.ipn.escom.mpg.entities.FormaPago;

@Entity
@Table(name = "tpd01_cliente")
public class Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SequenceGenerator(name = "tpd01_cliente_seq", sequenceName = "tpd01_cliente_seq", allocationSize = 1)
	@GeneratedValue(generator = "tpd01_cliente_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id_cliente")
	private Integer id;
	
	
	@Column(name="id_persona",nullable=false)
	private Integer idPersona;
	
	@OneToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona",insertable=false, updatable=false)
	private Persona persona;
	
	@OneToMany
	@JoinColumn(name="id_cliente", referencedColumnName="id_cliente", insertable=false, updatable=false)
	private List<Orden> ordenesDeCliente;
	
	@OneToMany(mappedBy="cliente")
	//@JoinColumn(name="id_cliente", referencedColumnName="id_cliente",insertable=false,updatable=false)
	private List<FormaPago> formasDePago;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Orden> getOrdenesDeCliente() {
		return ordenesDeCliente;
	}

	public void setOrdenesDeCliente(List<Orden> ordenesDeCliente) {
		this.ordenesDeCliente = ordenesDeCliente;
	}

	public List<FormaPago> getFormasDePago() {
		return formasDePago;
	}

	public void setFormasDePago(List<FormaPago> formasDePago) {
		this.formasDePago = formasDePago;
	}

	
	
}
