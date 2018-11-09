package com.fer.dam.buscaminasmkii.Casillas.Iconos;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.fer.dam.buscaminasmkii.R;

/**
 * Created by artfe on 12/12/2017.
 */

public class IconosMaterialWhite extends Iconos {

    public IconosMaterialWhite(Context context){

        Boton = ContextCompat.getDrawable(context, R.drawable.bboton);
        CeldaBlanca = ContextCompat.getDrawable(context, R.drawable.negro);
        CeldaVacia = ContextCompat.getDrawable(context, R.drawable.bceldavacia1);
        Bomba = ContextCompat.getDrawable(context, R.drawable.bbomba_normal);
        BombaExplota = ContextCompat.getDrawable(context, R.drawable.bombas4);
        Bandera = ContextCompat.getDrawable(context, R.drawable.flag);
        num1 = ContextCompat.getDrawable(context,R.drawable.bnum1);
        num2 = ContextCompat.getDrawable(context,R.drawable.bnum2);
        num3= ContextCompat.getDrawable(context,R.drawable.bnum3);
        num4= ContextCompat.getDrawable(context,R.drawable.bnum4);
        num5= ContextCompat.getDrawable(context,R.drawable.bnum5);
        num6 = ContextCompat.getDrawable(context,R.drawable.bnum6);
        num7= ContextCompat.getDrawable(context,R.drawable.bnum7);
        num8 = ContextCompat.getDrawable(context,R.drawable.bnum8);
    }


}
