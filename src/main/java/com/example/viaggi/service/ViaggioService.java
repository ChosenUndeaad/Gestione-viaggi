package com.example.viaggi.service;

import com.example.viaggi.model.StatoViaggio;
import com.example.viaggi.model.Viaggio;
import com.example.viaggi.repository.ViaggioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViaggioService {

    private final ViaggioRepository viaggioRepository;

    public ViaggioService(ViaggioRepository viaggioRepository) {
        this.viaggioRepository = viaggioRepository;
    }

    public List<Viaggio> getAll() {
        return viaggioRepository.findAll();
    }

    public Viaggio getById(Long id) {
        return viaggioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));
    }

    public Viaggio create(Viaggio viaggio) {
        return viaggioRepository.save(viaggio);
    }

    public Viaggio update(Long id, Viaggio viaggioAggiornato) {
        Viaggio viaggio = getById(id);
        viaggio.setDestinazione(viaggioAggiornato.getDestinazione());
        viaggio.setData(viaggioAggiornato.getData());
        return viaggioRepository.save(viaggio);
    }

    public void delete(Long id) {
        if (!viaggioRepository.existsById(id)) {
            throw new EntityNotFoundException("Viaggio non trovato");
        }
        viaggioRepository.deleteById(id);
    }

    public Viaggio aggiornaStato(Long id, StatoViaggio nuovoStato) {
        Viaggio viaggio = getById(id);
        viaggio.setStato(nuovoStato);
        return viaggioRepository.save(viaggio);
    }
}
