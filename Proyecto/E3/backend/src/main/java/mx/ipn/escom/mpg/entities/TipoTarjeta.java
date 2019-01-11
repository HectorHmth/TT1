package mx.ipn.escom.mpg.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Id;

@Entity
@Table(name = "tpg05_tipo_tarjeta")
@Inheritance(strategy = InheritanceType.JOINED)
public class TipoTarjeta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID=1L;
	
	@Id
	@Column(name = "id_tipo")
	private Integer id;
	
	@Column(name = "nb_tipo")
	private String nombre;

}
