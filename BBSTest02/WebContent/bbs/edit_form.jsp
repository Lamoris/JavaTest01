<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bbs.vo.BBSVO" %>
<%
	String authorId = (String)session.getAttribute("uid");
	BBSVO bbs = (BBSVO) request.getAttribute("bbs");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>디테일 수정 폼</title>
<style type="text/css">
	main { width:fit-content; margin:0 auto; }
	main>h3 { width:fit-content; margin:10px auto; }
	label { display:inline-block; width:3em; padding-right:0.5em; text-align: right;}
	label[for=contents]{vertical-align:top; margin-top:2em; }
	#div_btn {width:fit-content; margin:10px auto; }
	input[type=text] { width:300px;}
	textarea { width:300px; }
	footer { width:fit-content; margin:5px auto; }
	a { text-decoration: none;}
	#title {margin-bottom:0.5em;}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">

	function edit(num) {
		var serData = $('#edit_form').serialize();
		if (!confirm('정말로 수정하시겠습니까?'))
			return;
		$.ajax({
			url : 'bbs',
			method : 'post',
			cache : false,
			data : serData,
			dataType : 'json',
			success : function(res) {
				alert(res);
				alert(res.edited ? '수정 성공' : '수정 실패');
				location.href = 'bbs?cmd=detail&num='+num;
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
<h3>게시판 수정 폼</h3>
<form id="edit_form" onsubmit="return edit(<%=bbs.getNum()%>);">
	<input type="hidden" name="cmd" value="edited">
	<input type="hidden" name="num" value="<%=bbs.getNum()%>">
	<div><label for="title">글제목</label>
		<input type="text" id="title" name="title" value="<%=bbs.getTitle() %>">
	</div>
	<div><label for="contents">글내용</label>
		<textarea rows="5" cols="30" id="contents" name="contents"><%=bbs.getContents() %></textarea>
	</div>
	<div id="div_btn">
		<button type="reset">취소</button>
		<button type="submit">저장</button>
	</div>
</form>
<hr>
<footer>
	[<a href="bbs?cmd=list">글 목록</a>]
</footer>
</main>
</body>
</html>