<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String name = (String)request.getAttribute("name");   // 다형성 체크
	String phone = (String)request.getAttribute("phone");
	String address = (String)request.getAttribute("address");
	String message = (String)request.getAttribute("message");

	String pizza = (String)request.getAttribute("pizza");
	String[] toppingArr = (String[])request.getAttribute("toppingArr");
	String[] sideArr = (String[])request.getAttribute("sideArr");
	String payment = (String)request.getAttribute("payment");

	 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>