package com.fer.dam.buscaminasmkii.Casillas;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;

import com.fer.dam.buscaminasmkii.Casillas.Iconos.Iconos;
import com.fer.dam.buscaminasmkii.Casillas.Iconos.IconosClasicos;
import com.fer.dam.buscaminasmkii.Casillas.Iconos.IconosMaterialBlack;
import com.fer.dam.buscaminasmkii.Casillas.Iconos.IconosMaterialColor;
import com.fer.dam.buscaminasmkii.Casillas.Iconos.IconosMaterialWhite;
import com.fer.dam.buscaminasmkii.LogicaJuego;

public abstract class Celda extends AppCompatButton {

    private int x;
    private int y;
    private int posicion;
    private int valor;

    private boolean Bomba;
    private boolean Visible;
    private boolean Pulsado;
    private boolean Presionado;

    protected Iconos iconos;

    //Constructores

    public Celda(Context context, int pos) {

        super(context);

        setPosicion(pos);
    }

    public Celda(Context context, AttributeSet attrs) {

        super(context, attrs);

        setIcono(context);
        Log.i("Celda", "Creado celda");
    }

    public Celda(Context context) {

        super(context);

        setIcono(context);
        Log.i("Celda", "Creado celda");
    }

    public Celda(Context context, int x, int y) {

        super(context);

        setIcono(context);
        setPosicion(x, y);
        Log.i("Celda", "Creando celda");
    }

    ///////////////////////////

    public int getXp() {

        return x;
    }

    public int getYp() {

        return y;
    }

    public int getPosicion() {

        return posicion;
    }

    public void setPosicion(int posicion) {

        this.posicion = posicion;

        x = posicion % LogicaJuego.getInstance().getAncho();
        y = posicion / LogicaJuego.getInstance().getLargo();
    }

    public void setPosicion(int x, int y) {

        this.x = x;
        this.y = y;

        posicion = x * LogicaJuego.getInstance().getLargo() + y;

        invalidate();

        //necesario para evitar problemas en el redibujado
    }

    public int getValor() {

        return valor;
    }

    public void setValor(int valor) {

        Bomba = false;
        Visible = false;
        Pulsado = false;
        Presionado = false;

        if (valor == 9) {

            Bomba = true;
        }

        this.valor = valor;
    }

    public boolean getBomba() {

        return Bomba;
    }

    public void setBomba(boolean valor) {

        Bomba = valor;
    }

    public void setIcono(Context context) {

        switch (LogicaJuego.getInstance().getTipoIcono()) {

            case 1:
                iconos = new IconosClasicos(context);
                invalidate();
                break;
            case 2:
                iconos = new IconosMaterialBlack(context);
                invalidate();
                break;
            case 3:
                iconos = new IconosMaterialColor(context);
                invalidate();
                break;
            case 4:
                iconos = new IconosMaterialWhite(context);
                invalidate();
            default:
                iconos = new IconosClasicos(context);
                invalidate();
                break;
        }
    }

    public boolean getVisible() {

        return Visible;
    }

    public void setVisible(boolean valor) {

        Visible = valor;
        invalidate();
    }

    public boolean getPulsado() {

        return Pulsado;
    }

    public void setPulsado(boolean tipo) {

        Pulsado = tipo;
        invalidate();
    }

    public void setPulsado() {

        Pulsado = true;
        Visible = true;

        invalidate();
    }

    public boolean getPresionado() {

        return Presionado;
    }

    public void setPresionado(boolean valor) {

        Presionado = valor;
        //Visible = (valor = true) ? false:true;

        invalidate();
    }

    public void setPresionado() {

        Presionado = true;
        Visible = true;

        invalidate();
    }

    public void setVisible() {

        Visible = true;
    }

    public Iconos getIconos() {

        return iconos;
    }
}
