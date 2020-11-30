package com.example.practica2_juegohipotenochas;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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

    int[][] matriz;
    int[][] contadorAdyacentes;
    GridLayout gridLayout;
    int cantidadBotones;

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

    }

    public void colocarHipotenochas(int cantidadBotones) {
        switch (cantidadBotones) {
            case 8:
                matriz = new int[cantidadBotones][cantidadBotones];
                contadorAdyacentes = new int[cantidadBotones][cantidadBotones];
                for (int i = 0; i < 10; i++) {
                    int posicionAleatoriaFila = (int) Math.floor(Math.random() * (cantidadBotones - 1) + 1);
                    int posicionAleatoriaColumna = (int) Math.floor(Math.random() * (cantidadBotones - 1) + 1);
                    matriz[posicionAleatoriaFila][posicionAleatoriaColumna] = -1;

                }
                break;
            case 12:
                contadorAdyacentes = new int[cantidadBotones][cantidadBotones];
                matriz = new int[cantidadBotones][cantidadBotones];
                for (int i = 0; i < 30; i++) {
                    int posicionAleatoriaFila = (int) Math.floor(Math.random() * (cantidadBotones - 1) + 1);
                    int posicionAleatoriaColumna = (int) Math.floor(Math.random() * (cantidadBotones - 1) + 1);
                    matriz[posicionAleatoriaFila][posicionAleatoriaColumna] = -1;

                }
                break;
            case 16:
                matriz = new int[cantidadBotones][cantidadBotones];
                contadorAdyacentes = new int[cantidadBotones][cantidadBotones];
                for (int i = 0; i < 60; i++) {
                    int posicionAleatoriaFila = (int) Math.floor(Math.random() * (cantidadBotones - 1) + 1);
                    int posicionAleatoriaColumna = (int) Math.floor(Math.random() * (cantidadBotones - 1) + 1);
                    matriz[posicionAleatoriaFila][posicionAleatoriaColumna] = -1;

                }
                break;
        }

    }

    /**
     * @param cantidadBotones indica el nº de botones que queremos crear
     *                        8x8, 12x12, 16x16
     */
    private void dibujoTablero(int cantidadBotones) {

        ConstraintLayout constraintLayout = findViewById(R.id.layout_principal);
        gridLayout = new GridLayout(getApplicationContext());
        GridLayout.LayoutParams param = new GridLayout.LayoutParams();

        param.setMargins(0, 0, 0, 0);
        param.height = ViewGroup.LayoutParams.MATCH_PARENT;
        param.width = ViewGroup.LayoutParams.MATCH_PARENT;


        // con esta clase consigo averiguar cual es el tamaño en pixeles de la pantalla
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        // linearLayout para colocar los botones en la pantalla de acuerdo
        // al tamaño de esta
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                (size.x - 100) / cantidadBotones,
                (size.y - 350) / cantidadBotones,
                1.0f);
        // ajusto los margenes para que no se salgan de la pantalla los botones
        layoutParams.setMargins(0, 0, 0, 0);
        //layoutParams.weight=1;


        // gridLayout para colocar las filas y las colummnas
        gridLayout.setRowCount(cantidadBotones);
        gridLayout.setColumnCount(cantidadBotones);
        gridLayout.setLayoutParams(param);

        //matriz = new int[cantidadBotones][cantidadBotones];
        //int filasXcolumnas = cantidadBotones * cantidadBotones;

        for (int i = 0; i < (cantidadBotones); i++) {
            for (int j = 0; j < (cantidadBotones); j++) {


                if (matriz[i][j] == -1) {
                    ImageButton button = new ImageButton(MainActivity.this);
                    //  Da forma rectangular al botón y establece el padding a 0
                    button.setBackground(getResources().getDrawable(R.drawable.forma_boton));
                    button.setTag(matriz[i][j]);
                    button.setLayoutParams(layoutParams);
                    button.forceLayout();
                    button.setId(View.generateViewId());

                    button.setTag(contadorAdyacentes[i][j]);

                    contadorAdyacentes[i-1][j-1]++;
                    contadorAdyacentes[i-1][j  ]++;
                    contadorAdyacentes[i-1][j+1]++;
                    contadorAdyacentes[i  ][j-1]++;
                    contadorAdyacentes[i  ][j+1]++;
                    contadorAdyacentes[i+1][j-1]++;
                    contadorAdyacentes[i+1][j  ]++;
                    contadorAdyacentes[i+1][j+1]++;
                    button.setOnClickListener(this::onClick);
                    button.setOnLongClickListener(this);
                    gridLayout.addView(button);

                } else if (matriz[i][j] == 0) {
                    Button button = new Button(MainActivity.this);
                    //  Da forma rectangular al botón y establece el padding a 0
                    button.setBackground(getResources().getDrawable(R.drawable.forma_boton));
                    button.setTag(matriz[i][j]);
                    button.setLayoutParams(layoutParams);
                    button.forceLayout();
                    button.setId(View.generateViewId());
                    button.setGravity(Gravity.CENTER);
                    button.setOnClickListener(this);
                    button.setOnLongClickListener(this);
                    gridLayout.addView(button);
                }
            }

        }
        constraintLayout.removeAllViews();
        constraintLayout.addView(gridLayout);


    }


    /**
     * @param menu indica que menú se quiere inflrar
     *             en este caso se refiere al menú inicial
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuinicio, menu);
        return super.onCreateOptionsMenu(menu);
    }


    /**
     * @param item Se refiere al elemento seleccionado
     *             Instrucciones
     *             juegoNuevo
     *             Configuración
     *             Selección de personaje
     * @return devuelve el item seleccionado
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
        builder.setTitle(R.string.titulopersonaje);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.row_item, null);
        ListView lv = row.findViewById(R.id.listview);
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
                        personajeSeleccionado = R.drawable.hipo1;
                        Toast.makeText(MainActivity.this, "hipo1", Toast.LENGTH_SHORT).show();
                        diallogo.cancel();
                        break;
                    case 1:
                        personajeSeleccionado = R.drawable.hipo2;
                        Toast.makeText(MainActivity.this, "hipo2", Toast.LENGTH_SHORT).show();
                        diallogo.cancel();
                        break;
                    case 2:
                        personajeSeleccionado = R.drawable.hipo3;
                        Toast.makeText(MainActivity.this, "hipo3", Toast.LENGTH_SHORT).show();
                        diallogo.cancel();
                        break;
                    case 3:
                        personajeSeleccionado = R.drawable.hipo4;
                        Toast.makeText(MainActivity.this, "hipo4", Toast.LENGTH_SHORT).show();
                        diallogo.cancel();
                        break;
                    case 4:
                        personajeSeleccionado = R.drawable.hipo5;
                        Toast.makeText(MainActivity.this, "hipo5", Toast.LENGTH_SHORT).show();
                        diallogo.cancel();
                        break;
                    case 5:
                        personajeSeleccionado = R.drawable.hipo6;
                        Toast.makeText(MainActivity.this, "hipo6", Toast.LENGTH_SHORT).show();
                        diallogo.cancel();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "hipo1", Toast.LENGTH_SHORT).show();
                        personajeSeleccionado = R.drawable.hipo1;
                        diallogo.cancel();
                        break;

                }
            }
        });

    }

    /**
     * @param v vista que pertenece al botón pulsado
     */
    @Override
    public void onClick(View v) {
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        // en el método dibujarTablero() se introdujo la sentencia: button.setTag(matriz[i][j])
        // de ella me voy a valer pues sirve para establecer el valor que se le ha dado a cada botón
        // Lo voy a recuperar con getTag().
        //
        Object vista = v.getTag();
        Integer vistaInt = Integer.valueOf((Integer) vista);
        if (vistaInt == -1) {
            ImageButton imageButton = (ImageButton) v;
            imageButton.setImageDrawable(getResources().getDrawable(R.drawable.hipo1));
            imageButton.setScaleType(ImageView.ScaleType.FIT_XY);

        }


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
                colocarHipotenochas(PRINCIPIANTE);
                dibujoTablero(PRINCIPIANTE);
                break;
            case "Medio":
                Toast.makeText(this, "Nivel " + r, Toast.LENGTH_SHORT).show();
                colocarHipotenochas(MEDIO);
                dibujoTablero(MEDIO);
                break;
            case "Avanzado":
                Toast.makeText(this, "Nivel " + r, Toast.LENGTH_SHORT).show();
                colocarHipotenochas(AVANZADO);
                dibujoTablero(AVANZADO);
                break;
        }
    }


}