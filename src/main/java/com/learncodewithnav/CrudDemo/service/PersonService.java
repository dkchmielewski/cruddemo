package com.learncodewithnav.CrudDemo.service;

import java.util.List;

import com.learncodewithnav.CrudDemo.dto.request.PersonDTO;
import com.learncodewithnav.CrudDemo.entity.Person;

public interface PersonService {

	boolean savePerson(PersonDTO personDTO);
	
	List<Person> getAll();
	
	Person fetchById(long id);
	
	boolean updatePerson(PersonDTO personDTO);
	
	void deletePerson(long id);
	
}
