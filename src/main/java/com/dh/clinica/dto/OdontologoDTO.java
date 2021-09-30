package com.dh.clinica.dto;

import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.entity.Turno;

import java.io.Serializable;
import java.util.Set;

public class OdontologoDTO implements Serializable {


    public OdontologoDTO(String nombre, String apellido, int matricula) {
        this.apellido= apellido;
        this.nombre= nombre;
        this.matricula = matricula;
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

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Long getId() {
        return id;
    }

    private Long id;
    private String nombre;
    private String apellido;
    private Integer matricula;

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }

    private Set <Turno> turnos;

    public OdontologoDTO() {
    }

    public OdontologoDTO (Odontologo odontologo){
        this.id= odontologo.getId();
        this.nombre= odontologo.getNombre();
        this.apellido=odontologo.getApellido();
        this.matricula= odontologo.getMatricula();
        this.turnos= odontologo.getTurnos();
    }


}
