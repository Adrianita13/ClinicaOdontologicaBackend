package com.dh.clinica.service;
import com.dh.clinica.dto.OdontologoDTO;
import com.dh.clinica.dto.PacienteDTO;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.repository.OdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OdontologoService {

    @Autowired
    private OdontologoRepository repository;

    @Autowired
    ObjectMapper mapper;

    final static Logger logger = Logger.getLogger(OdontologoService.class);


    public Odontologo guardar(OdontologoDTO odontologo) throws BadRequestException {
        if (odontologo.getNombre() == null || odontologo.getApellido() == null || odontologo.getMatricula() == null) {
            logger.error("Se produjo un error al intentar guardar un odontólogo");
            throw new BadRequestException("los datos para guardar el odontólogo no son correctos");
        }
        Odontologo odontologoNuevo = mapper.convertValue(odontologo, Odontologo.class);
        return repository.save(odontologoNuevo);

    }

    public OdontologoDTO buscarPorId(Long id) throws BadRequestException {
        Optional<Odontologo> odontologoBuscado = repository.findById(id);
        if (odontologoBuscado.isPresent())
            return mapper.convertValue(odontologoBuscado.get(), OdontologoDTO.class);
        else throw new BadRequestException("No se puede encontrar un odontologo con id: " + id);
    }

    public List<OdontologoDTO> buscarTodos(){

        List <OdontologoDTO> response;
        List <Odontologo> listaOdontologos = this.repository.findAll();
        response= listaOdontologos.stream().map(OdontologoDTO::new).collect(Collectors.toList());

        return response;
    }


    public OdontologoDTO actualizar(OdontologoDTO odontologoDto) throws BadRequestException {
        if (buscarPorId(odontologoDto.getId()) == null) {
            logger.error("Se produjo un error al actualizar el odontólogo");
            throw new BadRequestException("No se puede actualizar el odontólogo porque odontólogo con id: " + odontologoDto.getId() + "no existe.");
        }
        Odontologo odontologo = mapper.convertValue(odontologoDto, Odontologo.class);
        Odontologo odontologoGuardado = repository.save(odontologo);
        return mapper.convertValue(odontologoGuardado, OdontologoDTO.class);
    }

    public void borrar(Long id) throws BadRequestException {
        buscarPorId(id);
        repository.deleteById(id);
        logger.info("Se eliminó el odontólogo");
    }

}
