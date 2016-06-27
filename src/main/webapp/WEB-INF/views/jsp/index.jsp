<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>

<body>
	<form action="signIn" method="post" commandName="user">
		<input type="text" placeholder="Username"
			pattern=".{4,}" /><br><br>
			 <input	type="password" placeholder="Password" pattern=".{6,}" /><br><br>
		<button>Login</button>
	</form>
</body>
</html>