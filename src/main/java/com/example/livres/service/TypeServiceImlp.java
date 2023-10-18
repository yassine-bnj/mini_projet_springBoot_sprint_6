package com.example.livres.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livres.dto.TypeDTO;
import com.example.livres.entities.Type;
import com.example.livres.repos.TypeRepository;

@Service
public class TypeServiceImlp implements TypeService{
@Autowired
TypeRepository typeRepository;
@Autowired
ModelMapper modelMapper;



@Override
public TypeDTO saveType(TypeDTO t) {
	return this.convertEntityToDto(this.typeRepository.save(   convertDtoToEntity(t)));
}

@Override
public TypeDTO updateType(TypeDTO t) {
	return this.convertEntityToDto(this.typeRepository.save(   convertDtoToEntity(t)));
}

@Override
public void deleteType(Type t) {
	typeRepository.delete(t);
	
}
@Override
public void deleteTypeById(Long id) {
	typeRepository.deleteById(id);
	
}



@Override
public TypeDTO getType(Long id) {
	
	
	return this.convertEntityToDto(typeRepository.findById(id).get());
	
}

@Override
public List<TypeDTO> getAllTypes() {
	
	List<TypeDTO> types = new ArrayList<TypeDTO>();
	for(Type t : typeRepository.findAll() ) {
		types.add(this.convertEntityToDto(t));
	}
	return types;
			}



@Override
public TypeDTO convertEntityToDto(Type t) {
	
	
	modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	TypeDTO typeDTO = modelMapper.map(t, TypeDTO.class);
	 return typeDTO;
}

@Override
public Type convertDtoToEntity(TypeDTO typeDto) {
	Type type=modelMapper.map(typeDto, Type.class);
	return type;
}
	
	
}
