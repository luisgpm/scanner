package com.example.scanneractivos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Puesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("pues_codigo")

    private int codigo;
    @SerializedName("pues_descripcion")

    private String descripcion;

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
