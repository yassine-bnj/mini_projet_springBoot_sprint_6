package com.example.livres;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.livres.dto.LivreDTO;
import com.example.livres.dto.TypeDTO;
import com.example.livres.entities.Livre;
import com.example.livres.entities.Type;
import com.example.livres.repos.LivreRepository;
import com.example.livres.repos.TypeRepository;
import com.example.livres.service.LivreService;
import com.example.livres.service.TypeService;

@SpringBootTest
class GestionLivresApplicationTests {
@Autowired
LivreRepository livreRepository;
@Autowired
LivreService livreService;
@Autowired
TypeService typeService;


@Autowired
TypeRepository typeRepository;
	@Test
	void contextLoads() {
	}
	
	
	@Test
	void addType() {
	
		TypeDTO t= new TypeDTO();
		t.setNomType("rrrrrr");
		t.setDescriptionType("kkkkkkkkkkkk");
		this.typeService.saveType(t);
	}
	
	
	
	@Test
	void findallLivres() {
		List<LivreDTO> livres = livreService.getAllLivres();
		for(LivreDTO l:livres ) {
			System.out.println(l.toString());
		}
	}
	
	
	
	@Test
	void findByTitreContains() {
		List<Livre> livres = livreRepository.findByTitre(" A quoi tient l'amour?");
		for(Livre l:livres ) {
			System.out.println(l.toString());
		}
	}
	@Test
	void findBynbPagesBetween() {
		List<Livre> livres = livreRepository.findBynbPagesBetween(310,320);
		for(Livre l:livres ) {
			System.out.println(l.toString());
		}
	}
	/*@Test
	void findByType() {
		TypeLivre t =typeRepository.getById(7L);
		List<Livre> livres = livreRepository.findByType(t);
		for(Livre l:livres ) {
			System.out.println(l.toString());
		}
	}*/
	@Test
	void findByTypeId() {
		
		List<Livre> livres = livreRepository.findByTypeIdType(7L);
		for(Livre l:livres ) {
			System.out.println(l.toString());
		}
	}

}
