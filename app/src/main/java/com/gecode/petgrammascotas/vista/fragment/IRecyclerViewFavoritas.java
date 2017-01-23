package com.gecode.petgrammascotas.vista.fragment;

import com.gecode.petgrammascotas.adapter.AdapterMascota;
import com.gecode.petgrammascotas.modelo.PerfilMascota;

import java.util.ArrayList;

/**
 * Created by gregorybr on 23-01-17.
 */

public interface IRecyclerViewFavoritas {

    public void generarLinearLyout();

    public AdapterMascota crearAdaptador(ArrayList<PerfilMascota> mascotas);

    public void inicializarAdaptador(AdapterMascota adaptador);


}