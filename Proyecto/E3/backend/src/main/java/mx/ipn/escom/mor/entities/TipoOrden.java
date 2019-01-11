package mx.ipn.escom.mor.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tpd08_tipo_orden")
@Inheritance(strategy = InheritanceType.JOINED)
public class TipoOrden implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SequenceGenerator(name = "tpd08_tipo_orden_seq", sequenceName = "tpd08_tipo_orden_seq", allocationSize = 1)
	@GeneratedValue(generator = "tpd08_tipo_orden_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id_tipo")
	private Integer id;
	
	@Column(name = "nb_tipo")
	private String nombre;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
