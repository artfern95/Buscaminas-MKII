package com.fer.dam.buscaminasmkii;

import android.app.Fragment;
import android.content.Context;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.fer.dam.buscaminasmkii.Casillas.Celda;

/**
 * Fragment para guardar los datos de la aplicacion durante los cambio en
 * tiempo de ejecucion.
 */

public class SaveInstance extends Fragment {

    private Celda[][] Tablero;
    private int Nivel;
    private int Tipo;
    private int Largo;
    private int Ancho;
    private int nbombasres;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    public void GuardarDatos(final Celda[][] tab,final int tipo, final int nivel, final int largo, final int ancho, int nbomas){

        Tablero = tab;
        Tipo = tipo;
        Nivel = nivel;
        Largo = largo;
        Ancho = ancho;
        nbombasres = nbomas;
    }

    public Celda[][] getTablero(){

        return Tablero;
    }

    public int getNivel(){

        return Nivel;
    }

    public int getTipo() {

        return Tipo;
    }

    public int getAncho() {

        return Ancho;
    }

    public int getLargo() {

        return Largo;
    }

    public int getNbombasres() {
        return nbombasres;
    }
}
