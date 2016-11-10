package com.gecode.petgrammascotas.vista.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gecode.petgrammascotas.R;
import com.gecode.petgrammascotas.adapter.AdapterPerfilMascota;
import com.gecode.petgrammascotas.modelo.PerfilMascota;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPerfil extends Fragment {
    private RecyclerView recyclerViewPerfil;
    private RecyclerView.LayoutManager layoutManager;
   // private View view;
    private FloatingActionButton mFab;

    ArrayList<PerfilMascota> lstperfilmascotas;

     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_perfil, container, false);

        recyclerViewPerfil = (RecyclerView) view.findViewById( R.id.rvPerfilMascota );
        recyclerViewPerfil.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(),3);
        recyclerViewPerfil.setLayoutManager(layoutManager);

        inicializarPerfilDatos();
        inicializarPerfilAdaptador();
        subirFoto(view);
        return view;
    }

    public  void  inicializarPerfilDatos(){
        lstperfilmascotas = new ArrayList<>();
        lstperfilmascotas.add(new PerfilMascota(R.drawable.lobo_1,"0"));
        lstperfilmascotas.add(new PerfilMascota(R.drawable.lobo_2,"0"));
        lstperfilmascotas.add(new PerfilMascota(R.drawable.lobo_3,"0"));
        lstperfilmascotas.add(new PerfilMascota(R.drawable.lobo_4,"0"));
        lstperfilmascotas.add(new PerfilMascota(R.drawable.lobo_5,"0"));
        lstperfilmascotas.add(new PerfilMascota(R.drawable.lobo_6,"0"));
        lstperfilmascotas.add(new PerfilMascota(R.drawable.lobo_7,"15"));
        lstperfilmascotas.add(new PerfilMascota(R.drawable.lobo_8,"7"));
        lstperfilmascotas.add(new PerfilMascota(R.drawable.lobo_9,"1"));
        lstperfilmascotas.add(new PerfilMascota(R.drawable.lobo_10,"10"));
        lstperfilmascotas.add(new PerfilMascota(R.drawable.lobo_11,"5"));
        lstperfilmascotas.add(new PerfilMascota(R.drawable.lobo_12,"15"));
        lstperfilmascotas.add(new PerfilMascota(R.drawable.lobo_13,"5"));

    }

    public void inicializarPerfilAdaptador(){
        AdapterPerfilMascota a = new AdapterPerfilMascota(lstperfilmascotas,getActivity());
        recyclerViewPerfil.setAdapter(a);
    }

    public void subirFoto(View view){
        mFab = (FloatingActionButton) view.findViewById(R.id.FAB_Camera_Perfil);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vFoto) {

                Snackbar.make(vFoto,getString(R.string.floating_mensaje), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
