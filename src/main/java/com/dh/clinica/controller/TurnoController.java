package com.dh.clinica.controller;
import com.dh.clinica.dto.TurnoDTO;
import com.dh.clinica.exception.BadRequestException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dh.clinica.entity.Turno;
import com.dh.clinica.service.TurnoService;
import java.util.List;


@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    final static Logger logger = Logger.getLogger(TurnoController.class);

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarPorId(@PathVariable Long id) throws BadRequestException {
        TurnoDTO turnoBuscado = turnoService.buscarPorId(id);
        logger.info("Se encontró el turno con id : " + id + ", correspondiente al Odontologo: " + turnoBuscado.getOdontologo().getApellido() + ", con id: "+ turnoBuscado.getPaciente().getId() + ", y al paciente: " + turnoBuscado.getPaciente().getApellido() + ", con id: " + turnoBuscado.getPaciente().getId() );
        return ResponseEntity.ok(turnoBuscado);
    }

    @PostMapping()
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turno) throws BadRequestException {
        logger.debug(turno.toString());
       TurnoDTO turnoPosteado = turnoService.guardar(turno);
       logger.debug(turnoPosteado);
        return ResponseEntity.ok(turnoPosteado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTurno (@PathVariable Long id) throws BadRequestException {
        turnoService.borrar(id);
        logger.debug("Se ha borrado el turno con id: " + id);
        return ResponseEntity.ok("Turno eliminado.");

    }

    @GetMapping()
    public ResponseEntity<List<TurnoDTO>> buscarTodos() {
        List<TurnoDTO> turnos = turnoService.buscarTodos();
        logger.info("Mostrando la lista de turnos.");
        return ResponseEntity.ok(turnos);
    }

    @PutMapping()
    public ResponseEntity<TurnoDTO> actualizarTurno(@RequestBody TurnoDTO turno) throws BadRequestException {
        TurnoDTO turnoActualizado= turnoService.actualizar(turno);
        return ResponseEntity.ok(turnoActualizado);
    }
}

