<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>
			Project Details
		</title>
		<link rel="stylesheet" type="text/css" href="/css/style2.css">
	</head>
	<body>
		<header>
			<h1>
				Project Details
			</h1>
			<form method="GET" action="/projects/dashboard">
				<button type="submit" class="link" >
					Back to Dashboard
				</button>
			</form>
		</header>
		<main>
			<div class="container">
				<div class="row space">
					<h3>
						Project:
					</h3>
					<h3>
						<c:out value="${project.getTitle()}"/>
					</h3>
				</div>
				<div class="row space">
					<h3>
						Description:
					</h3>
					<div class="container-2">
						<p>
							<c:out value="${project.getDescription()}"/>
						</p>
					</div>
				</div>
				
				<div class="row space">
					<h3>
						Due Date:
					</h3>
					<h3>
						<c:out value="${project.getDue()}"/>
					</h3>
				</div>
				<form method="GET" action="/projects/${project.getId()}/tasks">
					<button type="submit" class="link" >
						See tasks!
					</button>
				</form>
				<c:if test="${project.getUser().getEmail().equals(user)}">
				    <form method="POST" action="/projects/${project.getId()}/delete">
				    	<input type="hidden" name="_method" value="DELETE" />
						<button type="submit" class="delete" >
							Delete Project
						</button>
					</form>  
				</c:if>
			</div>
		</main>
	</body>
</html>