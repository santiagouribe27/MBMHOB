package database;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;

import entities.*;
import java.util.*;
/**
 * Created by JuanGuillermoGallego on 8/11/2017.
 */

public class LibrosDB extends SQLiteOpenHelper {

    private static final String dbName ="MBMHOB";
    private static final String tableName ="libros";

    private static String idColumn ="id";
    private static String tituloColumn ="titulo_libro";
    private static String autorColumn ="autor_libro";
    private static String reseñaColumn ="reseña_libro";
    private Context context;


    public LibrosDB(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + tableName + "("+
                idColumn + " integer primary key autoincrement, "
                + tituloColumn + " text, "
                + autorColumn + " text, "
                + reseñaColumn + " text "
        +");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + tableName);
        onCreate(sqLiteDatabase);
    }


    public List<Libros> FindAll () {
        try {
            List<Libros> librosList = new ArrayList<Libros>();
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from "+ tableName,null);
            if (cursor.moveToFirst()) {
                do {
                    Libros libros = new Libros();
                    libros.setId(cursor.getInt(0));
                    libros.setTitulo(cursor.getString(1));
                    libros.setAutor(cursor.getString(2));
                    libros.setReseña(cursor.getString(3));
                    librosList.add(libros);
                } while (cursor.moveToNext());
            }
            sqLiteDatabase.close();
            return librosList;
        } catch (Exception e) {
            return null;
        }
    }


    public boolean create(Libros libros) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(tituloColumn, libros.getTitulo());
            contentValues.put(autorColumn, libros.getAutor());
            contentValues.put(reseñaColumn,libros.getReseña());
            long rows =sqLiteDatabase.insert(tableName,null, contentValues);
            sqLiteDatabase.close();
            return rows >0;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean delete(String tituloLibro) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            int rows = sqLiteDatabase.delete(tableName, tituloColumn + " = ?", new String[]{ String.valueOf(tituloLibro)});
            sqLiteDatabase.close();
            return  rows>0;
        } catch (Exception e) {
            return false;
        }
    }

    public Libros find(String tituloLibro) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from" + tableName + " where " + tituloColumn + " = ?", new String[]{ String.valueOf(tituloLibro)});
            Libros libros = null;
            if (cursor.moveToFirst()){
                libros = new Libros();
                libros.setId(cursor.getInt(0));
                libros.setTitulo(cursor.getString(1));
                libros.setAutor(cursor.getString(2));
                libros.setReseña(cursor.getString(3));
            }
            sqLiteDatabase.close();
            return  libros;
        } catch (Exception e) {
            return null;
        }
    }

    public  boolean update(Libros libros) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(tituloColumn, libros.getTitulo());
            contentValues.put(autorColumn, libros.getAutor());
            contentValues.put(reseñaColumn, libros.getReseña());
            int rows = sqLiteDatabase.update(tableName, contentValues, idColumn + " = ? ", new String[]{String.valueOf(libros.getId())});
            sqLiteDatabase.close();
            return  rows>0;
        } catch (Exception e) {
            return false;
        }
    }




}
