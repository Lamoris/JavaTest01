<%@page import="com.bbs.vo.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Employee emp = (Employee) request.getAttribute("emp");
	pageContext.setAttribute("emp", emp);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Page</title>
<style type="text/css">
	main { width:fit-content; margin:0 auto; }
	main>h3 { width:fit-content; margin:1em auto; }
	table { border:1px solid black; width:500px; border-spacing:0; border-collapse: collapse;}
	th,td { border-right: 1px dashed black; text-align:center;}
	th:last-child(), td:last-child() { border-right:none; }
	th {background-color: #E0FFFF; border-bottom:3px double black; }
	tr:nth-child(odd) {background-color:#eee;}
	tr:hover { background-color: #F0FFFF; }
	footer { width:fit-content; margin:5px auto; }
	a { text-decoration: none;  }
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function edit(){
		if(!confirm('Are you Sure?'))
			return;
		else
			location.href='JdbcEmp?cmd=edit&empno='+${emp.empno};
	}
	
	function deleted(){
		if(!confirm('Double Check for Delete?'))
			return;
		else
			location.href='JdbcEmp?cmd=detail&empno='+${emp.empno};
	}
</script>
</head>
<body>
<main>
<h3>직원 상세정보 페이지</h3>
<table>
			
			<tr>
				<td>NO</td>
				<td>${emp.empno}</td>
			</tr>
			<tr>
				<td>Name</td>
				<td>${emp.ename}</td>
			</tr>
			<tr>
				<td>Job</td>
				<td>${emp.job}</td>
			</tr>
			<tr>
				<td>Manager</td>
				<td>${emp.mgr}</td>
			</tr>
			<tr>
				<td>Salary</td>
				<td>${emp.sal}</td>
			</tr>
			<tr>
				<td>Commission</td>
				<td>${emp.comm}</td>
			</tr>
			<tr>
				<td>Hire Date</td>
				<td>${emp.hiredate}</td>
			</tr>
			<tr>
				<td>Department Number</td>
				<td>${emp.deptno}</td>
			</tr>
		</table>
<hr>
<footer>
	[<a href="javascript:edit();">Employee Edit</a>] 
	[<a href="JdbcEmp?cmd=list">Employee List</a>] 
	[<a href="javascript:deleted();">Delete Data</a>]
</footer>
</main>
</body>
</html>