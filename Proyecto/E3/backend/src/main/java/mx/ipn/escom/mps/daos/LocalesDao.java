package mx.ipn.escom.mps.daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.ipn.escom.mps.entities.Local;

@Service("localesDao")
@Scope(value=BeanDefinition.SCOPE_SINGLETON)
public class LocalesDao {

	private static final String QUERY_1 = "select id, nombreLocal, nuLatitud ,nuLongitud ,nuRating, foto, tmInicio, tmFin,direccion from Local L where L.estadoLocal.nombreEstado=?";
	private static final String QUERY_2 = "select id, nombreLocal, nuLatitud ,nuLongitud ,nuRating, foto, tmInicio, tmFin,direccion from Local where nombreLocal=?";
	private static final String QUERY_3 = "select id, nombreLocal, nuLatitud ,nuLongitud ,nuRating, foto, tmInicio, tmFin,direccion from Local where id=?";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public List<Local> getLocalesPublicados(){
		Query q = entityManager.createQuery(QUERY_1);
		q.setParameter(0, "Publicado");
		List<Object[]> resultado = q.getResultList();
		List<Local> listaDeLocales = new ArrayList<>();
		for(Object o[]: resultado) {
			Object[] n = o;
			Local local = new Local();
			local.setId((Integer)n[0]);
			local.setNombreLocal((String)n[1]);
			local.setNuLatitud((Double)n[2]);
			local.setNuLongitud((Double)n[3]);
			local.setNuRating((Double)n[4]);
			local.setFoto((Byte[])n[5]);
			local.setTmInicio((Date)n[6]);
			local.setTmFin((Date)n[7]);
			local.setDireccion((String)n[8]);
			listaDeLocales.add(local);
		}
		return listaDeLocales;
	}
	
	
	public Local getLocalByName(String nombreLocal) {
		Query q = entityManager.createQuery(QUERY_2);
		q.setParameter(0, nombreLocal);
		List<Object[]> resultado = q.getResultList();
		if(resultado != null) {
			Object n[] = resultado.get(0);
			Local local = new Local();
			local.setId((Integer) n[0]);
			local.setNombreLocal((String)n[1]);
			local.setNuLatitud((Double)n[2]);
			local.setNuLongitud((Double)n[3]);
			local.setNuRating((Double)n[4]);
			local.setFoto((Byte[])n[5]);
			local.setTmInicio((Date)n[6]);
			local.setTmFin((Date)n[7]);
			local.setDireccion((String)n[8]);
			return local;
		}
		return null;
	}
	
	public Local getLocalById(Integer idLocal) {
		Query query = entityManager.createQuery(QUERY_3);
		query.setParameter(0, idLocal);
		List<Object[]> resultado = query.getResultList();
		if(resultado != null) {
			Object n[] = resultado.get(0);
			Local local = new Local();
			local.setId((Integer) n[0]);
			local.setNombreLocal((String)n[1]);
			local.setNuLatitud((Double)n[2]);
			local.setNuLongitud((Double)n[3]);
			local.setNuRating((Double)n[4]);
			local.setFoto((Byte[])n[5]);
			local.setTmInicio((Date)n[6]);
			local.setTmFin((Date)n[7]);
			local.setDireccion((String)n[8]);
			return local;
		}
		return null;
	}

}
