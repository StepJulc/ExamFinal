package com.upn.Bardales.Julcamoro.services;

import com.upn.Bardales.Julcamoro.entities.cartas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface CartaService {
    @GET("cartas")
    Call<List<cartas>> getAllUser();

    @GET("cartas")
    Call<List<cartas>> getAllUser(@Query("limit") int limit, @Query("page") int page);

    @GET("cartas")
    Call<List<cartas>> getBuscar(@Query("search") String nombre);

    @GET("cartas")
    Call<List<cartas>> getCartasBySincro(@Query("sincronizadoCartas") boolean sincronizar);

    @GET("cartas/{id}")
    Call<cartas> findUser(@Path("id") int id);

    // users
    @POST("cartas")
    Call<cartas> create(@Body cartas user);

    @PUT("cartas/{id}")
    Call<cartas> update(@Path("id") int id, @Body cartas user);

    @DELETE("cartas/{id}")
    Call<Void> delete(@Path("id") int id);
}
