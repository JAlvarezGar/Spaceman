package com.example.practica2_juegohipotenochas;

import java.io.Serializable;

public class Hipotenochas  {
    private int imagen;
    private String nombre;

    /**
     *
     * @param imagen
     * @param nombre
     */
    public Hipotenochas(int imagen, String nombre) {
        this.imagen = imagen;
        this.nombre= nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
