package com.fer.dam.buscaminasmkii;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import com.fer.dam.buscaminasmkii.Adapter.CellAdapter;
import com.fer.dam.buscaminasmkii.Casillas.Celda;
import com.fer.dam.buscaminasmkii.Casillas.Celdas;
import com.fer.dam.buscaminasmkii.Constructor.GenerarTablero;

import java.util.ArrayList;

/**
 * Logicas y control del juego
 * esta clase sigue el modelo singelton
 * solo puede ser instanciada una vez
 * uso este modelo por que asi los valores, nbombas, ancho y largos, no se pierden
 */

public class LogicaJuego{

    private static final LogicaJuego ourInstance = new LogicaJuego();
    private SaveInstance datos;

    private int Nivel = 1;
    private int TipoIcono = 1;
    private int ContadorBombas;

    private int Ancho = 8;
    private int Largo = 8;
    private int NBombas = 10;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CellAdapter adapter;
    //Mapa de valores enteros para inicializar recyclerview
    private int[][] ValorTablero;

    private boolean CambioDeEstado;
    private Context context;
    //Mapa de Celdas creadas en OnBindViewHolder, clase adapter
    //private Celdas[][] Tablero;
    private ArrayList<Celdas> Tablero2;

    public static LogicaJuego getInstance() {

        return ourInstance;
    }

    private LogicaJuego() {
    }

    //Inicializador del tablero

    public void CrearTablero(Context context, SaveInstance datos, RecyclerView.LayoutManager layoutManager, RecyclerView recyclerView, CellAdapter cellAdapter) {

        //Contexto del mainactivity
        this.context = context;
        this.datos = datos;
        this.layoutManager = layoutManager;
        this.recyclerView = recyclerView;
        adapter = cellAdapter;

        //Extraemos los valores de la carpeta res, ahi definimos unos valores enteros para cada dificultad
        setDificultdad();
        Tablero2 = new ArrayList<>();
        ValorTablero = new int[Largo][Ancho];
        ContadorBombas = NBombas;

        Log.i("Inicio", "Creando Tablero");

        ValorTablero = GenerarTablero.creatablero(NBombas, Largo, Ancho);
    }

    public void ReiniciarTablero() {

        setDificultdad();

        ValorTablero = GenerarTablero.creatablero(NBombas, Largo, Ancho);
        Tablero2.clear();

        adapter = new CellAdapter(context);
        recyclerView.setAdapter(adapter);
    }

    //Control de eventos

    public void Pulsado(int x, int y) {

        Log.i("Pulsado", "Pos: " + x + " :|: " + y);

        if ((x >= 0) && (x < Largo) && (y >= 0) && (y < Ancho) && !getCeldaPos(x, y).getPulsado() && !getCeldaPos(x, y).getPresionado() && !getCeldaPos(x, y).getVisible()) {

            getCeldaPos(x, y).setPulsado();

            if (getCeldaPos(x, y).getValor() == 0) {

                for (int i = -1; i <= 1; i++) {

                    for (int e = -1; e <= 1; e++) {

                        if (i != e) {

                            Pulsado(x + i, y + e);
                            Log.i("Pulsado", "Siguiente celda: " + x + " | " + y);
                        }
                    }
                }
            }
        }
    }

    public void Presionado(int x, int y){

        getCeldaPos(x, y).setPresionado();
        getCeldaPos(x, y).invalidate();

        if (getCeldaPos(x, y).getBomba() == true) {

            getCeldaPos(x, y).setVisible();
            ContadorBombas--;
            Log.i("Presionado", "Quedan: " + ContadorBombas + " bombas");
            RevisarFin();
        }

        else {

            FinDelJuego();
        }
    }

    //Métodos para reordenar el tablaero

    //Restablece el tablero despues de algun cambio

    public void CambiarIconos() {

        Log.i("Tablero", "Realizando cambio de iconos: " + TipoIcono);

        for (int i = 0; i < Tablero2.size(); i++) {

            Tablero2.get(i).setIcono(context);
        }
    }

    public void RestaurarTablero() {

        Log.i("Tablero", "Restaurando Tablero");

        if (datos != null) {

            CambioDeEstado = true;

            TipoIcono = datos.getTipo();
            Nivel = datos.getNivel();
            ContadorBombas = datos.getNbombasres();

            setDificultdad();

            arraylistToArray(datos.getTablero2());
            adapter = new CellAdapter(context, datos.getTablero2());

            recyclerView.setAdapter(adapter);
        }
    }

    public void GuardarTablero() {

        datos.GuardarDatos(Tablero2, TipoIcono, Nivel, ContadorBombas);
    }

