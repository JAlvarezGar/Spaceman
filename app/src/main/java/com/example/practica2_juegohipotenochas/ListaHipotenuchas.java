package com.example.practica2_juegohipotenochas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaHipotenuchas extends AppCompatActivity {

    ArrayList<Hipotenochas> hipotenochas;
    AdaptadorListView adaptadorListView;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_hipotenuchas);

        listView = (ListView) findViewById(R.id.listview);

        hipotenochas = new ArrayList<Hipotenochas>();
        hipotenochas.add(new Hipotenochas(R.drawable.hipo1));
        hipotenochas.add(new Hipotenochas(R.drawable.hipo2));
        hipotenochas.add(new Hipotenochas(R.drawable.hipo3));
        hipotenochas.add(new Hipotenochas(R.drawable.hipo4));
        hipotenochas.add(new Hipotenochas(R.drawable.hipo5));
        hipotenochas.add(new Hipotenochas(R.drawable.hipo6));
        hipotenochas.add(new Hipotenochas(R.drawable.hipo7));
        hipotenochas.add(new Hipotenochas(R.drawable.hipo8));
        hipotenochas.add(new Hipotenochas(R.drawable.hipo9));


        // el segundo parametro es el layout creado para el adaptadorListView
        // en este caso es el lista_view
        adaptadorListView = new AdaptadorListView(this, R.layout.list_view, hipotenochas);
        listView.setAdapter(adaptadorListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int personaje;

                Intent intent = new Intent(ListaHipotenuchas.this, MainActivity.class);

                // Según la posición del item pasamos la hipotenocha seleccionada a MainActivity
                switch (position) {
                    case 0:
                        intent.putExtra("personaje", (R.drawable.hipo1));
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("personaje", (R.drawable.hipo2));
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("personaje", (R.drawable.hipo3));
                        startActivity(intent);
                        break;
                    case 3:
                        intent.putExtra("personaje", (R.drawable.hipo4));
                        startActivity(intent);
                        break;
                    case 4:
                        intent.putExtra("personaje", (R.drawable.hipo5));
                        startActivity(intent);
                        break;
                    case 5:
                        intent.putExtra("personaje", (R.drawable.hipo6));
                        startActivity(intent);
                        break;
                    case 6:
                        intent.putExtra("personaje", (R.drawable.hipo7));
                        startActivity(intent);
                        break;
                    case 7:
                        intent.putExtra("personaje", (R.drawable.hipo8));
                        startActivity(intent);
                        break;
                    case 8:
                        intent.putExtra("personaje", (R.drawable.hipo9));
                        startActivity(intent);
                        break;
                    case 9:
                        intent.putExtra("personaje", (R.drawable.hipo1));
                        startActivity(intent);
                        break;

                }

            }

        });

    }
}
