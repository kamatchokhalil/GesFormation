package com.esprit.examen;

import com.esprit.examen.config.LoggingAspect;
import com.esprit.examen.entities.Formateur;
import com.esprit.examen.services.IFormateurService;
import jdk.net.SocketFlow;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// les deux importation pour la méthode get au niveau de perform




class FormateurRestControllerTest extends GesFApplicationTests{

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    IFormateurService formateurService;

    private MockMvc mockMvc;

    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Before
    public void setup(){
        //semulation d'un client de test avec une instanciation de context ou bien attribuer le context
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testAjouterFormateur(){

        Formateur formateur = new Formateur(null,"Oueslati","Ahmed khalil","INGÉNIEUR","FREELANCE","ahmed@gmail.com","pass123",true);
        formateurService.addFormateur(formateur);
        Long id = formateur.getId();
        Assert.assertNotNull(id);
        System.out.println("id : "+ id);

        Formateur savedFormateur = formateurService.getFormateurByID(id);
        try {
            Assert.assertEquals("Oueslati", savedFormateur.getNom());
            Assert.assertEquals("Ahmed khalil", savedFormateur.getPrenom());
            Assert.assertEquals("INGÉNIEUR", savedFormateur.getPoste());
            Assert.assertEquals("FREELANCE", savedFormateur.getContrat());
            Assert.assertEquals("ahmed@gmail.com", savedFormateur.getEmail());
            Assert.assertEquals("pass123", savedFormateur.getPassword());
            Assert.assertEquals(true, savedFormateur.getAdmin());
        }finally {
            System.out.println("Delete last insert .....");
            formateurService.supprimerFormateur(id);
        }

    }


    @Test
    @Transactional
    void testModifierFormateur() {
        Formateur formateur = new Formateur(null,"Oueslati","Ahmed khalil","INGÉNIEUR","FREELANCE","ahmed@gmail.com","pass123",true);
        formateurService.addFormateur(formateur);
        Long id = formateur.getId();
        Assert.assertNotNull(id);
        System.out.println("id => "+ id);
        Formateur savedFormateur = formateurService.getFormateurByID(id);
        System.out.println(" get added fid => "+ id);
        savedFormateur.setId(id);
        savedFormateur.setNom("test");
        savedFormateur.setPrenom("test");
        formateurService.modifierFormateur(savedFormateur);
        System.out.println("updated => ");
        Formateur updatedForm = formateurService.getFormateurByID(id);

        try {
            Assert.assertEquals("test", updatedForm.getNom());
            Assert.assertEquals("test", updatedForm.getPrenom());

        }finally {
            System.out.println("Delete last insert .....");
            formateurService.supprimerFormateur(id);
        }



    }
    @Test
    void testSgetAllFormateur() {
        try {
            mockMvc.perform(get("/formateur"))
                    .andExpect(status().isOk())
                    .andExpect((ResultMatcher) content().contentType("application/json;charest=UTF8"))
                    .andExpect(jsonPath("$.contrat").value("FREELANCE"))
                    .andExpect(jsonPath("$.nom").value("dev"))
                    .andExpect(jsonPath("$.prenon").value("dev"))
                    .andExpect(jsonPath("$.poste").value("INGÉNIEUR"))
                    .andExpect(jsonPath("$.email").value("dev@gmail.com"))
                    .andExpect(jsonPath("$.password").value("dev"))
                    .andExpect(jsonPath("$.admin").value("2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void testSupprimerFormateur() {
    }

}
