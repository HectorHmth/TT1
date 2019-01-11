package mx.ipn.escom.prueba.coffeeapp.repositories;

import android.app.Application;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.util.Log;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;
import mx.ipn.escom.prueba.coffeeapp.LocationManagerService;
import mx.ipn.escom.prueba.coffeeapp.NetworkBroadcastReceiver;
import mx.ipn.escom.prueba.coffeeapp.RetrofitManager;
import mx.ipn.escom.prueba.coffeeapp.asynctasks.GetLocalDetailAsyncTask;
import mx.ipn.escom.prueba.coffeeapp.asynctasks.GetLocalDetailByIdAsyncTask;
import mx.ipn.escom.prueba.coffeeapp.asynctasks.GetLocalesAsyncTask;
import mx.ipn.escom.prueba.coffeeapp.entities.Local;
import mx.ipn.escom.prueba.coffeeapp.exception.ConexionNoDisponibleException;
import mx.ipn.escom.prueba.coffeeapp.exception.NoExisteInformacionException;
import mx.ipn.escom.prueba.coffeeapp.exception.NoExistenLocalesException;
import mx.ipn.escom.prueba.coffeeapp.services.LocalesService;

public class LocalesRepository {
    private LocalesService localesService;

    public LocalesRepository(Application application){
        localesService = RetrofitManager.getRetrofitManager().create(LocalesService.class);
    }

    public List<Local> getLocales(Integer progress) throws  ConexionNoDisponibleException
            , NoExistenLocalesException
            , ConnectException
            , InterruptedException
            , ExecutionException {

        if (NetworkBroadcastReceiver.isConnected()){
            Location location = null;
            if(LocationManagerService.getLocationManager() != null){
                try{
                    location = LocationManagerService.getLocationManager().getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location == null){
                        location = LocationManagerService.getLocationManager().getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    }
                }catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
            GetLocalesAsyncTask getLocalesAsyncTask = new GetLocalesAsyncTask(localesService);
            getLocalesAsyncTask.execute().get();

            if (location != null){
                List<Local> localesCerca = new ArrayList<>();
                List<Local> locales = getLocalesAsyncTask.getLocalesList();
                for (Local local : locales){
                    Location ubicacionLocal = new Location("A");
                    ubicacionLocal.setLongitude(local.getNuLongitud().floatValue());
                    ubicacionLocal.setLatitude(local.getNuLatitud().floatValue());
                    float distancia = location.distanceTo(ubicacionLocal);
                    Log.d("--->DISTACNIA",String.valueOf(distancia/1000));
                    if(distancia/1000 < 2){
                        localesCerca.add(local);
                    }
                    Log.d("--->",String.valueOf(distancia/1000));
                }
                if(localesCerca.isEmpty())
                    return getLocalesAsyncTask.getLocalesList();
                else
                    return localesCerca;
            }


            if (getLocalesAsyncTask.getLocalesList() != null){
                return getLocalesAsyncTask.getLocalesList();
            }else{
                throw new NoExistenLocalesException();
            }
        }else{
            throw new ConexionNoDisponibleException();
        }
    }

    public Local getLocalById(Integer idLocal) throws  ConexionNoDisponibleException
                                                        , NoExisteInformacionException
                                                        , InterruptedException
                                                        , ExecutionException {
        if (NetworkBroadcastReceiver.isConnected()){
            GetLocalDetailByIdAsyncTask getLocalDetailByIdAsyncTask = new GetLocalDetailByIdAsyncTask(localesService);
            getLocalDetailByIdAsyncTask.execute(idLocal).get();
            if(getLocalDetailByIdAsyncTask.getResponseBody() != null){
                return getLocalDetailByIdAsyncTask.getResponseBody();
            }
            else{
                throw new NoExisteInformacionException();
            }
        }
        else{
            throw new ConexionNoDisponibleException();
        }
    }

    public Local getLocalByName(String nombreLocal) throws  ConexionNoDisponibleException
                                                            , NoExisteInformacionException
                                                            , InterruptedException
                                                            , ExecutionException {
        if (NetworkBroadcastReceiver.isConnected()){
            GetLocalDetailAsyncTask getLocalDetailAsyncTask = new GetLocalDetailAsyncTask(localesService);
            getLocalDetailAsyncTask.execute(nombreLocal).get();
            if (getLocalDetailAsyncTask.getResponseBody() != null){
                return getLocalDetailAsyncTask.getResponseBody();
            }else{
                throw new NoExisteInformacionException();
            }
        }else{
            throw new ConexionNoDisponibleException();
        }
    }
}
