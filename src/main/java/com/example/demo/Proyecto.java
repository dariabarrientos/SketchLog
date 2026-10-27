package com.example.demo;

public class Proyecto {

    private String id;
    private String nombre;
    private String categoria;
    private String tecnica;
    private String fecha;
    private String estado;
    private int calificacion;

    public Proyecto() {
    }

    public Proyecto(String id, String nombre, String categoria,
                    String tecnica, String fecha, String estado,
                    int calificacion) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.tecnica = tecnica;
        this.fecha = fecha;
        this.estado = estado;
        this.calificacion = calificacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}