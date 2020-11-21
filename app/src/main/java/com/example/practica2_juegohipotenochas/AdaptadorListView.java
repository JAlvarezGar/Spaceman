package com.example.practica2_juegohipotenochas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


// adaptadorListView para el list view
public class AdaptadorListView extends ArrayAdapter {

    ArrayList<Hipotenochas> hipotenochas;
    LayoutInflater layoutInflater;
    Adapter adapter;
    ImageView img1;

    // constructor en el que hay que incluir el LayoutInflater
    public AdaptadorListView(@NonNull Context context, int resource, ArrayList<Hipotenochas> hipotenochas) {
        super(context, resource);
        this.hipotenochas = hipotenochas;
        this.layoutInflater = layoutInflater;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return hipotenochas.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
       return  null;
    }



    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View vista = layoutInflater.inflate(R.layout.custom_listview, null);

        img1=(ImageView)vista.findViewById(R.id.imageView);
        img1.setImageResource(hipotenochas.get(position).getImagen());

        return vista;
    }

}
