package com.gecode.petgrammascotas.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.gecode.petgrammascotas.modelo.PerfilMascota;
import com.gecode.petgrammascotas.restApi.IEndPointsApi;
import com.gecode.petgrammascotas.restApi.adapter.RestApiAdapter;
import com.gecode.petgrammascotas.restApi.model.MascotasResponse;
import com.gecode.petgrammascotas.vista.fragment.IRecyclerViewFragmentFoto;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gregorybr on 23-01-17.
 */

public class RecyclerViewFragmentFotoPresenter implements IRecyclerViewFragmentFotoPresenter {

    private IRecyclerViewFragmentFoto iRecyclerViewFragmentFoto;
    private Context context;
    private ArrayList<PerfilMascota> mascotas;
    private Long userId;

    public RecyclerViewFragmentFotoPresenter(IRecyclerViewFragmentFoto iRecyclerViewFragmentFoto, Context context, String userName) {
        this.iRecyclerViewFragmentFoto = iRecyclerViewFragmentFoto;
        this.context = context;
        obtenerUserId(userName);
    }

    public RecyclerViewFragmentFotoPresenter(IRecyclerViewFragmentFoto iRecyclerViewFragmentFoto, Context context, Long userId) {
        this.iRecyclerViewFragmentFoto = iRecyclerViewFragmentFoto;
        this.context = context;
        this.userId = userId;
        obtenerMediosRecientes();
    }

    @Override
    public void mostrarMascotasRV() {
        iRecyclerViewFragmentFoto.inicializarAdaptador(iRecyclerViewFragmentFoto.crearAdaptador(mascotas));
        iRecyclerViewFragmentFoto.generarGridLyout();
    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        IEndPointsApi iEndPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);

        Call<MascotasResponse> mascotaResponseCall = iEndPointsApi.getRecentMedia(userId);

        mascotaResponseCall.enqueue(new Callback<MascotasResponse>() {
            @Override
            public void onResponse(Call<MascotasResponse> call, Response<MascotasResponse> response) {
                MascotasResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotasResponse> call, Throwable t) {
                Toast.makeText(context, "Algo pasó en la conexión, intentalo de nuevo", Toast.LENGTH_SHORT).show();
                Log.e("Falló la conexión" , t.toString());
            }
        });

    }

    @Override
    public ArrayList<PerfilMascota> getFotosMascotas() {
        return mascotas;
    }

    @Override
    public void obtenerUserId(String userName) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonUsuarioInfo = restApiAdapter.construyeGsonDeserializadorUserInformation();
        IEndPointsApi iEndPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonUsuarioInfo);


        //Call<MascotaResponse> mascotaResponseCall = iEndPointsApi.getRecentMedia();

        Call<MascotasResponse> mascotaResponseCall = iEndPointsApi.getUserByName(userName);

        mascotaResponseCall.enqueue(new Callback<MascotasResponse>() {
            @Override
            public void onResponse(Call<MascotasResponse> call, Response<MascotasResponse> response) {
                MascotasResponse mascotaResponse = response.body();
                if(mascotaResponse.getUserInformation().getId() != null) {
                    userId = Long.parseLong(mascotaResponse.getUserInformation().getId());
                    obtenerMediosRecientes();
                }
            }

            @Override
            public void onFailure(Call<MascotasResponse> call, Throwable t) {
                Toast.makeText(context, "Algo pasó en la conexión, intentalo de nuevo", Toast.LENGTH_SHORT).show();
                Log.e("Falló la conexión" , t.toString());
            }
        });
    }

}
