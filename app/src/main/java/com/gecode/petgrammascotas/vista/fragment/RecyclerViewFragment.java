package com.gecode.petgrammascotas.vista.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gecode.petgrammascotas.R;
import com.gecode.petgrammascotas.adapter.AdapterMascota;
import com.gecode.petgrammascotas.modelo.Mascota;
import com.gecode.petgrammascotas.presentador.IRecyclerViewFragmentPresenter;
import com.gecode.petgrammascotas.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by gregorybr on 30-10-16.
 */

public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView{
    // Variables Globales
    private RecyclerView listadoMascotas;
    //ArrayList<Mascota> lstmascotas;


    private IRecyclerViewFragmentPresenter presenter;

    public RecyclerViewFragment() {

    }

    public static RecyclerViewFragment newInstance(String param1, String param2) {
        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
        Bundle args = new Bundle();
        recyclerViewFragment.setArguments(args);
        return recyclerViewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_recycleview,container,false);
        // Creamos el RecyclerView
        listadoMascotas = (RecyclerView) v.findViewById( R.id.rvMascota );
        RecyclerViewFragmentPresenter recyclerViewFragmentPresenter = new RecyclerViewFragmentPresenter(this, getActivity(),false);
        //generarLinearLayoutVertical();
       // presenter = new RecyclerViewFragmentPresenter(this, getContext());
        return v;
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listadoMascotas.setLayoutManager( llm );
    }

    @Override
    public void crearAdapter(ArrayList<Mascota> lstMascotas) {
        AdapterMascota adaptador = new AdapterMascota(lstMascotas,getActivity());
        listadoMascotas.setAdapter(adaptador);
    }
}
