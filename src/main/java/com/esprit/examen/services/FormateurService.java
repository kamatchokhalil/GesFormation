package com.esprit.examen.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import com.esprit.examen.entities.Formateur;
import com.esprit.examen.entities.TypeCours;
import com.esprit.examen.repositories.FormateurRepository;



@Service
public class FormateurService implements IFormateurService{

	@Autowired
	FormateurRepository formateurRepository;
	private static final Logger l = LogManager.getLogger(FormateurService.class);
	@Override
	public Long addFormateur(Formateur formateur) {

			l.info("invok add formateur method ");
			formateurRepository.save(formateur);
			return formateur.getId();

	}

	@Override
	public void modifierFormateur(Formateur formateur) {
		try{
			l.info("invok delete ");
			formateurRepository.save(formateur);
		}catch (Exception e){
			l.error("check this : " + e.getMessage());
		}

		//return formateur.getId();
	}

	@Override
	public Formateur getFormateurByID(long id) {
		return formateurRepository.findById(id).get();
	}

	@Override
	public void supprimerFormateur(Long formateurId) {
		formateurRepository.deleteById(formateurId);
		
	}

/*
	@Override
	public Long nombreFormateursImpliquesDansUnCours(TypeCours typeCours) {
		return formateurRepository.nombreFormateursImpliquesDansUnCours(typeCours);
		return null;
	}
*/



	@Override
	public List<Formateur> listFormateurs() {
		
		return formateurRepository.findAll();
	}


	
	

}
