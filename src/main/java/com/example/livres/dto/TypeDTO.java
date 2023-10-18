package com.example.livres.dto;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeDTO {


	private Long idType;
	private String nomType;
	private String descriptionType;
	
	private List<LivreDTO> livres;
	}
