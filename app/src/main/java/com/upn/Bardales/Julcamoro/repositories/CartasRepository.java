package com.upn.Bardales.Julcamoro.repositories;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.upn.Bardales.Julcamoro.entities.cartas;

import java.util.List;

@Dao
public interface CartasRepository {

    @Query("SELECT * FROM cartas")
    List<cartas> getAllCarta();

    @Query("SELECT * FROM cartas WHERE sincronizadoCarta LIKE :searchSincro")
    List<cartas> searchCarta(boolean searchSincro);

    @Query("SELECT * FROM cartas WHERE id LIKE :id")
    cartas searchCartaID(int id);

    @Insert
    void createCarta(cartas carta);
    @Insert
    void AgregarList(List<cartas> cartas);
    @Update
    void  updateCartas(cartas carta);

    @Delete
    void delete(cartas carta);

    @Delete
    void deleteList(List<cartas> cartas);





}
