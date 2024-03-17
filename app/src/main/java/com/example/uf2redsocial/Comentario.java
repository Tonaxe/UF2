package com.example.uf2redsocial;

public class Comentario {
    private String nombreUsuario;
    private String contenido;
    private String fotoUsuarioUrl; // URL de la foto del usuario

    public Comentario(String nombreUsuario, String contenido, String fotoUsuarioUrl) {
        this.nombreUsuario = nombreUsuario;
        this.contenido = contenido;
        this.fotoUsuarioUrl = fotoUsuarioUrl;
    }

    // Getters y setters
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

    public String getFotoUsuarioUrl() {
        return fotoUsuarioUrl;
    }

    public void setFotoUsuarioUrl(String fotoUsuarioUrl) {
        this.fotoUsuarioUrl = fotoUsuarioUrl;
    }
}
