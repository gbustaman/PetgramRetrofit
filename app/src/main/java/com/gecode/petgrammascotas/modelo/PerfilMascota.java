package com.gecode.petgrammascotas.modelo;

/**
 * Created by gregorybr on 30-10-16.
 */

public class PerfilMascota {
    private String nombreMascotaPerfil;
    private int perfilFoto;
    private String perfilRaiting;

    public PerfilMascota(int perfilFoto, String perfilRaiting ) {
        this.perfilFoto = perfilFoto;
        this.perfilRaiting = perfilRaiting;
    }

    public PerfilMascota(int perfilFoto, String nombreMascotaPerfil,String perfilRaiting){
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

    public int getPerfilFoto() {
        return perfilFoto;
    }

    public void setPerfilFoto(int perfilFoto) {
        this.perfilFoto = perfilFoto;
    }

    public String getPerfilRaiting() {
        return perfilRaiting;
    }

    public void setPerfilRaiting(String perfilRaiting) {
        this.perfilRaiting = perfilRaiting;
    }
}
