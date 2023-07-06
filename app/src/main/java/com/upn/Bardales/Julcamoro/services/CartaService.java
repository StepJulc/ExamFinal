package com.upn.Bardales.Julcamoro.services;

import com.upn.Bardales.Julcamoro.entities.Cartas;

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
    Call<List<Cartas>> getAllUser();

    @GET("cartas")
    Call<List<Cartas>> getAllUser(@Query("limit") int limit, @Query("page") int page);

    @GET("cartas")
    Call<List<Cartas>> getBuscar(@Query("search") String nombre);

    @GET("cartas")
    Call<List<Cartas>> getCartasBySincro(@Query("sincronizadoCartas") boolean sincronizar);

    @GET("cartas/{id}")
    Call<Cartas> findUser(@Path("id") int id);

    @POST("cartas")
    Call<Cartas> create(@Body Cartas user);

    @PUT("cartas/{id}")
    Call<Cartas> update(@Path("id") int id, @Body Cartas user);

    @DELETE("cartas/{id}")
    Call<Void> delete(@Path("id") int id);
}
