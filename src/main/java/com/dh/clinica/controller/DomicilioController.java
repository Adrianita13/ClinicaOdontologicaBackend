package com.dh.clinica.controller;
import com.dh.clinica.dto.DomicilioDTO;
import com.dh.clinica.entity.Domicilio;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.service.DomicilioService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
    @RequestMapping("/domicilios")
    public class DomicilioController {

    final static Logger logger = Logger.getLogger(DomicilioController.class);

    @Autowired
    DomicilioService domicilioService;

        @PostMapping()
        public ResponseEntity<Domicilio> registrarDomicilio(@RequestBody DomicilioDTO domicilio) throws BadRequestException {
            Domicilio domicilioPosteado = domicilioService.guardar(domicilio);
            return ResponseEntity.ok(domicilioPosteado);
        }

        @GetMapping("/{id}")
        public ResponseEntity<DomicilioDTO> buscarPorId(@PathVariable Long id) throws BadRequestException {
            DomicilioDTO domicilioBuscado = domicilioService.buscarPorId(id);
            logger.info("Se encontró el domcilio con id : " + id + ", correspondiente al Paciente: " + domicilioBuscado.getPaciente().getApellido() + ", con id: " + domicilioBuscado.getPaciente().getId());
            return ResponseEntity.ok(domicilioBuscado);
        }

    @GetMapping
    public ResponseEntity<List<DomicilioDTO>> buscarTodos() {
            List<DomicilioDTO> domicilios = domicilioService.buscarTodos();
            logger.info("Mostrando lista de domicilios.");
            return ResponseEntity.ok(domicilios);
        }

        @PutMapping()
        public ResponseEntity<DomicilioDTO> actualizarDomicilio(@RequestBody DomicilioDTO domicilio) throws BadRequestException {
           DomicilioDTO domicilioActualizado= domicilioService.actualizar(domicilio);
            return ResponseEntity.ok(domicilioActualizado);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity eliminarDomicilio (@PathVariable Long id) throws BadRequestException {
            domicilioService.borrar(id);
            logger.debug("Se ha borrado el domicilio con id: " + id);
            return ResponseEntity.ok("Domicilio eliminado.");

        }
    }
