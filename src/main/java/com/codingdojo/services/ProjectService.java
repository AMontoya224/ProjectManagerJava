package com.codingdojo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.models.Project;
import com.codingdojo.repositories.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project insertIntoProjects( Project newProject ) {
		return projectRepository.save( newProject );
	}
	
	public List<Project> selectAllFromProjects(){
		List<Project> projectsList = projectRepository.findAll();
		if ( projectsList.isEmpty() ) {
			Project projectEmpty = new Project();
			projectsList.add(projectEmpty);
		}
		return projectsList;
	}
	
	public Project selectFromProjectsWhereId( Long id ) {
		List<Project> foundProject = projectRepository.findByid( id );
		if ( foundProject.isEmpty() ) {
			return null;
		}
		else {
			return foundProject.get( 0 );
		}
	}
	
	public void updateProjects( Project newProject ) {
		projectRepository.save(newProject);
	}
	
	public void deleteFromProjects( Long id ) {
		projectRepository.deleteById( id );
	}
}
