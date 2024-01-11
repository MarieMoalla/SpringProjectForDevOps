package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.controller.AuteurController;
import com.example.dao.AuteurDAO;
import com.example.entity.Auteur;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc 
class AuteurServiceTest {

    @Autowired
    private AuteurService _service;
    @Autowired
    private AuteurController _controller;
    @Autowired 
    private MockMvc mockMvc;

    @MockBean
    private AuteurDAO _repo;

    
    
    
    //connect to mysql container
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        // Replace these values with the actual connection details of your existing MySQL container
        String jdbcUrl = "jdbc:mysql://localhost:3307/gestionbibliodb";
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String username = "root";
        String password = "Mariem1999*";

        registry.add("spring.datasource.url", () -> jdbcUrl);
        registry.add("spring.datasource.driverClassName", () -> driverClassName);
        registry.add("spring.datasource.username", () -> username);
        registry.add("spring.datasource.password", () -> password);
        registry.add("spring.flyway.enabled", () -> "true");
    }

    @BeforeEach
    void setup() {
    	//for unit test
        Auteur auteur = new Auteur(1111, "name");
        Mockito.when(_repo.getAuteur((long) 1111)).thenReturn(auteur);

        // for integration test
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(_controller)
                .build();
    }

    // Unit test : Start
    @Test
    void test_get_auteur_by_id() {
        String auteur_name = "name";
        Auteur auteurById = _service.getAuteur((long) 1111);
        org.junit.jupiter.api.Assertions.assertEquals(auteur_name, auteurById.getNom());
    }
    // Unit test : End
    
    // Integration test : Start
    @Test
    void test_add_new_auteur() throws Exception {
        Auteur auteur = new Auteur(1111, "auteur");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/auteurs/createAuteur")
                .contentType("application/json")
                .content(asJsonString(auteur))
                .accept("application/json"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
    }

    // Integration test
    @Test
    void test_get_all_auteur() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/auteurs")
                .accept("application/json")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }
    // Integration test : End


    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
