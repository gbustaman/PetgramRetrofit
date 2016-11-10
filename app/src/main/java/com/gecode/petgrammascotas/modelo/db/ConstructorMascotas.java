package com.gecode.petgrammascotas.modelo.db;

import android.content.ContentValues;
import android.content.Context;

import com.gecode.petgrammascotas.R;
import com.gecode.petgrammascotas.modelo.Mascota;

import java.util.ArrayList;

/**
 * Created by gregorybr on 09-11-16.
 */

public class ConstructorMascotas {
    private static final int LIKE = 1 ;
    private Context context;
    BaseDatos db;
    public ConstructorMascotas(Context context) {
        this.context = context;
        db = new BaseDatos(context);
    }

    public ArrayList<Mascota> obtenerMascotas (){
        // Ejecutamos la conexion a mi Base de Datos
        db = new BaseDatos(context);
        //insertarTresMascotas(db);
        return db.obtenerMascotasDB();
    }

    public ArrayList<Mascota> obtenerLikesMascotas(){
       // BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascotasDB();
    }

    public void darLikeMascota (Mascota mascota){
        //BaseDatos db =new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES, mascota.getLikes() + LIKE);
        db.insertarLikeMascota(contentValues);
    }

    public int obtenerLikesMascota (Mascota mascota){
        //BaseDatos db = new BaseDatos(context);
        return  db.obtenerLikesMascota(mascota);
    }

    // Metodo para crear algunos objetos de mascotas
    public void insertarMascotas (){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Muñeco");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_2);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Laica");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_3);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Petrolero");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_4);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Valvula");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_5);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Gordo");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_6);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Mariposa");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_7);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Campeón");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_8);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Dumbo");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_9);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Chimbo");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_10);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Lasi");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_11);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Poker");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_12);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Thimboy");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_13);
        db.insertarMascotas(contentValues);

    }
}
