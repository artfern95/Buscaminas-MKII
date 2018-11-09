package com.fer.dam.buscaminasmkii.Layout;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.fer.dam.buscaminasmkii.LogicaJuego;

/**Esta clase es la que se encarga de colocar las celdas(Views) en Gridview
 * al no usar image button tienes que crear para pome er manejar el metodo get view asi como count,
 * uno devuelve la celda y otro establece el numero de elementos que hay dento de Gridview
 * Created by artfe on 10/12/2017.
 */

public class CustomAdapter extends BaseAdapter {

    public CustomAdapter(){

    }

    @Override
    public int getCount() {

        return LogicaJuego.Ancho * LogicaJuego.Largo;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return LogicaJuego.getInstance().getCeldaPos(position);
    }
}
