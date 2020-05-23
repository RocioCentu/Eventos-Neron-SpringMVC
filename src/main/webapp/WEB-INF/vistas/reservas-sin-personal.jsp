<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
	<link href="css/bootstrap.min.css" rel="stylesheet" >
	<!-- Bootstrap theme -->
	<link href="css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
	<!-- El logotipo y el icono que despliega el menú se agrupan
         para mostrarlos mejor en los dispositivos móviles -->
	<div class="navbar-header">

		<a class="navbar-brand" href="#">Logotipo</a>
	</div>

	<!-- Agrupar los enlaces de navegación, los formularios y cualquier
         otro elemento que se pueda ocultar al minimizar la barra -->
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav">
			<li ><a href="homeAdmin">inicio</a></li>
			<li><a href="listado-final-extras">Extras</a></li>

			<li><a href="listado-final-menu">Menu</a></li>
			<li><a href="listado-final-salon">Salon</a></li>
			<li><a href="">Personal</a></li>
		</ul>


		<div class="navbar-form navbar-left">
			<h5>Bienvenidos a</h5>
			<h5>Organizacion de Eventos NERON</h5>
		</div>




		<form class="navbar-form navbar-left"  method="post"  action="salon"  >
			<div class="form-group">
				<input type="submit" value="Reservar" class="btn btn-success">
			</div>
		</form>


		<form class="navbar-form navbar-left"  method="post"  action="cerrarsesion"  >
			<div class="form-group">
				<input type="submit" value="cerrar sesion" class="btn btn-success">
			</div>
		</form>

	</div>
</nav>
Reservas sin personal asignado<br><br><br>

<form:form action="registra-reserva-menu" method="GET">


				
		<table class="table table-hover text-center mt-4" border="1" cellpadding="1" cellspacing="0">
			<thead>
				<tr>
				<th>Reserva Id numero</th>
				<th></th>
				</tr>
			</thead>
			<tbody>
	  			<c:forEach var="reserva" items="${listaReservaSinPersonal}">
						<tr>
						<td>${reserva.id}</td>
						<td><input type="checkbox" name="idmenu" value="${reserva.id}"></td>
						</tr>
				</c:forEach>
			</tbody>
		</table>

		
		<br><br><br><br><br><br>
		
				<table border="1" cellpadding="1" cellspacing="0">
			<thead>
				<tr>
				<th>Reserva Id numero</th>
				<th></th>
				</tr>
			</thead>
			<tbody>
	  			<c:forEach var="reser" items="${listaSinPersonal}">
						<tr>
						<td>${reser.id}</td>
						<td><input type="checkbox" name="idmenu" value="${reser.id}"></td>
						</tr>
				</c:forEach>
			</tbody>
		</table>



	<button class="btn-agregar" Type="Submit"/>Agregar</button>
</form:form>


        <!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
