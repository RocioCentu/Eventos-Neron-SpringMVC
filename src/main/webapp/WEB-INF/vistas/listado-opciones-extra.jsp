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

			<h2>Seleccion de Extras</h2>
			<br> <br> <br>
			<p>

			</p>
			<div class="table70">
				<form:form action="registra-reserva-extras" method="POST" modelAttribute="vm">
					
					<c:if test="${!empty mensajeerror}">
						<div class="alert alert-danger">${mensajeerror}</div>
					</c:if>
					<form:errors path="*" element="div" class="alert alert-danger" />
					
					<table class="table table-hover text-center mt-4" border="1" cellpadding="1" cellspacing="0">
					<h4><b>Debe seleccionar: </b>al menos un extra y como máximo dos<br></h4>
						<thead>
							<tr>
								<th class="enc">Descripcion</th>
								<th class="enc">Precio</th>
								<th class="enc"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="menu" items="${listaopciones}">
								<tr>
									<td width="650" class="alt-celda margina-izq">${menu.nombre}</td>
									<td width="100" class="alt-celda">$<fmt:formatNumber currencySymbol="" value="${menu.precio}" type="currency"/></td>
									<td width="50" class="alt-celda"><input type="checkbox" name="idmenu[${menu.id}]" value="${menu.id}"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<br>
					<br>
					<br>

							<input class=" btn btn-success"	type="submit" value="Confirmar y continuar" />
							<a><input class=" btn btn-danger" type="button" value="Cancelar" /></a>
				</form:form>
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