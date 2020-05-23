<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Cantidad de servicios prestados por cada personal</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>

	<%-- ENCABEZADO --%>
	<jsp:include page="encabezado-administrador.jsp"></jsp:include>


	<%-- CUERPO --%>
	<div class="container tabla-ancho">
		<div class="row">
<h2>Asistencia del personal</h2><br>
			<c:forEach var="i" begin="1" end="6">

				<c:forEach var="tipo" items="${cargos}">
					<c:if test="${tipo.id==i}">
						<h4>Categoria: ${tipo.cargo}</h4>
					</c:if>
				</c:forEach>

				<table class="table table-hover text-center mt-4 tabla-ancho" border="1" cellpadding="1" cellspacing="0">
					<thead>
						<tr>
							<th width="100" class="enc">Legajo Nº</th>
							<th width="390" class="enc">Nombre y Apellido</th>
							<th width="110" class="enc">Servicios prestados</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="personal" items="${asistencia}">
							<c:if test="${personal.key.categoriaPersonal.id==i}">
								<tr>
									<td class="alt-celda">${personal.key.idPersonal}</td>
									<td class="alt-celda margina-izq">${personal.key.nombre} ${personal.key.apellido}</td>
									<td class="alt-celda">${personal.value}</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>

			</c:forEach>
			<a href="/proyecto-limpio-spring/home" class="btn btn-danger">Salir</a>

		</div>
	</div>

	<%-- PIE --%>
	<jsp:include page="pie.jsp"></jsp:include>

	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
