package com.dh.clinica.controller;
import com.dh.clinica.dto.OdontologoDTO;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/odontologos")
@CrossOrigin("*")
public class OdontologoController {

    @Autowired
    OdontologoService odontologoService;

    final static Logger logger = Logger.getLogger(OdontologoController.class);

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDTO> buscarPorId(@PathVariable Long id) throws BadRequestException {
        OdontologoDTO odontologoBuscado = odontologoService.buscarPorId(id);
        logger.info("Se encontró el odontólogo con id : " + id);
        return ResponseEntity.ok(odontologoBuscado);
    }

    @PostMapping()
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody OdontologoDTO odontologo) throws BadRequestException {
        Odontologo odontologoPosteado = odontologoService.guardar(odontologo);
        return ResponseEntity.ok(odontologoPosteado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarOdontologo (@PathVariable Long id) throws BadRequestException {
        odontologoService.borrar(id);
        logger.debug("Se ha borrado el odontólogo con id: " + id);
        return ResponseEntity.ok("Odontólogo con id: " + id + " ha sido eliminado.");

    }

    @GetMapping()
    public ResponseEntity<List<OdontologoDTO>> buscarTodos() {
        List<OdontologoDTO> odontologos = odontologoService.buscarTodos();
        return ResponseEntity.ok(odontologos);
    }

    @PutMapping()
    public ResponseEntity<OdontologoDTO> actualizarOdontologo(@RequestBody OdontologoDTO odontologo) throws BadRequestException {
        OdontologoDTO odontologoActualizado= odontologoService.actualizar(odontologo);
        logger.debug("Se actualizó el odontólogo.");
        return ResponseEntity.ok(odontologoActualizado);
    }
    }

