package com.example.viaggi.service;

import com.example.viaggi.model.Prenotazione;
import com.example.viaggi.repository.DipendenteRepository;
import com.example.viaggi.repository.PrenotazioneRepository;
import com.example.viaggi.repository.ViaggioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final DipendenteRepository dipendenteRepository;
    private final ViaggioRepository viaggioRepository;

    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository,
                               DipendenteRepository dipendenteRepository,
                               ViaggioRepository viaggioRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.dipendenteRepository = dipendenteRepository;
        this.viaggioRepository = viaggioRepository;
    }

    public List<Prenotazione> getAll() {
        return prenotazioneRepository.findAll();
    }

    public Prenotazione getById(Long id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));
    }

    public Prenotazione create(Prenotazione prenotazione) {
        var dipendente = dipendenteRepository.findById(prenotazione.getDipendente().getId())
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        var viaggio = viaggioRepository.findById(prenotazione.getViaggio().getId())
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));

        LocalDate data = prenotazione.getDataRichiesta();

        if (prenotazioneRepository.existsByDipendenteAndDataRichiesta(dipendente, data)) {
            throw new IllegalArgumentException("Dipendente già prenotato in questa data");
        }

        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        return prenotazioneRepository.save(prenotazione);
    }

    public void delete(Long id) {
        if (!prenotazioneRepository.existsById(id)) {
            throw new EntityNotFoundException("Prenotazione non trovata");
        }
        prenotazioneRepository.deleteById(id);
    }

    public List<Prenotazione> getByDipendenteId(Long dipendenteId) {
        return prenotazioneRepository.findByDipendenteId(dipendenteId);
    }
}
