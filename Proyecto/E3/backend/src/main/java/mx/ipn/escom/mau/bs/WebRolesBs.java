package mx.ipn.escom.mau.bs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.ipn.escom.bs.GenericBs;
import mx.ipn.escom.mau.daos.WebRolesDao;
import mx.ipn.escom.mau.entities.Rol;

@Service("webRolesBs")
@Scope(value= BeanDefinition.SCOPE_SINGLETON)
public class WebRolesBs extends GenericBs{
	
	@Autowired
	private WebRolesDao webRolesDao;
	
	public List<Rol> getWebRoles(){
		return webRolesDao.getRoles();
	}

}
