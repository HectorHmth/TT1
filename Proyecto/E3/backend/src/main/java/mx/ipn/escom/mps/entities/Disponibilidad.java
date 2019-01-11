package mx.ipn.escom.mps.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tps11_disponibilidad")
public class Disponibilidad implements Serializable{
	
	@SequenceGenerator(name="tps11_disponibilidad_seq", sequenceName="tps11_disponibilidad_seq", allocationSize=1)
	@GeneratedValue(generator="tps11_disponibilidad_seq", strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="id_disponibilidad")
	private Integer id;
	
	@Column(name="id_local")
	private Integer idLocal;
	
	@Column(name="id_producto")
	private Integer idProducto;

	@Column(name="tm_fecha_ini")
	private Date fechaInicio;
	
	@Column(name="nu_existencia_ini")
	private Integer nuExistenciaInicial;
	
	@Column(name="nu_existencia_ac")
	private Integer nuExistenciaActual;
	
	@Column(name="tm_fecha_ag")
	private Date fechaAgotamiento;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="id_local", referencedColumnName="id_local", updatable=false, insertable=false),
		@JoinColumn(name="id_producto", referencedColumnName="id_producto", updatable=false, insertable=false)
	})
	private ProductoLocal productoLocal;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getIdLocal() {
		return idLocal;
	}


	public void setIdLocal(Integer idLocal) {
		this.idLocal = idLocal;
	}


	public Integer getIdProducto() {
		return idProducto;
	}


	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Integer getNuExistenciaInicial() {
		return nuExistenciaInicial;
	}


	public void setNuExistenciaInicial(Integer nuExistenciaInicial) {
		this.nuExistenciaInicial = nuExistenciaInicial;
	}


	public Integer getNuExistenciaActual() {
		return nuExistenciaActual;
	}


	public void setNuExistenciaActual(Integer nuExistenciaActual) {
		this.nuExistenciaActual = nuExistenciaActual;
	}


	public Date getFechaAgotamiento() {
		return fechaAgotamiento;
	}


	public void setFechaAgotamiento(Date fechaAgotamiento) {
		this.fechaAgotamiento = fechaAgotamiento;
	}


	public ProductoLocal getProductoLocal() {
		return productoLocal;
	}


	public void setProductoLocal(ProductoLocal productoLocal) {
		this.productoLocal = productoLocal;
	}

}
