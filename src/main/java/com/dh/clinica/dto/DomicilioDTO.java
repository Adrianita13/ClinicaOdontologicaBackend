package com.dh.clinica.dto;

import com.dh.clinica.entity.Domicilio;
import com.dh.clinica.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;


public class DomicilioDTO implements Serializable {

    private Long id;
    private String localidad;
    private String provincia;
    private String calle;
    private String numero;

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    private Paciente paciente;

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public DomicilioDTO() {
    }

    public Long getId() {
        return id;
    }

    public DomicilioDTO (Domicilio domicilio){
        this.id= domicilio.getId();
        this.calle= domicilio.getCalle();
        this.numero= domicilio.getNumero();
        this.localidad= domicilio.getLocalidad();
        this.provincia= domicilio.getProvincia();
    }
}
