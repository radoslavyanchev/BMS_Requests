<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="springForm"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Покритие</title>
</head>
<body>

	<p>Форма за въвеждане на нови покрития</p>
	<c:if test="${not empty success}">
		<p style="color: green">${success}</p>
	</c:if>

	<springForm:form action="covering" method="post"
		modelAttribute="covering">

		<springForm:input type="text" placeholder="Covering name" path="name" />

		<label style="color: red"> <springForm:errors path="name" /></label>
		<label style="color: red"> ${errorName}</label>

		<br>
		<br>
		<springForm:select path="department">
			<springForm:option value="" label="--- Select Department ---"></springForm:option>
			<springForm:options items="${departmentsList}" itemValue="id"
				itemLabel="name"></springForm:options>
		</springForm:select>
		<label style="color: red"><springForm:errors path="department" /></label>
		<br>
		<br>
		<button>Създай</button>
	</springForm:form>
	<br>
	<br>


	<form action="coveringDelete" method="post">
		<c:forEach var="covering" items="${coveringList}">
			<form action="coveringDelete" method="post">
				${covering.name}
				<button>ИЗТРИИ</button>
				<br> <br>
			</form>
		</c:forEach>
</body>
</html>