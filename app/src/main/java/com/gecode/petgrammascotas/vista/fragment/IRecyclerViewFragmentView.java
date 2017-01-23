package com.gecode.petgrammascotas.vista.fragment;

import com.gecode.petgrammascotas.adapter.AdapterMascota;
import com.gecode.petgrammascotas.modelo.PerfilMascota;

import java.util.ArrayList;

/**
 * Created by gregorybr on 09-11-16.
 */

public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    //public void crearAdapter (ArrayList<Mascota> lstmascotas);
    public void inicializarAdaptador(AdapterMascota adaptador);
    public AdapterMascota crearAdapter(ArrayList<PerfilMascota> mascotas);




}
