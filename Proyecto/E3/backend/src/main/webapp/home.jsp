<?xml version="1.0" encoding="UTF-8" ?>

<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.0">
	<jsp:directive.page contentType="text/html; charset=UTF-8" 
		pageEncoding="UTF-8"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Home</title>
</head>
<body>
	<c:set value="${sessionScope['CUENTA_USUARIO']}" scope="page" var="cuenta"/>
	<h1>Bienvenido</h1>
	<ul>
		<c:forEach items="${cuenta.roles}" var="rol">
			<li> <c:out value="${rol.nombreRol}"/></li>
		</c:forEach>
	</ul>
</body>
</html>
</jsp:root>