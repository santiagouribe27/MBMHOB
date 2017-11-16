package com.sistemasempresariales.afinal.mbmhob;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import entities.*;
import database.*;
import android.widget.*;
import android.content.*;

public class AddLibroActivity extends AppCompatActivity {

    private Button btnAtras, btnAñadir;
    private EditText editTextTitulo, editTextAutor, editTextReseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_libro);

        this.editTextTitulo = (EditText) findViewById(R.id.editTextADDTitulo);
        this.editTextAutor = (EditText) findViewById(R.id.editTextADDAutor);
        this.editTextReseña = (EditText) findViewById(R.id.editTextADDReseña);

        this.btnAtras= (Button) findViewById(R.id.btnADDVolver);
        this.btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent1 = new Intent(AddLibroActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });

        this.btnAñadir= (Button) findViewById(R.id.btnADDAñadir);
        this.btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LibrosDB librosDB = new LibrosDB(getBaseContext());
                Libros libros = new Libros();
                libros.setTitulo(editTextTitulo.getText().toString());
                libros.setAutor(editTextAutor.getText().toString());
                libros.setReseña(editTextReseña.getText().toString());
                if (editTextTitulo.getText().length() ==0 ||editTextAutor.getText().length() ==0 || editTextReseña.getText().length()==0){
                    Toast.makeText(AddLibroActivity.this,"Debe llegar todos los campos", Toast.LENGTH_SHORT).show();
                } else
                if(librosDB.create(libros)){
                    Intent intent1=new Intent(AddLibroActivity.this, MainActivity.class);
                    startActivity(intent1);
                    Toast.makeText(AddLibroActivity.this,"¡Libro creado!", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage("NO SE HA PODIDO AGREGAR EL LIBRO");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.create().show();
                }
            }
        });
    }
}
