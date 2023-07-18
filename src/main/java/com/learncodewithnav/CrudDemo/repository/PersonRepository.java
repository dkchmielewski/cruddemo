package com.learncodewithnav.CrudDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learncodewithnav.CrudDemo.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
