package com.example.practica2_juegohipotenochas;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class DialogoPersonajes extends DialogFragment {

    ArrayList<Hipotenochas> hipotenochas;
    AdaptadorListView adaptadorListView;
    ListView listView;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

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




        LayoutInflater inflater = getActivity().getLayoutInflater();



        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Escoge Hipotenocha");
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // el segundo parametro es el layout creado para el adaptadorListView
        // en este caso es el lista_view
        adaptadorListView = new AdaptadorListView(getContext(), R.layout.list_view, hipotenochas);
        builder.setAdapter(adaptadorListView, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = (String) adaptadorListView.getItem(which);

                AlertDialog.Builder builderInner = new AlertDialog.Builder(getActivity());
                builder.setTitle("Escoge Hipotenocha");
                       // .setView(listHipo);

            }
        });

/*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int personaje;



                // Según la posición del item pasamos la hipotenocha seleccionada a MainActivity
                switch (position) {
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    case 8:

                        break;


                }

            }

        });*/

        return super.onCreateDialog(savedInstanceState);
    }
}
