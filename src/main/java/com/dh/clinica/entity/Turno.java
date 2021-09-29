package com.dh.clinica.entity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "turnos")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Turno.class)
public class Turno {

    @Id
    @SequenceGenerator(name = "turnos_sequence", sequenceName= "turnos_sequence")
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Paciente paciente;
    @ManyToOne
    @JoinColumn( name = "odontologo_id", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Odontologo odontologo;
    @Column
    private Date fecha;

    public Turno() {
    }

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date date) {
        this.fecha = date;
    }

    @Override
    public String toString() {
        return "Turno: " +
                " id= " + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", date=" + fecha;
    }
}
