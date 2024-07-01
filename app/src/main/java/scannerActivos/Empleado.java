package scannerActivos;

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
    @SerializedName("emp__puesto_codigo")

    private String codigoPuesto;
    @SerializedName("emp__estruc_nominal")

    private String estruNominal;

    private Puesto puesto;

    @SerializedName("estructura_nominal")

    private EstructuraNominal estructuraNominal;

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

    public String getCodigoPuesto() {
        return codigoPuesto;
    }

    public String getEstruNominal() {
        return estruNominal;
    }

    public void setCodigoPuesto(String codigoPuesto) {
        this.codigoPuesto = codigoPuesto;
    }

    public void setEstruNominal(String estruNominal) {
        this.estruNominal = estruNominal;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public EstructuraNominal getEstructuraNominal() {
        return estructuraNominal;
    }

    public void setEstructuraNominal(EstructuraNominal estructuraNominal) {
        this.estructuraNominal = estructuraNominal;
    }
}
