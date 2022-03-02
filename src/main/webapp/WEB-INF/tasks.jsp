<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>
			Props Page
		</title>
		<link rel="stylesheet" type="text/css" href="/css/style2.css">
	</head>
	<body>
		<header>
			<div>
				<h1>
					Project: <c:out value="${project.getTitle()}"/>
				</h1>
				<h3>
					Project Lead: <c:out value="${project.getUser().getFirstName()}"/>
				</h3>
			</div>
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
						Add a task ticket for this team:
					</h3>
					<form:form method="POST" action="/projects/${project.getId()}/tasks" modelAttribute="newTask">
						<div class="justify">
							<form:label path="task" for="task" class="inp">
								<form:textarea path="task" class="inp-input inp-textarea" name="task" id="task" placeholder=" " />
						    	<span class="inp-label inp-label-textarea">Task New</span>
						  		<span class="inp-focus"></span>
						  		<form:errors path="task" class="error"/>
							</form:label>
						</div>
						<button type="submit" class="submit" >
							Submit
						</button>
					</form:form>
				</div>
				<div>
					<c:forEach var="task" items="${tasksList}">
						<h3>
							Added by : <c:out value="${task.getUserTask().getFirstName()}"/> at <c:out value="${task.getCreatedAt()}"/>:
						</h3>
						<div class="container-2">
							<p>
								<c:out value="${task.getTask()}"/>
							</p>
						</div>
					</c:forEach>
				</div>
			</div>
		</main>
	</body>
</html>