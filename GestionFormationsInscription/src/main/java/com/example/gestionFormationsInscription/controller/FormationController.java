package com.example.gestionFormationsInscription.controller;

import com.example.gestionFormationsInscription.Repository.FormationRepository;
import com.example.gestionFormationsInscription.entities.Formation;
import com.example.gestionFormationsInscription.entities.Inscription;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class FormationController {

    @Autowired
    private FormationRepository formationRepository;

    // Affiche la liste des formations
    @GetMapping("/formations")
    public String listFormations(Model model) {
        model.addAttribute("formations", formationRepository.findAll());
        return "formations";
    }

    // Affiche le formulaire pour ajouter une nouvelle formation
    @GetMapping("/formations/new")
    public String showAddFormationForm(Model model) {
        model.addAttribute("formation", new Formation());
        return "add-formation";
    }

    // Ajoute une nouvelle formation
    @PostMapping("/formations/add")
    public String addFormation(@Valid Formation formation, BindingResult result) {
        if (result.hasErrors()) {
            return "add-formation";
        }
        formationRepository.save(formation);
        return "redirect:/formations";
    }

    // Affiche le formulaire de mise à jour pour une formation
    @GetMapping("/formations/edit/{id}")
    public String showUpdateFormationForm(@PathVariable("id") Long id, Model model) {
        Formation formation = formationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Formation introuvable avec l'ID : " + id));
        model.addAttribute("formation", formation);
        return "update-formation";
    }

    // Met à jour une formation
    @PostMapping("/formations/update/{id}")
    public String updateFormation(@PathVariable("id") Long id, @Valid Formation formation, BindingResult result) {
        if (result.hasErrors()) {
            return "update-formation";
        }
        formation.setId(id);
        formationRepository.save(formation);
        return "redirect:/formations";
    }

    // Supprime une formation
    @GetMapping("/formations/delete/{id}")
    public String deleteFormation(@PathVariable("id") Long id) {
        Formation formation = formationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Formation introuvable avec l'ID : " + id));
        formationRepository.delete(formation);
        return "redirect:/formations";
    }

}
