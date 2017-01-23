package com.gecode.petgrammascotas.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.gecode.petgrammascotas.modelo.PerfilMascota;
import com.gecode.petgrammascotas.restApi.IEndPointsApi;
import com.gecode.petgrammascotas.restApi.adapter.RestApiAdapter;
import com.gecode.petgrammascotas.restApi.model.MascotasResponse;
import com.gecode.petgrammascotas.vista.fragment.IRecyclerViewFavoritas;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gregorybr on 23-01-17.
 */

public class RecyclerViewFavoritasPresenter implements IRecyclerViewFavoritasPresenter {

    private IRecyclerViewFavoritas iRecyclerViewFavoritas;
    private Context context;
    //private ConstructorMascotas constructorMascotas;
    private ArrayList<PerfilMascota> seguidores;
    private int iSeguidores;
    private ArrayList<PerfilMascota> fotosMascotas;

    public RecyclerViewFavoritasPresenter(IRecyclerViewFavoritas iRecyclerViewFavoritas, Context context) {
        this.iRecyclerViewFavoritas = iRecyclerViewFavoritas;
        this.context = context;
        obtenerMascotasFavoritas();
        //obtenerSeguidores();
    }

    @Override
    public void obtenerMascotasFavoritas() {
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

    @Override
    public void mostrarMascotasRV() {
        iRecyclerViewFavoritas.inicializarAdaptador(iRecyclerViewFavoritas.crearAdaptador(fotosMascotas));
        iRecyclerViewFavoritas.generarLinearLyout();
    }

    @Override
    public void obtenerFotosSeguidores() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonFotosSeguidores = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        IEndPointsApi iEndPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonFotosSeguidores);

        //Call<MascotaResponse> mascotaResponseCall = iEndPointsApi.getRecentMedia();

        fotosMascotas = new ArrayList<>();

        iSeguidores = 0;
        for (final PerfilMascota perfilMascota : seguidores) {
            Call<MascotasResponse> mascotaResponseCall = iEndPointsApi.getRecentMedia(Long.parseLong(perfilMascota.getId()));

            mascotaResponseCall.enqueue(new Callback<MascotasResponse>() {
                @Override
                public void onResponse(Call<MascotasResponse> call, Response<MascotasResponse> response) {
                    iSeguidores++;
                    MascotasResponse mascotaResponse = response.body();
                    fotosMascotas.addAll(mascotaResponse.getMascotas());

                    if (iSeguidores == seguidores.size()) {
                        //Se obtienen las fotos con más likes

                        Collections.sort(fotosMascotas,Collections.<PerfilMascota>reverseOrder());
                        ArrayList<PerfilMascota> favoritas = new ArrayList<PerfilMascota>();
                        for(int i = 0; i<5; i++){
                            favoritas.add(fotosMascotas.get(i));
                        }
                        fotosMascotas = favoritas;

                        //fotosMascotas.sort((o1, o2) -> o1.getLikesFoto().compareTo(o2.getLikesFoto()));

                        //fotosMascotas.sort(FotoMascota);
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

}
