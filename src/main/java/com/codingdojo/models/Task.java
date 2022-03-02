package com.codingdojo.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table( name= "tasks" )
public class Task {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@Size(min = 3, message="La tarea debe tener mas de 3 caracteres.")
    private String task;
	
	private Date createdAt;
    private Date updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userTask_id")
    private User userTask;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="projectTask_id")
    private Project projectTask;
    
    public Task() {
    	
	}
    
	public Task( String task, Date createdAt, Date updatedAt, User userTask, Project projectTask) {
		this.task = task;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userTask = userTask;
		this.projectTask = projectTask;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUserTask() {
		return userTask;
	}

	public void setUserTask(User userTask) {
		this.userTask = userTask;
	}

	public Project getProjectTask() {
		return projectTask;
	}

	public void setProjectTask(Project projectTask) {
		this.projectTask = projectTask;
	}
    
}
