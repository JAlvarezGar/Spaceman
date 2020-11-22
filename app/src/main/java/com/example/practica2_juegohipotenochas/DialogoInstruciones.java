package com.example.practica2_juegohipotenochas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogoInstruciones extends DialogFragment {

    /**
     *
     * @param savedInstanceState
     * @return
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Usamos la clase Builder para construir el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Escribimos el título
        builder.setTitle("Intrucciones Hipotenochas");

        //Escribimos las instrucciones
        builder.setMessage(getString(R.string.textoinstrucciones));

        //añadimos el botón de Salida y su acción asociada
        builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                dialog.cancel();
                /**
                 *otra posiblidad . el valor true en setCancelable cierra el dialogo
                 *builder.setCancelable(true);
                 **/
            }
        });

        // Crear el AlertDialog y devolverlo
        return builder.create();
    }

}
