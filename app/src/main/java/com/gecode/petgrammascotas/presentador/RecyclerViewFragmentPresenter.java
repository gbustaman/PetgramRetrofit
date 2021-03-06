package com.gecode.petgrammascotas.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.gecode.petgrammascotas.modelo.PerfilMascota;
import com.gecode.petgrammascotas.modelo.db.ConstructorMascotas;
import com.gecode.petgrammascotas.restApi.IEndPointsApi;
import com.gecode.petgrammascotas.restApi.adapter.RestApiAdapter;
import com.gecode.petgrammascotas.restApi.model.MascotasResponse;
import com.gecode.petgrammascotas.vista.fragment.IRecyclerViewFragmentView;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gregorybr on 09-11-16.
 */

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter{

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    ConstructorMascotas constructorMascotas;
    //private ArrayList<Mascota> mascotas;
    private ArrayList<PerfilMascota> seguidores;
    private int iSeguidores;
    private ArrayList<PerfilMascota> fotosMascotas;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerSeguidores();
        //constructorMascotas= new ConstructorMascotas(this.context);
        //obtenerMascotasBaseDatos();
        /*if(bRaiting) {
            mostarMascotasFavoritas();
            return;
        }*/
        //mostrarMascotas();
    }

    @Override
    public void mostrarMascotas() {
        /*ArrayList<Mascota> mascotas = obtenerMascotas();
        Log.d("Mascota ",String.valueOf(mascotas.size()));
        if(mascotas.size() == 0){
            constructorMascotas.insertarMascotas();
            mascotas = obtenerMascotas();
        }
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
        iRecyclerViewFragmentView.crearAdapter(mascotas);*/
    }

    @Override
    public void mostrarMascotasRV() {
        /*iRecyclerViewFragmentView.generarLinearLayoutVertical();
        iRecyclerViewFragmentView.crearAdapter(obtenerLikesMascotas());*/
        this.iRecyclerViewFragmentView.inicializarAdaptador(this.iRecyclerViewFragmentView.crearAdapter(fotosMascotas));
        this.iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }

    @Override
    public void obtenerSeguidores() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonSeguidores = restApiAdapter.construyeGsonDeserializadorSeguidores();
        IEndPointsApi iEndPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonSeguidores);

        //Call<MascotaResponse> mascotaResponseCall = iEndPointsApi.getRecentMedia();

        Call<MascotasResponse> mascotaResponseCall = iEndPointsApi.getFollowedBy();

        mascotaResponseCall.enqueue(new Callback<MascotasResponse>() {
            @Override
            public void onResponse(Call<MascotasResponse> call, Response<MascotasResponse> response) {
                MascotasResponse mascotaResponse = response.body();
                seguidores = mascotaResponse.getMascotas();
                obtenerFotosSeguidores();
                //mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotasResponse> call, Throwable t) {
                Toast.makeText(context, "Algo pasó en la conexión, intentalo de nuevo", Toast.LENGTH_SHORT).show();
                Log.e("Falló la conexión" , t.toString());
            }
        });

    }

    public void obtenerFotosSeguidores() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonFotosSeguidores = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        IEndPointsApi iEndPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonFotosSeguidores);

        //Call<MascotaResponse> mascotaResponseCall = iEndPointsApi.getRecentMedia();

        fotosMascotas = new ArrayList<>();

        iSeguidores = 0;
        for (PerfilMascota fotoMascota:seguidores) {
            Call<MascotasResponse> mascotaResponseCall = iEndPointsApi.getRecentMedia(Long.parseLong(fotoMascota.getId()));

            mascotaResponseCall.enqueue(new Callback<MascotasResponse>() {
                @Override
                public void onResponse(Call<MascotasResponse> call, Response<MascotasResponse> response) {
                    iSeguidores++;
                    MascotasResponse mascotaResponse = response.body();
                    fotosMascotas.addAll(mascotaResponse.getMascotas());

                    if (iSeguidores == seguidores.size()) {
                        mostrarMascotasRV();
                    }
                    //mascotas = mascotaResponse.getMascotas();
                    //obtenerFotosDeSeguidores()

                }

                @Override
                public void onFailure(Call<MascotasResponse> call, Throwable t) {
                    Toast.makeText(context, "Algo pasó en la conexión, intentalo de nuevo", Toast.LENGTH_SHORT).show();
                    Log.e("Falló la conexión", t.toString());
                }
            });
        }
    }
/*
    @Override
    public ArrayList<Mascota> obtenerLikesMascotas() {
        return constructorMascotas.obtenerLikesMascotas();
    }

    @Override
    public ArrayList<Mascota> obtenerMascotas() {
        return constructorMascotas.obtenerMascotas();
    }*/
}
