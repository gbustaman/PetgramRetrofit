package com.gecode.petgrammascotas.restApi.model;

import com.gecode.petgrammascotas.modelo.PerfilMascota;

import java.util.ArrayList;

/**
 * Created by gregorybr on 22-01-17.
 */

public class MascotasResponse {
    private ArrayList<PerfilMascota> mascotas;

    private PerfilMascota userInformation;

    public ArrayList<PerfilMascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<PerfilMascota> mascotas) {
        this.mascotas = mascotas;
    }

    public PerfilMascota getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(PerfilMascota userInformation) {
        this.userInformation = userInformation;
    }
}
