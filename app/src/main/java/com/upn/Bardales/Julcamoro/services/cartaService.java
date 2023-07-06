package com.upn.Bardales.Julcamoro.services;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface cartaService {

    @Query("SELECT * FROM cartas")
    List<cartaService> getAllCarta();

    @Query("SELECT * FROM cartas WHERE sincronizadoCarta LIKE :searchSincro")
    List<cartaService> searchCarta(boolean searchSincro);

    @Query("SELECT * FROM cartas WHERE id LIKE :id")
    cartaService searchCartaID(int id);

    @Insert
    void createCarta(cartaService carta);
    @Insert
    void AgregarList(List<cartaService> cartas);
    @Update
    void  updateCartas(cartaService carta);

    @Delete
    void delete(cartaService carta);

    @Delete
    void deleteList(List<cartaService> cartas);
}
