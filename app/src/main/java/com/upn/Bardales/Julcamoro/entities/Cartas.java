package com.upn.Bardales.Julcamoro.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cartas")


public class Cartas {


    public int idB;
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "duelistaID")
    public int duelistaID;
    @ColumnInfo(name = "puntosAtaque")
    public int puntosAtaque;
    @ColumnInfo(name = "puntosDefenza")
    public int puntosDefenza;
    @ColumnInfo(name = "latitud")
    public String latitud;
    @ColumnInfo(name = "longitud")
    public String longitud;
    @ColumnInfo(name = "urlimagen")
    public String urlimagen;
    @ColumnInfo(name = "sincronizadoCarta")
    public boolean sincronizadoCarta;
    @ColumnInfo(name = "imagenBase64")
    public String imagenBase64;

    @ColumnInfo(name = "nameCarta")
    public String nameCarta;
}
