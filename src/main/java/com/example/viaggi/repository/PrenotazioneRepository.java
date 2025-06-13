package com.example.viaggi.repository;

import com.example.viaggi.model.Prenotazione;
import com.example.viaggi.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    boolean existsByDipendenteAndDataRichiesta(Dipendente dipendente, LocalDate dataRichiesta);

    List<Prenotazione> findByDipendenteId(Long dipendenteId);
}
