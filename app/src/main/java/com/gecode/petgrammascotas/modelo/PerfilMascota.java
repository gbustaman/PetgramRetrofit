package com.gecode.petgrammascotas.modelo;

/**
 * Created by gregorybr on 30-10-16.
 */

public class PerfilMascota  implements Comparable<PerfilMascota>{
    private String nombreMascotaPerfil;
    private int perfilFoto;
    private String perfilRaiting;
    // variables para Istagram
    private String urlFoto;
    private String urlFotoPerfil;
    private String usuario;
    private String nombreCompleto;
    private int likesFoto;
    private String id;

    public PerfilMascota(String urlFoto, String urlFotoPerfil,String nombreCompleto, int likesFoto) {
        this.urlFoto = urlFoto;
        this.urlFotoPerfil = urlFotoPerfil;
        this.nombreCompleto = nombreCompleto;
        this.likesFoto = likesFoto;
    }

    public PerfilMascota(int perfilFoto, String perfilRaiting ) {
        this.perfilFoto = perfilFoto;
        this.perfilRaiting = perfilRaiting;
    }

    public PerfilMascota() {
    }

    public PerfilMascota(int perfilFoto, String nombreMascotaPerfil, String perfilRaiting){
        this.perfilFoto=perfilFoto;
        this.perfilRaiting=perfilRaiting;
        this.nombreMascotaPerfil=nombreMascotaPerfil;
    }

    public String getNombreMascotaPerfil() {
        return nombreMascotaPerfil;
    }

    public void setNombreMascotaPerfil(String nombreMascotaPerfil) {
        this.nombreMascotaPerfil = nombreMascotaPerfil;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getUrlFotoPerfil() {
        return urlFotoPerfil;
    }

    public void setUrlFotoPerfil(String urlFotoPerfil) {
        this.urlFotoPerfil = urlFotoPerfil;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getLikesFoto() {
        return likesFoto;
    }

    public void setLikesFoto(int likesFoto) {
        this.likesFoto = likesFoto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int compareTo (PerfilMascota o) {
        return this.getLikesFoto()-o.getLikesFoto();
    }
}
