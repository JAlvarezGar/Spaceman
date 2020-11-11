package com.example.practica2_juegohipotenochas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaHipotenuchas extends AppCompatActivity {

    ArrayList<Hipotenochas> hipotenochas;
    Adaptador adaptador;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_hipotenuchas);

        listView=(ListView)findViewById(R.id.listview);

        hipotenochas= new ArrayList<Hipotenochas>();
        hipotenochas.add(new Hipotenochas(R.drawable.hipo1));
        hipotenochas.add(new Hipotenochas(R.drawable.hipo2));
        hipotenochas.add(new Hipotenochas(R.drawable.hipo3));
        hipotenochas.add(new Hipotenochas(R.drawable.hipo4));
        hipotenochas.add(new Hipotenochas(R.drawable.hipo5));
        hipotenochas.add(new Hipotenochas(R.drawable.hipo6));
        hipotenochas.add(new Hipotenochas(R.drawable.hipo7));
        hipotenochas.add(new Hipotenochas(R.drawable.hipo8));
        hipotenochas.add(new Hipotenochas(R.drawable.hipo9));


        // el segundo parametro es el layout creado para el adaptador
        // en este caso es el lista_view
        adaptador= new Adaptador(this,R.layout.list_view,hipotenochas);
        listView.setAdapter(adaptador);

    }
}