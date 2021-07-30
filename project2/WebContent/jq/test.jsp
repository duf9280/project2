<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.4.1.min.js"></script>
</head>
<body>
<script>
	function change01(){
		var subject = document.getElementById("subject").value;
		$.ajax({
			url:"test1.jsp",
			type:"post",
			dataType:"text",
			data:"subject="+subject,
			error:function(){
				
			},
			success:function(text){
				$(".teacher_div").html(text);
			},
			complete:function(){
				
			},
		});
	}
	function change02(){
		var teacher = document.getElementById("teacher").value;
		$.ajax({
			url:"test2.jsp",
			type:"post",
			dataType:"text",
			data:"teacher="+teacher,
			error:function(){
				
			},
			success:function(text){
				$(".aaa").html(text);
			},
			complete:function(){
				
			},
		});
	}	
</script>
<select id="subject" name="subject" onchange="change01()">
	<option value="">선택1</option>
	<option value="k">국어</option>
	<option value="e">영어</option>
</select>
<span class="teacher_div">
<select onclick="alery('대분류 먼저 선택');">
	<option value="">선택2</option>
	<option value=""></option>
	<option value=""></option>
</select>
</span>

<span class="aaa">
<select>
	<option value="">선택2</option>
	<option value=""></option>
	<option value=""></option>
</select>
</span>

</body>
</html>