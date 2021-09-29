package com.dh.clinica.controller;
import com.dh.clinica.dto.PacienteDTO;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.service.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/pacientes")
@CrossOrigin("*")
public class PacienteController {

    final static Logger logger = Logger.getLogger(PacienteController.class);

    @Autowired
    PacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Long id) throws BadRequestException {
      PacienteDTO pacienteBuscado = pacienteService.buscarPorId(id);
      logger.info("Se encontró el paciente con id : " + id);
        return ResponseEntity.ok(pacienteBuscado);
    }

    @PostMapping()
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody PacienteDTO paciente) throws BadRequestException {
        Paciente pacientePosteado = pacienteService.guardar(paciente);
       return ResponseEntity.ok(pacientePosteado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarPaciente (@PathVariable Long id) throws BadRequestException {
        pacienteService.borrar(id);
        logger.debug("Se ha borrado el paciente con id: " + id);
        return ResponseEntity.ok("Paciente eliminado");

    }

    @GetMapping()
    public ResponseEntity<List<PacienteDTO>> buscarTodos() {
        List<PacienteDTO> pacientes = pacienteService.buscarTodos();
        logger.info("Mostrando la lista de pacientes.");
        return ResponseEntity.ok(pacientes);
    }

    @PutMapping()
    public ResponseEntity<PacienteDTO> actualizarPaciente(@RequestBody PacienteDTO paciente) throws BadRequestException {
       if(paciente.getDomicilio() != null) paciente.getDomicilio().setPaciente(null);
        PacienteDTO pacienteActualizado= pacienteService.actualizar(paciente);
       logger.debug("Se actualizó el paciente.");
       return ResponseEntity.ok(pacienteActualizado);
    }

}
