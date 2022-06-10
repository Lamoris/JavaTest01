<%@page import="com.bbs.vo.Employee"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Employee emp = (Employee) request.getAttribute("emp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Update</title>
<style type="text/css">
	main {width:fit-content; margin:0 auto;}
	label {display: inline-block; width: 10em; margin-right: 1em; text-align: right;}
	form > div:last-child { width: fit-content; margin: 1em auto;}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function edit() {
		var serData = $('#edit_form').serialize();
		if (!confirm('Are you sure Edit this Employee Data?'))
			return;
		$.ajax({
			url : 'JdbcEmp',
			method : 'post',
			cache : false,
			data : serData,
			dataType : 'json',
			success : function(res) {
				alert(res.edited ? 'Edit OK' : 'Edit Error');
				location.href = 'JdbcEmp?cmd=detail&empno=${emp.empno}';
			},
			error : function(xhr, status, err) {
				alert(err);
			}
		});
		return false;
	}
</script>
</head>
<body>
<main>
<h3>Employee EditForm</h3>
<hr>
<form id="edit_form" onsubmit="return edit();">
	<input type="hidden" name="cmd" value="edited">
	<input type="hidden" name="empno" value="${emp.empno}">
	<div>
		<label>Employee Number</label>${emp.empno }
	</div>
	<div>
		<label>Employee Name</label>${emp.ename }
	</div>
	<div>
		<label for="sal">Salary</label>
		<input type="text" id="sal" name="sal" value="${emp.sal}">
	</div>
	<div>
		<label for="deptno">Department Number</label>
		<input type="text" id="deptno" name="deptno" value="${emp.deptno}">
	</div>
	<div id="div_btn">
		<button type="reset">reset</button>
		<button type="submit">submit</button>
	</div>
</form>
	<hr>
	<div>
		[<a href="JdbcEmp?cmd=list">Employee List</a>]
	</div>

</main>
</body>
</html>