package com.example.practica2_juegohipotenochas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class JuegoPrincipiante extends MainActivity {
    TextView tv1,tv2;
    String nivel;
    int personaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_principiante);

        tv1=(TextView)findViewById(R.id.tvnivel);
        tv2=(TextView)findViewById(R.id.tvPersonaje);

        // recibiendo datos de nivel  e Hipotenocha
        try {
            Bundle nivelesBundle = this.getIntent().getExtras();
            nivel = nivelesBundle.getString("nivel","Principiante");
            //Toast.makeText(this, "elegiste el nivelazo: "+nivel, Toast.LENGTH_SHORT).show();
            tv1.setText("nivel: "+nivel);
        }catch (Exception e){
            nivel="Principiante";
            Toast.makeText(this, "nivel por defecto básico", Toast.LENGTH_SHORT).show();
        }

        try {
            Bundle nivelesBundle = this.getIntent().getExtras();
            personaje = nivelesBundle.getInt("personaje",R.drawable.hipo1);
            //Toast.makeText(this, "elegiste el nivelazo: "+nivel, Toast.LENGTH_SHORT).show();
            tv2.setText("personaje: "+personaje);
        }catch (Exception e){
            personaje=(R.drawable.hipo1);
            Toast.makeText(this, "personaje por defecto hipo1", Toast.LENGTH_SHORT).show();
        }
    }
}