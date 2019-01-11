package mx.ipn.escom.mor.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mx.ipn.escom.mps.entities.Empleado;

@Entity
@Table(name = "tpd04_orden_atencion")
public class OrdenAtencion implements Serializable {
	@Id
	@Column(name = "id_orden")
	private Integer idOrden;
	
	@OneToOne
	@JoinColumn(name = "id_orden", referencedColumnName = "id_orden", insertable = false, updatable = false)
	@MapsId
	private Orden orden;
	
	@ManyToOne
	@JoinColumn(name="id_empleado",referencedColumnName="id_empleado", insertable=false, updatable = false)
	private Empleado empleado;
	
	@ManyToOne
	@JoinColumn(name="id_estado",referencedColumnName="id_estado")
	private EstadoOrdenAtencion estadoOrdenEnAtencion;

	public Integer getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(Integer idOrden) {
		this.idOrden = idOrden;
	}

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public EstadoOrdenAtencion getEstadoOrdenEnAtencion() {
		return estadoOrdenEnAtencion;
	}

	public void setEstadoOrdenEnAtencion(EstadoOrdenAtencion estadoOrdenEnAtencion) {
		this.estadoOrdenEnAtencion = estadoOrdenEnAtencion;
	}
	
	
	
	
	

}
