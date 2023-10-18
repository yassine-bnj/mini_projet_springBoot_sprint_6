package com.example.livres.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.livres.dto.LivreDTO;
import com.example.livres.entities.Livre;



@Service
public interface LivreService {

	LivreDTO saveLivre(LivreDTO l);
	LivreDTO updateLivre(LivreDTO l);
	void deleteLivre(Livre l);
	void deleteLivreById(Long id);
	LivreDTO getLivre(Long id);
	List<LivreDTO> getAllLivres();
	List<LivreDTO> getLivresByTypeID(Long id);
	List<LivreDTO> getLivresByTitre(String titre);
	List<LivreDTO> getLivresByTitreContains(String titre);
	LivreDTO convertEntityToDto(Livre livre);
	Livre convertDtoToEntity(LivreDTO livreDto);
}
