<%@ page language="java" contentType="text/html; charset=ISO-8859-7"
    pageEncoding="ISO-8859-7"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-7">
<meta charset="UTF-8">
<title>University Grading Management System - Students' menu</title>
<meta name="description" content="University Grades App">
<script src="./javascript/error.js" type="text/javascript"></script>
<link rel="stylesheet" href="Styles.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/table.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body, html {
	height: 100%;
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}
</style>
</head>
<body>
<div class="topnav">
		<a class="active" href="student.jsp">HOME</a> 
		<a href="student?action=viewStudentGrades">Grades</a>
		<a href="student?action=viewStudentDetails"	>profile</a> 
		<a style="float: right;" href="LogoutServlet"> LOGOUT</a>
	</div>
	<br>
	<div class="hero-image">
		<div class="hero-text">
			<h1 style="font-size: 70px">
				<strong>Unipi</strong>
			</h1>
			<h1>
				<strong>Welcome student</strong>
			</h1>
		</div>
	</div>
	<%if(session.getAttribute("username")==null)
	{
		response.sendRedirect("index.html");
	}
%>

   

	<%if (request.getAttribute("action")=="viewStudentDetails") {%>

	<table id="tabl"
		style="border: 1px solid black; margin-top: 200px; margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<th>Name</th>
				<th>Surname</th>
				<th>Username</th>
			</tr>
		</thead>
		<tbody>
			<tr>
			
				<td data-column="Name"><%=session.getAttribute("name")%></td>
				<td data-column="Surname"><%=session.getAttribute("surname")%></td>
				<td data-column="Username"><%=session.getAttribute("username")%></td>
			</tr>
		</tbody>
	</table>
	<% if (request.getAttribute("action")=="viewStudentGrades")%>
		<table id="tabl"
		style="border: 1px solid black; margin-top: 200px; margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
			
				<th>grade</th>
				<th>Course</th>
			</tr>
		</thead>
		<tbody>
			<tr>
			
				<td data-column="grade"><%=session.getAttribute("grade")%></td>
				<td data-column="course"><%=session.getAttribute("course")%></td>
				
			</tr>
		</tbody>
	</table>
	<%} %>
</body>
</html>