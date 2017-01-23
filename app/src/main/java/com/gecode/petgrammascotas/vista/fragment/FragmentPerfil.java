package com.gecode.petgrammascotas.vista.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gecode.petgrammascotas.R;
import com.gecode.petgrammascotas.adapter.AdapterPerfilMascota;
import com.gecode.petgrammascotas.modelo.PerfilMascota;
import com.gecode.petgrammascotas.presentador.IRecyclerViewFragmentFotoPresenter;
import com.gecode.petgrammascotas.presentador.RecyclerViewFragmentFotoPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPerfil extends Fragment implements IRecyclerViewFragmentFoto {

    private RecyclerView recyclerViewPerfil;
    //private RecyclerView.LayoutManager layoutManager;
   // private View view;
    private FloatingActionButton mFab;

    private ImageView imgvFotoFrg;
    private TextView tvNombreFrg;

    private ArrayList<PerfilMascota> fotosMascota;
    private IRecyclerViewFragmentFotoPresenter presenter;
    private long userId = Long.parseLong("4253551275") ;

    ArrayList<PerfilMascota> lstperfilmascotas;

     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_perfil, container, false);

       /* recyclerViewPerfil = (RecyclerView) view.findViewById( R.id.rvPerfilMascota );
        recyclerViewPerfil.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(),3);
        recyclerViewPerfil.setLayoutManager(layoutManager);*/

        /*inicializarPerfilDatos();
        inicializarPerfilAdaptador();*/
        subirFoto(view);

         imgvFotoFrg = (ImageView) view.findViewById(R.id.civPerfilMascota);
         tvNombreFrg = (TextView) view.findViewById(R.id.tvNombrePerfil);

         imgvFotoFrg.setImageResource(R.drawable.mascota_19_2);
         tvNombreFrg.setText("Gregory");

         SharedPreferences miPreferenciaCompartida = getActivity().getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);

         String nombrePerfilActual = miPreferenciaCompartida.getString(getResources().getString(R.string.NombrePerfil), "");

         recyclerViewPerfil = (RecyclerView) view.findViewById(R.id.rvPerfilMascota);
         //nombrePerfilActual = "";
         if(nombrePerfilActual != "") {
             presenter = new RecyclerViewFragmentFotoPresenter(this, getContext(),nombrePerfilActual);
         }else{
             presenter = new RecyclerViewFragmentFotoPresenter(this, getContext(),userId);
         }

         return view;
    }


    /*
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

    }*/

    /*public void inicializarPerfilAdaptador(){
        AdapterPerfilMascota a = new AdapterPerfilMascota(lstperfilmascotas,getActivity());
        recyclerViewPerfil.setAdapter(a);
    }*/


    @Override
    public AdapterPerfilMascota crearAdaptador(ArrayList<PerfilMascota> mascotas) {
        AdapterPerfilMascota a = new AdapterPerfilMascota(mascotas, getActivity());
        return a;
    }

    @Override
    public void inicializarAdaptador(AdapterPerfilMascota adaptador) {
        recyclerViewPerfil.setAdapter(adaptador);
    }

    @Override
    public void generarGridLyout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewPerfil.setLayoutManager(gridLayoutManager);


        fotosMascota = presenter.getFotosMascotas();

        if(fotosMascota != null && fotosMascota.size()>0){
            String nombreCompleto = fotosMascota.get(0).getNombreCompleto();
            String urlFotoPerfil = fotosMascota.get(0).getUrlFotoPerfil();

            tvNombreFrg.setText(nombreCompleto);

            Picasso.with(this.getActivity())
                    .load(urlFotoPerfil)
                    .placeholder(R.drawable.mascota_19_2)
                    .into(imgvFotoFrg);

        }
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
