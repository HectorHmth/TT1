package mx.ipn.escom.prueba.coffeeapp.business;

import android.app.Application;

import java.net.ConnectException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import mx.ipn.escom.prueba.coffeeapp.entities.Local;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.NoExistenLocalesException;
import mx.ipn.escom.prueba.coffeeapp.repositories.LocalesRepository;
import mx.ipn.escom.prueba.coffeeapp.repositories.LoginRepository;

public class LocalesBusiness {

    private LocalesRepository localesRepository;

    public LocalesBusiness(Application application){
        this.localesRepository  = new LocalesRepository(application);
    }

    public List<Local> getLocales(Integer progress) throws ConexionNoDisponibleException
            , NoExistenLocalesException
            , ConnectException
            , InterruptedException
            , ExecutionException {
        return localesRepository.getLocales(progress);
    }

}
