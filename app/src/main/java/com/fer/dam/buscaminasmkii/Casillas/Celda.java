package com.fer.dam.buscaminasmkii.Casillas;n

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import com.fer.dam.buscaminasmkii.Casillas.Iconos.Iconos;
import com.fer.dam.buscaminasmkii.Casillas.Iconos.IconosClasicos;
import com.fer.dam.buscaminasmkii.Casillas.Iconos.IconosMaterialBlack;
import com.fer.dam.buscaminasmkii.Casillas.Iconos.IconosMaterialColor;
import com.fer.dam.buscaminasmkii.Casillas.Iconos.IconosMaterialWhite;
import com.fer.dam.buscaminasmkii.LogicaJuego;
import com.fer.dam.buscaminasmkii.MainActivity;
import com.fer.dam.buscaminasmkii.R;
import com.fer.dam.buscaminasmkii.SaveInstance;

/*Esta clase hereda del celda, aqui es donde se implementa los listener asi como las condiciones
de dinujado de las celdas segun su estado : 1,2,4...presionado...bomba
* */

public class Celda extends Celdas implements View.OnClickListener, View.OnLongClickListener{

    private Iconos icon;
    private Context context;
    private Vibrator vibrator;

    //constructores por posicion (int ) o por coordenadas (x,y)

    public Celda(Context context, int pos, Iconos icon){

        super(context);

        this.context = context;
        this.icon = icon;

        vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);

        setPosicion(pos);

        Log.i("Celda","Valor: "+getPosicion());

        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    public Celda(Context context, int x, int y, Iconos icon) {

        super(context);

        this.context = context;
        this.icon = icon;

        vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);

        setPosicion(x,y);

        Log.i("Celda","Valor: "+getPosicion());

        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void onClick(View v) {

        LogicaJuego.getInstance().Pulsado(getXp(),getYp());
    }

    @Override
    public boolean onLongClick(View v) {

        LogicaJuego.getInstance().Presionado(getXp(),getYp());
        String texto = getResources().getString(R.string.puntuacion);
        Snackbar snackbar = Snackbar.make(v,texto+" --> "+MainActivity.contadorbombas,Snackbar.LENGTH_SHORT);
        snackbar.show();
        vibrator.vibrate(230);
        return false;
    }

    /*Aqui es donde se encuentran las condiciones para dibujar las celdas
    por su nombre es facil intuir como se dibuja
    */

    @Override
    protected void onDraw(Canvas canvas) {

        /*Este es un método es heredado de la clase View
        * Renderiza las casillas segun el valor de celda, bomba, números*/

        super.onDraw(canvas);

        CeldaLibre(canvas);

        Log.e("Celda","Dibujando");

        //OnLongClick

        if(getPresionado() == true && getBomba() == true){

            BombaEncontrada(canvas);
        }

        else if(getVisible() == true && getBomba() == true && getPulsado() == false){
            BombaEncontrada(canvas);
        }
        else{

            if (getPulsado()){

                if(getValor() == 9){
                    BombaExplota(canvas);
                    vibrator.vibrate(230);
                }
                else{
                    ValorCelda(canvas);
                }
            }

            else {

                CeldaLibre(canvas);
            }
        }
    }

    private void ValorCelda(Canvas canvas){

        Drawable drawable = null;
        Drawable back = null;

        Log.e("Celda","Asignando Valor: "+getValor());

        //Según el valor de la casilla, dibuja la imagen con el valor

        back = icon.getCeldaBlanca();

        switch (getValor()){

            case 1:
                drawable = icon.getNum1();
                break;
            case 2:
                drawable = icon.getNum2();
                break;
            case 3:
                drawable = icon.getNum3();
                break;
            case 4:
                drawable = icon.getNum4();
                break;
            case 5:
                drawable = icon.getNum5();
                break;
            case 6:
                drawable = icon.getNum6();
                break;
            case 7:
                drawable = icon.getNum7();
                break;
            case 8:
                drawable = icon.getNum8();
                break;
            case 0:
                drawable = icon.getCeldaVacia();
                break;
        }

        back.setBounds(0,0,getWidth(),getHeight());
        drawable.setBounds(0,0,getWidth(),getHeight());
        back.draw(canvas);
        drawable.draw(canvas);

    }

    //Métodos para dibujar las celdas

    //Se incluye la imagen de fondo (back), para evitar formas extrañas

    protected void BombaExplota(Canvas canvas){

        Drawable back = icon.getCeldaBlanca();
        Drawable drawable = icon.getBombaExplota();
        drawable.setBounds(0,0,getWidth(),getHeight());
        back.setBounds(0,0,getWidth(),getHeight());
        back.draw(canvas);
        drawable.draw(canvas);
    }

    private void BombaEncontrada(Canvas canvas){

        Drawable back = icon.getCeldaBlanca();
        Drawable drawable = icon.getBomba();
        drawable.setBounds(0,0,getWidth(),getHeight());
        back.setBounds(0,0,getWidth(),getHeight());
        back.draw(canvas);
        drawable.draw(canvas);
    }

    private void CeldaLibre(Canvas canvas){

        Drawable back = icon.getCeldaBlanca();
        Drawable drawable = icon.getBoton();
        back.setBounds(0,0,getWidth(),getHeight());
        drawable.setBounds(0,0,getWidth(),getHeight());
        back.draw(canvas);
        drawable.draw(canvas);
    }
}
