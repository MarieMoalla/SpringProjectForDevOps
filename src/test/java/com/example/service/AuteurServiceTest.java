package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.dao.AuteurDAO;
import com.example.entity.Auteur;
import com.google.common.base.Optional;

@SpringBootTest
class AuteurServiceTest {

	@Autowired
	private AuteurService _service;
	
	@MockBean
	private AuteurDAO _repo;
	
	@BeforeEach
	void setup()
	{
		Auteur auteur = new Auteur(1111,"name");
		Mockito.when(_repo.getAuteur((long)1111)).thenReturn(auteur);
	}
	
	@Test
	void test_get_auteur_by_id() {
		String auteur_name = "name";
		Auteur auteurbyid = _service.getAuteur((long)1111);
		org.junit.jupiter.api.Assertions.assertEquals(auteur_name, auteurbyid.getNom());
	}

}
