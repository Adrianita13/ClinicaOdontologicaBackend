package com.dh.clinica.service;
import com.dh.clinica.dto.TurnoDTO;
import com.dh.clinica.entity.Turno;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository repository;

    @Autowired
    ObjectMapper mapper;

    final static Logger logger = Logger.getLogger(TurnoService.class);

    public Turno guardar(TurnoDTO turno) throws BadRequestException {
        if(turno==null)
            throw new BadRequestException("Los datos para guardar un turno no son correctos");
        Turno turnoGuardado = mapper.convertValue(turno, Turno.class);
        logger.debug(turno.toString());
        logger.debug(turnoGuardado.toString());

        return repository.save(turnoGuardado);

    }

    public TurnoDTO buscarPorId(Long id) throws BadRequestException {
        Optional<Turno> turnoBuscado = repository.findById(id);
        if (turnoBuscado.isPresent())
            return mapper.convertValue(turnoBuscado.get(), TurnoDTO.class);
        else throw new BadRequestException("No se puede encontrar un turno con id: " + id);
    }

    public List<TurnoDTO> buscarTodos(){

        List <TurnoDTO> response;
        List <Turno> listaTurnos = this.repository.findAll();
        response= listaTurnos.stream().map(TurnoDTO::new).collect(Collectors.toList());

        return response;
    }

    public TurnoDTO actualizar(TurnoDTO turnoDto) throws BadRequestException {
        if(buscarPorId(turnoDto.getId())==null)
            throw new BadRequestException("No se puede actualizar el turno porque no existe un turno con id: " + turnoDto.getId());
        Turno turno = mapper.convertValue(turnoDto, Turno.class);
        Turno turnoGuardado = repository.save(turno);
        return mapper.convertValue(turnoGuardado, TurnoDTO.class); }


    public void borrar(Long id) throws BadRequestException {
        if (buscarPorId(id)==null){
            logger.error("Se produjo un error al intentar eliminar el turno.");
            throw new BadRequestException("No se puede eliminar el turno porque no existe un turno con id: " + id);}
        repository.deleteById(id);

    }

}
