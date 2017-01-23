package com.gecode.petgrammascotas.restApi.adapter;

import com.gecode.petgrammascotas.restApi.ConstantesRestApi;
import com.gecode.petgrammascotas.restApi.IEndPointsApi;
import com.gecode.petgrammascotas.restApi.deserializador.MascotaDeserializador;
import com.gecode.petgrammascotas.restApi.deserializador.SeguidorDeserializador;
import com.gecode.petgrammascotas.restApi.deserializador.UsuarioDeserializador;
import com.gecode.petgrammascotas.restApi.model.MascotasResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gregorybr on 23-01-17.
 */

public class RestApiAdapter {
    public IEndPointsApi establecerConexionRestApiInstagram(Gson gson){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(IEndPointsApi.class);

    }

    public Gson construyeGsonDeserializadorMediaRecent(){

        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(MascotasResponse.class, new MascotaDeserializador());

        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorUserInformation(){

        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(MascotasResponse.class, new UsuarioDeserializador());

        return gsonBuilder.create();

    }

    public Gson construyeGsonDeserializadorSeguidores(){

        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(MascotasResponse.class, new SeguidorDeserializador());

        return gsonBuilder.create();

    }
}
