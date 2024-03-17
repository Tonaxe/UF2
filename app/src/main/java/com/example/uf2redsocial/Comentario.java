package com.example.uf2redsocial;

public class Comentario {
    private String postId; // ID del post al que pertenece el comentario
    private String nombreUsuario;
    private String contenido;
    private String fotoUsuarioUrl; // URL de la foto del usuario

    public Comentario() {
        // Constructor vacío requerido por Firebase
    }

    public Comentario(String postId, String nombreUsuario, String contenido, String fotoUsuarioUrl) {
        this.postId = postId;
        this.nombreUsuario = nombreUsuario;
        this.contenido = contenido;
        this.fotoUsuarioUrl = fotoUsuarioUrl;
    }

    // Getters y setters
    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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

    public String getFotoUsuarioUrl() {
        return fotoUsuarioUrl;
    }

    public void setFotoUsuarioUrl(String fotoUsuarioUrl) {
        this.fotoUsuarioUrl = fotoUsuarioUrl;
    }
}
