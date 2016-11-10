package com.gecode.petgrammascotas.vista.fragment;

import com.gecode.petgrammascotas.modelo.Mascota;

import java.util.ArrayList;

/**
 * Created by gregorybr on 09-11-16.
 */

public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public void crearAdapter (ArrayList<Mascota> lstmascotas);




}
