<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>
			Edit my Task
		</title>
		<link rel="stylesheet" type="text/css" href="/css/style2.css">
	</head>
	<body>
		<header>
			<h1>
				Edit Project
			</h1>
		</header>
		<main>
			<div class="container">
				<form:form method="POST" action="/projects/${project.getId()}/edit" modelAttribute="newProject">
					<input type="hidden" name="_method" value="put">
					<div class="justify">
						<form:label path="title" for="title" class="inp">
							<form:input path="title" type="text" class="inp-input" name="title" id="title" placeholder=" " value="${project.getTitle()}" />
					    	<span class="inp-label">Project Title</span>
					  		<span class="inp-focus"></span>
					  		<form:errors path="title" class="error"/>
						</form:label>
					</div>
					<div class="justify">
						<form:label path="description" for="description" class="inp">
							<form:textarea path="description" class="inp-input inp-textarea" name="description" id="description" placeholder=" " value="${project.getDescription()}" />
					    	<span class="inp-label inp-label-textarea">Project Description</span>
					  		<span class="inp-focus"></span>
					  		<form:errors path="description" class="error"/>
						</form:label>
					</div>
					<div class="justify">
						<form:label path="due" for="due" class="inp">
							<form:input path="due" type="date" class="inp-input" name="due" id="due" placeholder=" " value="${project.getDue()}" />
					    	<span class="inp-label">Due Date</span>
					  		<span class="inp-focus"></span>
					  		<p class="error">
					  			<c:out value="${dueError}"/>
					  		</p>
						</form:label>
					</div>
					<button type="submit" class="submit" >
						Submit
					</button>
				</form:form>
				<form method="GET" action="/projects/dashboard">
					<button type="submit" class="delete" >
						Cancel
					</button>
				</form>
			</div>
		</main>
	</body>
</html>