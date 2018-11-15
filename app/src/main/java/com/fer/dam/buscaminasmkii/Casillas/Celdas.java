package com.fer.dam.buscaminasmkii.Casillas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.fer.dam.buscaminasmkii.Casillas.Iconos.Iconos;
import com.fer.dam.buscaminasmkii.LogicaJuego;

public class Celdas extends Celda implements View.OnLongClickListener, View.OnClickListener {

    public Celdas(Context context, int pos) {

        super(context, pos);
    }

    public Celdas(Context context, AttributeSet attrs) {

        super(context, attrs);
        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    public Celdas(Context context) {

        super(context);
        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    public Celdas(Context context, int x, int y) {

        super(context, x, y);
        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    //Listener para responder a los eventos

    @Override
    public void onClick(View view) {

        LogicaJuego.getInstance().Pulsado(getXp(), getYp());
        Log.i("OnClick", "Celda pulsada: " + getPosicion() + " , " + "con valor: " + getValor());
    }

    @Override
    public boolean onLongClick(View v) {

        LogicaJuego.getInstance().Presionado(getXp(), getYp());
        Log.i("OnLongClick", "Celda presionada: " + getPosicion() + " , " + "con valor: " + getValor());
        return false;
    }

    //Métodos para dibujar las celdas

    @Override
    protected void onDraw(Canvas canvas) {

        /*Este es un método es heredado de la clase View
         * Renderiza las casillas segun el valor de celda, bomba, números*/

        super.onDraw(canvas);

        CeldaLibre(canvas);

        Log.e("Celda", "Dibujando");

        //OnLongClick

        if (getPresionado() == true && getBomba() == true) {

            BombaEncontrada(canvas);
        } else if (getVisible() == true && getBomba() == true && getPulsado() == false) {
            BombaEncontrada(canvas);
        } else {

            if (getPulsado()) {

                if (getValor() == 9) {
                    BombaExplota(canvas);
                    //vibrator.vibrate(230);
                } else {
                    ValorCelda(canvas);
                }
            } else {

                CeldaLibre(canvas);
            }
        }
    }

    /*Representa el número de bombas que tiene cerca
     * para ello coje su valor de getValor()*/

    private void ValorCelda(Canvas canvas) {

        Drawable drawable = null;
        Drawable back = null;

        Log.e("Celda", "Asignando Valor: " + getValor());

        //Según el valor de la casilla, dibuja la imagen con el valor

        back = getIconos().getCeldaBlanca();

        switch (getValor()) {

            case 1:
                drawable = getIconos().getNum1();
                break;
            case 2:
                drawable = getIconos().getNum2();
                break;
            case 3:
                drawable = getIconos().getNum3();
                break;
            case 4:
                drawable = getIconos().getNum4();
                break;
            case 5:
                drawable = getIconos().getNum5();
                break;
            case 6:
                drawable = getIconos().getNum6();
                break;
            case 7:
                drawable = getIconos().getNum7();
                break;
            case 8:
                drawable = getIconos().getNum8();
                break;
            case 9:
                drawable = getIconos().getBomba();
                break;
            case 0:
                drawable = getIconos().getCeldaVacia();
                break;
        }

        back.setBounds(0, 0, getWidth(), getHeight());
        drawable.setBounds(0, 0, getWidth(), getHeight());
        back.draw(canvas);
        drawable.draw(canvas);

    }

    //Métodos para dibujar las celdas

    //Se incluye la imagen de fondo (back), para evitar formas extrañas

    //Dibuja la bomba cuando explota

    protected void BombaExplota(Canvas canvas) {

        Drawable back = getIconos().getCeldaBlanca();
        Drawable drawable = getIconos().getBombaExplota();
        drawable.setBounds(0, 0, getWidth(), getHeight());
        back.setBounds(0, 0, getWidth(), getHeight());
        back.draw(canvas);
        drawable.draw(canvas);
    }

    private void BombaEncontrada(Canvas canvas) {

        Drawable back = getIconos().getCeldaBlanca();
        Drawable drawable = getIconos().getBomba();
        drawable.setBounds(0, 0, getWidth(), getHeight());
        back.setBounds(0, 0, getWidth(), getHeight());
        back.draw(canvas);
        drawable.draw(canvas);
    }

    //Dibuja la cara en blanco de la celdas

    private void CeldaLibre(Canvas canvas) {

        Drawable back = getIconos().getCeldaBlanca();
        Drawable drawable = getIconos().getBoton();
        back.setBounds(0, 0, getWidth(), getHeight());
        drawable.setBounds(0, 0, getWidth(), getHeight());
        back.draw(canvas);
        drawable.draw(canvas);
    }
}
