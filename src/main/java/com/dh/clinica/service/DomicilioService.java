package com.dh.clinica.service;
import com.dh.clinica.dto.DomicilioDTO;
import com.dh.clinica.dto.PacienteDTO;
import com.dh.clinica.dto.TurnoDTO;
import com.dh.clinica.entity.Domicilio;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.entity.Turno;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.repository.DomicilioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

@Service
public class DomicilioService {

    @Autowired
    private DomicilioRepository repository;

    @Autowired
    ObjectMapper mapper;

    final static Logger logger = Logger.getLogger(DomicilioService.class);

    public Domicilio guardar(DomicilioDTO domicilio) throws BadRequestException {
        if(domicilio.getCalle()==null || domicilio.getNumero()==null|| domicilio.getLocalidad() ==null || domicilio.getProvincia() ==null) {
            logger.error("Se produjo un error al intentar guardar el domicilio");
            throw new BadRequestException("los datos para guardar un domicilio no son correctos");
        }
        Domicilio domicilioGuardado = mapper.convertValue(domicilio, Domicilio.class);
        return repository.save(domicilioGuardado);

    }

    public DomicilioDTO buscarPorId(Long id) throws BadRequestException {
        Optional<Domicilio> domicilioBuscado = repository.findById(id);
        if (domicilioBuscado.isPresent())
            return mapper.convertValue(domicilioBuscado.get(), DomicilioDTO.class);
        else throw new BadRequestException("No se puede encontrar un domicilio con id: " + id);
    }

    public List<DomicilioDTO> buscarTodos(){

        List <DomicilioDTO> response;
        List <Domicilio> listaDomicilios = this.repository.findAll();
        response= listaDomicilios.stream().map(DomicilioDTO::new).collect(Collectors.toList());

        return response;
    }

    public DomicilioDTO actualizar(DomicilioDTO domicilioDto) throws BadRequestException {
        if(buscarPorId(domicilioDto.getId())==null) {
            logger.error("Se produjo un error al intentar actualizar el domicilio.");
            throw new BadRequestException("No se puede actualizar el domicilio porque no existe un domicilio con id: " + domicilioDto.getId());
        }
        Domicilio domicilio = mapper.convertValue(domicilioDto, Domicilio.class);
        Domicilio domicilioGuardado = repository.save(domicilio);
        return mapper.convertValue(domicilioGuardado, DomicilioDTO.class); }


    public void borrar(Long id) throws BadRequestException {
        if (buscarPorId(id)==null){
            logger.error("Se produjo un error al intentar eliminar el domicilio.");
            throw new BadRequestException("No se puede eliminar el domicilio porque no existe un domicilio con id: " + id);}
        repository.deleteById(id);

    }

}
