package com.example.practica2_juegohipotenochas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener, DialogoNivelJuego.RespuestaNivel {

    ArrayList<Hipotenochas> hipotenochas;
    String nivelSeleccionado;
    int personajeSeleccionado;
    DialogoNivelJuego nivelJuego;
    DialogoInstruciones dInstrucciones;

    // constantes de nivel de juego
    // indican el nº de casillas del modo 8x8, 12x12, 16x16
    final int PRINCIPIANTE = 8;
    final int MEDIO = 12;
    final int AVANZADO = 16;

    LinearLayout linearLayout;
    TableLayout table;
    Button boton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        dibujoTablero(PRINCIPIANTE);


    }

    /**
     * @param cantidadBotones indica el nº de botones que queremos crear
     *                        8x8, 12x12, 16x16
     */
    private void dibujoTablero(int cantidadBotones) {

        table = new TableLayout(this);
        table.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT));
        /**
         * setStretchAllColumns = establecer Reducir todas las columnas
         * setShrinkAllColumns = establecer Estirar todas las columnas
         * */
        table.setStretchAllColumns(true);
        table.setShrinkAllColumns(true);
        table.setWeightSum(cantidadBotones);


        // Nº de filas
        for (int i = 0; i < cantidadBotones; i++) {
            TableRow tr = new TableRow(this);
            tr.setGravity(Gravity.CENTER);
            tr.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT, 1.0f));

            // Nº de columnas
            for (int j = 0; j < cantidadBotones; j++) {
                boton = new Button(this);
                boton.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                boton.setId(View.generateViewId());
                boton.setText(i + "," + j);
                boton.setTextSize(0);
                boton.setOnClickListener(this);
                boton.setOnLongClickListener(this);
                // añade los botones a las filas
                tr.addView(boton);
            }
            // añade las filas a la tabla
            table.addView(tr);
        }
        linearLayout.removeAllViews();
        // añade la tabla al linear layout
        linearLayout.addView(table);
    }

    /**
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuinicio, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * @param item
     * @return
     */
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

                seleccionPersonaje();
                return super.onOptionsItemSelected(item);

        }

        return super.onOptionsItemSelected(item);


    }

    /**
     * método para seleccionar una hipotenocha personalizada
     */
    private void seleccionPersonaje() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("SELECCIONA HIPOTENOCHA");
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.row_item, null);
        ListView lv = (ListView) row.findViewById(R.id.listview);
        lv.setAdapter(new AdaptadorPersonaje(this));

        builder.setView(row);
        AlertDialog diallogo = builder.create();
        diallogo.show();

        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(MainActivity.this, "hipo1", Toast.LENGTH_SHORT).show();
                        diallogo.cancel();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "hipo2", Toast.LENGTH_SHORT).show();
                        diallogo.cancel();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "hipo3", Toast.LENGTH_SHORT).show();
                        diallogo.cancel();
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "hipo4", Toast.LENGTH_SHORT).show();
                        diallogo.cancel();
                        break;
                    case 4:
                        Toast.makeText(MainActivity.this, "hipo5", Toast.LENGTH_SHORT).show();
                        diallogo.cancel();
                        break;
                    case 5:
                        Toast.makeText(MainActivity.this, "hipo6", Toast.LENGTH_SHORT).show();
                        diallogo.cancel();
                        break;

                }
            }
        });

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    /**
     * metodo que implemetamos con la interfaz:
     * public interface  RespuestaNivel{
     *         public void onRespuestaNivel(String r);
     *     }
     *     de el diálogo DialogoNivelJuego.java
     * @param r respuesta recibida al llamar al DialogoNivelJuego
     */
    @Override
    public void onRespuestaNivel(String r) {
        switch (r) {
            case "Principiante":
                Toast.makeText(this, "Nivel "+r, Toast.LENGTH_SHORT).show();
                dibujoTablero(PRINCIPIANTE);
                break;
            case "Medio":
                Toast.makeText(this, "Nivel "+r, Toast.LENGTH_SHORT).show();
                dibujoTablero(MEDIO);
                break;
            case "Avanzado":
                Toast.makeText(this, "Nivel "+r, Toast.LENGTH_SHORT).show();
                dibujoTablero(AVANZADO);
                break;
        }
    }
}