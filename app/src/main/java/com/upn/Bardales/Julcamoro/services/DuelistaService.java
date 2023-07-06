package com.upn.Bardales.Julcamoro.services;

import com.upn.Bardales.Julcamoro.entities.Duelista;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface DuelistaService {
    @GET("duelista")
    Call<List<Duelista>> getAllUser();

    @GET("duelista")
    Call<List<Duelista>> getAllUser(@Query("limit") int limit, @Query("page") int page);

    @GET("duelista")
    Call<List<Duelista>> getBuscar(@Query("search") String nombre);

    @GET("duelista")
    Call<List<Duelista>> getCartasBySincro(@Query("sincronizadoDuelista") boolean sincronizar);

    @GET("duelista/{id}")
    Call<Duelista> findUser(@Path("id") int id);

    @POST("duelista")
    Call<Duelista> create(@Body Duelista user);

    @PUT("duelista/{id}")
    Call<Duelista> update(@Path("id") int id, @Body Duelista user);

    @DELETE("duelista/{id}")
    Call<Void> delete(@Path("id") int id);
}
