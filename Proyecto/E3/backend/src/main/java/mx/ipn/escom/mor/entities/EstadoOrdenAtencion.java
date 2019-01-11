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
@Table(name = "tpd05_estado_orden_atencion")
@Inheritance(strategy = InheritanceType.JOINED)
public class EstadoOrdenAtencion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id_estado")
	private Integer id;
	
	@Column(name = "nb_estado")
	private String nombreEstado;
}
