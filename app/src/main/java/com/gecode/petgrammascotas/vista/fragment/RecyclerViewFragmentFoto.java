package com.gecode.petgrammascotas.vista.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gecode.petgrammascotas.R;
import com.gecode.petgrammascotas.adapter.AdapterPerfilMascota;
import com.gecode.petgrammascotas.modelo.PerfilMascota;
import com.gecode.petgrammascotas.presentador.IRecyclerViewFragmentFotoPresenter;
import com.gecode.petgrammascotas.presentador.RecyclerViewFragmentFotoPresenter;

import java.util.ArrayList;

/**
 * Created by gregorybr on 23-01-17.
 */

public class RecyclerViewFragmentFoto extends Fragment implements IRecyclerViewFragmentFoto {
    private ArrayList<PerfilMascota> mascotas;
    private RecyclerView listaMascotas;
    private IRecyclerViewFragmentFotoPresenter presenter;
    private long userId = Long.parseLong("4253551275") ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_recycleview, container, false);
        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascota);

        SharedPreferences miPreferenciaCompartida = getActivity().getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);

        String nombrePerfilActual = miPreferenciaCompartida.getString(getResources().getString(R.string.NombrePerfil), "");

        if(nombrePerfilActual != "") {
            presenter = new RecyclerViewFragmentFotoPresenter(this, getContext(), nombrePerfilActual);
        }
        else{
            presenter = new RecyclerViewFragmentFotoPresenter(this, getContext(), userId);
        }


        return v;
    }

    @Override
    public AdapterPerfilMascota crearAdaptador(ArrayList<PerfilMascota> mascotas) {
        AdapterPerfilMascota a = new AdapterPerfilMascota(mascotas, getActivity());
        return a;
    }

    @Override
    public void inicializarAdaptador(AdapterPerfilMascota adaptador) {
        listaMascotas.setAdapter(adaptador);
    }


    @Override
    public void generarGridLyout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        listaMascotas.setLayoutManager(gridLayoutManager);
    }

}
