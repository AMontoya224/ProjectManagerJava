package com.codingdojo.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.models.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
	@SuppressWarnings("unchecked")
	Project save( Project newProject );
	
	List<Project> findAll();
	
	List<Project> findByid( Long id );
	
	@Transactional
	void deleteById( Long id );
}
