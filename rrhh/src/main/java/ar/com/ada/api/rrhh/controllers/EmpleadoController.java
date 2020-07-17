package ar.com.ada.api.rrhh.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ar.com.ada.api.rrhh.entities.Empleado;
import ar.com.ada.api.rrhh.services.EmpleadoService;

@RestController
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @PostMapping("/empleados")
    public ResponseEntity<?> crearCategoria(@RequestBody Empleado empleado){

        empleadoService.crearEmpleado(empleado);

        return ResponseEntity.ok(empleado);
    }

    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> listarCategorias(){

        return ResponseEntity.ok(empleadoService.buscarCategorias());
    }

} 