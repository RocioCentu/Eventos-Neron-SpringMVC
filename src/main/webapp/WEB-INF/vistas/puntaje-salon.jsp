<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Salones</title>
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

			<h2>Nuestros Salones</h2><br><br>



			<div>
				<form action="buscar" method="GET">
					<input type="search" name="input" placeholder="Nombre del salon">
					<input type="submit" value="buscar">

				</form><br><br>
				<c:if test="${!empty mensaje}">
					<div class="alert alert-danger">${mensaje}</div>
				</c:if>
				<form:errors path="*" element="div" class="alert alert-danger" />
				<br>
			</div>

			<c:forEach items="${salones}" var="salon">


				<table class="table table-hover text-center mt-4" border="0" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td><img class="card-img-top imagen-salon"
								src="img/${salon.imagenCard}" alt="Card image cap"></td>
							<td><form:form action="puntuar-salon" method="Post"
									modelAttribute="mvSalon">

									<h2 class="card-title">${salon.nombre}</h2>
									<p class="card-title">Precio: $<fmt:formatNumber currencySymbol="" value="${salon.precio}" type="currency"/></p>
									<!--     <a href="VerImagenes?id=${salon.id}">
         <input  class=" btn btn-success" type="button" value="Ver mas"/>
     </a>-->
									<h3 class="card-title">Valoración Actual: <fmt:formatNumber type="number" pattern="#0.00" maxFractionDigits="2" value="${salon.puntaje}"/> / 10</h3>
									<input name="id" type="hidden" value="${salon.id}" />
									<label>Pon aqui tu puntaje</label>
									<input type="number"  max="10" min="1" name="puntaje">

								<c:if test="${empty puntaje}">
									<input type="hidden" value="0" name="puntaje" >
								</c:if>
								<input type="submit" value="puntuar">
								</form:form></td>

						</tr>
					</tbody>
				</table>

			</c:forEach>
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
