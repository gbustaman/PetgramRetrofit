package com.gecode.petgrammascotas.vista.fragment;

import com.gecode.petgrammascotas.adapter.AdapterPerfilMascota;
import com.gecode.petgrammascotas.modelo.PerfilMascota;

import java.util.ArrayList;

/**
 * Created by gregorybr on 23-01-17.
 */

public interface IRecyclerViewFragmentFoto {
    public AdapterPerfilMascota crearAdaptador(ArrayList<PerfilMascota> mascotas);

    public void inicializarAdaptador(AdapterPerfilMascota adaptador);

    public void generarGridLyout();
}
