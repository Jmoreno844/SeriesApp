package com.example.seriesapp.model.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.seriesapp.model.entities.Serie;

@Database(entities = {Serie.class},version = 1)
public abstract class BaseDeDatos extends RoomDatabase {
    public abstract SerieDAO serieDAO();

    private static BaseDeDatos instancia = null;

    public static BaseDeDatos obtenerInstancia(Context miContexto){
        if(instancia==null){
            instancia = Room.databaseBuilder(miContexto,
                    BaseDeDatos.class, "seriesapp.db").allowMainThreadQueries().build();
        }
        return instancia;
    }
}
