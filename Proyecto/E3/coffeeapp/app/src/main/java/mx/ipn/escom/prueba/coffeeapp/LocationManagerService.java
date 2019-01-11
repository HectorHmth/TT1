package mx.ipn.escom.prueba.coffeeapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class LocationManagerService extends Service {

    private static final String TAG = "COFFEEAPPGPS";
    private static LocationManager mLocationManager = null;
    private Location location;
    private static final int LOCATION_INTERVAL = 1000;
    private static final float LOCATION_DISTANCE = 0;


    private class LocationManagerListener implements LocationListener{
        Location mLocation;


        public LocationManagerListener(String provider){
            Log.d("--->","LocationListener" + provider );
            mLocation = new Location(provider);

        }

        @Override
        public void onLocationChanged(Location location) {
            Log.d("--->","CAMBIO_UBIC:"+location.toString());
            mLocation.set(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }

    LocationManagerListener[] mLocationListeners = new LocationManagerListener[]{
            new LocationManagerListener(LocationManager.GPS_PROVIDER),
            new LocationManagerListener(LocationManager.NETWORK_PROVIDER)
    };

    @Override
    public void onCreate() {
        super.onCreate();
        if (mLocationManager == null){
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }

        try{
            mLocationManager
                    .requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,LOCATION_INTERVAL, LOCATION_DISTANCE,mLocationListeners[1]);

        }catch (SecurityException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();

        }

        try{
            mLocationManager
                    .requestLocationUpdates(LocationManager.GPS_PROVIDER,LOCATION_INTERVAL,LOCATION_DISTANCE, mLocationListeners[0]);

        }catch (SecurityException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if (mLocationManager != null){
            for (int i = 0;i < mLocationListeners.length ;i++){
                try{
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d("","");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }


    public Location getLocation(){
        if(this.mLocationListeners[0].mLocation != null){
            return this.mLocationListeners[0].mLocation;
        }
        if(this.mLocationListeners[1].mLocation != null){
            return this.mLocationListeners[1].mLocation;
        }
        return null;
    }

    public static LocationManager getLocationManager(){
        return mLocationManager;
    }

}
