package com.upn.Bardales.Julcamoro.repositories;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.upn.Bardales.Julcamoro.entities.Cartas;

import java.util.List;

@Dao
public interface CartasRepository {

    @Query("SELECT * FROM Cartas")
    List<Cartas> getAllCarta();

    @Query("SELECT * FROM Cartas WHERE sincronizadoCarta LIKE :searchSincro")
    List<Cartas> searchCarta(boolean searchSincro);

    @Query("SELECT * FROM Cartas WHERE id LIKE :id")
    Cartas searchCartaID(int id);

    @Insert
    void createCarta(Cartas carta);
    @Insert
    void AgregarList(List<Cartas> cartas);
    @Update
    void  updateCartas(Cartas carta);

    @Delete
    void delete(Cartas carta);

    @Delete
    void deleteList(List<Cartas> cartas);





}
