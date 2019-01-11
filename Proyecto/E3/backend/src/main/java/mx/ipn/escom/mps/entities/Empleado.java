package mx.ipn.escom.mps.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import mx.ipn.escom.mau.entities.Persona;
import mx.ipn.escom.mor.entities.OrdenAtencion;

@Entity
@Table(name="tps02_empleado")
@PrimaryKeyJoinColumn(name="id_empleado", referencedColumnName="id_persona")
public class Empleado extends Persona implements Serializable{
	
	@ManyToOne
	@JoinColumn(name="id_local", referencedColumnName="id_local",updatable=false, insertable=false)
	private Local local;
	
	@Column(name="st_responsable")
	private Integer stResponsable;
	
	@OneToMany
	@JoinColumn(name="id_empleado", referencedColumnName="id_empleado", updatable=false, insertable=false)
	private List<OrdenAtencion> ordenesEnAtencion;


	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Integer getStResponsable() {
		return stResponsable;
	}

	public void setStResponsable(Integer stResponsable) {
		this.stResponsable = stResponsable;
	}

	public List<OrdenAtencion> getOrdenesEnAtencion() {
		return ordenesEnAtencion;
	}

	public void setOrdenesEnAtencion(List<OrdenAtencion> ordenesEnAtencion) {
		this.ordenesEnAtencion = ordenesEnAtencion;
	}
	
	
}
