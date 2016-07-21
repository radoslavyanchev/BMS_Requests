<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="springForm"	uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>A product</title>
</head>
<body>

	<p>Форма за въвеждане на нови продукти</p>
	<springForm:form  method="get" commandName="product">
		<springForm:input type="text" path="name" placeholder="Име на продукта" pattern=".{3,}" /><br>
		<br> <springForm:select path="deparment">
		<c:forEach var="deparments" items="${departments.name}">
	
			<springForm:option value="${departments.id}">${departments.name}</springForm:option>
			</c:forEach>
		</springForm:select>
		<button>Създай</button>
	</springForm:form>
</body>
</html>