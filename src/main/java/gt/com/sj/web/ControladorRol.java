package gt.com.sj.web;

import gt.com.sj.dao.RolDao;
import gt.com.sj.domain.Roles;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Roles")//enpoint principal 

public class ControladorRol {

    @Autowired
    private RolDao rolDao;

    @RequestMapping(value = "Mostrar", method = RequestMethod.GET)
    public ResponseEntity<List<Roles>> MostrarRoles() {

        List<Roles> roles = rolDao.findAll();
        return ResponseEntity.ok(roles);

    }

    @PostMapping(value = "Crear")
    public ResponseEntity<Roles> CrearRol(@RequestBody Roles rol) {

        Roles nuevoRol = rolDao.save(rol);
        return ResponseEntity.ok(nuevoRol);

    }

    @DeleteMapping(value = "Eliminar/{RolId}")
    public ResponseEntity<Void> EliminarRol(@PathVariable("RolId") int rolId) {

        rolDao.deleteById(rolId);

        return ResponseEntity.ok(null);
    }

    @PatchMapping(value = "EditarRol")
    public ResponseEntity<Roles> EditarRol(@RequestBody Roles rol) {

        Optional<Roles> rolActualizado = rolDao.findById(rol.getId());

        if (rolActualizado.isPresent()) {
            Roles actualizarRol = rolActualizado.get();
            actualizarRol.setRolName(rol.getRolName());
            rolDao.save(actualizarRol);
            return ResponseEntity.ok(actualizarRol);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
