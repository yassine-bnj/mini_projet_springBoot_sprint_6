package com.example.livres.service;

import java.util.List;

import com.example.livres.dto.TypeDTO;
import com.example.livres.entities.Type;



public interface TypeService {
	TypeDTO saveType(TypeDTO t);
	TypeDTO updateType(TypeDTO t);
	void deleteType(Type t);
	void deleteTypeById(Long id);
	TypeDTO getType(Long id);
	List<TypeDTO> getAllTypes();
	
	TypeDTO convertEntityToDto(Type t);
	Type convertDtoToEntity(TypeDTO typeDto);
}
