package entities;

import java.io.Serializable;

/**
 * Created by JuanGuillermoGallego on 8/11/2017.
 */

public class Libros implements Serializable{

    private int id;
    private String titulo, autor, reseña;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getReseña() {
        return reseña;
    }

    public void setReseña(String reseña) {
        this.reseña = reseña;
    }

    public Libros(int id, String titulo, String autor, String reseña) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.reseña = reseña;
    }

    public Libros() {
    }
}
