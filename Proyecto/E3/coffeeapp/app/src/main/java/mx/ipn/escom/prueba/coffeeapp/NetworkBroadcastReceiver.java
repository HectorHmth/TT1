package mx.ipn.escom.prueba.coffeeapp;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkBroadcastReceiver extends BroadcastReceiver {
    private static Boolean isConnected;
    @Override
    public void onReceive(Context context, Intent intent) {
        try{
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            Log.d("--->NWBS","CONECTADO:"+networkInfo.isConnected());
            isConnected = networkInfo.isConnected();
        }catch (Exception e){
            e.printStackTrace();
            isConnected = false;
            Log.d("--->NWBS","CONECTADO:"+false);
        }
    }

    public static Boolean isConnected() {
        return isConnected;
    }


}
