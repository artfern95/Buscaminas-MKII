package com.fer.dam.buscaminasmkii.Casillas.Iconos;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.fer.dam.buscaminasmkii.R;

/**
 * Created by artfe on 17/12/2017.
 */

public class IconosMaterialColor extends Iconos {

    public IconosMaterialColor(Context context){

        Boton = ContextCompat.getDrawable(context, R.drawable.boton);
        CeldaBlanca = ContextCompat.getDrawable(context, R.drawable.blanco);
        CeldaVacia = ContextCompat.getDrawable(context, R.drawable.celdavacia1);
        Bomba = ContextCompat.getDrawable(context, R.drawable.bomba_normal);
        BombaExplota = ContextCompat.getDrawable(context, R.drawable.bombas4);
        Bandera = ContextCompat.getDrawable(context, R.drawable.flag);
        num1 = ContextCompat.getDrawable(context,R.drawable.cnum1);
        num2 = ContextCompat.getDrawable(context,R.drawable.cnum2);
        num3= ContextCompat.getDrawable(context,R.drawable.cnum3);
        num4= ContextCompat.getDrawable(context,R.drawable.cnum4);
        num5= ContextCompat.getDrawable(context,R.drawable.cnum5);
        num6 = ContextCompat.getDrawable(context,R.drawable.cnum6);
        num7= ContextCompat.getDrawable(context,R.drawable.cnum7);
        num8 = ContextCompat.getDrawable(context,R.drawable.cnum8);
    }
}

