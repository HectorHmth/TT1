package mx.ipn.escom.mpg.entities;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tpg06_efectivo")
@PrimaryKeyJoinColumn(name="id_forma_pago", referencedColumnName="id_forma_pago")
public class Efectivo extends FormaPago implements Serializable {
	/**
	 * 
	 */
}
