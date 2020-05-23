<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<title>Listado</title>

		<!-- Bootstrap core CSS -->
		<link href="css/bootstrap.min.css" rel="stylesheet" >
		<!-- Bootstrap theme -->
		<link href="css/bootstrap-theme.min.css" rel="stylesheet">
	</head>
<body>
	<table class="table table-hover text-center mt-4" border="1" cellpadding="1" cellspacing="0">
	<thead>
	<tr>
	<th>Nombre</th>
	<th>Apellido</th>
	<th>DNI</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="persona" items="${listaf}"> 
		<tr>
		<td>${persona.nombre}</td>
		<td>${persona.apellido}</td>
		<td>${persona.dni}</td>
		</tr>
    </c:forEach>  
	</tbody>
	</table>   	     
</body>
</html>