package com.example.viaggi.controller;

import com.example.viaggi.model.StatoViaggio;
import com.example.viaggi.model.Viaggio;
import com.example.viaggi.service.ViaggioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaggi")
public class ViaggioController {

    private final ViaggioService viaggioService;

    public ViaggioController(ViaggioService viaggioService) {
        this.viaggioService = viaggioService;
    }

    @GetMapping
    public List<Viaggio> getAll() {
        return viaggioService.getAll();
    }

    @GetMapping("/{id}")
    public Viaggio getById(@PathVariable Long id) {
        return viaggioService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Viaggio> create(@Valid @RequestBody Viaggio viaggio) {
        return new ResponseEntity<>(viaggioService.create(viaggio), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Viaggio update(@PathVariable Long id, @RequestBody Viaggio viaggio) {
        return viaggioService.update(id, viaggio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        viaggioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/stato")
    public Viaggio cambiaStato(@PathVariable Long id, @RequestParam StatoViaggio stato) {
        return viaggioService.aggiornaStato(id, stato);
    }
}
