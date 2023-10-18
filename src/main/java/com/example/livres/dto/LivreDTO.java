package com.example.livres.dto;

import java.util.Date;

import com.example.livres.entities.Image;
import com.example.livres.entities.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivreDTO {

	
	private Long idLivre;
	private String titre;
	private String auteur;
	private int nbPages;
	private Date dateParution;
	private Type type;
	private Image image;
	private String nomType;
}
