package com.upn.Bardales.Julcamoro.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "duelista")

public class duelista {

    public  int idS;
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "nameDuelista")
    public String nameDuelista;
    @ColumnInfo(name = "sincronizadoDuelista")
    public boolean sincronizadoDuelista;

}
