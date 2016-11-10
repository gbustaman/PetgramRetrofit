package com.gecode.petgrammascotas.modelo.db;

/**
 * Created by gregorybr on 09-11-16.
 */

public final class ConstantesBaseDatos {

    public static final String DATABASE_NAME = "mascotas";
    public static final int DATE_BASE_VERSION = 1;

    // 1er Tabla: Mascota
    public static final String TABLE_MASCOTA = "mascota";
    public static final String TABLE_MASCOTA_ID = "id";
    public static final String TABLE_MASCOTA_NONMBRE = "nombre";
    public static final String TABLE_MASCOTA_FOTO = "foto";

    // 2da Tabla: Mascota Likes
    public static final String TABLE_LIKES_MASCOTA = "mascotas_likes";
    public static final String TABLE_LIKES_MASCOTA_ID = "id";
    public static final String TABLE_LIKES_MASCOTA_ID_MASCOTA = "id_mascota";
    public static final String TABLE_LIKES_MASCOTA_NUMERO_LIKES= "numero_likes";

}
