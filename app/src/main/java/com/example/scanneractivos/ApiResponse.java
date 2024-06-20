package com.example.scanneractivos;

public class ApiResponse {
    private Activo activos;
    private String foto;

    // Getters y setters
    public Activo getActivos() {
        return activos;
    }

    public void setActivos(Activo activos) {
        this.activos = activos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
