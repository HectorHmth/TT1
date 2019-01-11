package mx.ipn.escom.daos;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("genericDao")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class GenericDao {
	@PersistenceContext
	EntityManager entityManager;
	
	public <T> T findById(Class<T> clazz, Serializable id){
		T entidad = entityManager.find(clazz, id);
		return entidad;
	}
	
	public <T> T findByName(Class<T> clazz, String name) {
		T entidad = entityManager.find(clazz, name);
		return entidad;
	}
	
	public <T> void update(T entidad) {
		entityManager.merge(entidad);
		entityManager.flush();
	}
	
	public <T> void save(T entidad) {
		entityManager.persist(entidad);
		entityManager.flush();
	}
	
	
	public <T> void delete(T entidad) {
		entityManager.remove(entidad);
		entityManager.flush();
	}
}
