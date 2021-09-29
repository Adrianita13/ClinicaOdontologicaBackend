package com.dh.clinica.dto;
import com.dh.clinica.entity.Domicilio;
import com.dh.clinica.entity.Paciente;
import java.io.Serializable;
import java.util.Date;

public class PacienteDTO implements Serializable {

    private String apellido;
    private Domicilio domicilio;
    private Long id;
    private Integer dni;
    private String nombre;
    private Date fechaIngreso;

    public Date getFechaIngreso() {
        return fechaIngreso;
    }
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getDni() {
        return dni;
    }
    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Long getId() {
        return id;
    }

    public PacienteDTO (Paciente paciente){
        this.id= paciente.getId();
        this.nombre= paciente.getNombre();
        this.apellido=paciente.getApellido();
        this.domicilio= paciente.getDomicilio();
        this.fechaIngreso = paciente.getFechaIngreso();
        this.dni = paciente.getDni();
    }

    public PacienteDTO() {
    }
}
