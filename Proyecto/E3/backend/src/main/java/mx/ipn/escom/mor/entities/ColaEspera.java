package mx.ipn.escom.mor.entities;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mx.ipn.escom.mps.entities.Local;

@Entity
@Table(name = "tpd07_cola_espera")
public class ColaEspera implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SequenceGenerator(name = "tpd07_cola_espera_seq", sequenceName = "tpd07_cola_espera_seq", allocationSize = 1)
	@GeneratedValue(generator = "tpd07_cola_espera_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id_cola")
	private Integer id;
	
	@Column(name = "fh_cola")
	private Date fecha;
	
	@Column(name = "tm_ultima_act")
	private Date hora;
	
	@OneToMany
	@JoinColumn(name = "id_cola", referencedColumnName="id_cola", insertable = false, updatable = false)
	private List<Orden> orden;
	
	@ManyToOne
	@JoinColumn(name="id_local",referencedColumnName="id_local",insertable=false, updatable = false)
	private Local local;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public List<Orden> getOrden() {
		return orden;
	}

	public void setOrden(List<Orden> orden) {
		this.orden = orden;
	}
	
	
	
}
