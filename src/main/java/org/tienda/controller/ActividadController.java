package org.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tienda.entity.ActividadModel;
import org.tienda.exceptions.EntityAlreadyExistsException;
import org.tienda.exceptions.EntityNotFoundException;
import org.tienda.service.ActividadService;

import java.util.List;

@RestController
@RequestMapping("/actividades")
public class ActividadController {
    @Autowired
    private ActividadService actividadService;

    @GetMapping
    public ResponseEntity<CustomResponse> getActividad() {
        try {
            List<ActividadModel> actividades = actividadService.findAll();
            CustomResponse cr;
            if (actividades.isEmpty()) {
                cr = new CustomResponse(true, "No hay actividades registradas", "Empty list");
            } else {
                cr = new CustomResponse(true, "Actividades encontradas", actividades);
            }
            return ResponseEntity.status(200).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "ERROR DB", e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getActividadById(@PathVariable Long id) {
        try {
            ActividadModel actividad = actividadService.findById(id);
            CustomResponse cr = new CustomResponse(true, "Actividad encontrada", actividad);
            return ResponseEntity.status(200).body(cr);

        } catch (EntityNotFoundException e) {
            CustomResponse cr = new CustomResponse(false, "Actividad no encontrada", e.getMessage());
            return ResponseEntity.status(404).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "ERROR DB", e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse> saveActividad(@RequestBody ActividadModel actividadModel) {
        try {
            ActividadModel actividad = actividadService.save(actividadModel);
            CustomResponse cr = new CustomResponse(true, "Actividad guardada", actividad);
            return ResponseEntity.status(201).body(cr);

        } catch (EntityAlreadyExistsException e) {
            CustomResponse cr = new CustomResponse(false, "Actividad ya existe", e.getMessage());
            return ResponseEntity.status(400).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "ERROR DB", e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updateActividad(@PathVariable Long id, @RequestBody ActividadModel actividadModel) {
        try {
            actividadModel.setId(id);
            ActividadModel actividad = actividadService.update(actividadModel);
            CustomResponse cr = new CustomResponse(true, "Actividad actualizada", actividad);
            return ResponseEntity.status(200).body(cr);

        } catch (EntityNotFoundException e) {
            CustomResponse cr = new CustomResponse(false, "Actividad no encontrada", e.getMessage());
            return ResponseEntity.status(404).body(cr);
        } catch (EntityAlreadyExistsException e) {
            CustomResponse cr = new CustomResponse(false, "Actividad ya existe", e.getMessage());
            return ResponseEntity.status(400).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "ERROR DB", e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteActividad(@PathVariable Long id) {
        try {
            ActividadModel actividad = actividadService.delete(id);
            CustomResponse cr = new CustomResponse(true, "Actividad eliminada", actividad);
            return ResponseEntity.status(200).body(cr);

        } catch (EntityNotFoundException e) {
            CustomResponse cr = new CustomResponse(false, "Actividad no encontrada", e.getMessage());
            return ResponseEntity.status(404).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "ERROR DB", e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }
}
