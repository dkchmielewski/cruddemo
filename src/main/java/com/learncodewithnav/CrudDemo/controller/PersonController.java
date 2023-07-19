package com.learncodewithnav.CrudDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.learncodewithnav.CrudDemo.dto.request.PersonDTO;
import com.learncodewithnav.CrudDemo.dto.response.CommonResponse;
import com.learncodewithnav.CrudDemo.entity.Person;
import com.learncodewithnav.CrudDemo.exception.PersonNotFoundException;
import com.learncodewithnav.CrudDemo.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonService service;
	
	@PostMapping("/")
	public ResponseEntity<CommonResponse> addPerson(@RequestBody PersonDTO personDTO) {
		if(service.savePerson(personDTO))
			return ResponseEntity.status(HttpStatus.OK).body(new CommonResponse("200", "Person saved successfully."));
		return ResponseEntity.status(HttpStatus.OK).body(new CommonResponse("200", "Unable to save person."));
	}
	
	@GetMapping("/")
	public List<Person> fetchAll() {
		return service.getAll(); 
	}
	
	@GetMapping
	public Person fetchPersonDetails(@RequestParam(name = "id") long id) {
		return service.fetchById(id);
	}
	
	@PutMapping
	public ResponseEntity<CommonResponse> updatePersonDetails(@RequestBody PersonDTO personDTO) {
		if(service.updatePerson(personDTO))
			return ResponseEntity.status(HttpStatus.OK)
					.body(new CommonResponse("200", "Person details has been updated successfully."));
		return ResponseEntity.status(HttpStatus.OK).body(new CommonResponse("200", "Unable to update person."));
	}
	
	@DeleteMapping
	public void deletePerson(@RequestParam(name = "id") long id) {
		service.deletePerson(id);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private void personNotFoundHandler(PersonNotFoundException ex) {
		
	}
	
}
