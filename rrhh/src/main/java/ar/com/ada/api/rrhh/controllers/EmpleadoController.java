package ar.com.ada.api.rrhh.controllers;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ar.com.ada.api.rrhh.entities.Empleado;
import ar.com.ada.api.rrhh.models.request.InfoBasicaEmpleadoRequest;
import ar.com.ada.api.rrhh.models.request.SueldoInfoRequest;
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

    @GetMapping("/empleados/{id}")
    public ResponseEntity<?> traerEmpleadoPor(@PathVariable int id){

        Empleado empleado = empleadoService.buscarEmpleadoPor(id);

        if(empleado != null){
            return ResponseEntity.ok(empleado);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/empleados/{id}/sueldos")
    public ResponseEntity<?> actualizarSueldoEmpleado(@PathVariable int id,@RequestBody SueldoInfoRequest infoNueva){

        GenericResponse r = new GenericResponse();

        Empleado empleado = empleadoService.buscarEmpleadoPor(id);

        if (empleado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean resultado = false;
        resultado = empleadoService.actualizarSueldoEmpleado(empleado, infoNueva.sueldoNuevo);

        if (resultado) {
            r.isOk = true;
            r.id = empleado.getEmpleadoId();
            r.message = "Actualizado con éxito.";
            return ResponseEntity.ok(r);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<?> borrarEmpleado(@PathVariable int id){

        Empleado empleado = empleadoService.buscarEmpleadoPor(id);

        if(empleado != null){

            empleadoService.borrarEmpleado(empleado);

            GenericResponse resp = new GenericResponse();
            resp.isOk = true;
            resp.id = empleado.getEmpleadoId();
            resp.message = "Fue eliminado con exito";

       return ResponseEntity.ok(resp); 
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);



    }

} 