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

/**
 * Created by gregorybr on 23-01-17.
 */

public class UsuarioDeserializador implements JsonDeserializer<MascotasResponse> {

    @Override
    public MascotasResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();

        MascotasResponse mascotaResponse = gson.fromJson(json,MascotasResponse.class);

        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        mascotaResponse.setUserInformation(deserializarUsuarioDeJson(mascotaResponseData));

        return mascotaResponse;
    }

    private PerfilMascota deserializarUsuarioDeJson(JsonArray mascotaResponseData){

        PerfilMascota mascota = new PerfilMascota();

        for (int i = 0; i< mascotaResponseData.size(); i++){

            JsonObject mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();
            //JsonObject userJson = MascotaResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id = mascotaResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = mascotaResponseDataObject.get(JsonKeys.USER_FULLNAME).getAsString();
            String fotoPerfil = mascotaResponseDataObject.get(JsonKeys.USER_PROFILE_PICTURE).getAsString();

            mascota.setId(id);
            mascota.setNombreCompleto(nombreCompleto);
            mascota.setUrlFotoPerfil(fotoPerfil);

        }

        return mascota;

    }
}
