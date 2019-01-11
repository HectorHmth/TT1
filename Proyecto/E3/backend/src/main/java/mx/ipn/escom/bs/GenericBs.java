package mx.ipn.escom.bs;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.ipn.escom.daos.GenericDao;

@Service("genericBs")
@Scope(value=BeanDefinition.SCOPE_SINGLETON)
public class GenericBs {
	
	@Autowired
	private GenericDao genericDao;
	
	public <T> T findById(Class<T> clazz, Serializable id) {
		return genericDao.findById(clazz, id);
	}
	
	public <T> T findByName(Class<T> clazz, String name) {
		return genericDao.findByName(clazz, name);
	}
	
	public <T> void update(T entidad) {
		genericDao.update(entidad);
	}
	
	public <T> void save(T entidad) {
		genericDao.save(entidad);
	}
	
	public <T> void delete(T entidad) {
		genericDao.delete(entidad);
	}

}
