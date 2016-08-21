
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="springForm"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Department</title>
</head>
<body>

	<p>Въвеждане на нов отдел</p>
	<c:if test="${not empty success}">
		<p style="color: green">${success}</p>
	</c:if>

	<springForm:form action="department" method="post"
		modelAttribute="department" accept-charset="UTF-8">

		<springForm:input type="text" placeholder="Department name"
			path="name" />

		<label style="color: red"> <springForm:errors path="name" /></label>
		<label style="color: red"> ${errorName}</label>

		<br>
		<br>
		<button>Създай</button>
	</springForm:form>
</body>
</html>