package com.example.viaggi.controller;

import com.example.viaggi.model.Dipendente;
import com.example.viaggi.service.DipendenteService;
import com.example.viaggi.service.UploadService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {

    private final DipendenteService dipendenteService;
    private final UploadService uploadService;

    public DipendenteController(DipendenteService dipendenteService, UploadService uploadService) {
        this.dipendenteService = dipendenteService;
        this.uploadService = uploadService;
    }

    // GET all
    @GetMapping
    public List<Dipendente> getAll() {
        return dipendenteService.getAll();
    }

    // GET by ID
    @GetMapping("/{id}")
    public Dipendente getById(@PathVariable Long id) {
        return dipendenteService.getById(id);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Dipendente> create(@Valid @RequestBody Dipendente dipendente) {
        Dipendente created = dipendenteService.create(dipendente);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Dipendente update(@PathVariable Long id, @RequestBody Dipendente dipendente) {
        return dipendenteService.update(id, dipendente);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dipendenteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Upload immagine profilo
    @PostMapping("/{id}/upload-immagine")
    public ResponseEntity<?> uploadImmagineProfilo(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        try {
            String url = uploadService.uploadImage(file);
            Dipendente updated = dipendenteService.aggiornaImmagineProfilo(id, url);
            return ResponseEntity.ok(updated);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore durante l'upload dell'immagine: " + e.getMessage());
        }
    }
}
