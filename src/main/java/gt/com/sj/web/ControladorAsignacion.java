package gt.com.sj.web;

import gt.com.sj.dao.AsignacionDao;
import gt.com.sj.domain.Asignaciones;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Asignaciones")//enpoint principal 

public class ControladorAsignacion {
    
    @Autowired
    private AsignacionDao asignacionDao;

    @RequestMapping(value = "Mostrar", method = RequestMethod.GET)
    public ResponseEntity<List<Asignaciones>> MostrarRoles() {

        List<Asignaciones> asignaciones = asignacionDao.findAll();
        return ResponseEntity.ok(asignaciones);

    }

    @PostMapping(value = "Crear")
    public ResponseEntity<Asignaciones> CrearAsignacion(@RequestBody Asignaciones asignacion) {

        Asignaciones nuevaAsignacion = asignacionDao.save(asignacion);
        return ResponseEntity.ok(nuevaAsignacion);

    }

    @DeleteMapping(value = "Eliminar/{asignacionId}")
    public ResponseEntity<Void> EliminarAsignacion(@PathVariable("asignacionId") int asignacionId) {

        asignacionDao.deleteById(asignacionId);

        return ResponseEntity.ok(null);
    }

    @PatchMapping(value = "EditarAsignacion")
    public ResponseEntity<Asignaciones> EditarAsignacion(@RequestBody Asignaciones asignacion) {

        Optional<Asignaciones> asignacionActualizada = asignacionDao.findById(asignacion.getId());

        if (asignacionActualizada.isPresent()) {
            Asignaciones actualizarAsignacion = asignacionActualizada.get();
            actualizarAsignacion.setId_roles(asignacion.getId_roles());
            asignacionDao.save(actualizarAsignacion);
            return ResponseEntity.ok(actualizarAsignacion);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}

    

