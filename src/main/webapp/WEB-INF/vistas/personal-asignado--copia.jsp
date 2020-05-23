<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<title>Servicios prestados por cada personal</title>
<link href="css/gallo.css" rel="stylesheet" >
</head>
<body>
				<p>Personal asignado al evento</p>
				

	<c:forEach var = "i" begin = "1" end = "6">
	  			<c:forEach var="tipo" items="${cargos}">
					<c:if test="${tipo.id==i}">
						<td>Categoria: ${tipo.cargo}</td>
					</c:if>	
				</c:forEach>   
		<table border="1" cellpadding="1" cellspacing="0">
			<thead>
				<tr>
				<th>Nombre</th>
				<th>Apellido</th>
				</tr>
			</thead>
			<tbody>
	  			<c:forEach var="personal" items="${listadopersonalasignado}">
					<c:if test="${personal.categoriaPersonal.id==i}">
						<tr>
						<td>${personal.apellido}</td>
						<td>${personal.nombre}</td>
						</tr>
					</c:if>	
				</c:forEach>
			</tbody>
		</table>
		<br><br><br>
    </c:forEach>
	     
</body>
</html>