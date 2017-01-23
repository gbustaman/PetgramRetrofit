package com.gecode.petgrammascotas.presentador;

import com.gecode.petgrammascotas.modelo.PerfilMascota;

import java.util.ArrayList;

/**
 * Created by gregorybr on 23-01-17.
 */

public interface IRecyclerViewFragmentFotoPresenter {
    public void mostrarMascotasRV();

    public void obtenerMediosRecientes();

    public ArrayList<PerfilMascota> getFotosMascotas();

    public void obtenerUserId(String userName);
}
