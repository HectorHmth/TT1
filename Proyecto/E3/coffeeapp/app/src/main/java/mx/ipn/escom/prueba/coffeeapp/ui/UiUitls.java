package mx.ipn.escom.prueba.coffeeapp.ui;

import java.util.regex.Pattern;

import mx.ipn.escom.prueba.coffeeapp.constants.FieldErrorContants;

public class UiUitls {

    public static int stringValidate(String toValidate, Pattern pattern){
        if (toValidate != null){
            if (!toValidate.equals("")){
                //Add patern
                return FieldErrorContants.SIN_ERRORES;
            }else
                return FieldErrorContants.CAMPO_VACIO;
        }else
            return FieldErrorContants.CAMPO_VACIO;
    }
}
