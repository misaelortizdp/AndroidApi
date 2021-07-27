package com.example.Apibooks;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LibroAdapter extends ArrayAdapter<Libro>{
    Intent i;




    public LibroAdapter(@NonNull Context context, @NonNull ArrayList<Libro> Libros) {
        super(context, 0, Libros);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Libro comp = getItem(position);



        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_libros, parent, false);
        }
        // Lookup view for data population
        TextView id = (TextView) convertView.findViewById(R.id.txttitulo);
        TextView compet = (TextView) convertView.findViewById(R.id.txtautor);
        TextView area = (TextView) convertView.findViewById(R.id.txtimage);
        // Populate the data into the template view using the data object
        id.setText(comp.getTitulo());
        compet.setText(comp.getAutor());
        area.setText(comp.getImg_url());
        // Return the completed view to render on screen
        return convertView;
    }
}

