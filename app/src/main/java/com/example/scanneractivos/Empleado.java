package com.example.scanneractivos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("emp__codigo")
    private int emp_codigo;
    @SerializedName("emp__nombre")
    private String empNombre;

    @SerializedName("emp__paterno")
    private String empPaterno;

    @SerializedName("emp__materno")
    private String empMaterno;

    @SerializedName("emp__r_f_c")
    private String empRfc;

    // Getters y setters
    public int getEmpCodigo() {
        return emp_codigo;
    }

    public void setEmpCodigo(int emp_codigo) {
        this.emp_codigo = emp_codigo;
    }

    public String getEmpNombre() {
        return empNombre;
    }

    public String getEmpPaterno() {
        return empPaterno;
    }

    public String getEmpMaterno() {
        return empMaterno;
    }

    public String getEmpRfc() {
        return empRfc;
    }

    public void setEmpNombre(String empNombre) {
        this.empNombre = empNombre;
    }

    public void setEmpPaterno(String empPaterno) {
        this.empPaterno = empPaterno;
    }

    public void setEmpMaterno(String empMaterno) {
        this.empMaterno = empMaterno;
    }

    public void setEmpRfc(String empRfc) {
        this.empRfc = empRfc;
    }
}
