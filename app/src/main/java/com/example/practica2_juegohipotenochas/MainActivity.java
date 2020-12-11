package com.example.practica2_juegohipotenochas;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import static com.example.practica2_juegohipotenochas.R.string.hipoEncontrada;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        View.OnLongClickListener,
        DialogoNivelJuego.RespuestaNivel {


    int personajeSeleccionado;
    DialogoNivelJuego nivelJuego;
    DialogoInstruciones dInstrucciones;
    DialogoPersonaje dialogoPersonaje;

    int[][] matriz;
    // constantes de nivel de juego
    // indican el nº de casillas del modo 8x8, 12x12, 16x16
    final int PRINCIPIANTE = 8;
    final int MEDIO = 12;
    final int AVANZADO = 16;

    GridLayout gridLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personajeSeleccionado = R.drawable.hipo3;

    }

    /**
     * Método para situar Hipotenochas en el tablero
     * en la cantidad escogida según el tamaño de del tablero
     *
     * @param cantidadBotones Segun el nivel elegido: 8, 12 o 16 ( por fila y columna )
     */
    public void colocarHipotenochas(int cantidadBotones) {
        switch (cantidadBotones) {
            case 8:
                matriz = new int[cantidadBotones][cantidadBotones];

                // ahora posiciono en ciertas matrices un -1 como hipotechocha
                for (int i = 0; i < 10; i++) {
                    int posicionAleatoriaFila = (int) Math.floor(Math.random() * (cantidadBotones - 1));
                    int posicionAleatoriaColumna = (int) Math.floor(Math.random() * (cantidadBotones - 1));

                    // hay hipotenocha
                    matriz[posicionAleatoriaFila][posicionAleatoriaColumna] = -1;

                    // comprueba si hay hiporenochas en las casillas adyacentes
                    adyacentes(posicionAleatoriaFila, posicionAleatoriaColumna, cantidadBotones);


                }


                break;

            case 12:

                matriz = new int[cantidadBotones][cantidadBotones];
                for (int i = 0; i < 30; i++) {
                    int posicionAleatoriaFila = (int) Math.floor(Math.random() * (cantidadBotones - 1) + 1);
                    int posicionAleatoriaColumna = (int) Math.floor(Math.random() * (cantidadBotones - 1) + 1);
                    // comprueba que no haya una Hipotenusa, en caso contrario hace un nuevo intento
                    // restandole 1 al bucle
                    if (matriz[posicionAleatoriaFila][posicionAleatoriaColumna] == -1) {
                        i = i - 1;
                    }
                    // hay hipotenocha
                    matriz[posicionAleatoriaFila][posicionAleatoriaColumna] = -1;

                    adyacentes(posicionAleatoriaFila, posicionAleatoriaColumna, cantidadBotones);
                }
                break;

            case 16:
                matriz = new int[cantidadBotones][cantidadBotones];

                for (int i = 0; i < 60; i++) {
                    int posicionAleatoriaFila = (int) Math.floor(Math.random() * (cantidadBotones - 1) + 1);
                    int posicionAleatoriaColumna = (int) Math.floor(Math.random() * (cantidadBotones - 1) + 1);
                    if (matriz[posicionAleatoriaFila][posicionAleatoriaColumna] == -1) {
                        i = i - 1;
                    }
                    // hay hipotenocha
                    matriz[posicionAleatoriaFila][posicionAleatoriaColumna] = -1;
                    adyacentes(posicionAleatoriaFila, posicionAleatoriaColumna, cantidadBotones);
                }
                break;
        }

    }

    /**
     *   metodo para rellenar las casillas adyacentes
     *   con la cantidad de hipotenochas cercanas
     *
     * @param posicionAleatoriaFila nº aleatorio para designar una fila
     * @param posicionAleatoriaColumna nº aleatorio para designar una columna
     * @param cantidadBotones Segun el nivel elegido: 8, 12 o 16 ( por fila y columna )
     */
    private void adyacentes(int posicionAleatoriaFila, int posicionAleatoriaColumna, int cantidadBotones) {

         /*
                Las 8 minas adyacentes a la celda seleccionada

                    N.O   N   N.E
                      \   |   /
                       \  |  /
                    O----Celda----E
                         / | \
                       /   |  \
                    S.O    S   S.E

                celda-->Celda Actual (fila, columna)
                N -->  Norte         (fila-1, columna)
                S -->  Sur           (fila+1, columna)
                E -->  Este          (fila, columna+1)
                W -->  Oeste         (fila, columna-1)
                N.E--> Noreste       (fila-1, columna+1)
                N.O--> Noroeste      (fila-1, columna-1)
                S.E--> Sureste       (fila+1, columna+1)
                S.O--> Suroeste      (fila+1, columna-1)
                */

        try {
            // Celda Noroeste
            if (matriz[posicionAleatoriaFila - 1][posicionAleatoriaColumna - 1] != -1
                    && posicionAleatoriaFila - 1 >= 0
                    && posicionAleatoriaColumna - 1 >= 0) {
                matriz[posicionAleatoriaFila - 1][posicionAleatoriaColumna - 1] =
                        (matriz[posicionAleatoriaFila - 1][posicionAleatoriaColumna - 1]) + 1;
            }


            // Celda Norte
            if (matriz[posicionAleatoriaFila - 1][posicionAleatoriaColumna] != -1
                    && posicionAleatoriaFila - 1 >= 0) {
                matriz[posicionAleatoriaFila - 1][posicionAleatoriaColumna] =
                        (matriz[posicionAleatoriaFila - 1][posicionAleatoriaColumna]) + 1;
            }


            // Celda Noreste
            if (matriz[posicionAleatoriaFila - 1][posicionAleatoriaColumna + 1] != -1
                    && posicionAleatoriaFila + 1 >= 0
                    && posicionAleatoriaColumna <= cantidadBotones) {
                matriz[posicionAleatoriaFila - 1][posicionAleatoriaColumna + 1] =
                        (matriz[posicionAleatoriaFila - 1][posicionAleatoriaColumna + 1]) + 1;
            }


            // Celda Oeste
            if (matriz[posicionAleatoriaFila][posicionAleatoriaColumna - 1] != -1
                    && posicionAleatoriaFila >= 0
                    && posicionAleatoriaColumna >= 0) {
                matriz[posicionAleatoriaFila][posicionAleatoriaColumna - 1] =
                        (matriz[posicionAleatoriaFila][posicionAleatoriaColumna - 1]) + 1;
            }


            // celda Suroeste
            if (matriz[posicionAleatoriaFila + 1][posicionAleatoriaColumna - 1] != -1
                    && posicionAleatoriaFila <= cantidadBotones
                    && posicionAleatoriaColumna - 1 >= 0) {
                matriz[posicionAleatoriaFila + 1][posicionAleatoriaColumna - 1] =
                        (matriz[posicionAleatoriaFila + 1][posicionAleatoriaColumna - 1]) + 1;
            }


            // celda Sur
            if (matriz[posicionAleatoriaFila + 1][posicionAleatoriaColumna] != -1
                    && posicionAleatoriaFila <= cantidadBotones) {
                matriz[posicionAleatoriaFila + 1][posicionAleatoriaColumna] =
                        (matriz[posicionAleatoriaFila + 1][posicionAleatoriaColumna]) + 1;
            }


            // Celda sureste
            if (matriz[posicionAleatoriaFila + 1][posicionAleatoriaColumna + 1] != -1
                    && posicionAleatoriaFila <= cantidadBotones
                    && posicionAleatoriaColumna <= cantidadBotones) {
                matriz[posicionAleatoriaFila + 1][posicionAleatoriaColumna + 1] =
                        (matriz[posicionAleatoriaFila + 1][posicionAleatoriaColumna + 1]) + 1;
            }


            // Celda Este
            if (matriz[posicionAleatoriaFila][posicionAleatoriaColumna + 1] != -1
                    && posicionAleatoriaColumna <= cantidadBotones) {
                matriz[posicionAleatoriaFila][posicionAleatoriaColumna + 1] =
                        (matriz[posicionAleatoriaFila][posicionAleatoriaColumna + 1]) + 1;
            }


        } catch (Exception exception) {
            Log.e("ERROR ", "Array fuera de rango");

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

        // gridLayout para colocar las filas y las colummnas
        gridLayout.setRowCount(cantidadBotones);
        gridLayout.setColumnCount(cantidadBotones);
        gridLayout.setLayoutParams(param);

        for (int i = 0; i < cantidadBotones; i++) {
            for (int j = 0; j < cantidadBotones; j++) {

                if (this.matriz[i][j] == -1) {
                    ImageButton button = new ImageButton(this);
                    //  Da forma rectangular al botón y establece el padding a 0
                    button.setBackground(getResources().getDrawable(R.drawable.forma_boton));
                    button.setLayoutParams(layoutParams);
                    button.setTag(this.matriz[i][j]);
                    button.setId(View.generateViewId());
                    button.setOnClickListener(this);
                    button.setOnLongClickListener(this);
                    this.gridLayout.addView(button);

                } else {
                    Button b = new Button(this);
                    //  Da forma rectangular al botón y establece el padding a 0
                    b.setBackground(getResources().getDrawable(R.drawable.forma_boton));
                    b.setLayoutParams(layoutParams);
                    b.setTag(this.matriz[i][j]);
                    b.setId(View.generateViewId());
                    b.setGravity(Gravity.CENTER);
                    b.setOnClickListener(this);
                    b.setOnLongClickListener(this);
                    this.gridLayout.addView(b);
                }
            }

        }
        constraintLayout.removeAllViews();
        constraintLayout.addView(gridLayout);
    }


    /**
     * @param menu indica que menú se quiere inflar
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

                colocarHipotenochas(PRINCIPIANTE);
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
                        personajeSeleccionado = R.drawable.hipo3;
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
        v.setOnClickListener(null);
        v.setOnLongClickListener(null);
        Integer vistaInt = 0;
        // en el método dibujarTablero() se introdujo la sentencia: button.setTag(matriz[i][j])
        // de ella me voy a valer pues sirve para establecer el valor que se le ha dado a cada botón
        // Lo voy a recuperar con getTag().
        //
        Object vista = v.getTag();
        vistaInt = Integer.valueOf((Integer) vista);

        if (vistaInt == -1) {
            ImageButton imageButton = (ImageButton) v;
            imageButton.setScaleType(ImageView.ScaleType.FIT_XY);
            imageButton.setBackgroundResource((personajeSeleccionado));

            // Dialogo que indica el final de la partida einicia una nueva
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Juego de las hipotenochas");
            builder.setIcon(personajeSeleccionado);
            builder.setMessage("\nLo siento, creo que has perdido.");
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    colocarHipotenochas(PRINCIPIANTE);
                    dibujoTablero(PRINCIPIANTE);
                }
            });
            // Create the AlertDialog
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            Button button = (Button) v;
            button.setText(String.valueOf(v.getTag()));
        }


    }

    /**
     *
     * @param v la vista en pulsación larga para mostrar la hipotenocha descubierta
     * @return
     */
    @Override
    public boolean onLongClick(View v) {
        v.setOnClickListener(null);
        v.setOnLongClickListener(null);
        Integer vistaInt = 0;
        // en el método dibujarTablero() se introdujo la sentencia: button.setTag(matriz[i][j])
        // de ella me voy a valer pues sirve para establecer el valor que se le ha dado a cada botón
        // Lo voy a recuperar con getTag().
        //
        Object vista = v.getTag();
        vistaInt = Integer.valueOf((Integer) vista);

        if (vistaInt == -1) {
            ImageButton imageButton = (ImageButton) v;
            imageButton.setScaleType(ImageView.ScaleType.FIT_XY);
            imageButton.setBackgroundResource((personajeSeleccionado));
            Toast.makeText(this, hipoEncontrada, Toast.LENGTH_SHORT).show();
        } else {
            Button button = (Button) v;
            button.setText(String.valueOf(v.getTag()));
        }
        
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