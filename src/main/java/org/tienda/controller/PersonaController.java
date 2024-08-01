package org.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tienda.entity.PersonaModel;
import org.tienda.exceptions.EntityAlreadyExistsException;
import org.tienda.exceptions.EntityNotFoundException;
import org.tienda.service.PersonaService;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<CustomResponse> getPersona() {
        try {
            List<PersonaModel> personas = personaService.findAll();
            CustomResponse cr;
            if (personas.isEmpty()) {
                cr = new CustomResponse(true, "No hay personas registradas", "Empty list");
            } else {
                cr = new CustomResponse(true, "Personas encontradas", personas);
            }
            return ResponseEntity.status(200).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "ERROR DB", e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getPersonaById(@PathVariable Long id) {
        try {
            PersonaModel persona = personaService.findById(id);
            CustomResponse cr = new CustomResponse(true, "Persona encontrada", persona);
            return ResponseEntity.status(200).body(cr);

        } catch (EntityNotFoundException e) {
            CustomResponse cr = new CustomResponse(false, "Persona no encontrada", e.getMessage());
            return ResponseEntity.status(404).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "ERROR DB", e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse> savePersona(@RequestBody PersonaModel personaModel) {
        try {
            PersonaModel persona = personaService.save(personaModel);
            CustomResponse cr = new CustomResponse(true, "Persona guardada", persona);
            return ResponseEntity.status(201).body(cr);

        } catch (EntityAlreadyExistsException e) {
            CustomResponse cr = new CustomResponse(false, "Persona ya existe", e.getMessage());
            return ResponseEntity.status(400).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "ERROR DB", e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updatePersona(@PathVariable Long id, @RequestBody PersonaModel personaModel) {
        try {
            personaModel.setId(id);
            PersonaModel persona = personaService.update(personaModel);
            CustomResponse cr = new CustomResponse(true, "Persona actualizada", persona);
            return ResponseEntity.status(200).body(cr);

        } catch (EntityNotFoundException e) {
            CustomResponse cr = new CustomResponse(false, "Persona no encontrada", e.getMessage());
            return ResponseEntity.status(404).body(cr);
        } catch (EntityAlreadyExistsException e) {
            CustomResponse cr = new CustomResponse(false, "Persona ya existe", e.getMessage());
            return ResponseEntity.status(400).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "ERROR DB", e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deletePersona(@PathVariable Long id) {
        try {
            PersonaModel persona = personaService.delete(id);
            CustomResponse cr = new CustomResponse(true, "Persona eliminada", persona);
            return ResponseEntity.status(200).body(cr);

        } catch (EntityNotFoundException e) {
            CustomResponse cr = new CustomResponse(false, "Persona no encontrada", e.getMessage());
            return ResponseEntity.status(404).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "ERROR DB", e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }

}
