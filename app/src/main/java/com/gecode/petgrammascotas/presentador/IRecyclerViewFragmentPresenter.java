package com.gecode.petgrammascotas.presentador;

import com.gecode.petgrammascotas.modelo.Mascota;

import java.util.ArrayList;

/**
 * Created by gregorybr on 09-11-16.
 */

public interface IRecyclerViewFragmentPresenter {

    public void mostrarMascotas();
    public void mostarMascotasFavoritas();
   // public void mostrarMascotasRV();

    public ArrayList<Mascota> obtenerLikesMascotas();
    public ArrayList<Mascota> obtenerMascotas();

}
