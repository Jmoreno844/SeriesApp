package com.example.seriesapp.model.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.seriesapp.model.entities.Serie;

import java.util.List;

@Dao  //aca van los metodos de la base de datos
public interface SerieDAO {

    @Query("select * from series")
    List<Serie> obtenerTodo();

    @Insert
    void insertarElemento(Serie miSerie);

    @Update
    void editar(Serie miSerie);

    @Delete
    void eliminar(Serie miserie);

    @Query("select * from series where identificador = :parametro")
    Serie obtenerPorIdentificador(int parametro);  //retorna Serie

}
