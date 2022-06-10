<%@ page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	List<String> list = new ArrayList<>();
	list.add("강호동");
	list.add("유재석");
	list.add("신동엽");

	//pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL TEST</title>
</head>
<body>
	<c:set var="list" value="<%=list%>" />
	<c:forEach var="mem" items="${list}">
		<div>${mem}</div>
	</c:forEach>
</body>
</html>