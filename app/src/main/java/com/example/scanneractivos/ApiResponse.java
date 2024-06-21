package com.example.scanneractivos;

public class ApiResponse {
    private Activo activo;
    private String foto;

    // Getters y setters
    public Activo getActivo() {
        return activo;
    }

    public void setActivo(Activo activo) {
        this.activo = activo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