    /*public void RestaurarTableroHV (Boolean horozontal){

        Largo = datos.getAncho();
        Ancho = datos.getLargo();
        setTipoIcono(datos.getTipo());

        Tablero = new Celdas[Largo][Ancho];
        ArrayList<Celdas> lista = new ArrayList<>();
        Celdas [][] tableroprovisional = datos.getTablero();

        for(int i = 0; i < Ancho; i++){

            for(int e = Largo -1; e >= 0; e --){

                lista.add(tableroprovisional[i][e]);
            }
        }

        if(horozontal == true){

            Log.i("Tablero","Restaurando en horozontal");

            int cont = 0;

            for(int i = Largo -1; i >= 0; i --) {

                for(int e = Ancho -1; e >= 0; i --){

                    Tablero[e][i] = lista.get(cont);
                    cont++;
                }
            }

            adapter.notifyDataSetChanged();
            lista = null;
        }

        else{

            Log.i("Tablero","Restaurando en vertical");

            int cont = lista.size();

            for(int i = Largo -1; i >= 0; i --) {

                for(int e = Ancho -1; e >= 0; i --){

                    Tablero[e][i] = lista.get(cont);
                    cont--;
                }
            }

            adapter.notifyDataSetChanged();
            lista = null;
        }
    }*/

    //Get y Set especiales

    public void setDificultdad() {

        switch (Nivel) {

            case 1:
                Ancho = context.getResources().getInteger(R.integer.AnchoFacil);
                Largo = context.getResources().getInteger(R.integer.LargoFacil);
                NBombas = context.getResources().getInteger(R.integer.BombasFacil);
                break;
            case 2:
                Ancho = context.getResources().getInteger(R.integer.AnchoMedio);
                Largo = context.getResources().getInteger(R.integer.LargoMedio);
                NBombas = context.getResources().getInteger(R.integer.BombasMedio);
                break;
            case 3:
                Ancho = context.getResources().getInteger(R.integer.AnchoDificil);
                Largo = context.getResources().getInteger(R.integer.LargoDificil);
                NBombas = context.getResources().getInteger(R.integer.BombasDificil);
                break;
            default:
                Ancho = context.getResources().getInteger(R.integer.AnchoFacil);
                Largo = context.getResources().getInteger(R.integer.LargoFacil);
                NBombas = context.getResources().getInteger(R.integer.BombasFacil);
                break;
        }
    }

    public void arraylistToArray(ArrayList<Celdas> tablero) {

        int cont = 0;

        for (int i = 0; i < Largo; i++) {

            for (int e = 0; e < Ancho; e++) {

                ValorTablero[i][e] = tablero.get(i).getValor();
                cont++;
            }
        }
    }

    public Celda getCeldaPos(int x, int y) {

        int pos = y * Largo + x;

        return Tablero2.get(pos);
    }

    public Celda getCeldaPos(int pos) {

        return Tablero2.get(pos);
    }

    /*
    public Celda getCeldaPos(int x, int y) {

        return Tablero[x][y];
    }*/

    public int getValorCelda(int posicion) {

        int x = posicion % Largo;
        int y = posicion / Largo;

        return ValorTablero[x][y];
    }

    /*
    public Celdas getCeldaPos(int posicion) {

        int x = posicion % Largo;
        Log.i("Rv-Valor-x", "|:" + x);
        int y = posicion / Largo;
        Log.i("Rv-Valor-y", "|:" + y);

        return Tablero[x][y];
    }*/

    public void AlmacenarCeldas(Celdas celdas) {

        Tablero2.add(celdas);
    }

    /*
    public void AlmacenarCelda(Celdas celda, int posicion) {

        int x = posicion % Largo;
        int y = posicion / Largo;

        Tablero[x][y] = celda;
    }*/

    //Díalogos fin del juego y comprobación de fin de juego

    public void FinDelJuego () {

        for(int i = 0; i < Largo; i++){

            for(int e = 0; e < Ancho; e ++){

                if (getCeldaPos(i, e).getBomba() == true) {
                    getCeldaPos(i, e).setVisible();
                    getCeldaPos(i, e).invalidate();
                }
            }
        }

        DialogoFinDelJuego();

    }

    private void RevisarFin(){

        if (ContadorBombas == 0) {

            DialogoJuegoFinalizado();
        }
    }

    //Díalogos

    private void DialogoJuegoFinalizado(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setTitle(R.string.Gana);

        alertDialogBuilder
                .setMessage(R.string.pregunta)
                .setCancelable(false)
                .setPositiveButton(R.string.si,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                        //CrearTablero(context);
                        ReiniciarTablero();
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
                        //CrearTablero(context);
                        ReiniciarTablero();
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


    //Getter and Setters


    public ArrayList<Celdas> getTablero() {

        return Tablero2;
    }

    public int getAncho() {

        return Ancho;
    }

    public int getLargo() {

        return Largo;
    }

    public int getTipoIcono() {

        return TipoIcono;
    }

    public void setTipoIcono(int tipoIcono) {

        TipoIcono = tipoIcono;
    }

    public int getNivel() {

        return Nivel;
    }

    public void setNivel(int nivel) {

        Nivel = nivel;
    }

    public void setAncho(int ancho) {

        Ancho = ancho;
    }

    public void setLargo(int largo) {

        Largo = largo;
    }

    public int getContadorBombas() {

        return ContadorBombas;
    }

    public void setContadorBombas(int contadorBombas) {

        ContadorBombas = contadorBombas;
    }

    public int getNBombas() {

        return NBombas;
    }

    public void setNBombas(int NBombas) {
        this.NBombas = NBombas;
    }

    public boolean isCambioDeEstado() {

        return CambioDeEstado;
    }

    public void setCambioDeEstado(boolean cambioDeEstado) {
        CambioDeEstado = cambioDeEstado;
    }
}
