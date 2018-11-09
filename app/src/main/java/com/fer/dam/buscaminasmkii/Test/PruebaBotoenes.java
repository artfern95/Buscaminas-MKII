package com.fer.dam.buscaminasmkii.Test;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageButton;

public class PruebaBotoenes {

    ImageButton button;


    public void prueba(){

        Drawable Boton = ContextCompat.getDrawable(context, R.drawable.button);

        button.setImageDrawable(button);
    }
}
