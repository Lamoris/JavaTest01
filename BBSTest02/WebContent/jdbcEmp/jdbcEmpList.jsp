<%@page import="com.bbs.vo.Employee"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	List<Employee> list = (List<Employee>) request.getAttribute("list");
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee List</title>
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
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function(){
		$('input[type=radio]').click(function(evt)){
			var category = evt.target.value;
			
			var obj = {};
			obj.cmd = 'getItemList';
			obj.category = category;
			
			$.ajax({
				url : 'JdbcEmp',
				method : 'post',
				cache : false,
				data : obj,
				dataType : 'json',
				success : function(res) {
					var obj = JSON.parse(res);
					var options = "";
					for(var i=0; i<obj.length;i++){
						var option = "<option value='"+ obj[i] +"'>";
						options += option;
					}
					$('#list').html(options);
				},
				error : function(xhr, status, err) {
					alert(err);
				}
			});
			return false;
		}
});
</script>
</head>
<body>
<main>
<h3>Employee List</h3>
<table>
<tr><th>NO</th><th>Name</th><th>Department<br>(code)</th><th>Salary</th><th>Hire Date</th></tr>

	<c:forEach var="emp" items="${list}">
		<tr>
			<td>${emp.empno}</td>
			<td>
				<a href="JdbcEmp?cmd=detail&empno=${emp.empno}">${emp.ename}</a>
			</td>
			<td>
				<a href="JdbcEmp?cmd=empList_dept&deptno=${emp.deptno}">${emp.deptno}</a>
			</td>
			<td>${emp.sal}</td>
			<td>${emp.hiredate}</td>
		</tr>
	</c:forEach>		
	</table>
	
	<form id="searchBy" action="JdbcEmp" method="post">
	<input type="hidden" name="cmd" value="searchEmp">
		<div>
			<!-- input값이 많아도 하나만 넘어간다. ex)search -->
			<label>Number</label>
			<input type="radio" id="search0" name="search" value="empno">&nbsp;
			<label>Name</label>
			<input type="radio" id="search1" name="search" value="ename">&nbsp;
			<input type="text" id="searchBar" name="searchBar">&nbsp;
			<button type="reset">reset</button>
			<button type="submit">submit</button>
		</div>
	</form>
</main>
<datalist id="list"></datalist>
</body>
</html>