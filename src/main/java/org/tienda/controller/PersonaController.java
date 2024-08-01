package org.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tienda.entity.PersonaModel;
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

            // add exception notfound
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

            // add exception already exists
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "ERROR DB", e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }
}
