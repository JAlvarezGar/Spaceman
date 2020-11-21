package com.example.practica2_juegohipotenochas;

public class Hipotenochas {
    int imagen;
    String nombre;

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
