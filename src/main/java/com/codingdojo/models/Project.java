package com.codingdojo.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table( name= "projects" )
public class Project {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@Size(min = 2, max = 40, message="El titulo debe tener entre 2 y 40 caracteres.")
    private String title;
	
	@NotNull
	@Size(min = 3, message="La descripcion debe tener mas de 3 caracteres.")
    private String description;
	
    private Date due;
    private Date createdAt;
    private Date updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="join_id")
    private User join;
    
    @OneToMany(mappedBy="projectTask", fetch = FetchType.LAZY)
    private List<Task> taskList;
    
    public Project() {
    	
	}
    
	public Project( Long id, String title, String description, Date due, Date createdAt, Date updatedAt, User user, User join ) {
		this.title = title;
		this.description = description;
		this.due = due;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
		this.join = join;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getDue() {
		return due;
	}

	public void setDue(Date due) {
		this.due = due;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getJoin() {
		return join;
	}

	public void setJoin(User join) {
		this.join = join;
	}
    
}
