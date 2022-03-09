package com.esprit.examen.services;

import java.util.List;
import com.esprit.examen.entities.Formateur;
import com.esprit.examen.entities.TypeCours;

public interface IFormateurService {
	Long addFormateur(Formateur formateur);

	void modifierFormateur(Formateur formateur);

	Formateur getFormateurByID( long id);

	void supprimerFormateur(Long formateurId);
	
	//Long nombreFormateursImpliquesDansUnCours(TypeCours typeCours);
		
	List<Formateur> listFormateurs();
}
