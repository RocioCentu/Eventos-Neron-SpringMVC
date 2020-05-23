<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<jsp:include page="encabezado-cliente-index.jsp"></jsp:include>


	<%-- CUERPO --%>

	<div class="container">
		<div class="row call-to-action">
			<form class="navbar-form navbar-left" method="post" action="salon">
				<div class="form-group">
					<input type="submit" value="Reservar" class="btn btn-success btn-grande">
				</div>
			</form>
		</div>
	</div>
	<table class=" table table-hover text-center mt-4">
<br>
	<div class="container">
		<div class="row">		
		<h1><center>Nuestros mejores salones</center></h1>
</div></div>

		<c:forEach items="${salones}" var="salon">

			<td class="border border-success">
			<img class="card-img-top imagen-salon-grande" src="img/${salon.imagenCard}" alt="Card image cap">
				<h2>${salon.nombre}</h2> <br>

				<h4>Precio: $<fmt:formatNumber currencySymbol="" value="${salon.precio}" type="currency"/></h4>
				
 
				<h4>Valoración Actual: <fmt:formatNumber type="number" pattern="#0.00" maxFractionDigits="2" value="${salon.puntaje}"/> / 10</h4>
				</td>

		</c:forEach>
	</table>

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