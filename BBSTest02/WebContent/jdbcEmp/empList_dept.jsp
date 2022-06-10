<%@page import="com.bbs.vo.Employee"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	List<Employee> list = (List<Employee>) request.getAttribute("list");
	pageContext.setAttribute("list", list);
%>
<html>
<head>
<meta charset="UTF-8">
<title>EmpList by Department</title>
<style type="text/css">
	main { width:fit-content; margin:0 auto; }
	main>h3 { width:fit-content; margin:10px auto; }
	table { border:1px solid black; width:500px; border-spacing:0; border-collapse: collapse;}
	th,td { border-right: 1px dashed black; text-align:center;}
	th:last-child(), td:last-child() { border-right:none; }
	th {background-color: #E0FFFF; border-bottom:3px double black; }
	tr:nth-child(odd) {background-color:#eee;}
	tr:hover { background-color: #F0FFFF; }
	footer { width:fit-content; margin:5px auto; }
	a { text-decoration: none;  }
</style>
</head>
<body>
<main>
<h3>Department (${list[0].deptno}) </h3>
<table>
	<tr>
		<th>NO</th>
		<th>Name</th>
		<th>Job</th>
		<th>Manager</th>
		<th>Salary</th>
		<th>Commission</th>
		<th>Hire Date</th>
	</tr>
	<c:forEach var="emp" items="${list}">
		<tr>
			<td>${emp.empno}</td>
			<td>${emp.ename}</td>
			<td>${emp.job}</td>
			<td>${emp.mgr}</td>
			<td>${emp.sal}</td>
			<td>${emp.comm}</td>
			<td>${emp.hiredate}</td>
		</tr>
	</c:forEach>
</table>
[<a href="JdbcEmp?cmd=list">Employee List</a>] 
</main>
</body>
</html>