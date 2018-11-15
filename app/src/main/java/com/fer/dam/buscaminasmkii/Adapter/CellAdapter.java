package com.fer.dam.buscaminasmkii.Adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fer.dam.buscaminasmkii.Casillas.Celda;
import com.fer.dam.buscaminasmkii.Casillas.Celdas;
import com.fer.dam.buscaminasmkii.LogicaJuego;
import com.fer.dam.buscaminasmkii.R;

import java.util.ArrayList;


public class CellAdapter extends RecyclerView.Adapter<CellAdapter.CellViewHolder> {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<Celdas> TableroTemporal;

    public CellAdapter(Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public CellAdapter(Context context, ArrayList<Celdas> lista) {

        this.context = context;
        inflater = LayoutInflater.from(context);

        TableroTemporal = new ArrayList<>(lista);
    }

    @NonNull
    @Override
    public CellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_view_cell, parent, false);
        return new CellViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CellViewHolder holder, int position) {

        if (LogicaJuego.getInstance().isCambioDeEstado() != true) {

            holder.celda.setValor(LogicaJuego.getInstance().getValorCelda(position));
            holder.celda.setPosicion(position);
        } else {

            holder.celda.setValor(TableroTemporal.get(position).getValor());
            holder.celda.setPosicion(position);
            holder.celda.setVisible(TableroTemporal.get(position).getVisible());
            holder.celda.setPulsado(TableroTemporal.get(position).getPulsado());
            holder.celda.setPresionado(TableroTemporal.get(position).getPresionado());

            LogicaJuego.getInstance().setCambioDeEstado(false);
            TableroTemporal.clear();
        }

        Log.i("onBindViewHolder", ":| Valor Celda" + holder.celda.getValor());
        Log.i("onBindViewHolder", ":| Visible: " + holder.celda.getVisible() + " :|Posicion:" + position);
    }

    @Override
    public int getItemCount() {

        //Número de casillas que tiene que generar para representar
        return LogicaJuego.getInstance().getLargo() * LogicaJuego.getInstance().getAncho();
    }

    public class CellViewHolder extends RecyclerView.ViewHolder {

        private Celdas celda;

        public CellViewHolder(View itemView) {

            super(itemView);
            celda = itemView.findViewById(R.id.RvCell);
            LogicaJuego.getInstance().AlmacenarCeldas(celda);
        }
    }

}
