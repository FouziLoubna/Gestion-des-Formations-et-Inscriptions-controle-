package com.example.gestionFormationsInscription.Repository;

import com.example.gestionFormationsInscription.entities.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {}
