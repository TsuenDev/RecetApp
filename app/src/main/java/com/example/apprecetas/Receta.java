package com.example.apprecetas;

public class Receta {
    //Clase para el objeto de tipo receta
    private int id_receta;
    private String titulo;
    private String tiempo;
    private String ingredientes;
    private String instrucciones;

    //Constructores para el objeto clase Receta
    public Receta(){

    }

    public Receta(int id, String titulo, String tiempo, String ingredientes, String instrucciones){
        this.id_receta=id;
        this.titulo=titulo;
        this.tiempo=tiempo;
        this.ingredientes=ingredientes;
        this.instrucciones=instrucciones;
    }

    //Metodos para acceder a los atributos de la clase Receta
    public int getId() {
        return id_receta;
    }

    public void setId(int id) {
        this.id_receta = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }
}
