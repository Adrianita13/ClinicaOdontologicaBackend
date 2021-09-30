package com.dh.clinica.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table (name = "pacientes")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Paciente.class)
public class Paciente {

    @Id
    @SequenceGenerator(name = "paciente_sequence", sequenceName= "paciente_sequence")
    @GeneratedValue (strategy= GenerationType.SEQUENCE, generator = "paciente_generator")
    private Long id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private Date fechaIngreso;

    @OneToMany(mappedBy= "paciente")
    private Set <Turno> turnos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;

    public Paciente() {
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "Paciente: " +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", domicilio=" + domicilio;
    }
}
