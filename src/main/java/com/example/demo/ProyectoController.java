package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    private final ProyectoRepository repository;

    public ProyectoController(ProyectoRepository repository) {
        this.repository = repository;
    }

    // GET: listar todos los proyectos
    @GetMapping
    public List<Proyecto> listar() {
        return repository.findAll();
    }

    // GET: buscar proyectos por nombre
    @GetMapping("/buscar")
    public List<Proyecto> buscar(
            @RequestParam("nombre") String nombre) {
        return repository.findByNombre(nombre);
    }

    // GET: consultar estadísticas
    @GetMapping("/estadisticas")
    public Map<String, Object> estadisticas() {
        Map<String, Object> datos = new LinkedHashMap<>();

        datos.put("totalProyectos", repository.count());
        datos.put("completados", repository.countByEstado("Completado"));
        datos.put("enProgreso", repository.countByEstado("En progreso"));
        datos.put("pendientes", repository.countByEstado("Pendiente"));

        return datos;
    }

    // GET: buscar un proyecto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> buscarPorId(
            @PathVariable("id") String id) {

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: crear un proyecto
    @PostMapping
    public ResponseEntity<Proyecto> crear(
            @RequestBody Proyecto proyecto) {

        proyecto.setId(null);

        Proyecto guardado = repository.save(proyecto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(guardado);
    }

    // PUT: actualizar un proyecto completo
    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> actualizar(
            @PathVariable("id") String id,
            @RequestBody Proyecto datos) {

        Proyecto proyecto = repository.findById(id).orElse(null);

        if (proyecto == null) {
            return ResponseEntity.notFound().build();
        }

        proyecto.setNombre(datos.getNombre());
        proyecto.setCategoria(datos.getCategoria());
        proyecto.setTecnica(datos.getTecnica());
        proyecto.setFecha(datos.getFecha());
        proyecto.setEstado(datos.getEstado());
        proyecto.setCalificacion(datos.getCalificacion());

        return ResponseEntity.ok(repository.save(proyecto));
    }

    // PATCH: actualizar solamente el estado
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Proyecto> actualizarEstado(
            @PathVariable("id") String id,
            @RequestBody Map<String, String> datos) {

        Proyecto proyecto = repository.findById(id).orElse(null);

        if (proyecto == null) {
            return ResponseEntity.notFound().build();
        }

        String estado = datos.get("estado");

        if (estado == null || estado.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        proyecto.setEstado(estado);

        return ResponseEntity.ok(repository.save(proyecto));
    }

    // DELETE: eliminar un proyecto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable("id") String id) {

        if (!repository.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
