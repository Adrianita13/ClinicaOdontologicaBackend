package com.dh.clinica.service;
import com.dh.clinica.dto.PacienteDTO;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    ObjectMapper mapper;

    final static Logger logger = Logger.getLogger(PacienteService.class);

    public Paciente guardar(PacienteDTO paciente) throws BadRequestException {
        if (paciente.getDni() == null || paciente.getApellido() == null || paciente.getNombre() == null)
            throw new BadRequestException("los datos para guardar el paciente no son correctos");
        Paciente pacienteNuevo = mapper.convertValue(paciente, Paciente.class);
        return repository.save(pacienteNuevo);

    }

    public PacienteDTO buscarPorId(Long id) throws BadRequestException {
        Optional<Paciente> pacienteBuscado = repository.findById(id);
        if (pacienteBuscado.isPresent())

            return mapper.convertValue(pacienteBuscado.get(), PacienteDTO.class);
        else throw new BadRequestException("No se puede encontrar un paciente con id: " + id);
    }

    public List<PacienteDTO> buscarTodos() {

        List<PacienteDTO> response;
        List<Paciente> listaPacientes = this.repository.findAll();
        response = listaPacientes.stream().map(PacienteDTO::new).collect(Collectors.toList());
        logger.info("Se muestra la lista de pacientes");
        return response;
    }


    public PacienteDTO actualizar(PacienteDTO pacienteDto) throws BadRequestException {
        if (buscarPorId(pacienteDto.getId()) == null) {
            logger.error("Se produjo un error al actualizar el paciente");
            throw new BadRequestException("No se puede actualizar el paciente porque paciente con id: " + pacienteDto.getId() + "no existe.");
        }
        Paciente paciente = mapper.convertValue(pacienteDto, Paciente.class);
        Paciente pacienteGuardado = repository.save(paciente);
        return mapper.convertValue(pacienteGuardado, PacienteDTO.class);
    }


    public void borrar(Long id) throws BadRequestException {
        buscarPorId(id);
        repository.deleteById(id);
        logger.info("Se eliminó el paciente");
    }
}


