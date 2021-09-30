package com.dh.clinica;
import com.dh.clinica.dto.OdontologoDTO;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.service.OdontologoService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServiceTests {


    @Autowired
    OdontologoService odontologoService;

    @Test
    public void guardar() throws BadRequestException {
        Odontologo odontologoGuardado = odontologoService.guardar(new OdontologoDTO("Juan", "Ramirez", 348971960));
        Assert.assertTrue(odontologoGuardado.getId() != null);
   }

        @Test
        public void traerTodos () {
            List<OdontologoDTO> odontologos = odontologoService.buscarTodos();
            Assert.assertTrue(!odontologos.isEmpty());
            Assert.assertTrue(odontologos.size() == 1);
            System.out.println(odontologos);
        }

    }

