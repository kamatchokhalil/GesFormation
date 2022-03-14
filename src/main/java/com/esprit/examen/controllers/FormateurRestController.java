package com.esprit.examen.controllers;

import com.esprit.examen.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.esprit.examen.services.IFormateurService;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;

@RestController
public class FormateurRestController {

    @Autowired
    IFormateurService formateurService;

    @RequestMapping(value = "/docker")
    @ResponseBody
    public String accueilFormateur() {
        //ceci est un test for testaaaaa
       return "Welcome docker from formateur";
    }

    @PostMapping(value = "/ajouterFormateur")
    @ResponseBody
    public ResponseEntity ajouterFormateur(@Valid @RequestBody Formateur formateur) throws Exception{

        if (checkExistingContrat(formateur.getContrat()) == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Champs contrat invalide");
        }

        if (checkExistingPost(formateur.getPoste()) == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Champs poste invalide");
        }

        formateur.setPoste(formateur.getPoste().toUpperCase());
        formateur.setContrat(formateur.getContrat().toUpperCase());
        formateurService.addFormateur(formateur);
        return ResponseEntity.ok().body(formateur);
    }

    public Boolean checkExistingPost(String post) {

        for (int i=0; i< Poste.values().length;i++){


            if (Poste.values()[i].toString().equals(post.toUpperCase())){
                return true;
            }
        }

        return false;

    }
    public Boolean checkExistingContrat(String contrat) {

		for (int i=0; i< Contrat.values().length;i++){


			if (Contrat.values()[i].toString().equals(contrat.toUpperCase())){
				return true;
			}
		}

		return false;


	/*	for( Contrat enumContrat: Contrat.values()) {
			if (!enumContrat.equals(contrat.toUpperCase())){
				return
			}
		}*/

    }

    @PutMapping("/modifierFormateur")
    @ResponseBody
    public Formateur modifierFormateur(@RequestBody Formateur formateur) {
        formateurService.addFormateur(formateur);
        return formateur;
    }

    @DeleteMapping("/supprimerFormateur/{formateurId}")
    @ResponseBody
    public void supprimerFormateur(@PathVariable("formateurId") Long formateurId) {
        formateurService.supprimerFormateur(formateurId);
    }

//	@GetMapping("/nombreFormateursImpliquesDansUnCours/{typeCours}")
//	@ResponseBody
//	public Long nombreFormateursImpliquesDansUnCours(@PathVariable("typeCours") TypeCours typeCours) {
//		Long nombreFormateurs=formateurService.nombreFormateursImpliquesDansUnCours(typeCours);
//		return nombreFormateurs;
//	}
}
