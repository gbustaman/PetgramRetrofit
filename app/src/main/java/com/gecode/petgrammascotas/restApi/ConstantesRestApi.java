package com.gecode.petgrammascotas.restApi;

/**
 * Created by gregorybr on 22-01-17.
 */

public final class ConstantesRestApi {
    //https://api.instagram.com/v1/
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "4253551275.54d932f.5131710fb4d54c15a08ec3bd63d9c5bc";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    //https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    //Para buscar un usuario
    //https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN
    public static final String KEY_GET_USER_SEARCH = "users/search";
    //public static final String KEY_USER_SEARCH = INICIO_QUERY_STRING + "q={user-name}";
    public static final String URL_SEARCH_USER_BY_NAME = KEY_GET_USER_SEARCH + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    //Para traer las fotos más recientes de un usuario a apartir de su ID
    //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN
    public static final String KEY_GET_RECENT_MEDIA_USER_ID = "users/{user-id}/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER_ID = KEY_GET_RECENT_MEDIA_USER_ID + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    //Para obtener los seguidores
    //https://api.instagram.com/v1/users/self/followed-by?access_token=ACCESS-TOKEN
    public static final String KEY_GET_FOLLOWED_BY = "users/self/followed-by";
    public static final String URL_GET_FOLLOWED_BY = KEY_GET_FOLLOWED_BY + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

}
