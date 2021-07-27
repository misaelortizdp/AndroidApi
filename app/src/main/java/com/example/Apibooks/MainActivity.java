package com.example.Apibooks;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button  servicio1, servicio2;
    TextView dato;
    int sw=0;

    String url1 = "https://api.nytimes.com/svc/books/v3/lists/current/hardcover-fiction.json?api-key=feAsAM32NQI7ayJsROlrOz0iAw0wMgPV";
    String url2= "https://api.nytimes.com/svc/books/v3/lists/names.json?api-key=feAsAM32NQI7ayJsROlrOz0iAw0wMgPV";

    ArrayList<Libro> arraylib1 = new ArrayList<Libro>();
    ArrayList<Libro> arraylib2 = new ArrayList<Libro>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


         dato = findViewById(R.id.txtdatos);
         servicio1 = findViewById(R.id.btnservicio1);
         servicio2 = findViewById(R.id.btnservicio2);


        servicio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conectar1();
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        Intent i = new Intent(getApplicationContext(), Listado.class);
                        i.putExtra("swicht", sw);
                        i.putParcelableArrayListExtra("listado", arraylib1);
                        startActivity(i);
                    }
                }, 1500);


            }
        });


        servicio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conectar2();
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        Intent j = new Intent(getApplicationContext(), Listado.class);
                        j.putParcelableArrayListExtra("listado", arraylib2);
                        startActivity(j);
                    }
                }, 1500);

            }
        });





    }
    public void conectar2(){
        sw=2;
        requestDatos(url2);
    }


    public void conectar1(){
        sw=1;
        requestDatos(url1);
    }

    public void requestDatos(String url){
        RequestQueue cola = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                      if (sw==1){
                          parserJson1(response);
                      }else{
                          if(sw==2){
                              parserJson2(response);
                          }

                      }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error en la conexion", Toast.LENGTH_LONG).show();
            }
            })
        {
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("api-key", "feAsAM32NQI7ayJsROlrOz0iAw0wMgPV");
                return headers;
            }
        };
        cola.add(jsonObjectRequest);
    }

        public void parserJson1(JSONObject response){
        try {
            String cadena = "";
            JSONObject results = response.getJSONObject("results");
            JSONArray books = results.getJSONArray("books");

            for (int i = 0 ; i<books.length(); i++) {
                JSONObject libs = books.getJSONObject(i);
                String titulo = libs.getString("title");
                String autor = libs.getString("author");
                String img_url = libs.getString("book_image");
                cadena = cadena + titulo + "," + autor + "," + img_url + "\n";
                Libro li = new Libro(titulo,autor,img_url);
                arraylib1.add(li);
            }
            dato.setText(cadena);
        } catch (JSONException e) {
           Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        }

    public void parserJson2(JSONObject response){
        try {
            String cadena = "";
            JSONArray results = response.getJSONArray("results");
            for (int i = 0 ; i<results.length(); i++) {
                JSONObject libs = results.getJSONObject(i);
                String nombre = libs.getString("list_name");
                String fantigua = libs.getString("oldest_published_date");
                String freciente = libs.getString("newest_published_date");
                cadena =  cadena + nombre + "," + fantigua + "," + freciente + "\n";
                Libro li = new Libro(nombre,fantigua,freciente);
                arraylib2.add(li);
            }
            dato.setText(cadena);
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
