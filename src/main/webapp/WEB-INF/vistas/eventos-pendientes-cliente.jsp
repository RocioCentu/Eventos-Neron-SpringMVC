<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>

	<%-- ENCABEZADO --%>
	<jsp:include page="encabezado-cliente.jsp"></jsp:include>


	<%-- CUERPO --%>

	<div class="container">
		<div class="row">

			<h2>Reservas pendientes</h2><br><br>
			<h4>Seleccione una reserva para ver el detalle de la misma</h4>
			
			
			<c:if test="${!empty mensajeerror}">
	 			<div class="alert alert-danger">${mensajeerror}</div>
			</c:if>
			
			<form:form action="listado-eventos-pendientes-cliente" method="POST" modelAttribute="idreserva">
				<table class="table table-hover text-center mt-4" border="1" cellpadding="1" cellspacing="0">
					<thead>
						<tr>
							<th class="enc">Salón</th>
							<th class="enc">Dirección</th>
							<th class="enc">Fecha</th>
							<th class="enc">Horario</th>
							<th class="enc"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${listadoPendientesCliente}">

							<tr>
								<td class="alt-celda">${item.salon.nombre}</td>
								<td class="alt-celda">${item.salon.direccion}</td>
								<td class="alt-celda">${item.fecha}</td>
								<td class="alt-celda">${item.horario}</td>
								<td class="alt-celda"><input type="radio" name="idreserva" value="${item.idReserva}"></td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
								
				<c:if test="${empty idreserva}">
					<input type="hidden" value=0 name="idreserva" />
				</c:if>
				
				<button class="btn btn-success" Type="Submit" />Seleccionar</button>
				<a href="/proyecto-limpio-spring/home" class="btn btn-danger">Cancelar</a>
			</form:form>
			<br> <br> <br>
		</div>
	</div>
	<%-- PIE --%>
	<jsp:include page="pie.jsp"></jsp:include>

</body>
</html>