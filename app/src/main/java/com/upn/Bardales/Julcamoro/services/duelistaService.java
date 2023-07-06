package com.upn.Bardales.Julcamoro.services;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface duelistaService {

    @Query("SELECT * FROM duelista")
    List<duelistaService> getAllDuelista();

    @Query("SELECT * FROM duelista  WHERE sincronizadoDuelista LIKE :searchSincro")
    List<duelistaService> searchDuelista(boolean searchSincro);

    @Query("SELECT * FROM duelista WHERE id LIKE :id")
    duelistaService searchDuelistaID(int id);

    @Insert
    void createDuelista(duelistaService duelista);
    @Insert
    void AgregarList(List<duelistaService> duelistas);
    @Update
    void  updateDuelista(duelistaService duelista);

    @Delete
    void delete(duelistaService duelista);

    @Delete
    void deleteList(List<duelistaService> duelistas);


}
