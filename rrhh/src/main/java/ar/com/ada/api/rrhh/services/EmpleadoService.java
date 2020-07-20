package ar.com.ada.api.rrhh.services;

import java.math.BigDecimal;
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

    public boolean actualizarSueldoEmpleado(Empleado empleadoOriginal, BigDecimal sueldo){

        empleadoOriginal.setSueldo(sueldo);

        repository.save(empleadoOriginal);

        return true;

    }

    public void actualizarEstado(Empleado empleado,int estadoId){

        empleado.setEstadoId(estadoId);

        repository.save(empleado);
    }

    public void borrarEmpleado(Empleado empleado){

        this.actualizarEstado(empleado, 0);

    }
}