package com.gecode.petgrammascotas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.gecode.petgrammascotas.adapter.AdapterMascota;
import com.gecode.petgrammascotas.modelo.Mascota;
import com.gecode.petgrammascotas.modelo.PerfilMascota;
import com.gecode.petgrammascotas.presentador.IRecyclerViewFavoritasPresenter;
import com.gecode.petgrammascotas.presentador.RecyclerViewFavoritasPresenter;
import com.gecode.petgrammascotas.vista.fragment.IRecyclerViewFavoritas;

import java.util.ArrayList;

public class MascotasFavoritas extends AppCompatActivity implements IRecyclerViewFavoritas {

    private RecyclerView listadoMascotas;
    private ArrayList<Mascota> mascotas;
    private TextView tituloPantalla;
    private IRecyclerViewFavoritasPresenter iRecyclerViewFavoritasPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);

        Toolbar toolPersonalizado = (Toolbar) findViewById(R.id.abFavotito);
        setSupportActionBar(toolPersonalizado);

        listadoMascotas = (RecyclerView) findViewById( R.id.rvMascota );
        iRecyclerViewFavoritasPresenter = new RecyclerViewFavoritasPresenter(this,getBaseContext());

         agregarFAB();
    }

    // Retorna a la Primera Activida al dar Click en el boton retroceso
    public  void  irPrimeraActividad (View v) {
        finish();
    }

    // Accion del boton Camara, solo nos muestra un mensaje por el momento.
    public void agregarFAB(){
        FloatingActionButton mFAB = (FloatingActionButton) findViewById(R.id.FAB_Camera);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,getString(R.string.floating_mensaje), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void generarLinearLyout() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listadoMascotas.setLayoutManager( llm );
    }

    @Override
    public AdapterMascota crearAdaptador(ArrayList<PerfilMascota> lstMascotas) {
        AdapterMascota adaptador = new AdapterMascota(lstMascotas,this);
        //listadoMascotas.setAdapter(adaptador);
        return adaptador;
    }

    @Override
    public void inicializarAdaptador(AdapterMascota adaptador) {
        listadoMascotas.setAdapter(adaptador);
    }


}
