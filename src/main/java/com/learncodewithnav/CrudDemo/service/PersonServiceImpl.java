package com.learncodewithnav.CrudDemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learncodewithnav.CrudDemo.dto.request.PersonDTO;
import com.learncodewithnav.CrudDemo.entity.Person;
import com.learncodewithnav.CrudDemo.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository repository;

	@Override
	public boolean savePerson(PersonDTO personDTO) {
		Person person = initializePerson(personDTO);
		Person saved = repository.save(person);
		if (saved != null)
			return true;
		return false;
	}

	@Override
	public List<Person> getAll() {
		return repository.findAll();
	}

	@Override
	public Person fetchById(long id) {
		Optional<Person> findById = repository.findById(id);
		if (findById.isPresent())
			return findById.get();
		return null;
	}
	
	@Override
	public boolean updatePerson(PersonDTO personDTO) {
		long id = personDTO.getId();
		Optional<Person> findById = repository.findById(id);
		if(findById.isPresent()) {
			Person person = findById.get();
			person.setName(personDTO.getName());
			person.setMobile(personDTO.getMobile());
			person.setDob(personDTO.getDob());
			
			repository.save(person);
			return true;
		}
		return false;
	}
	
	@Override 
	public void deletePerson(long id) {
		Optional<Person> findById = repository.findById(id);
		if(findById.isPresent()) {
			Person person = findById.get();
			repository.delete(person);
		}
	}

	private Person initializePerson(PersonDTO personDTO) {
		Person person = new Person();
		person.setName(personDTO.getName());
		person.setMobile(personDTO.getMobile());
		person.setDob(personDTO.getDob());
		return person;
	}

}
