<nav class="navbar navbar-default enca" role="navigation">
    <!-- El logotipo y el icono que despliega el men� se agrupan
         para mostrarlos mejor en los dispositivos m�viles -->
    <div class="logotipo-encabezado"></div>

    <!-- Agrupar los enlaces de navegaci�n, los formularios y cualquier
         otro elemento que se pueda ocultar al minimizar la barra -->
    <div class="collapse navbar-collapse navbar-ex1-collapse enca">
        <ul class="nav navbar-nav enc-margen">
        
        <div class="superior">
        	<div class="navbar-form navbar-left">
            <p class="bienvenido">Bienvenido: ${usuario}</p>
            </div>
        	<div class="navbar-form bloque-neron">
            <p class="neron">Organizacion de Eventos NERON</p>
            </div>
            <form class="navbar-form navbar-left separacion-salir"  method="post"  action="cerrarsesion"  >
            <div class="form-group">
            <input type="submit" value="cerrar sesion" class="btn btn-danger">
            </div>
        	</form>
       
        
        </div>
        
            <li ><a class="btn" href="homeAdmin">inicio</a></li>
            <!-- <li><a href="listado-final-extras">Extras</a></li>
            <li><a href="listado-final-menu">Menu</a></li>
            <li><a href="listado-final-salon">Salon</a></li> -->  
            <li><a class="btn" href="ingresar-menu">Ingresar Men�</a></li>
            <li><a class="btn" href="listado-eventos-pendientes">REASIGNACION PERSONAL - Eventos pendientes</a></li>
            <li><a class="btn" href="ingreso-extras">Ingresar Extras</a></li>
            <li><a class="btn" href="listado-final-extras">Listado de Extras</a></li>
            <li><a class="btn" href="trabajo-personal">Asistencia del Personal</a></li>
        </ul>






    </div>
</nav>