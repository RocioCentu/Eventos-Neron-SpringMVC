<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Listado para la seleccion del menu</title>
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

			<h2>Seleccion del menu</h2>
			<br>
			<p>
				<b>Debe seleccionar para:</b><br>
				Finger Food: minímo 8 y máximo 14 opciones<br>
				Entrada: minímo 1 y máximo 2 opciones<br>
				Plato Principal: minímo 1 y máximo 2 opciones<br>
				Bebida: minímo 4 y máximo 7 opciones<br>
				Postre: minímo 1 y máximo 2 opciones<br>
				Mesa Dulce: minímo 4 y máximo 6 opciones<br>
			</p>
			<br>
			<br>
			<div class="table70">
				<form:form action="registra-reserva-menu" method="POST" modelAttribute="vm">

					<c:if test="${!empty mensajeerror}">
						<div class="alert alert-danger">${mensajeerror}</div>
					</c:if>
					<form:errors path="*" element="div" class="alert alert-danger" />


					<c:forEach var="i" begin="1" end="6">
						<c:forEach var="tipo" items="${secciones}">
							<c:if test="${tipo.id==i}">
								<h3>${tipo.detalle}</h3>
							</c:if>
						</c:forEach>
						<table class="table table-hover text-center mt-4" border="1"
							cellpadding="1" cellspacing="0">
							<thead>
								<tr>
									<th width="650" class="enc">Descripción</th>
									<th width="100" class="enc">Precio</th>
									<th width="50" class="enc"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="menu" items="${listaopciones}">
									<c:if test="${menu.tipoDeMenu.id==i}">
										<tr>
											<td class="alt-celda margina-izq">${menu.descripcion}</td>
											<td class="alt-celda">$<fmt:formatNumber currencySymbol="" value="${menu.precio}" type="currency"/></td>
											<td class="alt-celda"><input type="checkbox" name="idmenu[${menu.idMenu}]" value="${menu.idMenu}"></td>
										</tr>										
									</c:if>
								</c:forEach>
							</tbody>
						</table>

					</c:forEach>


							<input class=" btn btn-success"	type="submit" value="Confirmar y continuar" />
							<a><input class=" btn btn-danger" type="button" value="Cancelar" /></a>
				</form:form>
			</div>

			<div class="container ofrecidos">
				<h3>Menus que te pueden interesar</h3>
				<div class="row slider">

					<table class=" table table-hover text-center mt-4">


						<c:forEach var="i" begin="1" end="6">

							<c:forEach items="${menus}" var="menu">
								<td class="card border-primary p-3"><img
									class="card-img-top imagen-salon-mini"
									src="img/${menu.imagenCard}" alt="Card image cap">
									<div class="card-body>">
										<c:forEach var="tipo" items="${secciones}">
											<c:if test="${tipo.id==i}">
												<h4 class="card-title">${tipo.detalle}</h4>
											</c:if>
										</c:forEach>


										<p class="card-title">${menu.descripcion}</p>
										<p class="card-text">Precio: $<fmt:formatNumber currencySymbol="" value="${menu.precio}" type="currency"/></p>


										<h4 class="card-title">Valoración Actual: <fmt:formatNumber type="number" pattern="#0.00" maxFractionDigits="2" value="${menu.puntaje}"/> / 10</h4>
										<input name="id" type="hidden" value="${menu.idMenu}" />
										</td>
							</c:forEach>
						</c:forEach>

					</table>
				</div>
			</div>
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
