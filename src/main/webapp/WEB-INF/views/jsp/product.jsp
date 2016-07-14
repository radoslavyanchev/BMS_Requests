<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>A product</title>
</head>
<body>

	<p>Форма за въвеждане на нови продукти</p>
	<form action="" method="get" commandName="user">
		<input type="text" placeholder="Име на продукта" pattern=".{3,}" /><br><br>
			 <input type="radio" name="dp" value="PKG" checked> ПКГ<br>
  <input type="radio" name="dp" value="dr"> ДР<br>
  <input type="radio" name="dp" value="POT"> ПОТ<br>
		<button>Създай</button>
	</form>
</body>
</html>