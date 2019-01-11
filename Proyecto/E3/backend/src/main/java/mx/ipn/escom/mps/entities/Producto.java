package mx.ipn.escom.mps.entities;

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

@Entity
@Table(name="tps05_producto")
@Inheritance(strategy=InheritanceType.JOINED)
public class Producto implements Serializable{

	@Id
	@SequenceGenerator(name="tps05_producto_seq", sequenceName="tps05_producto_seq", allocationSize=1)
	@GeneratedValue(generator="tps05_producto_seq", strategy=GenerationType.SEQUENCE)
	@Column(name="id_producto")
	private Integer id;
	
	@Column(name="id_estado")
	private Integer idEstado;
	
	@Column(name="nb_producto")
	private String nombreProducto;
	
	@Column(name="tm_estimado_prep")
	private Double tmEstimadoPrep;
	
	@Column(name="nu_rating")
	private Double nuRating;
	
	@Column(name="tx_descripcion")
	private String descripcion;
	
	@JsonIgnore
	@Column(name="by_foto")
	private Byte[] foto;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_tipo", referencedColumnName="id_tipo", insertable=false, updatable=false)
	private TipoProducto tipoProducto;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_estado", referencedColumnName="id_estado", insertable=false, updatable=false)
	private EstadoProducto estadoProducto;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Double getTmEstimadoPrep() {
		return tmEstimadoPrep;
	}

	public void setTmEstimadoPrep(Double tmEstimadoPrep) {
		this.tmEstimadoPrep = tmEstimadoPrep;
	}

	public Byte[] getFoto() {
		return foto;
	}

	public void setFoto(Byte[] foto) {
		this.foto = foto;
	}

	public Double getNuRating() {
		return nuRating;
	}

	public void setNuRating(Double nuRating) {
		this.nuRating = nuRating;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public EstadoProducto getEstadoProducto() {
		return estadoProducto;
	}

	public void setEstadoProducto(EstadoProducto estadoProducto) {
		this.estadoProducto = estadoProducto;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	
	
	
		
}
