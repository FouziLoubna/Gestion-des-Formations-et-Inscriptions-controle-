
# Système de Gestion des Formations et des Inscriptions

## Description
Cette application permet de gérer des formations et les inscriptions des étudiants. Chaque formation peut accueillir plusieurs étudiants inscrits. L'application offre une interface utilisateur simple pour afficher la liste des formations disponibles et les détails des inscriptions des étudiants pour chaque formation.

## Fonctionnalités
- **Liste des Formations** : Affiche toutes les formations proposées.
- **Détail de l'Inscription** : Permet de visualiser les étudiants inscrits pour une formation spécifique.

## Modèle de Données
### Entités
1. **Formation**
   - `nom` : Nom de la formation
   - `durée` : Durée de la formation
   - `description` : Description de la formation

2. **Inscription**
   - `date` : Date de l'inscription
   - `formation` : Référence à la formation inscrite
   - `étudiant` : Détails de l'étudiant inscrit

## Pages Web
1. **Page Liste des Formations** : Affiche les formations proposées.
2. **Page Détail de l'Inscription** : Affiche les étudiants inscrits à une formation spécifique.

## Technologies Utilisées
- Backend : Spring Boot (MVC)
- Frontend : Thymeleaf
- Base de Données : MySQL 
- IDE : IntelliJ

