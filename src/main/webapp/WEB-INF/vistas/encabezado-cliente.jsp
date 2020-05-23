<nav class="navbar navbar-default enca" role="navigation">
    <!-- El logotipo y el icono que despliega el menú se agrupan
         para mostrarlos mejor en los dispositivos móviles -->
    <div class="logotipo-encabezado"></div>

    <!-- Agrupar los enlaces de navegación, los formularios y cualquier
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
				<li><a class="btn" href="home">Inicio</a></li>
				<li><a class="btn" href="salones-a-puntuar">Nuestros Salones</a></li>
				<li><a class="btn" href="menus-a-puntuar">Nuestros Menus</a></li>			
				<li><a class="btn" href="eventos-pendientes-cliente">Ver detalles de la/s Reserva/s</a></li>
				<li><a class="btn" href="condiciones-cancelacion-reserva">Cancelar Reserva</a></li>
				<jsp:include page="reservar.jsp"></jsp:include>
        </ul>






    </div>
</nav>