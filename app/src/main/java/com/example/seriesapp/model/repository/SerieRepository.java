package com.example.seriesapp.model.repository;

import android.content.Context;

import com.example.seriesapp.model.entities.Serie;
import com.example.seriesapp.model.local.BaseDeDatos;
import com.example.seriesapp.model.local.SerieDAO;

import java.util.List;

public class SerieRepository {

    SerieDAO serieDAO;

    public SerieRepository(Context miContexto){  // el contexto puede ser MainActivity.this o MainActivity.class
        BaseDeDatos miBaseDatos = BaseDeDatos.obtenerInstancia(miContexto);
        this.serieDAO = miBaseDatos.serieDAO();    //el this.  nos lleva hacia la clase en general ( el .java)
    }

    public List<Serie> obtenerTodasSeries(){
        return serieDAO.obtenerTodo();
    }

    public void insertarSerie(Serie miserie){
        serieDAO.insertarElemento(miserie);
    }

    public void actualizarSerie(Serie miSerie){
        serieDAO.editar(miSerie);
    }

    public void eliminarSerie(Serie miSerie){
        serieDAO.eliminar(miSerie);
    }

    public Serie consultarSeriePorId(int id){
        return serieDAO.obtenerPorIdentificador(id);
    }

}
