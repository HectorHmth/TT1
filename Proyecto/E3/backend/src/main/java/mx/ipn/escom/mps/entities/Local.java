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
import com.fasterxml.jackson.annotation.JsonProperty;

import mx.ipn.escom.mor.entities.ColaEspera;

@Entity
@Table(name="tps01_local")
public class Local implements Serializable{
	
	@SequenceGenerator(name="tps01_local_seq", sequenceName="tps01_local_seq", allocationSize=1)
	@GeneratedValue(generator="tps01_local_seq", strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="id_local")
	private Integer id;
	
	@Column(name="nb_local")
	private String nombreLocal;
	
	@Column(name="nu_rating")
	private Double nuRating;
	
	@Column(name="nu_longitud")
	private Double nuLongitud;
	
	@Column(name="nu_latitud")
	private Double nuLatitud;
	
	@Column(name="tm_inicio")
	private Date tmInicio;
	
	@Column(name="tm_fin")
	private Date tmFin;
	
	@JsonIgnore
	@Column(name="by_foto")
	private Byte[] foto;
	
	@Column(name="tx_direccion")
	private String direccion;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_cafeteria", referencedColumnName="id_cafeteria", updatable=false, insertable=false)
	private Cafeteria cafeteria;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_estado", referencedColumnName="id_estado",updatable=false, insertable=false)
	private EstadoLocal estadoLocal;
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name="id_local", referencedColumnName="id_local",updatable=false, insertable=false)
	private List<ProductoLocal> productosLocal;
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name="id_local", referencedColumnName="id_local", updatable=false, insertable=false)
	private List<Empleado> empleadosLocal;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "tps09_producto_local", joinColumns = {
			@JoinColumn(name = "id_local", referencedColumnName = "id_local", insertable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_producto", referencedColumnName = "id_producto", insertable = false, updatable = false) })
	private List<Producto> productosEnLocal;
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name="id_local", referencedColumnName = "id_local", insertable = false, updatable= false)
	private List<Promocion> promociones;
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name="id_local", referencedColumnName= "id_local", insertable = false, updatable = false)
	private List<ColaEspera> colasDeEspera;
	

	@JsonProperty("idLocal")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreLocal() {
		return nombreLocal;
	}

	public void setNombreLocal(String nombreLocal) {
		this.nombreLocal = nombreLocal;
	}

	public Double getNuRating() {
		return nuRating;
	}

	public void setNuRating(Double nuRating) {
		this.nuRating = nuRating;
	}

	public Double getNuLongitud() {
		return nuLongitud;
	}

	public void setNuLongitud(Double nuLongitud) {
		this.nuLongitud = nuLongitud;
	}


	public Double getNuLatitud() {
		return nuLatitud;
	}

	public void setNuLatitud(Double nuLatitud) {
		this.nuLatitud = nuLatitud;
	}

	public Date getTmInicio() {
		return tmInicio;
	}

	public void setTmInicio(Date tmInicio) {
		this.tmInicio = tmInicio;
	}

	public Date getTmFin() {
		return tmFin;
	}

	public void setTmFin(Date tmFin) {
		this.tmFin = tmFin;
	}

	public Byte[] getFoto() {
		return foto;
	}

	public void setFoto(Byte[] foto) {
		this.foto = foto;
	}

	public Cafeteria getCafeteria() {
		return cafeteria;
	}

	public void setCafeteria(Cafeteria cafeteria) {
		this.cafeteria = cafeteria;
	}

	public EstadoLocal getEstadoLocal() {
		return estadoLocal;
	}

	public void setEstadoLocal(EstadoLocal estadoLocal) {
		this.estadoLocal = estadoLocal;
	}

	public List<ProductoLocal> getProductosLocal() {
		return productosLocal;
	}

	public void setProductosLocal(List<ProductoLocal> productosLocal) {
		this.productosLocal = productosLocal;
	}

	public List<Empleado> getEmpleadosLocal() {
		return empleadosLocal;
	}

	public void setEmpleadosLocal(List<Empleado> empleadosLocal) {
		this.empleadosLocal = empleadosLocal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Producto> getProductosEnLocal() {
		return productosEnLocal;
	}

	public void setProductosEnLocal(List<Producto> productosEnLocal) {
		this.productosEnLocal = productosEnLocal;
	}

	public List<Promocion> getPromociones() {
		return promociones;
	}

	public void setPromociones(List<Promocion> promociones) {
		this.promociones = promociones;
	}

	public List<ColaEspera> getColasDeEspera() {
		return colasDeEspera;
	}

	public void setColasDeEspera(List<ColaEspera> colasDeEspera) {
		this.colasDeEspera = colasDeEspera;
	}
	
	


}
