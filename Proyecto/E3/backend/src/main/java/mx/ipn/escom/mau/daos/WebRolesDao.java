package mx.ipn.escom.mau.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.ipn.escom.mau.entities.Rol;

@Service("webRolesDao")
@Scope(value =BeanDefinition.SCOPE_SINGLETON)
public class WebRolesDao {
	
	private static final String QUERY_1 = "from Rol where nombreRol=:rolUno or nombreRol=:rolDos or nombreRol=:rolTres";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Rol> getRoles(){
		Query q = entityManager.createQuery(QUERY_1);
		q.setParameter("rolUno", "Jefe");
		q.setParameter("rolDos", "Cajero");
		q.setParameter("rolTres", "Cocinero");
		List<Rol> rolesWeb = q.getResultList();
		return rolesWeb;
	}

}
