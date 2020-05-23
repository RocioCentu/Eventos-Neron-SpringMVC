<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
	<link href="css/bootstrap.min.css" rel="stylesheet" >
	<!-- Bootstrap theme -->
	<link href="css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>

	<%-- ENCABEZADO --%>
	<jsp:include page="encabezado-administrador.jsp"></jsp:include>


	  <%-- CUERPO --%>
	  
        <div class="container">
            <div class="row">
            
<form:form action="reasigna-personal" method="POST" modelAttribute="vmReasignaPersonal">
<input type="hidden" name="idreserva" value="${idreserva}">

<p>Listado del personal asignado al evento de la reserva numero: ${idreserva}</p>
<p>Con reasignaciones</p>

	<!-- Mensaje error VALIDATE -->
	<form:errors path="*" element="div" class="alert alert-danger" />

	<c:forEach var = "i" begin = "1" end = "6">
	  			<c:forEach var="tipo" items="${cargos}">
					<c:if test="${tipo.id==i}">
						<td>Categoria: ${tipo.cargo}</td>
					</c:if>	
				</c:forEach>   
		<table class="table table-hover text-center mt-4"  border="1" cellpadding="1" cellspacing="0">
			<thead>
				<tr>
				<th>Nombre</th>
				<th>Apellido</th>
				<th></th>
				</tr>
			</thead>
			<tbody>
	  			<c:forEach var="personal" items="${listadop}">
					<c:if test="${personal.categoriaPersonal.id==i}">
						<tr>
						<td>${personal.apellido}</td>
						<td>${personal.nombre}</td>
						<td><input type="checkbox" name="idpersonal[${personal.idPersonal}]" value="${personal.idPersonal}"></td>
						</tr>
					</c:if>	
				</c:forEach>
			</tbody>
		</table>
		<br><br><br>
    </c:forEach>
	<button class="btn-agregar" Type="Submit"/>Bajar del evento</button>
	<a href="/proyecto-limpio-spring/homeAdmin" class="btn-agregar btn btn success">Salir</a>
</form:form>
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