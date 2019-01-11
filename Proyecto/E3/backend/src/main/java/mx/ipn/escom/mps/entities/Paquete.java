package mx.ipn.escom.mps.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tps07_paquete")
@PrimaryKeyJoinColumn(name="id_paquete",referencedColumnName="id_producto")
public class Paquete extends Producto implements Serializable{

	@JsonIgnore
	@ManyToMany
	@JoinTable(name="tps10_paquete_producto",joinColumns= {
			@JoinColumn(name="id_paquete", referencedColumnName="id_paquete", insertable = false, updatable=false)}, inverseJoinColumns= {
					@JoinColumn(name="id_local", referencedColumnName="id_local",insertable=false, updatable=false),
					@JoinColumn(name="id_producto", referencedColumnName="id_producto",insertable=false,updatable=false)})
	private List<ProductoLocal> productosEnPaquete;
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name="id_paquete", referencedColumnName="id_paquete")
	private List<PaqueteProducto> productos;

	public List<ProductoLocal> getProductosEnPaquete() {
		return productosEnPaquete;
	}

	public void setProductosPaquete(List<ProductoLocal> productosEnPaquete) {
		this.productosEnPaquete = productosEnPaquete;
	}

	public List<PaqueteProducto> getProductos() {
		return productos;
	}

	public void setProductos(List<PaqueteProducto> productos) {
		this.productos = productos;
	}

	public void setProductosEnPaquete(List<ProductoLocal> productosEnPaquete) {
		this.productosEnPaquete = productosEnPaquete;
	}

	
	
	
	
}
