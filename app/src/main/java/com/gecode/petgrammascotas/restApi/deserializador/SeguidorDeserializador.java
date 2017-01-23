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

public class SeguidorDeserializador implements JsonDeserializer<MascotasResponse> {

    @Override
    public MascotasResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();

        MascotasResponse mascotaResponse = gson.fromJson(json,MascotasResponse.class);

        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        mascotaResponse.setMascotas(deserializarSeguidores(mascotaResponseData));

        return mascotaResponse;
    }

    private ArrayList<PerfilMascota> deserializarSeguidores(JsonArray mascotaResponseData){

        ArrayList<PerfilMascota> seguidores = new ArrayList<>();

        for (int i = 0; i< mascotaResponseData.size(); i++){

            JsonObject mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();
            //JsonObject userJson = MascotaResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id = mascotaResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = mascotaResponseDataObject.get(JsonKeys.USER_FULLNAME).getAsString();
            String fotoPerfil = mascotaResponseDataObject.get(JsonKeys.USER_PROFILE_PICTURE).getAsString();
            String nombreUsuario = mascotaResponseDataObject.get(JsonKeys.USER_NAME).getAsString();

            PerfilMascota seguidorActual = new PerfilMascota();

            seguidorActual.setId(id);
            seguidorActual.setNombreCompleto(nombreCompleto);
            seguidorActual.setUrlFotoPerfil(fotoPerfil);
            seguidorActual.setUsuario(nombreUsuario);

            seguidores.add(seguidorActual);

        }

        return seguidores;

    }

}
