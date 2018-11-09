package com.fer.dam.buscaminasmkii;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.fer.dam.buscaminasmkii.SettingsActivity.SettingsActivity;

import org.xml.sax.ext.LexicalHandler;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private GridView grid;
    private Toolbar toolbar;
    public static int nivel;
    public static int tipo;
    private Context context;
    private SaveInstance datos;
    private FragmentManager fragmentManager;
    public static int contadorbombas;
    public static boolean modorotacion;
    public static boolean orientacion;
    private boolean cambios = false;
    private boolean cambiorientacion = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.i("Estado","onCreate");

        //Boton flotante para reiniciar el juego

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.NewGame);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.juego, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                LogicaJuego.getInstance().CrearTablero(context,grid);
                cambios = false;
            }
        });

        //Menu lateral

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        grid = (GridView)findViewById(R.id.GridView);
        context = this;

        //orientacion = false;
        tipo = 2;
        nivel = 1;
        modorotacion = false;
        //setcolor(tipo);

        //Inicio el juego

        LogicaJuego.getInstance().CrearTablero(this,grid);

        //Aqui creamos un fragment para guardar los datos cuando se produce un cambio en tiempo de ejecucion

        fragmentManager = getFragmentManager();
        datos = (SaveInstance) fragmentManager.findFragmentByTag("datos");

        if(datos == null){

            datos = new SaveInstance();
            fragmentManager.beginTransaction().add(datos,"datos").commit();
            datos.GuardarDatos(LogicaJuego.getInstance().getTablero(),tipo,nivel,LogicaJuego.Largo,LogicaJuego.Ancho,contadorbombas);
        }
    }

    //Llamamos al metodo para guardar la instacia

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        cambios = false;
        Log.i("Estado","onSaveInstance");
        datos.GuardarDatos(LogicaJuego.getInstance().getTablero(),tipo,nivel,LogicaJuego.Largo,LogicaJuego.Ancho,contadorbombas);
    }

    //Metodo para manejar los cambios en tiempo de ejecucion, no solo la orientacion

    public void AplicarAjustes(){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String largo = sharedPreferences.getString("Pref_largo","");
        String ancho = sharedPreferences.getString("Pref_ancho","");
        String bomba = sharedPreferences.getString("Pref_bombas","");

        Log.i("Cambios",""+largo+"|"+ancho);

        int Largo = Integer.parseInt(largo);
        int Ancho = Integer.parseInt(ancho);
        int Bomba = Integer.parseInt(bomba);

        if(Largo >= 9 && Largo <= 27 && Ancho >= 9 && Ancho <= 37 && Bomba >= 15 && Bomba <= 77 ){

            LogicaJuego.Ancho = Ancho;
            LogicaJuego.Largo = Largo;
            LogicaJuego.Nbombas = Bomba;

            LogicaJuego.getInstance().CrearTableroPersonalizado(this,grid);

            String texto = getResources().getString(R.string.tablero)+getResources().getString(R.string.largotext)+Largo+" | "+getResources().getString(R.string.anchotext)+Ancho+" | "+getResources().getString(R.string.bombas)+" : "+Bomba;
            Toast.makeText(this,texto, Toast.LENGTH_SHORT).show();
        }

        else{
            LogicaJuego.getInstance().CrearTablero(this,grid);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Log.i("Estado","onConfigurtionChange");

        datos.GuardarDatos(LogicaJuego.getInstance().getTablero(),tipo,nivel,LogicaJuego.Largo,LogicaJuego.Ancho,contadorbombas);
        Log.i("Estado","Guardando tablero: "+datos.getTablero().length);
        grid = (GridView)findViewById(R.id.GridView);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            modorotacion = true;

            if(orientacion==true) {
                LogicaJuego.getInstance().RestaurarTableroHorizontal(datos.getTablero(), grid, datos.getTipo(), datos.getLargo(), datos.getAncho(), this);
                contadorbombas = datos.getNbombasres();
                Log.i("Orientacion","Invertir matriz");
            }
            else{
                LogicaJuego.getInstance().RestaurarTablero(datos.getTablero(),grid,datos.getTipo(),datos.getLargo(),datos.getAncho(),this);
            contadorbombas = datos.getNbombasres();
            Log.i("Orientacion","Normal");
            }
        }

        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

            modorotacion = false;

            if (orientacion==true) {
                LogicaJuego.getInstance().RestaurarTableroVertical(datos.getTablero(), grid, datos.getTipo(), datos.getLargo(), datos.getAncho(), this);
                contadorbombas = datos.getNbombasres();
                Log.i("Orientacion","Invertir matriz");
            }
            else {
                LogicaJuego.getInstance().RestaurarTablero(datos.getTablero(), grid, datos.getTipo(), datos.getLargo(), datos.getAncho(), this);
                contadorbombas = datos.getNbombasres();
                Log.i("Orientacion", "Normal");
            }
        }
    }

    //Establece el fondo de la aplicacion en funcion de lo iconoos usados

    private void setcolor(int tipo){

        ConstraintLayout cl = (ConstraintLayout)findViewById(R.id.layout);

        switch (tipo) {
            case 1:
                cl.setBackgroundColor(Color.BLACK);
                break;
            case 2:
                cl.setBackgroundColor(Color.WHITE);
                break;
            case 3:
                cl.setBackgroundColor(Color.rgb(192,192,192));
                break;
        }
    }

    //respuesta al pusar la tecla atras

    @Override
    protected void onResume() {

        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        orientacion = sharedPreferences.getBoolean("Pref_switch",false);

        Log.i("Estado","onResume");

        if(datos != null) {
            LogicaJuego.getInstance().RestaurarTablero(datos.getTablero(), grid, datos.getTipo(), datos.getLargo(), datos.getAncho(), this);
            contadorbombas = datos.getNbombasres();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        datos = null;
    }

    @Override
    protected void onStop() {

        super.onStop();
        Log.i("Estado","onStop");
        datos.GuardarDatos(LogicaJuego.getInstance().getTablero(),tipo,nivel,LogicaJuego.Largo,LogicaJuego.Ancho,contadorbombas);
    }

    @Override
    protected void onPause() {

        super.onPause();
        Log.i("Estado","onPause");
        datos.GuardarDatos(LogicaJuego.getInstance().getTablero(),tipo,nivel,LogicaJuego.Largo,LogicaJuego.Ancho,contadorbombas);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i("Estado","onRestart");

        if(datos != null){
            LogicaJuego.getInstance().RestaurarTablero(datos.getTablero(),grid,datos.getTipo(),datos.getLargo(),datos.getAncho(),this);
            contadorbombas = datos.getNbombasres();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //creamos el menu lateral

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //listener de los elementos selecionado en el menu de la derecha
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {

            case R.id.bombasres:

                AlertDialog.Builder puntuacion = new AlertDialog.Builder(this);

                puntuacion.setMessage(getResources().getString(R.string.puntuacion)+": "+MainActivity.contadorbombas).setTitle(getResources().getString(R.string.puntuacion
                ));
                puntuacion.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = puntuacion.create();
                dialog.show();
                return true;

            case R.id.instruciones:

                AlertDialog.Builder instrucciones = new AlertDialog.Builder(this);

                instrucciones.setMessage(getResources().getString(R.string.instruciones)).setTitle(getResources().getString(R.string.tituloins));
                instrucciones.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog2 = instrucciones.create();
                dialog2.show();
                return true;

            case R.id.ajustes:

                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                cambios = true;
                return true;

            case R.id.salir:

                System.exit(0);
                return true;

            case R.id.juego:{

                AplicarAjustes();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    //listener del menu lateral para los iconos y modos de juego

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.NavBarajustes) {

            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            cambios = true;
        }

        else if (id == R.id.NavBardificil) {

            cambios = false;
            nivel = 3;
            LogicaJuego.getInstance().CrearTablero(context,grid);
        }

        else if (id == R.id.NavBarmedio) {

            cambios = false;
            nivel = 2;
            LogicaJuego.getInstance().CrearTablero(context,grid);
        }

        else if (id == R.id.NavBarfacil) {

            cambios = false;
            nivel = 1;
            LogicaJuego.getInstance().CrearTablero(context,grid);
        }

        else if (id == R.id.NavBarOpciones) {

        }

        else if(id==R.id.IconosClasicos){

            tipo = 1;
            LogicaJuego.getInstance().GuardarTablero(grid);
            setcolor(3);
        }

        else if(id==R.id.IconosMaterialb){

            tipo = 2;
            LogicaJuego.getInstance().GuardarTablero(grid);
            setcolor(2);
        }

        else if(id==R.id.IconosMaterialC){

            tipo = 4;
            LogicaJuego.getInstance().GuardarTablero(grid);
            setcolor(2);
        }

        else if(id==R.id.IconosMaterialw){

            tipo = 3;
            LogicaJuego.getInstance().GuardarTablero(grid);
            setcolor(1);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}