package com.fer.dam.buscaminasmkii;

import android.app.Fragment;
import android.content.Context;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.fer.dam.buscaminasmkii.Casillas.Celda;
import com.fer.dam.buscaminasmkii.Casillas.Celdas;

import java.util.ArrayList;

/**
 * Fragment para guardar los datos de la aplicacion durante los cambio en
 * tiempo de ejecucion.
 */

public class SaveInstance extends Fragment {

    private Celdas[][] Tablero;

    private ArrayList<Celdas> Tablero2;
    private int Nivel;
    private int Tipo;
    private int nbombasres;
    private int Largo;
    private int Ancho;

    @Override
    public void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    public void GuardarDatos(final ArrayList<Celdas> tab, final int tipo, final int nivel, final int nbomas) {

        Tablero2 = tab;
        Nivel = nivel;
        Tipo = tipo;
        nbombasres = nbomas;
    }

    public void GuardarDatos(final Celdas[][] tab, final int nivel, final int nbomas, final int largo, final int ancho) {

        Tablero = tab;
        Nivel = nivel;
        nbombasres = nbomas;
        Largo = largo;
        Ancho = ancho;
    }

    public void GuardarDatos(final Celdas[][] tab, final int tipo, final int nivel, int nbomas) {

        Tablero = tab;
        Tipo = tipo;
        Nivel = nivel;
        nbombasres = nbomas;
    }

    public Celdas[][] getTablero() {

        return Tablero;
    }

    public int getNivel(){

        return Nivel;
    }

    public int getLargo() {
        return Largo;
    }

    public void setLargo(int largo) {
        Largo = largo;
    }

    public int getAncho() {
        return Ancho;
    }

    public void setAncho(int ancho) {
        Ancho = ancho;
    }

    public int getTipo() {

        return Tipo;
    }
    public int getNbombasres() {

        return nbombasres;
    }

    public ArrayList<Celdas> getTablero2() {
        return Tablero2;
    }
}
