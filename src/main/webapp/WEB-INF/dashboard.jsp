<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>
			Read Share
		</title>
		<link rel="stylesheet" type="text/css" href="/css/style2.css">
		<script type="text/javascript" src="js/app2.js"></script>
	</head>
	<body>
		<header>
			<div>
				<h1>
					Welcome, <c:out value="${firstName}"/>!
				</h1>
			</div>
			<div>
				<form method="GET" action="/logout">
					<button type="submit" class="link" >
						logout
					</button>
				</form>
				<form method="GET" action="/projects/new">
					<button type="submit" class="link" >
						+ Add a to my shelf!
					</button>
				</form>
			</div>
		</header>
		<main>
			<div>
				<h3>
					All Projects
				</h3>
				<table>
		        	<thead>
				        <tr>
				            <th>Project</th>
				            <th>Team Lead</th>
				            <th>Due Date</th>
				            <th>Action</th>
				        </tr>
				    </thead>
		        	<tbody>
						<c:forEach var="project" items="${projectsListNot}">
							<tr>
		                        <td>
		                        	<form method="GET" action="/projects/${project.getId()}">
										<button type="submit" class="link" >
											<c:out value="${project.getTitle()}"/>
										</button>
									</form>
		                        </td>
		                        <td><c:out value="${project.getUser().getFirstName()}"/></td>
		                        <td><c:out value="${project.getDue()}"/></td>
		                        <td>
		                        	<form method="POST" action="/projects/${project.getId()}/join">
										<button type="submit" class="link" >
											Join Team
										</button>
									</form>
		                        </td>
							</tr>
		            	</c:forEach>
					</tbody>
				</table>
			</div>
			<div>
				<h3>
					Your Projects
				</h3>
				<table>
		        	<thead>
				        <tr>
				            <th>Project</th>
				            <th>Lead</th>
				            <th>Due Date</th>
				            <th>Action</th>
				        </tr>
				    </thead>
		        	<tbody>
						<c:forEach var="project" items="${projectsListYes}">
							<tr>
		                        <td>
		                        	<form method="GET" action="/projects/${project.getId()}">
										<button type="submit" class="link" >
											<c:out value="${project.getTitle()}"/>
										</button>
									</form>
		                        </td>
		                        <td><c:out value="${project.getUser().getFirstName()}"/></td>
		                        <td><c:out value="${project.getDue()}"/></td>
		                        <td>
		                        	<c:if test="${project.getUser().getEmail().equals(user)}">
			                        	<div class="row">
				                        	<form method="GET" action="/projects/${project.getId()}/edit">
												<button type="submit" class="link" >
													edit
												</button>
											</form>
											<form method="POST" action="/projects/${project.getId()}/delete">
										    	<input type="hidden" name="_method" value="DELETE" />
												<button type="submit" class="link" >
													delete
												</button>
											</form> 
										</div>
									</c:if>
									<c:if test="${! project.getUser().getEmail().equals(user)}">
			                        	<form method="POST" action="/projects/${project.getId()}/leave">
											<button type="submit" class="link" >
												Leave Team
											</button>
										</form>
									</c:if>
		                        </td>
							</tr>
		            	</c:forEach>
					</tbody>
				</table>
			</div>
		</main>
		<footer>
			<p>
                Developed by <a href="https://github.com/AMontoya224" target="_blank">Andres Montoya</a> 
            </p>
		</footer>
	</body>
</html>