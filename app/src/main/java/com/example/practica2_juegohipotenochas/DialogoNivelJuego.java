package com.example.practica2_juegohipotenochas;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogoNivelJuego extends DialogFragment {

    String[] niveles = {"Principiante", "Medio", "Avanzado"};
    String posicionString;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Escoge nivel de juego")
                .setItems(niveles, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int posicion) {

                        switch (posicion) {
                            case 0:
                                Intent intentPrincipiante= new Intent(getContext(),MainActivity.class);
                               posicionString="Principiante";
                                //Toast.makeText(getContext(), posicionString, Toast.LENGTH_SHORT).show();
                                intentPrincipiante.putExtra("nivel",posicionString);
                                startActivity(intentPrincipiante);
                                break;
                            case 1:
                                Intent intentMedio= new Intent(getContext(),MainActivity.class);
                                posicionString="Medio";
                                //Toast.makeText(getContext(), posicionString, Toast.LENGTH_SHORT).show();
                                intentMedio.putExtra("nivel",posicionString);
                                startActivity(intentMedio);
                                break;
                            case 2:
                                Intent intentAvanzado= new Intent(getContext(),MainActivity.class);
                                posicionString="Avanzado";
                                //Toast.makeText(getContext(), posicionString, Toast.LENGTH_SHORT).show();
                                intentAvanzado.putExtra("nivel",posicionString);
                                startActivity(intentAvanzado);
                                break;
                        }
                    }
                });
        return builder.create();
    }
}
