package com.upn.Bardales.Julcamoro.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.upn.Bardales.Julcamoro.entities.Duelista;
import com.upn.Bardales.Julcamoro.entities.Cartas;
import com.upn.Bardales.Julcamoro.repositories.CartasRepository;
import com.upn.Bardales.Julcamoro.repositories.DuelistasRepository;

@Database(entities = {Duelista.class, Cartas.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DuelistasRepository duelistasRepository();
    public abstract CartasRepository cartaRepository();

    public static AppDatabase getInstance(Context context){
        return Room.databaseBuilder(context,AppDatabase.class, "AplicaciónFinal")
                .allowMainThreadQueries()
                .build();
    }
}

