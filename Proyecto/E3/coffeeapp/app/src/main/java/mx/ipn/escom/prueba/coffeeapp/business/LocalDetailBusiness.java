package mx.ipn.escom.prueba.coffeeapp.business;

import android.app.Application;

import java.util.concurrent.ExecutionException;

import mx.ipn.escom.prueba.coffeeapp.entities.Local;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.NoExisteInformacionException;
import mx.ipn.escom.prueba.coffeeapp.repositories.LocalesRepository;
import mx.ipn.escom.prueba.coffeeapp.services.LocalesService;

public class LocalDetailBusiness {
    private LocalesRepository localesRepository;

    public LocalDetailBusiness(Application application){
        localesRepository = new LocalesRepository(application);
    }


    public Local getLocal(String nombreLocal) throws ConexionNoDisponibleException
            , NoExisteInformacionException
            , InterruptedException
            , ExecutionException {
        return localesRepository.getLocalByName(nombreLocal);
    }

    public Local getLocalById(Integer idLocal) throws ConexionNoDisponibleException
            , NoExisteInformacionException
            , InterruptedException
            , ExecutionException {
        return localesRepository.getLocalById(idLocal);
    }
}
