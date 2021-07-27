package com.example.Apibooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;



public class Listado extends AppCompatActivity {

    ArrayList<Libro> lib = new ArrayList<Libro>();
    ListView listado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        Intent i = getIntent();
        lib = i.getParcelableArrayListExtra("listado");
        listado = findViewById(R.id.lstcompetitions);
        if (lib !=null && lib.size()>0){
           LibroAdapter adapter = new LibroAdapter(this, lib );
           listado.setAdapter(adapter);
           adapter.notifyDataSetChanged();
           }
           else{
            Toast.makeText(this,"No hay datos" , Toast.LENGTH_LONG).show();
        }
    }
}
