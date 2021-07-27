package com.example.Apibooks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListaAdapter extends ArrayAdapter<Lista>{

    public ListaAdapter(@NonNull Context context, @NonNull ArrayList<Lista> Listas) {
        super(context, 0, Listas);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Lista comp = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listas, parent, false);
        }
        // Lookup view for data population
        TextView nombre = (TextView) convertView.findViewById(R.id.txtnombre);
        TextView fantigua = (TextView) convertView.findViewById(R.id.txtmasantigua);
        TextView freciente = (TextView) convertView.findViewById(R.id.txtmasreciente);
        // Populate the data into the template view using the data object
        nombre.setText(comp.getNombre());
        fantigua.setText(comp.getFantigua());
        freciente.setText(comp.getFreciente());
        // Return the completed view to render on screen
        return convertView;
    }
}

