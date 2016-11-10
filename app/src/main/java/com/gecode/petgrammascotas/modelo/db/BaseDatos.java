package com.gecode.petgrammascotas.modelo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gecode.petgrammascotas.modelo.Mascota;

import java.util.ArrayList;

/**
 * Created by gregorybr on 09-11-16.
 */

public class BaseDatos extends SQLiteOpenHelper {
    // Variables Globales
    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATE_BASE_VERSION);
        this.context = context;

    }

    // Se crea la estructura de la Base de Datos.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTA + " (" +
                ConstantesBaseDatos.TABLE_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE + " TEXT," +
                ConstantesBaseDatos.TABLE_MASCOTA_FOTO + " INTEGER" +
                " )";

        String queryCrearTablaLikesMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA + " (" +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + " INTEGER, " +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTA + "(" + ConstantesBaseDatos.TABLE_MASCOTA_ID + ")" +
                " )";
        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikesMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROB TABLE IF EXIST " + ConstantesBaseDatos.TABLE_MASCOTA);
        db.execSQL("DROB TABLE IF EXIST " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA);
        onCreate(db);
    }

    /*******************************************************************
     ***********  Metodo para optener ddtos de lao Like Mascotas *******
     *******************************************************************/

    // Creacion de los metodo para esta aplicacion
    // Metodo que consulta a la Base de Datos
    public ArrayList<Mascota> obtenerLikesMascotasDB() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        // Consultar la tabla Mascota con "SELECT * FROM "
        //String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA;
        String query = "SELECT " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + ", COUNT(" +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + ") AS " +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES +
                " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                " GROUP BY " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA +
                " ORDER BY " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES + " DESC";
        // Abrir la base de Datos (en forma de lectura o escritura)
        SQLiteDatabase db = this.getWritableDatabase();
        // Ejecutar el query
        // rawQuery devuelve la coleccion de datos que consulto (query) y como resultado devolvera un Cursor
        Cursor registros = db.rawQuery(query, null);

        // Necesitamos recorrer los registros y para ello usaremos un while, ademas construiremos el objeto que
        // vamos a almacenar en nuestra lista mascotas

        int count = 1;

        while (registros.moveToNext()) {
            if (count > 5)
                break;
            // Llemaos los datos a mascota
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(registros.getColumnIndex(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA)));
            mascotaActual.setLikes(registros.getInt(registros.getColumnIndex(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES)));

            query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA + " WHERE " + ConstantesBaseDatos.TABLE_MASCOTA_ID + " =" + mascotaActual.getId();
            Cursor cursorMascota = db.rawQuery(query, null);

            if (cursorMascota.moveToNext()) {
                mascotaActual.setNombreMascota(cursorMascota.getString(cursorMascota.getColumnIndex(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE)));
                mascotaActual.setFoto(cursorMascota.getInt(cursorMascota.getColumnIndex(ConstantesBaseDatos.TABLE_MASCOTA_FOTO)));
            }
            mascotas.add(mascotaActual);
            count++;
        }
        db.close();
        return mascotas;
    }
    /**************************************************************
     ***********  Metodo para optener ddtos de las Mascotas *******
     **************************************************************/

    public ArrayList<Mascota> obtenerMascotasDB(){
        ArrayList<Mascota> mascotas=new ArrayList<>();
        String query = "SELECT * FROM "+ConstantesBaseDatos.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()){
            Mascota mascotaactual = new Mascota();
            mascotaactual.setId(cursor.getInt(cursor.getColumnIndex(ConstantesBaseDatos.TABLE_MASCOTA_ID)));
            mascotaactual.setNombreMascota(cursor.getString(cursor.getColumnIndex(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE)));
            mascotaactual.setFoto(cursor.getInt(cursor.getColumnIndex(ConstantesBaseDatos.TABLE_MASCOTA_FOTO)));

            query = "SELECT COUNT("+ ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES +") as raiting " +
                    "FROM "+ConstantesBaseDatos.TABLE_LIKES_MASCOTA+
                    " WHERE "+ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA +" ="+mascotaactual.getId();

            Cursor registros = db.rawQuery(query,null);
            if(registros.moveToNext()){
                mascotaactual.setLikes(registros.getInt(0));
            }else {
                mascotaactual.setLikes(0);
            }
            mascotas.add(mascotaactual);
        }
        db.close();
        return mascotas;
    }

    /***************************************************************
     ***********  Definimos nuestro metodo insertar mascotas *******
     ***************************************************************/

    // El contentValues va a tener asociado el valor con su respectivo campo.
    public void insertarMascotas(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA, null, contentValues);
        db.close();
    }

    //Definimos unmetodo insertar like a la tabla de LikesMascota.
    public void insertarLikeMascota(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_MASCOTA, null, contentValues);
        db.close();
    }

    public int obtenerLikesMascota(Mascota mascota) {
        int likes = 0;
        // Generamos la consulta a la Base de datos
        // ademas ejecutaremos un filtro a partir del id de la mascota y generar la suma de likes
        // para esto usaremos un COUNT
        String query = " SELECT COUNT(" + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES + ")" +    //solo obtenemos el dato de likes
                " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA + //obtenre de la tabla LIke_Mascota
                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + "=" + mascota.getId();      // DOnde: traera la suma de los likes

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()) {
            likes = registros.getInt(0);
        }
        db.close();
        return likes;
    }
}
