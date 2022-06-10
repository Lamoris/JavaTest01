<%@page import="com.bbs.vo.Employee"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("greeting", "Hello World");
	Employee emp = new Employee();
	emp.setEmpno(11);
	emp.setEname("scott");
	emp.setHiredate(java.sql.Date.valueOf("2001-01-01"));
	pageContext.setAttribute("emp", emp);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL(Expression Language)</title>
</head>
<body>
	<p>
	<!-- 자바코드를 이용하여 화면에 표시 -->
		<%
			String greet = (String) pageContext.getAttribute("greeting");
			out.print(greet);
		%>
	</p>
	<p>
	<!-- Expression Tag 이용하여 화면에 표시 -->
		<%=(String) pageContext.getAttribute("greeting")%>
	</p>
	<p>
		<!-- EL 이용하여 화면에 표시 -->
		${greeting}
	</p>
	<p>
	${emp } <br/>
	사번 : ${emp.empno }<br/>
	이름 : ${emp.ename }<br/>
	입사일 : ${emp.hiredate }
	</p>
</body>
</html>