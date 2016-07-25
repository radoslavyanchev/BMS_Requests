<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="springForm"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>A product</title>
</head>
<body>

	<p>Форма за въвеждане на нови продукти</p>
	<springForm:form method="get" modelAttribute="product">
		<springForm:input type="text" path="name"
			placeholder="Име на продукта" pattern=".{3,}" />
		<br>
		<br>
		<select id="department" name="departments">
		<!-- Var mi e promenlivata items e masiva-->
			<c:forEach var="department" items="${departments}">
				<option value="${department.id}">${department.name}</option>
			</c:forEach>
		</select>
		<button>Създай</button>
	</springForm:form>
</body>
</html>