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

	<!--  <c:if test="${not empty errorDepartment}">
		<p style="color: red">Грешка: ${error}</p>
	</c:if>
	 -->
	<c:if test="${not empty success}">
		<p style="color: green">${success}</p>
	</c:if>

	<springForm:form action="product" method="post"
		modelAttribute="product">

		<springForm:input type="text" placeholder="Product name" path="name" />
		<label style="color: red"> <springForm:errors path="name" /></label>
		<c:if test="${not empty errorName}">
			<label style="color: red">${errorName}</label>
		</c:if>
		<br>
		<br>
		<springForm:select path="department">
			<springForm:option value="" label="--- Select Department ---"></springForm:option>
			<springForm:options items="${departmentsList}" itemValue="id"
				itemLabel="name"></springForm:options>
		</springForm:select>

		<c:if test="${not empty errorDepartment}">
			<label style="color: red">${errorDepartment}</label>
		</c:if>
		<br>
		<br>

		<button>Създай</button>
	</springForm:form>
</body>
</html>