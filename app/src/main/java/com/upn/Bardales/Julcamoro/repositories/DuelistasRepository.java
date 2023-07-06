package com.upn.Bardales.Julcamoro.repositories;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.upn.Bardales.Julcamoro.entities.Duelista;

import java.util.List;

@Dao
public interface DuelistasRepository {

    @Query("SELECT * FROM Duelista")
    List<Duelista> getAllDuelista();

    @Query("SELECT * FROM duelista WHERE sincronizadoDuelista LIKE :searchSincro")
    List<Duelista> searchDuelista(boolean searchSincro);

    @Query("SELECT * FROM Duelista WHERE id LIKE :id")
    Duelista searchDuelistaID(int id);

    @Insert
    void createDuelista(Duelista duelista);
    @Insert
    void AgregarList(List<Duelista> duelistas);
    @Update
    void  updateduelista(Duelista duelista);

    @Delete
    void delete(Duelista duelista);

    @Delete
    void deleteList(List<Duelista> duelistas);

}
