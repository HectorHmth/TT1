package mx.ipn.escom.prueba.coffeeapp;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import mx.ipn.escom.prueba.coffeeapp.daos.CuentaDao;
import mx.ipn.escom.prueba.coffeeapp.daos.PersonaDao;
import mx.ipn.escom.prueba.coffeeapp.entities.Cuenta;
import mx.ipn.escom.prueba.coffeeapp.entities.Local;
import mx.ipn.escom.prueba.coffeeapp.entities.Persona;

@Database(entities = {Persona.class,Cuenta.class,Local.class},version = 6)
public abstract class AppDataBase extends RoomDatabase {
    public abstract PersonaDao personaDao();
    public abstract CuentaDao cuentaDao();
    private static volatile AppDataBase INSTANCE;

    public static synchronized  AppDataBase getDatabase(final Context context){
        if (INSTANCE == null){

            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "base_datos")
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return INSTANCE;
    }

    public  void buildDatabase(){
        Log.d("--->","CREARBD");
        cuentaDao().deleteAll();
        personaDao().deleteAll();
        Persona persona = new Persona();
        /*persona.setNombrePersona("Francisco Isidoro");
        persona.setPrimerApellido("Mera");
        persona.setSegundoApellido("Torres");*/

        Cuenta cuenta = new Cuenta();
        //cuenta.setNombreUsuario("fcoescom");
        cuenta.setContrasena("prueba123");

        //persona.setCuenta(cuenta);

        //personaDao().insert(persona);
        //cuentaDao().insert(cuenta);

        persona.setNombrePersona("Diana Laura");
        persona.setPrimerApellido("Mejia");
        persona.setSegundoApellido("Mendoza");
        cuenta.setNombreUsuario("dianam");
        persona.setCuenta(cuenta);

        personaDao().insert(persona);
        cuentaDao().insert(cuenta);

        Log.d("--->","FIN CREAR BD");
    }
}
