<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <!-- Bootstrap core CSS -->
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
            <li ><a  href="home"> Inicio    </a></li>
            <li><a  href="salones-a-puntuar"> Nuestros Salonest</a></li>
            <li><a  href="menus-a-puntuar"> Nuestros Menus</a></li>

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

<!--Carousel Wrapper-->
<div class="row">
    <div class="col-md-12">
          <div class="mdb-lightbox no-margin">


            <c:forEach items="${imagenes}" var="imagenes">
            <figure class="col-md-4">

                    <img alt="${imagenes.nombre}" src="img/${imagenes.nombre}" width="790" height="444">

                    <h3 class="text-center my-3">Photo title</h3>

            </figure>
       </c:forEach>








    </div>
    </div>
</div>

</body>
</html>
