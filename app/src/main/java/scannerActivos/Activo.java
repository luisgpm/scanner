package scannerActivos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
public class Activo implements Serializable {
    private static final long serialVersionUID = 2L;
    private String placa;
    private String activo;
    private String desc_corta;
    private String modelo;
    private String marca;
    private String no_serie;
    private String estado;
    @SerializedName("fecha_capitalizacion")

    private Date fecha;
    private int id_empleado;
    private Empleado empleado;

    private long no_activo;

    // Getters y setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getActivo() {
        return activo != null ? activo : "N/A" ;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getDescCorta() {
        return desc_corta != null ? desc_corta : "N/A";
    }

    public void setDescCorta(String desc_corta) {
        this.desc_corta = desc_corta;
    }

    public String getModelo() {
        return modelo != null ? modelo : "N/A" ;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca != null ? marca : "N/A";
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNoSerie() {
        return no_serie != null ? no_serie : "N/A";
    }

    public void setNoSerie(String no_serie) {
        this.no_serie = no_serie;
    }

    public String getEstado() {
        return estado != null ? estado : "N/A";
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdEmpleado() {
        return id_empleado;
    }

    public void setIdEmpleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public long getNo_activo() {
        return no_activo;
    }

    public void setNo_activo(long no_activo) {
        this.no_activo = no_activo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
