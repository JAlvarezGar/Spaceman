package com.example.practica2_juegohipotenochas;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogoNivelJuego extends DialogFragment {

    String[] niveles = {"Principiante", "Medio", "Avanzado"};
    String posicionString;
    RespuestaNivel nivel;

    /**
     *
     * @param savedInstanceState
     * @return
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View miDialogo = inflater.inflate(R.layout.custom_listview, null);

        builder.setTitle("Escoge nivel de juego")
                .setItems(niveles, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int posicion) {

                        switch (posicion) {
                            case 0:
                                nivel.onRespuestaNivel("Principiante");

                                break;
                            case 1:
                                nivel.onRespuestaNivel("Medio");
                                break;

                            case 2:
                                nivel.onRespuestaNivel("Avanzado");
                                break;

                        }
                    }
                });
        return builder.create();
    }
    public interface  RespuestaNivel{
        public void onRespuestaNivel(String r);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        nivel=(RespuestaNivel)getActivity();
    }
}
