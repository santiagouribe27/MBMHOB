package com.sistemasempresariales.afinal.mbmhob;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import adapters.LibrosListAdapter;
import entities.*;
import database.*;
import android.widget.*;
import android.content.*;

public class MainActivity extends AppCompatActivity {

    private Button btnAgregar, btnBuscar;
    private ListView listViewLibros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnAgregar = (Button) findViewById(R.id.btnAgregar);
        this.btnBuscar= (Button) findViewById(R.id.btnBuscar);

        this.btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BuscarActivity.class);
                startActivity(intent);
            }
        });

        this.btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent1 = new Intent(MainActivity.this,AddLibroActivity.class);
                startActivity(intent1);
            }
        });

        final LibrosDB librosDB = new LibrosDB(this);
        this.listViewLibros = (ListView) findViewById(R.id.listViewLibros);
        this.listViewLibros.setAdapter(new LibrosListAdapter(this,librosDB.FindAll()));

        this.listViewLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Libros libros = librosDB.FindAll().get(i);
                Intent intent1 = new Intent(MainActivity.this, DetalleLibroActivity.class);
                intent1.putExtra("libros", libros);
                startActivity(intent1);
            }
        });
    }
}
