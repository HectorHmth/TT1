package mx.ipn.escom.mau.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.ipn.escom.mau.entities.Cuenta;

@Service("loginDao")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class LoginDao {
	
	private static final String QUERY_1 =  "from Cuenta where nombreUsuario=?";
	@PersistenceContext
	private EntityManager entityManager;
	
	public Cuenta findCuentaByNombre(String username) {
		System.out.println("--->"+username);
		Query q = entityManager.createQuery(QUERY_1);
		q.setParameter(0, username);
		System.out.println("--->BeforeQuery");
		Cuenta cuenta = (Cuenta) q.getSingleResult();
		
		/*
		List<Object[]> rows = q.getResultList();
		Object[] r = rows.get(0);
		Cuenta cuenta = new Cuenta();
		cuenta.setNombreUsuario((String)r[0]);
		cuenta.setContrasena((String) r[1]);*/
		return cuenta;
	}

}
