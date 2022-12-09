package com.example.tarea;

import java.io.Serializable;
import java.util.ArrayList;

public class Tarea implements Serializable {
    private String titulo;
    private String descripcion;

    //region SETTER AND GETTER
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String toString(){
        return "" + titulo+" "+descripcion;
    }
    //endregion
    //region CONSTRUCTORS
    public Tarea(){}
    public Tarea(String titulo ,String description){
        setTitulo(titulo);
        setDescripcion(description);

    }
//endregion


}
