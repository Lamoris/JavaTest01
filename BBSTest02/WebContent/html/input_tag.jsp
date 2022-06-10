<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서버로 데이터 전송을 위한 HTML TAG</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function test(){
		console.log($('#input_form').serialize());
		return false;
	}
	$(function(){
		$('input[type=radio]').click(function(evt){
			if(evt.target.value == '1'){
				alert('기혼자 입니다.');
			}else(evt.target.value == '0'){
				alert('혼자왔니?');
				alert('어 아직 싱글이야');
			}
		})	
	});
</script>
</head>
<body>
	<form id="input_form" onsubmit="return test();">
		<div>
			<label>기혼</label>
			<input type="radio" id="married1" name="married" value="1">
		</div>
		<div>
			<label>미혼</label>
			<input type="radio" id="married0" name="married" value="0">
		</div>
		<div>
			<button type="reset">취소</button>
			<button type="submit">전송</button>
		</div>
		<div>
		<input type="text" list="nums">
		<datalist id="nums">
			<option value="11">
			<option value="12">
			<option value="13">
			<option value="14">
		</datalist>
		</div>
	</form>
	
	<datalist>
		
	</datalist>
</body>
</html>