package com.esprit.examen.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.esprit.examen.entities.Cours;
import com.esprit.examen.services.ICoursService;

class CoursRestControllerTest {

	@Autowired
	ICoursService coursServices;

	@Test
	void testAjouterCours() {
		Cours cours = new Cours(null, "description", "INFORMATIQUE", "intitle"); 
		System.out.print(cours);
		coursServices.addCours(cours);
		Long id = cours.getId();
		System.out.print(id);
		Assert.assertNotNull(id);
		System.out.println("id : "+ id);
		Cours savedCours = coursServices.getCoursByID(id);
		try {
	        Assert.assertEquals("description", savedCours.getDescription());
	        Assert.assertEquals("INFORMATIQUE", savedCours.getTypeCours());
	        Assert.assertEquals("intitle", savedCours.getIntitule());
	     
		}
		finally {
	        System.out.println("Delete last insert .....");
	        coursServices.supprimerCours(id);
	    }
	}

	@Test
	void testModifierCours() {
		fail("Not yet implemented");
	}

	@Test
	void testSupprimerCours() {
		fail("Not yet implemented");
	}

	@Test
	void testListeCours() {
		fail("Not yet implemented");
	}

	@Test
	void testAffecterFormateurASession() {
		fail("Not yet implemented");
	}

}
