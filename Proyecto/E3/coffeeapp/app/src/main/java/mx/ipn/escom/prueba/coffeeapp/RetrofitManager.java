package mx.ipn.escom.prueba.coffeeapp;


import mx.ipn.escom.prueba.coffeeapp.constants.Constants;
import retrofit2.Retrofit;

import retrofit2.converter.jackson.JacksonConverterFactory;


public class RetrofitManager {
    private static volatile Retrofit INSTANCE;
    private static final String URL = Constants.URL;


    public static synchronized Retrofit getRetrofitManager(){
        if(INSTANCE == null){



            INSTANCE = new Retrofit.Builder()
                            .addConverterFactory(JacksonConverterFactory.create())
                            .baseUrl(URL)
                            .build();

            }
        return INSTANCE;
    }

}
