package ar.com.ada.api.rrhh.controllers;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ar.com.ada.api.rrhh.entities.Empleado;
import ar.com.ada.api.rrhh.models.request.InfoBasicaEmpleadoRequest;
import ar.com.ada.api.rrhh.models.response.GenericResponse;
import ar.com.ada.api.rrhh.services.CategoriaService;
import ar.com.ada.api.rrhh.services.EmpleadoService;

@RestController
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    CategoriaService catService;

    @PostMapping("/empleados")
    public ResponseEntity<?> crearCategoria(@RequestBody InfoBasicaEmpleadoRequest info){

        Empleado empleado = new Empleado();
        empleado.setNombre(info.nombre);
        empleado.setEdad(info.edad);
        empleado.setSueldo(info.sueldo);
        empleado.setCategoria(catService.buscarCategoriaPor(info.categoriaId));
        empleado.setEstadoId(1);
        empleado.setFechaAlta(new Date());

        empleadoService.crearEmpleado(empleado);

        GenericResponse response = new GenericResponse();
        response.isOk = true;
        response.id = empleado.getEmpleadoId();
        response.message = "Empleado generado con exito";
        return ResponseEntity.ok(response);
    }

    @GetMapping("/empleados")
    public ResponseEntity<?> listarEmpleado(){

        return ResponseEntity.ok(empleadoService.buscarEmpleados());
    }

} 