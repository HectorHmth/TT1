package mx.ipn.escom.prueba.coffeeapp.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import mx.ipn.escom.prueba.coffeeapp.daos.CuentaDao;
import mx.ipn.escom.prueba.coffeeapp.entities.Cuenta;

public class GetAsyncTask extends AsyncTask<String , Void, Void>{
        private CuentaDao cuentaDAO;
        public Cuenta cuenta;

        public GetAsyncTask(CuentaDao dao){
            this.cuentaDAO = dao;
        }

    @Override
    protected Void doInBackground(String...strings) {
            if (strings != null){
                Log.d("--->GetAsyncTask", strings[0]);
                this.cuenta = this.cuentaDAO.getByNombreUsuario(strings[0]);
                if(this.cuenta != null)
                    Log.d("--->GetAsyncTask", cuenta.getNombreUsuario() + "-" + cuenta.getContrasena());
            }else{
                Log.d("--->GetAsyncTask", "SE PASA A NET");
            }

            return null;
    }
}
