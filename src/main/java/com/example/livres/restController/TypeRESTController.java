package com.example.livres.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.livres.dto.TypeDTO;
import com.example.livres.service.TypeService;



@RestController
@RequestMapping("/api/types")
@CrossOrigin
public class TypeRESTController {
	
	@Autowired
    TypeService typeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<TypeDTO> getAllTypes() {
	return typeService.getAllTypes();
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public TypeDTO getTypeById(@PathVariable("id") Long id) {
	return typeService.getType(id);
	 }
	
	@RequestMapping(method = RequestMethod.POST)
	public TypeDTO createType(@RequestBody TypeDTO type) {
	return typeService.saveType(type);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public TypeDTO updateType(@RequestBody TypeDTO type) {
	return typeService.updateType(type);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public void deleteType(@PathVariable("id") Long id)
	{
		typeService.deleteTypeById(id);
	}

}
