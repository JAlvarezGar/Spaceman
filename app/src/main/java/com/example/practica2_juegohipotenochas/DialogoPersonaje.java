package com.example.practica2_juegohipotenochas;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class DialogoPersonaje extends DialogFragment {

    RespuestaPersonaje personajes;

    int personajeSeleccionado;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("SELECCIONA HIPOTENOCHA");
        LayoutInflater inflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.row_item, null);
        ListView lv = (ListView) row.findViewById(R.id.listview);
        lv.setAdapter(new AdaptadorPersonaje(getContext()));

        builder.setView(row);
        AlertDialog diallogo = builder.create();
        diallogo.show();

        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        personajes.onRespuestaPersonaje(0);
                        diallogo.cancel();
                        break;
                    case 1:
                        personajes.onRespuestaPersonaje(position);
                        diallogo.cancel();
                        break;
                    case 2:
                        personajes.onRespuestaPersonaje(position);
                        diallogo.cancel();
                        break;
                    case 3:
                        personajes.onRespuestaPersonaje(position);
                        diallogo.cancel();
                        break;
                    case 4:
                        personajes.onRespuestaPersonaje(position);
                        diallogo.cancel();
                        break;
                    case 5:
                        personajes.onRespuestaPersonaje(position);
                        diallogo.cancel();
                        break;

                }
            }
        });
        return super.onCreateDialog(savedInstanceState);
    }
    public interface  RespuestaPersonaje{
        public void onRespuestaPersonaje(int i);
    }
}
