package com.upn.Bardales.Julcamoro.services;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.upn.Bardales.Julcamoro.entities.duelista;

import java.util.List;

@Dao
public interface duelistaService {
    @Query("SELECT * FROM duelista")
    List<duelista> getAllDuelista();

    @Query("SELECT * FROM duelista WHERE sincronizadoDuelista LIKE :searchSincro")
    List<duelista> searchDuelista(boolean searchSincro);

    @Query("SELECT * FROM duelista WHERE id LIKE :id")
    duelista searchDuelistaID(int id);

    @Insert
    void createDuelista(duelista duelista);
    @Insert
    void AgregarList(List<duelista> duelistas);
    @Update
    void  updateDuelista(duelista duelista);

    @Delete
    void delete(duelista duelista);

    @Delete
    void deleteList(List<duelista> duelistas);


}
