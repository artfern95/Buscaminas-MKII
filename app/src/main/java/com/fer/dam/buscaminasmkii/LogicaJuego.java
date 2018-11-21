package com.fer.dam.buscaminasmkii;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.fer.dam.buscaminasmkii.Casillas.Celda;
import com.fer.dam.buscaminasmkii.Casillas.Iconos.Iconos;
import com.fer.dam.buscaminasmkii.Casillas.Iconos.IconosClasicos;
import com.fer.dam.buscaminasmkii.Casillas.Iconos.IconosMaterialBlack;
import com.fer.dam.buscaminasmkii.Casillas.Iconos.IconosMaterialColor;
import com.fer.dam.buscaminasmkii.Casillas.Iconos.IconosMaterialWhite;
import com.fer.dam.buscaminasmkii.Constructor.GenerarTablero;
import com.fer.dam.buscaminasmkii.Constructor.ModosJuego;
import com.fer.dam.buscaminasmkii.Layout.CustomAdapter;

import java.util.ArrayList;

/**
 * Logicas y control del juego
 * esta clase sigue el modelo singelton
 * solo puede ser instanciada una vez
 * uso este modelo por que asi los valores, nbombas, ancho y largos, no se pierden
 */

public class LogicaJuego{

    private static LogicaJuego ourInstance;

    public static int Nbombas ;
    public static int Ancho ;
    public static int Largo ;
    private Context context;
    private GridView grid;
    private Iconos icon;
    private Celda[][] Tablero;//Tablero principal
    private Celda[][] tablero;//tablero para guardar las celdas a redibujar

    public static LogicaJuego getInstance() {

        if(ourInstance == null) {

            ourInstance = new LogicaJuego();
        }
        return ourInstance;
    }

    private LogicaJuego() {

    }

    public void CrearTablero(Context context, GridView grid){

        Log.e("Tablero", "Creando tablero de tipo");

        this.context = context;
        this.setGrid(grid);

        //Dificultad

        switch (MainActivity.nivel){

            case 1:
                Ancho = ModosJuego.Anchofacil;
                Largo = ModosJuego.Largofacil;
                Nbombas = ModosJuego.Nbombasfacil;
                break;
            case 2:
                Ancho = ModosJuego.Anchomedio;
                Largo = ModosJuego.Largomedio;
                Nbombas = ModosJuego.Nbombasmedio;
                break;
            case 3:
                Ancho = ModosJuego.Anchodificil;
                Largo = ModosJuego.Largodificil;
                Nbombas = ModosJuego.NBombasdificil;
                break;
        }

        //Tema

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

        Tablero = new Celda[Largo][Ancho];
        MainActivity.contadorbombas = Nbombas;

        //Genera los valores y la posicion de las bombas

        int [][] valorestablero = GenerarTablero.creatablero(Nbombas,Largo,Ancho);
        IniciarJuego(context,valorestablero,grid);
    }

    public void CrearTableroPersonalizado(Context context,GridView grid){

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

        Tablero = new Celda[Largo][Ancho];
        MainActivity.contadorbombas = Nbombas;

        int [][] valorestablero = GenerarTablero.creatablero(Nbombas,Largo,Ancho);
        IniciarJuego(context,valorestablero,grid);
    }

    private void IniciarJuego(Context context,int [][] base, GridView grid){

        /*Crea las celdas y con el array bidimensional creado anterior mente
        le asigna el valor, ya sea numero o bomba, a las celdas
        * */

        Log.i("Tablero","Iniciando Juego");

        grid.setNumColumns(Largo);

        for(int i = 0; i < Largo; i ++){

            for(int e = 0; e < Ancho; e ++) {

                Tablero[i][e] = new Celda(context,i,e,icon);

                Tablero[i][e].setValor(base[i][e]);
                Tablero[i][e].invalidate();
            }
        }

        CustomAdapter gridadapter = new CustomAdapter();
        grid.setAdapter(gridadapter);
    }

    public Celda getCeldaPos(int pos){

        int x = 0;
        int y = 0;

        /*if(MainActivity.orientacion == true && MainActivity.modorotacion == true) {
            x = pos / Ancho;
            y = pos % Ancho;
        }*/
        //else {
            x = pos % Largo;
            y = pos / Largo;
        //}

        /*boolean fin = false;

        int x = 0;
        int y = 0;
        int cont = 0;

        while(fin == false){

            for(int i = 0; i < Ancho; i ++){

                for(int e = 0; e < Largo; e ++){

                    if(cont == pos){
                        x = e;
                        y = i;

                        fin = true;
                    }

                    cont ++;
                }
            }

        }*/

        return Tablero[x][y];
    }

    public Celda getCeldapos(int x, int y){

        return Tablero[x][y];
    }

    /*Respuesta al onclick, dentro, si la celda es 0 (no tiene bombas cerca)
    muestra el contenido de las celdas cercanas donde no este cerca una bomba
    * */

