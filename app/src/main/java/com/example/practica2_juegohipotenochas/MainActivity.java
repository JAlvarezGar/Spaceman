package com.example.practica2_juegohipotenochas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Hipotenochas> hipotenochas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

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

                return super.onOptionsItemSelected(item);
            case R.id.configuracion:

                return super.onOptionsItemSelected(item);
            case R.id.selectpersonaje:

                Intent intent= new Intent(this,ListaHipotenuchas.class);
                startActivity(intent);

                return super.onOptionsItemSelected(item);



        }
        return super.onOptionsItemSelected(item);
    }
}