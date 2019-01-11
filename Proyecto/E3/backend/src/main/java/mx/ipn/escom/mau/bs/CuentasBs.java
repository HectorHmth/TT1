package mx.ipn.escom.mau.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.ipn.escom.bs.GenericBs;
import mx.ipn.escom.mau.daos.CuentasDao;
import mx.ipn.escom.mau.entities.Persona;

@Service("cuentasBs")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class CuentasBs extends GenericBs{
	
	@Autowired
	private CuentasDao cuentasDao;
	
	public Persona getPersona(Integer idCuenta) {
		Persona persona = findById(Persona.class,idCuenta);
		return persona;
	}

}
