package mx.ipn.escom.mau.bs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.ipn.escom.bs.GenericBs;
import mx.ipn.escom.exception.NombreUsuarioRepetido;
import mx.ipn.escom.mau.daos.SignUpDao;
import mx.ipn.escom.mau.entities.EstadoCuenta;
import mx.ipn.escom.mau.entities.Persona;
import mx.ipn.escom.mau.entities.Rol;
import mx.ipn.escom.mor.entities.Cliente;
import mx.ipn.escom.mpg.entities.Efectivo;
import mx.ipn.escom.mpg.entities.EstadoFormaPago;
import mx.ipn.escom.mpg.entities.FormaPago;
import mx.ipn.escom.mpg.entities.TipoForma;

@Service("signUpBs")
@Scope(value= BeanDefinition.SCOPE_SINGLETON)
public class SignUpBs extends GenericBs{

	@Autowired
	private SignUpDao signUpDao;
	
	@Transactional(rollbackOn=Exception.class)
	public Persona registrarPersona(Persona persona) throws NombreUsuarioRepetido{
		
		if(!checkIfExists(persona.getCuenta().getNombreUsuario())) {
			List<Rol> rolesCliente = new ArrayList<Rol>();
			rolesCliente.add(findById(Rol.class,1));
			rolesCliente.add(findById(Rol.class,6));
			persona.getCuenta().setRoles(rolesCliente); 
			
			//Duda
			EstadoCuenta estadoCuenta = findById(EstadoCuenta.class,1);
			persona.getCuenta().setEstadoCuenta(estadoCuenta);
			persona.getCuenta().setIdEstado(estadoCuenta.getId());

			persona.getCuenta().getAcceso().setCuenta(persona.getCuenta());
			persona.getCuenta().setPersona(persona);
			
			Cliente cliente = new Cliente();
	
			Efectivo efectivo = new Efectivo();
			efectivo.setVisible(1);
			
			TipoForma tipoForma = findById(TipoForma.class, 1);
			efectivo.setTipo(tipoForma);
			efectivo.setIdTipoForma(tipoForma.getId());
			
			EstadoFormaPago estadoFormaPago = findById(EstadoFormaPago.class, 1);
			efectivo.setIdEstado(estadoFormaPago.getIdEstado());
			efectivo.setEstadoFormaPago(estadoFormaPago);
			
			try {
				save(persona.getCuenta().getAcceso());
				save(persona.getCuenta());
				save(persona);
				cliente.setPersona(persona);
				persona.setCliente(cliente);
				cliente.setIdPersona(persona.getId_persona());
				save(cliente);
				save(persona);
				efectivo.setCliente(cliente);
				efectivo.setIdCliente(cliente.getId());
				save(efectivo);
				save(cliente);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else {
			throw new NombreUsuarioRepetido();
		}
		return persona;
	}
	
	
	private boolean checkIfExists(String nombreUsuario){
		Persona persona;
		try{
			persona = signUpDao.findPersonaByNombreUsuario(nombreUsuario);
			if(persona == null) {
				return false;
			}else {
				return true;
			}
		}catch(NoResultException e) {
			e.printStackTrace();
			return false;
		}
	}
}
