package com.example.practica2_juegohipotenochas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Hipotenochas> hipotenochas;
    //final  int personajeSeleccionado;
    String nivelSeleccionado;
    int personajeSeleccionado;
    SharedPreferences preferencias;
    DialogoNivelJuego nivelJuego;
    DialogoPersonajes dialogoPersonajes;
    DialogoInstruciones dInstrucciones;
    String nivel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencias = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferencias.edit();
        try {
            Bundle hipotenochaselecccionada = this.getIntent().getExtras();
            personajeSeleccionado = hipotenochaselecccionada.getInt("personaje");

            //preferencias = getSharedPreferences("data",   Context.MODE_PRIVATE);
            //SharedPreferences.Editor edit = preferencias.edit();
            edit.putInt("icono", personajeSeleccionado);
            edit.apply();

        } catch (Exception e) {
            personajeSeleccionado = R.drawable.hipo1;

            //preferencias = getSharedPreferences("data",   Context.MODE_PRIVATE);
            // SharedPreferences.Editor edit = preferencias.edit();
            edit.putInt("icono", personajeSeleccionado);
            edit.apply();
        }

        try {
            Bundle nivel = this.getIntent().getExtras();
            nivelSeleccionado = nivel.getString("nivel");

            //preferencias = getSharedPreferences("data",   Context.MODE_PRIVATE);
            //SharedPreferences.Editor edit = preferencias.edit();
            edit.putString("niv", nivelSeleccionado);
            edit.apply();


        } catch (Exception e) {
            nivelSeleccionado = "Principiante";

            // preferencias = getSharedPreferences("data",   Context.MODE_PRIVATE);
            //SharedPreferences.Editor edit = preferencias.edit();
            edit.putString("niv", nivelSeleccionado);
            edit.apply();
        }

        // prefs.getString("nombre del campo" , "valor por defecto")
        int hipopotamo = preferencias.getInt("icono", R.drawable.hipo3);
        String level = preferencias.getString("niv", "Principiante");
        Toast.makeText(this, "NIVEL " + level, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "PERSONAJE " + hipopotamo, Toast.LENGTH_SHORT).show();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuinicio, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.instrucciones:
                dInstrucciones = new DialogoInstruciones();
                dInstrucciones.show(getSupportFragmentManager(), "instrucciones");
                return super.onOptionsItemSelected(item);
            case R.id.juegonuevo:
                Intent nuevo = new Intent(this, JuegoPrincipiante.class);
                startActivity(nuevo);

                return super.onOptionsItemSelected(item);
            case R.id.configuracion:
                nivelJuego = new DialogoNivelJuego();
                nivelJuego.show(getSupportFragmentManager(), "niveljuego");
                return super.onOptionsItemSelected(item);
            case R.id.selectpersonaje:

                dialogoPersonajes = new DialogoPersonajes();
                dialogoPersonajes.show(getSupportFragmentManager(),"personajes");
                return super.onOptionsItemSelected(item);

        }

        return super.onOptionsItemSelected(item);


    }


}