package mx.ipn.escom.prueba.coffeeapp.observers;

import android.util.Log;

import com.google.android.material.textfield.TextInputLayout;

import androidx.lifecycle.Observer;
import mx.ipn.escom.prueba.coffeeapp.R;
import mx.ipn.escom.prueba.coffeeapp.constants.FieldErrorContants;

public class SimpleTextInputLayoutObserver implements Observer {
    private TextInputLayout textInputLayout;
    private String errorMessage;

    public SimpleTextInputLayoutObserver(TextInputLayout textInputLayout, String errorMessage){
        this.errorMessage = errorMessage;
        if(textInputLayout == null){
            Log.d("--->","LA ENTRADA DEL TEXTINPUT NO ES VALIDA");
        }else{
            this.textInputLayout = textInputLayout;
        }
    }

    @Override
    public void onChanged(Object o) {
        if (o instanceof Integer){
            if (o == FieldErrorContants.CAMPO_VACIO){
                textInputLayout.setErrorEnabled(true);

                textInputLayout.setError(errorMessage);
            }else{
                textInputLayout.setErrorEnabled(false);
            }
        }
    }
}
