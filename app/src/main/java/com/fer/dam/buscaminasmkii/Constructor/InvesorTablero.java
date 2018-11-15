package com.fer.dam.buscaminasmkii.Constructor;

import android.content.Context;
import android.util.Log;
import android.widget.GridView;

import com.fer.dam.buscaminasmkii.Casillas.Celda;
import com.fer.dam.buscaminasmkii.Casillas.Iconos.IconosClasicos;
import com.fer.dam.buscaminasmkii.Casillas.Iconos.IconosMaterialBlack;
import com.fer.dam.buscaminasmkii.Casillas.Iconos.IconosMaterialColor;
import com.fer.dam.buscaminasmkii.Casillas.Iconos.IconosMaterialWhite;
import com.fer.dam.buscaminasmkii.LogicaJuego;
import com.fer.dam.buscaminasmkii.MainActivity;

import java.util.ArrayList;

public class InvesorTablero {

    /*
    public static void RestaurarTableroHorizontal(Celda[][]base, GridView view,int tipo, int largo, int ancho, Context context){

        Log.i("Tablero","Horizontal");

        ArrayList<Celda> lista = new ArrayList<Celda>();

        LogicaJuego.Largo = ancho;
        LogicaJuego.Ancho = largo;
        MainActivity.tipo = tipo;

        LogicaJuego.getInstance().setGrid(view);
        LogicaJuego.getInstance().getGrid().setNumColumns(LogicaJuego.Largo);

        //LogicaJuego.getInstance().getTablero() = new Celda[LogicaJuego.Largo][LogicaJuego.Ancho];

        for(int e = ancho - 1; e >= 0; e --){

            for(int i = 0; i <  largo; i ++){

                lista.add(base[i][e]);
            }
        }

        int num  = 0;

        for(int e = Largo - 1; e >= 0; e --) {

            for (int i = Ancho - 1; i >= 0; i--) {

                Tablero[e][i] = new Celda(context,e,i,icon);
                Tablero[e][i].setValor(lista.get(num).getValor());
                Tablero[e][i].setPulsado(lista.get(num).getPulsado());
                Tablero[e][i].setVisible(lista.get(num).getVisible());
                Tablero[e][i].setPresionado(lista.get(num).getPresionado());
                Tablero[e][i].invalidate();
                num++;
            }
        }

        CustomAdapter adapter = new CustomAdapter();
        grid.setAdapter(adapter);
    }


    public static void RestaurarTablero(Celda[][]base, GridView view, int tipo, int largo, int ancho, Context context){

        Log.i("Tablero","Restaurando");

        Largo = largo;
        Ancho = ancho;
        grid = view;
        MainActivity.tipo = tipo;

        Tablero = new Celda[Largo][Ancho];

        grid.setNumColumns(Largo);

        switch (MainActivity.tipo) {
            case 1:
                icon = new IconosClasicos(context);
                break;
            case 2:
                icon = new IconosMaterialBlack(context);
                break;
            case 3:
                icon = new IconosMaterialWhite(context);
                break;
            case 4:
                icon = new IconosMaterialColor(context);
                break;
        }

        for(int i = 0; i < Largo; i ++){

            for(int e = 0; e < Ancho; e ++){

                Tablero[i][e] = new Celda(context,i,e,icon);
                Tablero[i][e].setValor(base[i][e].getValor());
                Tablero[i][e].setPulsado(base[i][e].getPulsado());
                Tablero[i][e].setVisible(base[i][e].getVisible());
                Tablero[i][e].setPresionado(base[i][e].getPresionado());

                Tablero[i][e].invalidate();
            }
        }

        CustomAdapter adapter = new CustomAdapter();
        grid.setAdapter(adapter);
    }

    public void RestaurarTableroVertical(Celda[][]base, GridView view,int tipo, int largo, int ancho, Context context){

        Log.i("Tablero","Vertical");

        ArrayList<Celda> lista = new ArrayList<Celda>();

        Largo = ancho;
        Ancho = largo;
        MainActivity.tipo = tipo;
        grid = view;

        grid.setNumColumns(Largo);

        Tablero = new Celda[Largo][Ancho];

        for(int i = 0; i < Ancho; i ++){

            for(int e = Largo - 1; e >= 0; e --){

                lista.add(base[i][e]);
            }
        }

        int num = lista.size() - 1;

        for(int i = Ancho - 1; i >= 0; i --){

            for(int e = Largo - 1; e >= 0 ; e --){

                Tablero[e][i] = new Celda(context,e,i,icon);
                Tablero[e][i].setValor(lista.get(num).getValor());
                Tablero[e][i].setPulsado(lista.get(num).getPulsado());
                Tablero[e][i].setVisible(lista.get(num).getVisible());
                Tablero[e][i].setPresionado(lista.get(num).getPresionado());
                Tablero[e][i].invalidate();
                num--;
            }
        }
    }

    */
}
