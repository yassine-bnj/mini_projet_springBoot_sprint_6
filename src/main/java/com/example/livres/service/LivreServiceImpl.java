package com.example.livres.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.livres.dto.LivreDTO;
import com.example.livres.entities.Livre;
import com.example.livres.repos.LivreRepository;


@Service
public class LivreServiceImpl implements LivreService{
    
	@Autowired
	 LivreRepository livreRepository;
	@Autowired
	ModelMapper modelMapper;

    
    @Override
	public LivreDTO saveLivre(LivreDTO l) {
    	return this.convertEntityToDto(livreRepository.save(this.convertDtoToEntity(l)))   ;
		
	}

	@Override
	public LivreDTO updateLivre(LivreDTO l) {
		return this.convertEntityToDto(livreRepository.save(this.convertDtoToEntity(l)));
	}

	@Override
	public void deleteLivre(Livre l) {
		this.livreRepository.delete(l);
		
	}
	@Override
	public void deleteLivreById(Long id) {
		this.livreRepository.deleteById(id);;
		
	}

	@Override
	public LivreDTO getLivre(Long id) {
		return this.convertEntityToDto(this.livreRepository.findById(id).get());
		}

	@Override
	public List<LivreDTO> getAllLivres() {
	
		
		List<LivreDTO> livres=new ArrayList<>();
		for(Livre l: this.livreRepository.findAll()) {
			livres.add(this.convertEntityToDto(l));
		}
		
		return livres;
	}

	
	@Override
	public List<LivreDTO> getLivresByTypeID(Long id) {
		
		
		List<LivreDTO> livres=new ArrayList<>();
		for(Livre l: this.livreRepository.findByTypeIdType(id)) {
			livres.add(this.convertEntityToDto(l));
		}
		
		return livres;
	}
	
	@Override
	public List<LivreDTO> getLivresByTitre(String titre) {
		List<LivreDTO> livres=new ArrayList<>();
		for(Livre l:this.livreRepository.findByTitre(titre)) {
			livres.add(this.convertEntityToDto(l));
		}
		
		return livres;
	}
	@Override
	public List<LivreDTO> getLivresByTitreContains(String titre) {
		
		List<LivreDTO> livres=new ArrayList<>();
		for(Livre l:this.livreRepository.findByTitreContains(titre)) {
			livres.add(this.convertEntityToDto(l));
		}
		
		return livres;
	}
	@Override
	public LivreDTO convertEntityToDto(Livre l) {
		
	/*	LivreDTO livreDTO= new LivreDTO();
		
		livreDTO.setAuteur(l.getAuteur());
		livreDTO.setDateParution(l.getDateParution());
		livreDTO.setIdLivre(l.getIdLivre());
		livreDTO.setNbPages(l.getNbPages());
		livreDTO.setTitre(l.getTitre());
		livreDTO.setType(l.getType());
		
		return livreDTO;*/
		
		/*return LivreDTO.builder()
				.idLivre(l.getIdLivre())
				.titre(l.getTitre())
				.auteur(l.getAuteur())
				.dateParution(l.getDateParution())
				.nbPages(l.getNbPages())
			//	.type(l.getType())
				.nomType(l.getType().getNomType())
				.build();*/
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		LivreDTO livreDTO = modelMapper.map(l, LivreDTO.class);
		 return livreDTO; 
		
	}

	@Override
	public Livre convertDtoToEntity(LivreDTO livreDto) {
		/*Livre livre=new Livre();
		  livre.setAuteur(livreDto.getAuteur());
		  livre.setDateParution(livreDto.getDateParution());
		  livre.setIdLivre(livreDto.getIdLivre());
		  livre.setNbPages(livreDto.getNbPages());
		//  livre.setType(livreDto.getType());
		  livre.setAuteur(livreDto.getAuteur());

		
		
		return livre;*/
		Livre livre=modelMapper.map(livreDto, Livre.class);
		return livre;
	}

}
