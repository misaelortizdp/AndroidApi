package com.example.Apibooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;



public class Listado2 extends AppCompatActivity {

    ArrayList<Lista> list = new ArrayList<Lista>();
    ListView listado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado2);
        Intent i = getIntent();
        list = i.getParcelableArrayListExtra("listados");
        listado = findViewById(R.id.lstlistas);
        if (list !=null && list.size()>0){
            ListaAdapter adapter = new ListaAdapter(this, list );
            listado.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(this,"No hay datos" , Toast.LENGTH_LONG).show();
        }
    }
}
