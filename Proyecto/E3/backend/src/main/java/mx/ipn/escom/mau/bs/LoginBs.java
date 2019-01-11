package mx.ipn.escom.mau.bs;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.ipn.escom.bs.GenericBs;
import mx.ipn.escom.mau.daos.LoginDao;
import mx.ipn.escom.mau.entities.Acceso;
import mx.ipn.escom.mau.entities.Cuenta;
import mx.ipn.escom.mau.entities.EstadoCuenta;

@Service("loginBs")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class LoginBs extends GenericBs{

	@Autowired
	private LoginDao loginDao;
	
	@Transactional(rollbackOn = Exception.class)
	public Cuenta login(String username, String password){
		Cuenta cuenta = loginDao.findCuentaByNombre(username);
		if(cuenta != null) {
			if(cuenta.getContrasena().equals(password)) {
					if(!cuentaBloqueada(cuenta)) {
						cuenta.getAcceso().setFechaUltimoAcceso(new Date());
						update(cuenta);
						return cuenta;	
					}
			}else {
				registrarIntentoFallido(cuenta);
			}	
		}
		return cuenta;
	}
	
	
	private boolean cuentaBloqueada(Cuenta cuenta) {
		EstadoCuenta estadoCuenta = cuenta.getEstadoCuenta();
		if(estadoCuenta.getNombreEstado().equals("Bloqueada"))
			return true;
		else
			return false;
	}
	
	private void registrarIntentoFallido(Cuenta cuenta) {
		Acceso acceso = cuenta.getAcceso();
		Integer intentos = acceso.getNumeroIntentos();
		if(intentos < 3) {
			acceso.setNumeroIntentos( intentos += 1);
			update(acceso);
		}
		else {
			EstadoCuenta estado = findByName(EstadoCuenta.class, "Bloqueado");
			cuenta.setEstadoCuenta(estado);
			update(cuenta);
		}
	}
}
