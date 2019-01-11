package mx.ipn.escom.mpg.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mx.ipn.escom.mor.entities.Cliente;



@Entity
@Table(name = "tpg01_forma_pago")
@Inheritance(strategy = InheritanceType.JOINED)
public class FormaPago implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SequenceGenerator(name = "tpg01_forma_pago_seq", sequenceName = "tpg01_forma_pago_seq", allocationSize = 1)
	@GeneratedValue(generator = "tpg01_forma_pago_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id_forma_pago")
	private Integer id;
	
	@JsonIgnore
	@Column(name = "id_cliente")
	private Integer idCliente;
	
	@JsonIgnore
	@Column(name = "id_tipo")
	private Integer idTipoForma;
	
	@JsonIgnore
	@Column(name = "id_estado")
	private Integer idEstado;
	
	@JsonIgnore
	@Column(name = "st_visible")
	private Integer visible;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_cliente", referencedColumnName="id_cliente",insertable=false, updatable=false)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="id_tipo",referencedColumnName="id_tipo",insertable = false, updatable = false)
	private TipoForma tipo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_estado", referencedColumnName="id_estado", insertable=false,updatable = false)
	private EstadoFormaPago estadoFormaPago;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoForma getTipo() {
		return tipo;
	}

	public void setTipo(TipoForma tipo) {
		this.tipo = tipo;
	}

	public Integer getIdTipoForma() {
		return idTipoForma;
	}

	public void setIdTipoForma(Integer idTipoForma) {
		this.idTipoForma = idTipoForma;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public Integer getVisible() {
		return visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}

	public EstadoFormaPago getEstadoFormaPago() {
		return estadoFormaPago;
	}

	public void setEstadoFormaPago(EstadoFormaPago estadoFormaPago) {
		this.estadoFormaPago = estadoFormaPago;
	}
}
