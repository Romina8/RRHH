package ar.com.ada.api.rrhh.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.ada.api.rrhh.entities.Empleado;
import ar.com.ada.api.rrhh.repos.EmpleadoRepository;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository repository;

    public void crearEmpleado(Empleado empleado) {

        repository.save(empleado);
    }

    public List<Empleado> buscarEmpleados() {

        return repository.findAll();
    }

    public Empleado buscarEmpleadoPor(int id){

        Optional<Empleado> eo = repository.findById(id);

        if(eo.isPresent()){
            return eo.get();
        }
        return null;
    }
}