    public void Pulsado(int x, int y){

        Log.i("Pulsado","Pos: "+x+" | "+y);

        if((x >= 0) && (x < Largo) && (y >= 0) && (y < Ancho) && !getCeldapos(x,y).getPulsado() && !getCeldapos(x,y).getPresionado() && !getCeldapos(x,y).getVisible()){

            getCeldapos(x,y).setPulsado();

            if(getCeldapos(x,y).getValor() == 0){

                for(int i = -1; i <= 1; i ++){

                    for(int e = -1; e <= 1; e ++){

                        if(i != e){

                            Pulsado(x + i, y + e);
                            Log.i("Pulsado","Siguiente celda: "+x+" | "+y);
                        }
                    }
                }
            }

            if(getCeldapos(x,y).getValor()==9){
                FinDelJuego();
            }
        }

        //RevisarFin();
    }

    //respuesta al onlongclick

    public void Presionado(int x, int y){

        getCeldapos(x,y).setPresionado();

        if(getCeldapos(x,y).getBomba() == true && getCeldapos(x,y).getVisible() == true) {

            Toast.makeText(context,"Esta celda ya esta visible",Toast.LENGTH_SHORT).show();
        }

        if(getCeldapos(x,y).getBomba() == true && getCeldapos(x,y).getVisible() != true){

            getCeldapos(x,y).setVisible();
            getCeldapos(x,y).invalidate();
            MainActivity.contadorbombas = MainActivity.contadorbombas - 1;
            Log.i("Presionado","Quedan: "+MainActivity.contadorbombas+" bombas");
            RevisarFin();
        }

        else {

            FinDelJuego();
            getCeldapos(x,y).setVisible();
            getCeldapos(x,y).invalidate();
        }
    }

    public Celda[][] getTablero(){

        return Tablero;
    }

    //Fin del juego, muestra las posiciones de las bombas escondidas

    public void FinDelJuego () {

        for(int i = 0; i < Largo; i++){

            for(int e = 0; e < Ancho; e ++){

                if(getCeldapos(i,e).getBomba()==true){
                    getCeldapos(i,e).setVisible();
                    getCeldapos(i,e).invalidate();
                }
            }
        }

        DialogoFinDelJuego();

    }

    private void RevisarFin(){

        if(MainActivity.contadorbombas == 0){

            DialogoJuegoFinalizado();
        }
    }

    private void DialogoJuegoFinalizado(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setTitle(R.string.Gana);

        alertDialogBuilder
                .setMessage(R.string.pregunta)
                .setCancelable(false)
                .setPositiveButton(R.string.si,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                        CrearTablero(context,grid);
                    }
                })
                .setNegativeButton(R.string.no,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        System.exit(0);
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    private void DialogoFinDelJuego(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setTitle(R.string.fin);

        alertDialogBuilder
                .setMessage(R.string.pregunta)
                .setCancelable(false)
                .setPositiveButton(R.string.si,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                        CrearTablero(context,grid);
                    }
                })
                .setNegativeButton(R.string.no,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        System.exit(0);
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    //Restaura el tablero en los cambios en tiempo de ejecucion

    public void RestaurarTableroVertical(Celda[][]base, GridView view,int tipo, int largo, int ancho, Context context){

        Log.i("Tablero","Vertical");

        ArrayList<Celda> lista = new ArrayList<Celda>();

        Largo = ancho;
        Ancho = largo;
        MainActivity.tipo = tipo;
        setGrid(view);

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

    public void RestaurarTableroHorizontal(Celda[][]base, GridView view,int tipo, int largo, int ancho, Context context){

        Log.i("Tablero","Horizontal");

        ArrayList<Celda> lista = new ArrayList<Celda>();

        Largo = ancho;
        Ancho = largo;
        MainActivity.tipo = tipo;
        setGrid(view);

        grid.setNumColumns(Largo);

        Tablero = new Celda[Largo][Ancho];

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

    public void RestaurarTablero(Celda[][]base, GridView view,int tipo, int largo, int ancho, Context context){

        Log.i("Tablero","Restaurando");

        Largo = largo;
        Ancho = ancho;
        setGrid(view);
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

    //Para el cambio de tema

    public void GuardarTablero(GridView grid){

        Log.i("Tablero","Guardando");

        tablero = Tablero;
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

                Tablero[i][e] = new Celda(context,tablero[i][e].getXp(),tablero[i][e].getYp(),icon);
                Tablero[i][e].setValor(tablero[i][e].getValor());
                Tablero[i][e].setPulsado(tablero[i][e].getPulsado());
                Tablero[i][e].setVisible(tablero[i][e].getVisible());
                Tablero[i][e].setPresionado(tablero[i][e].getPresionado());

                Tablero[i][e].invalidate();
            }
        }

        CustomAdapter adapter = new CustomAdapter();
        grid.setAdapter(adapter);
    }

    public void setGrid(GridView grid) {

        this.grid = grid;
    }

    public GridView getGrid(){

        return grid;
    }
}
