package com.example.gestionFormationsInscription.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
    public class Formation {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nom;
        private int duree;
        private String description;

        @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL)
        private List<Inscription> inscriptions;

        // Constructeurs
        public Formation() {
        }

        public Formation(String nom, int duree, String description) {
            this.nom = nom;
            this.duree = duree;
            this.description = description;
        }

        // Getters et setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public int getDuree() {
            return duree;
        }

        public void setDuree(int duree) {
            this.duree = duree;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<Inscription> getInscriptions() {
            return inscriptions;
        }

        public void setInscriptions(List<Inscription> inscriptions) {
            this.inscriptions = inscriptions;
        }


    }


