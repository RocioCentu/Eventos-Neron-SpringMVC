<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/extras.css">
		
	    <title>Eliminar Personal</title>
	    
	</head>
	
	<header>
				<center><b><i>Nerón - Eliminar Personal</i></b></center><br><br>

	
	
	</header>
	<body><center>
				<form:form action="eliminar-personal" method="POST" modelAttribute="Personal">
				<b><i>Por favor, elimine a algún personal</i></b>   
	
					

				
					<button Type="Submit"/>Eliminar</button>
				</form:form>
				<br>
				</center>
	</body>
</html>