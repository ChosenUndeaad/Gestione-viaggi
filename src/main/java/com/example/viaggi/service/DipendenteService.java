package com.example.viaggi.service;

import com.example.viaggi.model.Dipendente;
import com.example.viaggi.repository.DipendenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class DipendenteService {

    private final DipendenteRepository dipendenteRepository;

    public DipendenteService(DipendenteRepository dipendenteRepository) {
        this.dipendenteRepository = dipendenteRepository;
    }

    public List<Dipendente> getAll() {
        return dipendenteRepository.findAll();
    }

    public Dipendente getById(Long id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
    }

    public Dipendente create(Dipendente dipendente) {
        if (dipendenteRepository.existsByUsername(dipendente.getUsername())) {
            throw new IllegalArgumentException("Username già in uso");
        }
        return dipendenteRepository.save(dipendente);
    }

    public Dipendente update(Long id, Dipendente updated) {
        Dipendente dipendente = getById(id);
        dipendente.setNome(updated.getNome());
        dipendente.setCognome(updated.getCognome());
        dipendente.setEmail(updated.getEmail());
        return dipendenteRepository.save(dipendente);
    }

    public void delete(Long id) {
        if (!dipendenteRepository.existsById(id)) {
            throw new EntityNotFoundException("Dipendente non trovato");
        }
        dipendenteRepository.deleteById(id);
    }

    // Upload immagine (solo URL, con Cloudinary a parte)
    public Dipendente aggiornaImmagineProfilo(Long id, String url) {
        Dipendente dipendente = getById(id);
        dipendente.setImmagineProfiloUrl(url);
        return dipendenteRepository.save(dipendente);
    }
}
