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

<title>Agregar Extras</title>
</head>


<body>

	<%-- ENCABEZADO --%>
	<jsp:include page="encabezado-administrador.jsp"></jsp:include>


	<%-- CUERPO --%>
	<div class="container">
		<div class="row">


			<div>
				<center>
					<h2>Ingresar extras<h2>
				</center>
				<br>
				<br>
			</div>
			<!-- Mensaje error VALIDATE -->
				<form:errors path="*" element="div" class="alert alert-danger" />
			<center>
				
					
				<form:form action="registro-extras" method="POST" modelAttribute="Extras">
					<b><i>Por favor, ingrese un extra:</i></b>
					<center>
						<input name="nombre" placeholder="Mago"><br>
						<br>

						<b><i>Por favor, ingrese un precio:</i></b><br>
				
						<input name="precio" placeholder="200" /><br>
						<br>
					</center>

					<button class="btn btn-success" Type="Submit" />Agregar extra</button>
					<a href="/proyecto-limpio-spring/homeAdmin" class="btn btn-danger">Salir</a>
				</form:form>
				
			</center>
			
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