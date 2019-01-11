package mx.ipn.escom.mps.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="tps12_cafeteria")
public class Cafeteria implements Serializable{
	
	@SequenceGenerator(name="tps12_cafeteria_seq", sequenceName="tps12_cafeteria_seq", allocationSize=1)
	@GeneratedValue(generator="tps12_cafeteria_seq", strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="id_cafeteria")
	private Integer id;
	
	@Column(name="nb_cafeteria")
	private String nombreCafeteria;
	
	@OneToMany
	@JoinColumn(name="id_cafeteria", referencedColumnName="id_cafeteria", updatable=false, insertable=false)
	private List<Local> locales;
	
	@OneToMany
	@JoinColumn(name="id_cafeteria", referencedColumnName="id_cafeteria", updatable=false, insertable=false)
	private List<Producto> productosCafeteria;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreCafeteria() {
		return nombreCafeteria;
	}

	public void setNombreCafeteria(String nombreCafeteria) {
		this.nombreCafeteria = nombreCafeteria;
	}

	public List<Local> getLocales() {
		return locales;
	}

	public void setLocales(List<Local> locales) {
		this.locales = locales;
	}

	public List<Producto> getProductosCafeteria() {
		return productosCafeteria;
	}

	public void setProductosCafeteria(List<Producto> productosCafeteria) {
		this.productosCafeteria = productosCafeteria;
	}
	
	

}
