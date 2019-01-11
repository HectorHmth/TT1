package mx.ipn.escom.mau.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("cuentasDao")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class CuentasDao {
	
	@PersistenceContext
	private EntityManager entityManager;

}
