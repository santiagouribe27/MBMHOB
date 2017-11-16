package com.sistemasempresariales.afinal.mbmhob;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.*;
import entities.Libros;

public class BuscarActivity extends AppCompatActivity {

    private Button btnBuscar,btnVolver;
    private EditText editTextBuscar, editTextTitulo, editTextAutor, editTextReseña;

    private Libros libros = new Libros();
    private LibrosDB librosDB;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        btnVolver= (Button) findViewById(R.id.btnVolverBuscar);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuscarActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        librosDB = new LibrosDB(getApplicationContext());

        btnBuscar= (Button) findViewById(R.id.btnBuscarBuscar);
        editTextTitulo= (EditText) findViewById(R.id.editTextBuscaTitulo);
        editTextBuscar= (EditText) findViewById(R.id.editTextBuscar);
        editTextAutor= (EditText) findViewById(R.id.editTextBuscaAutor);
        editTextReseña= (EditText) findViewById(R.id.editTextBuscaReseña);


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultar();
            }
        });

    }


    public void consultar() {

        sqLiteDatabase = librosDB.getWritableDatabase();

        String[] parametros ={editTextBuscar.getText().toString()};
        String[] campos ={"titulo_libro", "autor_libro", "reseña_libro"};

        try {
            Cursor cursor = sqLiteDatabase.rawQuery("select * from" + " libros" + " where " + campos[0] + " = ?", new String[]{ String.valueOf(parametros[0])});
            cursor.moveToFirst();
            editTextTitulo.setText(cursor.getString(1));
            editTextAutor.setText(cursor.getString(2));
            editTextReseña.setText(cursor.getString(3));
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(BuscarActivity.this,"No existe el libro", Toast.LENGTH_SHORT).show();
        }

    }
}
