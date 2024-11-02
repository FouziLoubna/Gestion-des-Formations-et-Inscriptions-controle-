package com.example.gestionFormationsInscription.Repository;
import com.example.gestionFormationsInscription.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {}
