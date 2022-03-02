package com.codingdojo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.models.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
	@SuppressWarnings("unchecked")
	Task save( Task newTask );
	
	List<Task> findByid( Long id ); 	
	
	List<Task> findAllById( Long id );
}
