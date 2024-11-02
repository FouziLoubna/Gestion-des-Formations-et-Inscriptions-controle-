package com.example.gestionFormationsInscription.controller;

import com.example.gestionFormationsInscription.Repository.FormationRepository;
import com.example.gestionFormationsInscription.Repository.InscriptionRepository;
import com.example.gestionFormationsInscription.entities.Formation;
import com.example.gestionFormationsInscription.entities.Inscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/inscriptions")
public class InscriptionController {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private FormationRepository formationRepository;
    private static final Logger logger = LoggerFactory.getLogger(InscriptionController.class);
    @GetMapping("/new")
    public String showAddInscriptionForm(Model model) {
        Inscription inscription = new Inscription();
        inscription.setFormation(new Formation());
        model.addAttribute("inscription", inscription);
        model.addAttribute("formations", formationRepository.findAll());
        return "add-inscription";
    }

    @GetMapping("/{formationId}")
    public String listInscriptions(@PathVariable("formationId") Long formationId, Model model) {
        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new IllegalArgumentException("Formation introuvable avec l'ID : " + formationId));

        List<Inscription> inscriptions = inscriptionRepository.findAll().stream()
                .filter(inscription -> inscription.getFormation().getId().equals(formationId))
                .collect(Collectors.toList());

        logger.info("Formation: " + formation);
        logger.info("Inscriptions: " + inscriptions);

        model.addAttribute("formation", formation);
        model.addAttribute("inscriptions", inscriptions);
        return "inscriptions";
    }

      // Vue du formulaire d'ajout d'inscription



    // Affiche le formulaire pour ajouter une nouvelle inscription avec formationId
    @GetMapping("/new/{formationId}")
    public String showAddInscriptionForm(@PathVariable("formationId") Long formationId, Model model) {
        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new IllegalArgumentException("Formation introuvable avec l'ID : " + formationId));

        Inscription inscription = new Inscription();
        inscription.setFormation(formation);

        model.addAttribute("inscription", inscription);
        model.addAttribute("formations", formationRepository.findAll());
        return "add-inscription";

    }

    // Enregistre une nouvelle inscription dans la base de données
    @PostMapping("/add")
    public String addInscription(@Valid @ModelAttribute("inscription") Inscription inscription,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("formations", formationRepository.findAll());
            return "add-inscription";
        }

        // Vérifiez si l'ID de formation est bien présent
        if (inscription.getFormation() == null || inscription.getFormation().getId() == null) {
            result.rejectValue("formation", "error.formation", "Une formation doit être sélectionnée.");
            model.addAttribute("formations", formationRepository.findAll());
            return "add-inscription";
        }

        Formation formation = formationRepository.findById(inscription.getFormation().getId())
                .orElseThrow(() -> new IllegalArgumentException("Formation introuvable avec l'ID : " + inscription.getFormation().getId()));
        inscription.setFormation(formation);

        inscriptionRepository.save(inscription);
        return "redirect:/inscriptions/" + formation.getId();
    }


    // Enregistre une nouvelle inscription dans la base de données
    @PostMapping("/add/{formationId}")
    public String addInscriptionId(@PathVariable Long formationId,
                                 @Valid @ModelAttribute("inscription") Inscription inscription,
                                 BindingResult result, Model model) {
        if (formationId == null) {
            throw new IllegalArgumentException("Formation ID must not be null");
        }

        if (result.hasErrors()) {
            model.addAttribute("formations", formationRepository.findAll());
            return "add-inscription";
        }

        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new IllegalArgumentException("Formation introuvable avec l'ID : " + formationId));
        inscription.setFormation(formation);

        inscriptionRepository.save(inscription);
        return "redirect:/inscriptions/" + formationId;
    }

    // Affiche le formulaire de mise à jour pour une inscription
    @GetMapping("/edit/{id}")
    public String showUpdateInscriptionForm(@PathVariable("id") Long id, Model model) {
        Inscription inscription = inscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inscription introuvable avec l'ID : " + id));

        model.addAttribute("inscription", inscription);
        model.addAttribute("formations", formationRepository.findAll());
        return "update-inscription";
    }

    // Met à jour une inscription
    @PostMapping("/update/{id}")
    public String updateInscription(@PathVariable("id") Long id,
                                    @Valid @ModelAttribute("inscription") Inscription inscription,
                                    BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("formations", formationRepository.findAll());
            return "update-inscription";
        }

        inscriptionRepository.save(inscription);
        return "redirect:/inscriptions/" + inscription.getFormation().getId();
    }

    // Supprime une inscription
    @GetMapping("/delete/{id}")
    public String deleteInscription(@PathVariable("id") Long id) {
        Inscription inscription = inscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inscription introuvable avec l'ID : " + id));

        Long formationId = inscription.getFormation().getId();
        inscriptionRepository.delete(inscription);
        return "redirect:/inscriptions/" + formationId;
    }
}
