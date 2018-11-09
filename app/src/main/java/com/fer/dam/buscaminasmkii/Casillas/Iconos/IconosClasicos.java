package com.fer.dam.buscaminasmkii.Casillas.Iconos;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.fer.dam.buscaminasmkii.R;

/**
 * Created by artfe on 11/12/2017.
 */

public class IconosClasicos extends Iconos {

    public IconosClasicos(Context context){

        Boton = ContextCompat.getDrawable(context, R.drawable.button);
        CeldaBlanca = ContextCompat.getDrawable(context, R.drawable.blanco);
        CeldaVacia = ContextCompat.getDrawable(context, R.drawable.number_0);
        Bomba = ContextCompat.getDrawable(context, R.drawable.bomb_normal);
        BombaExplota = ContextCompat.getDrawable(context, R.drawable.bomb_exploded);
        Bandera = ContextCompat.getDrawable(context, R.drawable.flag);
        num1 = ContextCompat.getDrawable(context,R.drawable.number_1);
        num2 = ContextCompat.getDrawable(context,R.drawable.number_2);
        num3= ContextCompat.getDrawable(context,R.drawable.number_3);
        num4= ContextCompat.getDrawable(context,R.drawable.number_4);
        num5= ContextCompat.getDrawable(context,R.drawable.number_5);
        num6 = ContextCompat.getDrawable(context,R.drawable.number_6);
        num7= ContextCompat.getDrawable(context,R.drawable.number_7);
        num8 = ContextCompat.getDrawable(context,R.drawable.number_8);
    }
}
