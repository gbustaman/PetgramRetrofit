package com.gecode.petgrammascotas.presentador;

import android.content.Context;
import android.util.Log;

import com.gecode.petgrammascotas.modelo.Mascota;
import com.gecode.petgrammascotas.modelo.db.ConstructorMascotas;
import com.gecode.petgrammascotas.vista.fragment.IRecyclerViewFragmentView;

import java.util.ArrayList;

/**
 * Created by gregorybr on 09-11-16.
 */

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter{

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    ConstructorMascotas constructorMascotas;
    //private ArrayList<Mascota> mascotas;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context, boolean bRaiting) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        constructorMascotas= new ConstructorMascotas(this.context);
        //obtenerMascotasBaseDatos();

        if(bRaiting) {
            mostarMascotasFavoritas();
            return;
        }
        mostrarMascotas();
    }

    @Override
    public void mostrarMascotas() {
        ArrayList<Mascota> mascotas = obtenerMascotas();
        Log.d("Mascota ",String.valueOf(mascotas.size()));
        if(mascotas.size() == 0){
            constructorMascotas.insertarMascotas();
            mascotas = obtenerMascotas();
        }
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
        iRecyclerViewFragmentView.crearAdapter(mascotas);
    }

    @Override
    public void mostarMascotasFavoritas() {
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
        iRecyclerViewFragmentView.crearAdapter(obtenerLikesMascotas());

    }

    @Override
    public ArrayList<Mascota> obtenerLikesMascotas() {
        return constructorMascotas.obtenerLikesMascotas();
    }

    @Override
    public ArrayList<Mascota> obtenerMascotas() {
        return constructorMascotas.obtenerMascotas();
    }


/*
    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();
    }*/


}
