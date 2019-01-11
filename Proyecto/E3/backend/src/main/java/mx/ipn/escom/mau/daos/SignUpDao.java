package mx.ipn.escom.mau.daos;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.ipn.escom.mau.entities.Cuenta;
import mx.ipn.escom.mau.entities.Persona;

@Service("signUpDao")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class SignUpDao {
	
	private static final String QUERY_1 = "from Cuenta c where nombreUsuario=:nombreUsuario";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Persona findPersonaByNombreUsuario(String nombreUsuario) 
			throws NoResultException{
		Query query = entityManager.createQuery(QUERY_1);
		query.setParameter("nombreUsuario", nombreUsuario);
		Cuenta c = (Cuenta)query.getSingleResult();
		return c.getPersona();
	}
	
	

}
