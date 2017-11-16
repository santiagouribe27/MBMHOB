package com.sistemasempresariales.afinal.mbmhob;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import adapters.LibrosListAdapter;
import entities.*;
import database.*;

import android.view.View;
import android.widget.*;
import android.content.*;

public class DetalleLibroActivity extends AppCompatActivity {

    private TextView textViewTtitulo, textViewAutor, textViewReseña;
    private Button btnVolver, btnEditar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_libro);

        final Intent intent1 = getIntent();
        final Libros libros = (Libros) intent1.getSerializableExtra("libros");

        this.textViewTtitulo = (TextView) findViewById(R.id.textViewDetalleTitulo);
        this.textViewTtitulo.setText(libros.getTitulo());
        this.textViewAutor = (TextView) findViewById(R.id.textViewDetalleAutor);
        this.textViewAutor.setText(libros.getAutor());
        this.textViewReseña = (TextView) findViewById(R.id.textViewDetalleReseña);
        this.textViewReseña.setText(libros.getReseña());

        this.btnVolver= (Button) findViewById(R.id.btnDetalleVolver);
        this.btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(DetalleLibroActivity.this,MainActivity.class);
                startActivity(intent2);
            }
        });

        this.btnEliminar=(Button) findViewById(R.id.btnDetalleEliminar);
        this.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                builder.setCancelable(false);
                builder.setTitle("Eliminar el libro: "+ textViewTtitulo.getText().toString());
                builder.setMessage("¿Esta seguro?");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LibrosDB librosDB = new LibrosDB(getBaseContext());
                        if (librosDB.delete(libros.getTitulo())) {
                            Toast.makeText(DetalleLibroActivity.this,"¡Libro eliminado!", Toast.LENGTH_SHORT).show();
                            Intent intent3 = new Intent(DetalleLibroActivity.this, MainActivity.class);
                            startActivity(intent3);
                        } else {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getBaseContext());
                            builder1.setCancelable(false);
                            builder1.setMessage("No se ha podido eliminar el libro");
                            builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            builder1.create().show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.create().show();
            }
        });

        this.btnEditar= (Button) findViewById(R.id.btnDetalleEditar);
        this.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalleLibroActivity.this,EditLibroActivity.class);
                intent.putExtra("libros", libros);
                startActivity(intent);
            }
        });
    }

}
