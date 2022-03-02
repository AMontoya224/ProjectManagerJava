package com.codingdojo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.models.Task;
import com.codingdojo.repositories.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	
	public Task insertIntoTasks( Task newTask ) {
		return taskRepository.save( newTask );
	}
	
	public List<Task> selectFromTasksWhereId( Long id ){
		List<Task> tasksList = taskRepository.findByid( id );
		if ( tasksList.isEmpty() ) {
			Task taskEmpty = new Task();
			tasksList.add(taskEmpty);
		}
		return tasksList;
	}
	
	public List<Task> selectAllFromTasksWhereId( Long id ){
		List<Task> tasksList = taskRepository.findAllById( id );
		if ( tasksList.isEmpty() ) {
			Task taskEmpty = new Task();
			tasksList.add(taskEmpty);
		}
		return tasksList;
	}
}
