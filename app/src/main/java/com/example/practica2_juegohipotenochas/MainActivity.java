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

    DialogoNivelJuego nivelJuego;
    String nivel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefsPersonaje = getSharedPreferences("personaje_data",   Context.MODE_PRIVATE);
        SharedPreferences.Editor editPersonaje = prefsPersonaje.edit();
        SharedPreferences prefsNivel = getSharedPreferences("nivel_data",   Context.MODE_PRIVATE);
        SharedPreferences.Editor editNivel = prefsNivel.edit();
        try {
            Bundle hipotenochaselecccionada = this.getIntent().getExtras();
            personajeSeleccionado = hipotenochaselecccionada.getInt("personaje");

            editPersonaje.putInt("personaje", personajeSeleccionado);
            editPersonaje.commit();

        } catch (Exception e) {
            personajeSeleccionado = R.drawable.hipo1;
            editPersonaje.putInt("personaje", personajeSeleccionado);
            editPersonaje.commit();
        }

        try {
            Bundle nivel = this.getIntent().getExtras();
            nivelSeleccionado = nivel.getString("nivel");

            editNivel.putString("nivel", nivelSeleccionado);
            editNivel.commit();


        } catch (Exception e) {
            nivelSeleccionado = "Principiante";
            editNivel.putString("nivel", nivelSeleccionado);
            editNivel.commit();
        }

        // prefs.getString("nombre del campo" , "valor por defecto")
        String hipopotamo = prefsPersonaje.getString("personaje", "Principiante");
        int level = prefsNivel.getInt("nivel", R.drawable.hipo1);
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

                DialogoInstruciones dInstrucciones = new DialogoInstruciones();
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

                Intent intent = new Intent(this, ListaHipotenuchas.class);
                startActivity(intent);

                return super.onOptionsItemSelected(item);

        }

        return super.onOptionsItemSelected(item);


    }


}