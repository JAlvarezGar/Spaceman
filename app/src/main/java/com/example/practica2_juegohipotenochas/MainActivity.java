package com.example.practica2_juegohipotenochas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Hipotenochas> hipotenochas;
    //final  int personajeSeleccionado;
    String nivelSeleccionado;
    int personajeSeleccionado;

    DialogoNivelJuego nivelJuego;

    DialogoInstruciones dInstrucciones;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




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


                return super.onOptionsItemSelected(item);
            case R.id.configuracion:
                nivelJuego = new DialogoNivelJuego();
                nivelJuego.show(getSupportFragmentManager(), "niveljuego");
                return super.onOptionsItemSelected(item);
            case R.id.selectpersonaje:

                AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder.setTitle("SELECCIONA HIPOTENOCHA");
                LayoutInflater inflater=(LayoutInflater)this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View row=inflater.inflate(R.layout.row_item,null);
                ListView lv=(ListView)row.findViewById(R.id.listview);
                lv.setAdapter(new AdaptadorPersonaje(this));

                builder.setView(row);
                AlertDialog diallogo=builder.create();
                diallogo.show();



                return super.onOptionsItemSelected(item);

        }

        return super.onOptionsItemSelected(item);


    }


}