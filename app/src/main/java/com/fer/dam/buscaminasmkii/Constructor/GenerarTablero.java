package com.fer.dam.buscaminasmkii.Constructor;

import android.util.Log;

import java.util.Random;

public class GenerarTablero {

    public static int [][] creatablero(int Nbombas, int Largo, int Ancho){

        Log.i("GeneraTablero","Creando Tablero con: "+Nbombas+" ,Bombas y de : "+Ancho+" x "+Largo);

        int [][] base = new int [Largo][Ancho];
        Random numr = new Random();

        while(Nbombas > 0){

            int x = numr.nextInt(Largo);
            int y = numr.nextInt(Ancho);

            if(base[x][y] != 9){

                base[x][y] = 9;
                Nbombas--;
            }
        }

        base = calcularminascercanas(base,Largo,Ancho);

        return base;
    }

    private static int [][] calcularminascercanas(int [][]base, int Largo, int Ancho){

        /*AVANZAMOS POR EL ARRAY PARA COMPROBAR EL VALOR */

        String valor = "";

        for(int i = 0; i < Largo; i ++){

            for(int e = 0;e < Ancho; e ++){

                base[i][e] = valorcelda(base,i,e,Largo,Ancho);
            }
        }

        /*Aqui se muestra los valores de las casillas por el log*/

        for(int i = 0 ; i < Ancho ; i ++){

            valor = " | ";

            for(int e = 0 ; e < Largo ; e ++){

                valor = valor + String.valueOf(base[e][i]).replace("9","B") + " | ";
            }
            Log.i("Bombas",valor);
        }

        return base;
    }

    private static int valorcelda(int [][]base,int x, int y, int Largo, int Ancho){

        if(base[x][y] == 9){

            return 9; //DEVUELVE UAN BOMBA, PARA NO ALTERAR EL TABLERO
        }

        int cont = 0;

        /*AQUI COMPROBAMOS LOS ALREDEDORES DE LA CELDA PARA SABER SI TIENE MINAS CERCA
        SEGUN EL NUMERO DE MINAS QUE TENGA SE EL ASIGNA UN VALOR, MAXIMO 8
        * */

        for(int i = x -1 ; i <= x + 1; i ++){

            for(int e = y - 1; e <= y + 1; e ++){

                if(esunamina(base,i,e,Largo,Ancho) == true){

                    cont ++;
                }
            }
        }

        return cont;
    }

    private static boolean esunamina (int [][] base, int x, int y, int Largo, int Ancho){

        /*COMPROBAMOS QUE LA CELDA QUE LE PASANOS NO ESTE EN LOS BORDES
        * SI LO ESTA, NO REALIZA LA COMPROBACION EN LAS ZONAS QUE ESTE FUERA
        * DE LOS MARGENES*/

        if(x < Largo && x >= 0 && y < Ancho && y >= 0){

            if(base[x][y] == 9){

                return true;
            }
        }

        return false;
    }
}
