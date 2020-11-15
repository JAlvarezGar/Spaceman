package com.example.practica2_juegohipotenochas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Hipotenochas> hipotenochas;
    int personajeSeleccionado;
    DialogoNivelJuego nivelJuego;
    String nivel;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.pruebanivel);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuinicio, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.instrucciones:

                DialogoInstruciones dInstrucciones= new DialogoInstruciones();
                dInstrucciones.show(getSupportFragmentManager(),"instrucciones");
                return super.onOptionsItemSelected(item);
            case R.id.juegonuevo:
                Intent nuevo= new Intent(this,JuegoPrincipiante.class);
                startActivity(nuevo);

                return super.onOptionsItemSelected(item);
            case R.id.configuracion:
                nivelJuego= new DialogoNivelJuego();
                nivelJuego.show(getSupportFragmentManager(),"niveljuego");


                return super.onOptionsItemSelected(item);
            case R.id.selectpersonaje:

                Intent intent= new Intent(this,ListaHipotenuchas.class);
                startActivity(intent);
                /**
                try {
                    Bundle hipotenochaselecccionada = this.getIntent().getExtras();
                    personajeSeleccionado = hipotenochaselecccionada.getInt("personaje");
                }catch (Exception e){
                    personajeSeleccionado=R.drawable.hipo1;
                }*/

                return super.onOptionsItemSelected(item);

        }

        return super.onOptionsItemSelected(item);


    }


}