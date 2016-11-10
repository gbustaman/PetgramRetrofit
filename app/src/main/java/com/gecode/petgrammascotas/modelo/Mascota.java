package com.gecode.petgrammascotas.modelo;

/**
 * Created by gregorybr on 28-10-16.
 */

public class Mascota {

    private int id;
    private String nombreMascota;
    private int foto;
    private int likes;


    public Mascota(int foto, String nombreMascota, int likes) {
        this.nombreMascota = nombreMascota;
        this.foto = foto;
        this.likes = likes;

    }

    public Mascota() {

    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
