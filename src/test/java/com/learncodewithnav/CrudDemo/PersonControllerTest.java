package com.learncodewithnav.CrudDemo;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.learncodewithnav.CrudDemo.controller.PersonController;
import com.learncodewithnav.CrudDemo.entity.Person;
import com.learncodewithnav.CrudDemo.exception.PersonNotFoundException;
import com.learncodewithnav.CrudDemo.service.PersonService;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonService service;
	
	@Test
	public void getPerson_ShouldReturnPerson() throws Exception {
		given(service.fetchById(3)).willReturn(new Person(3, "John Doe", "444", "unknown"));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/persons?id=3"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("name").value("John Doe"))
			.andExpect(jsonPath("mobile").value("444"));
	}
	
	@Test
	public void getPerson_notFound() throws Exception {
		given(service.fetchById(anyLong())).willThrow(new PersonNotFoundException());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/persons?id=1"))
			.andDo(print())
			.andExpect(status().isNotFound());
		
	}

}
