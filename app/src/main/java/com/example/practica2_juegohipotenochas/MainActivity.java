package com.example.practica2_juegohipotenochas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        View.OnLongClickListener,
        DialogoNivelJuego.RespuestaNivel {

    ArrayList<Hipotenochas> hipotenochas;
    String nivelSeleccionado;
    int personajeSeleccionado;
    DialogoNivelJuego nivelJuego;
    DialogoInstruciones dInstrucciones;
    DialogoPersonaje dialogoPersonaje;

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
        //linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        dibujoTablero(PRINCIPIANTE);


    }

    /**
     * @param cantidadBotones indica el nº de botones que queremos crear
     *                        8x8, 12x12, 16x16
     */
    private void dibujoTablero(int cantidadBotones) {

        RelativeLayout relativeLayout= (RelativeLayout)findViewById(R.id.layout_principal);
        GridLayout gridLayout = new GridLayout(getApplicationContext());
        GridLayout.LayoutParams param= new GridLayout.LayoutParams();
        param.setMargins(0, 0, 0, 0);
        param.height = -1;
        param.width = -1;

        // con esta clase consigo averiguar cual es el tamaño en pixeles de la pantalla
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        // linearLayout para colocar los botones en la pantalla de acuerdo
        // al tamaño de esta
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                (size.x ) / cantidadBotones,
                (size.y-150 ) / cantidadBotones,
                1.0f);
        // ajusto los margenes para que no se salgan de la pantalla los botones
        layoutParams.setMargins(0, -10, -15, -10);

        // gridLayout para colocar las filas y las colummnas
        gridLayout.setRowCount(cantidadBotones);
        gridLayout.setColumnCount(cantidadBotones);
        gridLayout.setLayoutParams(param);

        ImageButton button;

        for (int i = 0; i < (cantidadBotones*cantidadBotones); i++) {
            //button = new Button(this);
            button= new ImageButton(this);
            button.setLayoutParams(layoutParams);
            button.forceLayout();
            //button.setText("b");
            button.setId(View.generateViewId());
            //button.setGravity(button.TEXT_ALIGNMENT_CENTER);
            System.out.println(button.getId());
            gridLayout.addView(button, i);
        }
        relativeLayout.removeAllViews();
        relativeLayout.addView(gridLayout);
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

                dibujoTablero(PRINCIPIANTE);
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
//        dialogoPersonaje = new DialogoPersonaje();
//        dialogoPersonaje.show(getSupportFragmentManager(),"personaje");
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
     * public void onRespuestaNivel(String r);
     * }
     * de el diálogo DialogoNivelJuego.java
     *
     * @param r respuesta recibida al llamar al DialogoNivelJuego
     */
    @Override
    public void onRespuestaNivel(String r) {
        switch (r) {
            case "Principiante":
                Toast.makeText(this, "Nivel " + r, Toast.LENGTH_SHORT).show();
                dibujoTablero(PRINCIPIANTE);
                break;
            case "Medio":
                Toast.makeText(this, "Nivel " + r, Toast.LENGTH_SHORT).show();
                dibujoTablero(MEDIO);
                break;
            case "Avanzado":
                Toast.makeText(this, "Nivel " + r, Toast.LENGTH_SHORT).show();
                dibujoTablero(AVANZADO);
                break;
        }
    }

//    @Override
//    public void onRespuestaPersonaje(int i) {
//        switch (i){
//            case 0:
//                int hipotenocha=(R.drawable.hipo1);
//                Toast.makeText(this, "hipo1", Toast.LENGTH_SHORT).show();
//                break;
//            case 1:
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//            case 4:
//                break;
//            case 5:
//                break;
//        }
//
//    }
}