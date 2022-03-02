package com.codingdojo.controllers;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.models.Project;
import com.codingdojo.models.Task;
import com.codingdojo.models.User;
import com.codingdojo.services.ProjectService;
import com.codingdojo.services.TaskService;
import com.codingdojo.services.UserService;

@Controller
@RequestMapping( "/projects" )
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskService taskService;
	
	public long millis = System.currentTimeMillis();  
	public Date date = new Date(millis); 
	
	@GetMapping( "/dashboard" )
	public String dashboard_G( Model model, HttpSession session ) {
		if( session.getAttribute("email") != null ) {
			List<Project> projectsListNot = projectService.selectAllFromProjects();
			List<Project> projectsListYes = projectService.selectAllFromProjects();
			model.addAttribute( "firstName", session.getAttribute("firstName") );
			model.addAttribute( "user", session.getAttribute("email").toString() );
			model.addAttribute( "projectsListNot", projectsListNot );
			model.addAttribute( "projectsListYes", projectsListYes );
			return "dashboard.jsp";
		}
		else {
			return "redirect:/";
		}
	}
	
	@GetMapping( "/new" )
	public String new_G( Model model, HttpSession session ) {
		if( session.getAttribute("email") != null ) {
			model.addAttribute("newProject", new Project());
            return "new.jsp";
		}
		else {
			return "redirect:/";
		}
	}
	
	@PostMapping( "/new" )
	public String new_P( @Valid @ModelAttribute("newProject") Project newProject, 
			 			 BindingResult result, Model model, HttpSession session,
			 			 RedirectAttributes flash) {
		if( result.hasErrors() ) {
            return "new.jsp";
		}
	    if(newProject.getDue().before(date) ){
	    	flash.addFlashAttribute( "dueError", "El proyecto no debe ser pasado" );
			return "redirect:/projects/new";
	    }
		User foundUser = userService.selectFromUsersWhereEmail( session.getAttribute("email").toString() );
		newProject.setUser(foundUser);
		newProject.setCreatedAt(date);
		newProject.setUpdatedAt(date);
		projectService.insertIntoProjects( newProject );
		return "redirect:/projects/dashboard";
	}
	
	@GetMapping( "/{id}/edit" )
	public String edit_G( @PathVariable("id") Long id, Model model,
						  @ModelAttribute("newProject") Project newProject, 
						  HttpSession session ) {
		if( session.getAttribute("email") != null ) {
			Project foundProject = projectService.selectFromProjectsWhereId(id);
			if( foundProject == null ) {
				return "redirect:/projects/dashboard";
			}
			model.addAttribute( "project", foundProject );
            return "edit.jsp";
		}
		else {
			return "redirect:/";
		}
	}
	
	@PutMapping( "/{id}/edit" )
	public String edit_P( @PathVariable("id") Long id,
						  @Valid @ModelAttribute("newProject") Project newProject,
						  BindingResult result, HttpSession session,
						  RedirectAttributes flash ) {
		if( result.hasErrors() ) {
			return "edit.jsp";
		}
		if(newProject.getDue().before(date) ){
	    	flash.addFlashAttribute( "dueError", "El proyecto no debe ser pasado" );
	    	return String.format( "redirect:/projects/%s/edit", id );
	    }
		User foundUser = userService.selectFromUsersWhereEmail( session.getAttribute("email").toString() );
		newProject.setUser(foundUser);
		newProject.setUpdatedAt(date);
		projectService.updateProjects( newProject );
		return "redirect:/projects/dashboard";
	}
	
	@GetMapping( "/{id}" )
	public String show_G( @PathVariable("id") Long id, Model model, HttpSession session ) {
		if( session.getAttribute("email") != null ) {
			Project foundProject = projectService.selectFromProjectsWhereId(id);
			if( foundProject == null ) {
				return "redirect:/projects/dashboard";
			}
			model.addAttribute( "project", foundProject );
			model.addAttribute( "user", session.getAttribute("email").toString() );
            return "show.jsp";
		}
		else {
			return "redirect:/";
		}
	}
	
	@DeleteMapping( "/{id}/delete" )
	public String delete_D( @PathVariable("id") Long id ) {
		projectService.deleteFromProjects( id );
		return "redirect:/projects/dashboard";
	}
	
	@GetMapping( "/{id}/tasks" )
	public String tasks_G( @PathVariable("id") Long id, Model model, HttpSession session ) {
		if( session.getAttribute("email") != null ) {
			Project foundProject = projectService.selectFromProjectsWhereId(id);
			if( foundProject == null ) {
				return "redirect:/projects/dashboard";
			}
			List<Task> tasksList = taskService.selectAllFromTasksWhereId(id);
			model.addAttribute( "project", foundProject );
			model.addAttribute( "newTask", new Task() );
			model.addAttribute( "tasksList", tasksList );
            return "tasks.jsp";
		}
		else {
			return "redirect:/";
		}
	}
	
	@PostMapping( "/{id}/tasks" )
	public String tasks_P( @PathVariable("id") Long id,
						   @Valid @ModelAttribute("newTask") Task newTask,
						   BindingResult result, Model model, HttpSession session ) {
		if( result.hasErrors() ) {
			Project foundProject = projectService.selectFromProjectsWhereId(id);
			model.addAttribute( "project", foundProject );
            return "tasks.jsp";
		}
		User foundUser = userService.selectFromUsersWhereEmail( session.getAttribute("email").toString() );
		Project foundProject = projectService.selectFromProjectsWhereId(id);
		newTask.setUserTask(foundUser);
		newTask.setProjectTask(foundProject);
		newTask.setCreatedAt(date);
		newTask.setUpdatedAt(date);
		taskService.insertIntoTasks( newTask );
		return String.format( "redirect:/projects/%s/tasks", id );
	}
}
