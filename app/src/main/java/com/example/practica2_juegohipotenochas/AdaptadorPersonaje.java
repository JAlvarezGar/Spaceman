package com.example.practica2_juegohipotenochas;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorPersonaje extends BaseAdapter {
    Context context;
    ArrayList<Hipotenochas> arrayList;

    /**
     *
     * @param context
     */
    public AdaptadorPersonaje(Context context) {
        this.context = context;
        arrayList= new ArrayList<>();
        Resources res= context.getResources();
        String[] nombres=res.getStringArray(R.array.nombres);
        int[] images={R.drawable.astronauta1,R.drawable.astronauta2,R.drawable.astronauta3,
                R.drawable.astronauta4,R.drawable.astronauta5,R.drawable.astronauta6,
                R.drawable.astronauta7,R.drawable.astronauta8,R.drawable.astronauta9};
    /**
     *  Crea un arraylist con todas la hipotenochas y sus nombres
     */
        for (int i = 0; i <nombres.length ; i++) {
            arrayList.add(new Hipotenochas(images[i],nombres[i]));
        }

    }

    /**
     *
     * @return devuelve el tamaño del arraylist
     */
    @Override
    public int getCount() {
        return arrayList.size();
    }

    /**
     *
     * @param position
     * @return devuelve la posicion del item
     */
    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row= inflater.inflate(R.layout.custom_listview,null);
        TextView tv1=(TextView)row.findViewById(R.id.textView);
        ImageView img1=(ImageView)row.findViewById(R.id.imageView);

        Hipotenochas temp_obj=arrayList.get(position);
        tv1.setText(temp_obj.getNombre());
        img1.setImageResource(temp_obj.getImagen());

        return row;
    }
}
