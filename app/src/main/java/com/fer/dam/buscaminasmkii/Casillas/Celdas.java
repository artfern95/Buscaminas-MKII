package com.fer.dam.buscaminasmkii.Casillas;

import android.content.Context;
import android.view.View;

import com.fer.dam.buscaminasmkii.LogicaJuego;

/**Clase abstracta que proporciona los posibles estado de la celda asi como sus
 * valor posicion en entero, X e Y, y su valor como celda
 *
 * Created by artfe on 10/12/2017.
 */

public abstract class Celdas extends View {

    private int x;
    private int y;
    private int posicion;
    private int valor;

    private boolean Bomba;
    private boolean Visible;
    private boolean Pulsado;
    private boolean Presionado;

    public Celdas(Context context) {

        super(context);
    }

    //METODOS GET

    public int getXp(){

        return x;
    }

    public int getYp(){

        return y;
    }

    public int getPosicion(){

        return posicion;
    }

    public int getValor(){

        return valor;
    }

    public boolean getBomba(){

        return Bomba;
    }

    public boolean getVisible(){

        return Visible;
    }

    public boolean getPulsado(){

        return Pulsado;
    }

    public boolean getPresionado(){

        return Presionado;
    }

    //METODOS SET

    public void setPosicion(int posicion){

        x = posicion % LogicaJuego.Ancho;
        y = posicion / LogicaJuego.Largo;
    }

    public void setPosicion(int x, int y){

        this.x = x;
        this.y = y;

        posicion = x * LogicaJuego.Ancho + y;

        invalidate();

        //necesario para evitar problemas en el redibujado
    }

    public void setValor(int valor){

        Bomba = false;
        Visible = false;
        Pulsado = false;
        Presionado = false;

        if(valor == 9){

            Bomba = true;
        }

        this.valor = valor;
    }

    public void setBomba(boolean valor){

        Bomba = valor;
    }

    public void setVisible(){

        Visible = true;
    }

    public void setVisible(boolean valor){

        Visible = valor;
    }

    public void setPulsado (){

        Pulsado = true;
        Visible = true;

        invalidate();
    }

    public void setPulsado(boolean tipo){

        Pulsado = tipo;

        invalidate();
    }

    public void setPresionado(boolean valor){

        Presionado = valor;

        invalidate();
    }

    public void setPresionado (){

        Presionado = true;
        Visible = true;
        invalidate();
    }
}
