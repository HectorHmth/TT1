<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<jsp:directive.page contentType="text/html; charset=UTF-8" 
		pageEncoding="UTF-8"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Iniciar Sesión</title>
</head>
<body>
<h1>Iniciar Sesión</h1>
<form action="${pageContext.request.contextPath}/LoginController" method="POST">
	<div>
		<div>Nombre de Usuario</div>
		<div>
			<input type="text" name="cuenta.nb_usuario" required="true"/>
		</div>
	</div>
	<div>
		<div>Contraseña</div>
		<div><input type="text" name="cuenta.contrasena" required="true"/>
		</div>
	</div>
	<input type="submit" value="Iniciar Sesión"/>
</form>

</body>
</html>
</jsp:root>