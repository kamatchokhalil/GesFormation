package com.esprit.examen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.examen.entities.Cours;
import com.esprit.examen.repositories.CoursRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Service
public class CoursService implements ICoursService {

	private static final Logger l = LogManager.getLogger(CoursService.class);
	
	@Autowired
	CoursRepository coursRepository;
	@Override
	public Long addCours(Cours cours) {
		l.info("In ajouterCours() : ");
		coursRepository.save(cours);
		return cours.getId();
	}

	@Override
	public Long modifierCours(Cours cours) {
		coursRepository.save(cours);
		return cours.getId();
		}

	@Override
	public void supprimerCours(Long coursId) {
		try {
			l.info("In supprimerCours() : ");
			l.debug("Je vais lancer la suppression.");
		coursRepository.deleteById(coursId);
		l.debug("Je viens de lancer la suppression");
		l.debug("Je viens de finir la suppression.");

		l.info("Out supprimerCours() without errors.");
		}
		catch (Exception e) { 
			l.error("Erreur dans supprimerCours() :  {e}");
			}
	}

	@Override
	public List<Cours> getCours() {
		
		return coursRepository.findAll();
	}
	
	@Override
	public void affecterCoursASession(Long coursId, Long sessionId)
	{
		/*todo*/
        
	}

	@Override
	public Cours getCoursByID(Long id) {
		return coursRepository.findById(id).orElse(null);
	}



}
