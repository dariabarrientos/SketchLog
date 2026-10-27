package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProyectoRepository {

    private final Map<String, Proyecto> proyectos =
            new LinkedHashMap<>();

    public ProyectoRepository() {
        Proyecto ejemplo = new Proyecto(
                UUID.randomUUID().toString(),
                "Atardecer",
                "Paisaje",
                "Acuarela",
                "2026-10-20",
                "En progreso",
                4
        );

        proyectos.put(ejemplo.getId(), ejemplo);
    }

    public List<Proyecto> findAll() {
        return new ArrayList<>(proyectos.values());
    }

    public Optional<Proyecto> findById(String id) {
        return Optional.ofNullable(proyectos.get(id));
    }

    public Proyecto save(Proyecto proyecto) {
        if (proyecto.getId() == null ||
                proyecto.getId().isBlank()) {
            proyecto.setId(UUID.randomUUID().toString());
        }

        proyectos.put(proyecto.getId(), proyecto);
        return proyecto;
    }

    public List<Proyecto> findByNombre(String nombre) {
        List<Proyecto> resultado = new ArrayList<>();

        for (Proyecto proyecto : proyectos.values()) {
            if (proyecto.getNombre() != null &&
                    proyecto.getNombre().toLowerCase()
                            .contains(nombre.toLowerCase())) {
                resultado.add(proyecto);
            }
        }

        return resultado;
    }

    public boolean deleteById(String id) {
        return proyectos.remove(id) != null;
    }

    public long countByEstado(String estado) {
        return proyectos.values().stream()
                .filter(p -> p.getEstado() != null &&
                        p.getEstado().equalsIgnoreCase(estado))
                .count();
    }

    public long count() {
        return proyectos.size();
    }
}
