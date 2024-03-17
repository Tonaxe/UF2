package com.example.uf2redsocial;

public class Comentario {
    private String nombreUsuario;
    private String contenido;

    public Comentario(String nombreUsuario, String contenido) {
        this.nombreUsuario = nombreUsuario;
        this.contenido = contenido;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
