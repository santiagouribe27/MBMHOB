package com.sistemasempresariales.afinal.mbmhob;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import entities.*;
import database.*;

import android.view.View;
import android.widget.*;
import android.content.*;

public class EditLibroActivity extends AppCompatActivity {

    private EditText editTextTitulo, editTextAutor, editTextReseña;
    private Button btnVolver, btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_libro);
        final Intent intent = getIntent();
        final Libros libros = (Libros) intent.getSerializableExtra("libros");

        this.editTextTitulo= (EditText) findViewById(R.id.editTextEditTitulo);
        this.editTextTitulo.setText(libros.getTitulo());
        this.editTextAutor= (EditText) findViewById(R.id.editTextEditAutor);
        this.editTextAutor.setText(libros.getAutor());
        this.editTextReseña= (EditText) findViewById(R.id.editTextEdirReseña);
        this.editTextReseña.setText(libros.getReseña());

        this.btnVolver = (Button) findViewById(R.id.btnEditAtras);
        this.btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EditLibroActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });

        this.btnGuardar = (Button) findViewById(R.id.btnEditGuardar);
        this.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LibrosDB librosDB = new LibrosDB(getBaseContext());
                libros.setTitulo(editTextTitulo.getText().toString());
                libros.setAutor(editTextAutor.getText().toString());
                libros.setReseña(editTextReseña.getText().toString());

                if (librosDB.update(libros)) {
                    Toast.makeText(EditLibroActivity.this,"¡Libro actualizado!", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(EditLibroActivity.this, MainActivity.class);
                    startActivity(intent1);
                } else  {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage("No se ha podido actualizar el libro");
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
