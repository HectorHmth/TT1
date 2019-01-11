package mx.ipn.escom.mps.bs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.ipn.escom.bs.GenericBs;
import mx.ipn.escom.mps.daos.LocalesDao;
import mx.ipn.escom.mps.entities.EstadoLocal;
import mx.ipn.escom.mps.entities.Local;

@Service("localesBs")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class LocalesBs extends GenericBs{

	@Autowired
	private LocalesDao localesDao;
	
	public List<Local> getLocalesPublicados(){
		return localesDao.getLocalesPublicados();
	}
	
	public Local getLocalByName(String nombreLocal) {
		return localesDao.getLocalByName(nombreLocal);
	}
	
	public Local getLocalById(Integer idLocal) {
		return localesDao.getLocalById(idLocal);
	}
}
