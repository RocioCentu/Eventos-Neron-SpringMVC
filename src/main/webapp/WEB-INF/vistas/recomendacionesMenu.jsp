<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Recomendaciones Menu</title>
</head>
<body>
<h2>Menus que te pueden interesar</h2>


<br>

	<c:if test="${tope>=1}">
		<h2>menu</h2><br>
	<c:forEach items="${menus1}" var="menu">

	${menu.descripcion}
		$${menu.costo}<br>


	</c:forEach>
	</c:if>

<br>


<br>

<c:if test="${tope>=2}">
	<h2>menu</h2><br>
	<c:forEach items="${menus2}" var="menu">

		${menu.descripcion}
		$${menu.costo}<br>


	</c:forEach>
</c:if>

<br>


<br>

<c:if test="${tope>=3}">
	<h2>menu</h2><br>
	<c:forEach items="${menus3}" var="menu">

		${menu.descripcion}
		$${menu.costo}<br>


	</c:forEach>
</c:if>

<br>


<br>

<c:if test="${tope>=4}">
	<h2>menu</h2><br>
	<c:forEach items="${menus4}" var="menu">

		${menu.descripcion}
		$${menu.costo}<br>


	</c:forEach>
</c:if>

<br>


<br>

<c:if test="${tope>=5}">
	<h2>menu</h2><br>
	<c:forEach items="${menus5}" var="menu">

		${menu.descripcion}
		$${menu.costo}<br>


	</c:forEach>
</c:if>

<br>


</body>
</html>
