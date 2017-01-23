package com.gecode.petgrammascotas.restApi.deserializador;

import com.gecode.petgrammascotas.modelo.PerfilMascota;
import com.gecode.petgrammascotas.restApi.JsonKeys;
import com.gecode.petgrammascotas.restApi.model.MascotasResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by gregorybr on 23-01-17.
 */

public class MascotaDeserializador implements JsonDeserializer<MascotasResponse> {

    @Override
    public MascotasResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();

        MascotasResponse mascotaResponse = gson.fromJson(json,MascotasResponse.class);

        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        mascotaResponse.setMascotas(deserializarMascotaDeJson(mascotaResponseData));


        return mascotaResponse;
    }

    private ArrayList<PerfilMascota> deserializarMascotaDeJson(JsonArray mascotaResponseData){

        ArrayList<PerfilMascota> mascotas = new ArrayList<>();

        for (int i = 0; i< mascotaResponseData.size(); i++){

            JsonObject MascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();
            JsonObject userJson = MascotaResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = userJson.get(JsonKeys.USER_FULLNAME).getAsString();
            String fotoPerfil = userJson.get(JsonKeys.USER_PROFILE_PICTURE).getAsString();
            String nombreUsuario = userJson.get(JsonKeys.USER_NAME).getAsString();

            JsonObject imageJson = MascotaResponseDataObject.get(JsonKeys.MEDIA_IMAGES).getAsJsonObject();
            JsonObject stdResolutionJson = imageJson.getAsJsonObject(JsonKeys.MEDIA_IMAGES_STANDARD_RESOLUTION);
            String urlFoto = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = MascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            PerfilMascota mascotaActual = new PerfilMascota();
            mascotaActual.setId(id);
            mascotaActual.setNombreCompleto(nombreCompleto);
            mascotaActual.setUsuario(nombreUsuario);
            mascotaActual.setUrlFotoPerfil(fotoPerfil);
            mascotaActual.setUrlFoto(urlFoto);
            mascotaActual.setLikesFoto(likes);


            mascotas.add(mascotaActual);

        }

        return mascotas;

    }


}