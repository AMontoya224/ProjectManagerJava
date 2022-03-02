package com.codingdojo.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table( name= "users" )
public class User {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )  //auto incremental
	private long id;
	
	@NotNull
	@Size( min=3, max=20, message="El nombre debe tener al menos 3 caracteres" )
	@Pattern(regexp = "[a-zA-Z]+", message = "El nombre solo puede tener letras")
	private String firstName;
	
	@NotNull
	@Size( min=3, max=20, message="El apellido debe tener al menos 3 caracteres" )
	@Pattern(regexp = "[a-zA-Z]+", message = "El apellido solo puede tener letras")
	private String lastName;
	
	@NotNull
	@Size( min=5, max=20, message="El usuario debe tener al menos 5 caracteres" )
	private String userName;
	
	@NotNull
    private Date birthdate;
	
	@NotNull
	@Email(message="Ingrese un correo valido")
	private String email;
	
	@NotNull
	@Size( min=8, max=100, message="La contraseña debe tener al menos 8 caracteres" )
	private String password;
	
	@Transient
	private String confirm;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Project> projectList;
	
	@OneToMany(mappedBy="join", fetch = FetchType.LAZY)
    private List<Project> joinList;
	
	@OneToMany(mappedBy="userTask", fetch = FetchType.LAZY)
    private List<Task> taskList;
	
	public User() {
		
	}
	
	public User( String firstName, String lastName, String userName, Date birthdate, String email,  String password, String confirm ) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.birthdate = birthdate;
		this.email = email;
		this.password = password;
		this.confirm = confirm;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	public List<Project> getJoinList() {
		return joinList;
	}

	public void setJoinList(List<Project> joinList) {
		this.joinList = joinList;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	
}
