package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.dao.AuteurDAO;
import com.example.entity.Auteur;
import com.example.service.AuteurService;


@DataJpaTest
@AutoConfigureTestEntityManager
class GestionBibliothequeApplicationTests {

    @Autowired
    private AuteurService auteurService;
    @Autowired
    private TestEntityManager entityManager;
    
    /*@MockBean
    private AuteurDAO repo;
    
    
    @Test
    public void getAuteursTest ()
    {
        Auteur tut1 = new Auteur();
        tut1.setNom("tut1");
        entityManager.persist(tut1);

        Auteur tut2 = new Auteur();
        tut2.setNom("tut2");
        entityManager.persist(tut2);

        Iterable auteurs = repo.getPaginatedAuteurs(null);

        assertThat(auteurs).hasSize(3).contains(tut1, tut2);
    }
    
    @BeforeEach
    void initUseCase() {
      var registerUseCase = new GestionBibliothequeApplicationTests(repo);
    }

    @Test
    void savedUserHasRegistrationDate() {
      User user = new User("zaphod", "zaphod@mail.com");
      User savedUser = registerUseCase.registerUser(user);
      assertThat(savedUser.getRegistrationDate()).isNotNull();
    }*/

}
