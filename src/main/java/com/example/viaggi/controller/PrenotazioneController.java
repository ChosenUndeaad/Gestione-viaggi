package com.example.viaggi.controller;

import com.example.viaggi.model.Prenotazione;
import com.example.viaggi.service.PrenotazioneService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    public PrenotazioneController(PrenotazioneService prenotazioneService) {
        this.prenotazioneService = prenotazioneService;
    }

    @GetMapping
    public List<Prenotazione> getAll() {
        return prenotazioneService.getAll();
    }

    @GetMapping("/{id}")
    public Prenotazione getById(@PathVariable Long id) {
        return prenotazioneService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Prenotazione> create(@Valid @RequestBody Prenotazione prenotazione) {
        Prenotazione created = prenotazioneService.create(prenotazione);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        prenotazioneService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dipendente/{id}")
    public List<Prenotazione> getByDipendente(@PathVariable Long id) {
        return prenotazioneService.getByDipendenteId(id);
    }
}
