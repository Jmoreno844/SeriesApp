package com.example.seriesapp.model.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;



@Entity(tableName = "series")  //con entity decimos que queremos que serie sea una base de datos,
public class Serie implements Serializable {
    @PrimaryKey(autoGenerate = true) //creamos el identificador primario de la base de datos.
    private int identificador;

    private String nombre;

    private String descripcion;

    @ColumnInfo(name = "url_imagen")
    private String URLLink;

    public Serie(String nombre, String descripcion, String URLLink) {
        this.identificador = 0;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.URLLink = URLLink;
    }
    public Serie(){
        this.identificador = 0;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getURLLink() {
        return URLLink;
    }

    public void setURLLink(String URLLink) {
        this.URLLink = URLLink;
    }
}