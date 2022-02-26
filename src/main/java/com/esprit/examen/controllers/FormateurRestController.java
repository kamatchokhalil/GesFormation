package com.esprit.examen.controllers;

import com.esprit.examen.entities.Contrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.esprit.examen.entities.Cours;
import com.esprit.examen.entities.Formateur;
import com.esprit.examen.entities.TypeCours;
import com.esprit.examen.services.IFormateurService;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;

@RestController
public class FormateurRestController {

    @Autowired
    IFormateurService formateurService;

    @RequestMapping(value = "/ajouterFormateur")
    @ResponseBody
    public ResponseEntity ajouterFormateur(@Valid @RequestBody Formateur formateur) throws Exception{


        if (checkExistingContrat(formateur.getContrat()) == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("rien");
        }

		formateurService.addFormateur(formateur);
        return ResponseEntity.ok().body(formateur);
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
