package mx.ipn.escom.mps.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tps13_promocion")
public class Promocion implements Serializable {
	
	@SequenceGenerator(name="tps13_promocion_seq", sequenceName="tps13_promocion_seq", allocationSize=1)
	@GeneratedValue(generator="tps13_promocion_seq", strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="id_promocion")
	private Integer id;
	
	@Column(name="nb_promocion")
	private String nombrePromocion;
	
	@Column(name="tx_descripcion")
	private String descripcion;
	
	@Column(name="nu_precio")
	private Double precioTotal;
	
	@Column(name="fecha_inicio")
	private Date fechaInicio;
	
	@Column(name="fh_fin")
	private Date fechaFin;
	
	@Column(name="nu_porcentaje")
	private Double porcentaje;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_local", referencedColumnName="id_local",updatable=false, insertable=false)
	private Local local;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="tps14_producto_promocion", joinColumns= {
			@JoinColumn(name="id_promocion", referencedColumnName="id_promocion", updatable=false, insertable=false)}, 
	inverseJoinColumns= {
			@JoinColumn(name="id_local", referencedColumnName="id_local", updatable=false,insertable=false),
				@JoinColumn(name="id_producto", referencedColumnName="id_producto",insertable=false, updatable=false)})
	private List<ProductoLocal> productosDeLocalEnPromocion;
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name="id_promocion",referencedColumnName="id_promocion",insertable=false,updatable = false)
	private List<ProductoPromocion> productosEnPromocion;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombrePromocion() {
		return nombrePromocion;
	}

	public void setNombrePromocion(String nombrePromocion) {
		this.nombrePromocion = nombrePromocion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public List<ProductoLocal> getProductosDeLocalEnPromocion() {
		return productosDeLocalEnPromocion;
	}

	public void setProductosDeLocalEnPromocion(List<ProductoLocal> productosDeLocalEnPromocion) {
		this.productosDeLocalEnPromocion = productosDeLocalEnPromocion;
	}

	public List<ProductoPromocion> getProductosEnPromocion() {
		return productosEnPromocion;
	}

	public void setProductosEnPromocion(List<ProductoPromocion> productosEnPromocion) {
		this.productosEnPromocion = productosEnPromocion;
	}
	
}
