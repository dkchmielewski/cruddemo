package com.learncodewithnav.CrudDemo;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.learncodewithnav.CrudDemo.entity.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CrudDemoApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testParameters() throws Exception {
		// arrange
		// act
		ResponseEntity<Person> response = restTemplate.getForEntity("/persons?id=1", Person.class);
		// assert
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getName()).isEqualTo("John Doe");
		assertThat(response.getBody().getId()).isEqualTo(1);
		assertThat(response.getBody().getMobile()).isEqualTo("999999999");
	}

}
