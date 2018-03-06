<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- viewport : 기본 크기를 핸드폰 기계의 크기에 맞춤 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap css -->
<link href='<c:url value="/resources/bootstrap/css/bootstrap.min.css" />' rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- Bootstrap js -->
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function(){
	    $("#btninsert").click(function(){
	        //var title = document.form1.title.value; ==> name속성으로 처리할 경우
	        //var content = document.form1.content.value;
	        //var writer = document.form1.writer.value;
	        var title = $("#title").val();
	        var content = $("#content").val();
	        if(title == ""){
	            document.form1.title.focus();
	            return;
	        }
	        if(content == ""){
	            document.form1.content.focus();
	            return;
	        }
	        // 폼에 입력한 데이터를 서버로 전송
	        document.data.submit();
	    });
	    
	    $("#btnlist").click(function(){
	    	location.href="${path}/gongjiboard/board/list"
	    });
	});

	//글자수 제한하는 함수 생성
	function checkLength(comment) {
		//길이가 20000보다 크면 조건 실행
		if (comment.value.length > 20000 ) {
			comment.blur();
			comment.value = comment.value.substring(0, 20000);
			comment.focus();
			return false;//해당 길이 이상일 경우 false값 리턴
		}
	}
</script>
<style>
	th { width:50px; background-color:#d9edf7;}
</style>
</head>
<body>
	<h1 class='text-center'>게시판</h1>
	<br>
	<div style='width:100%; margin:auto;'>
	<form name='data' method='post' action='${path}/gongjiboard/board/insert'>
	<table class='table table-bordered table-sm' width='100%'>
		<tr>
		<th class='text-center'>제목</th>
		<td><input type='text' name='title' id='title' maxlength='40' style='width:95%;' required /></td>
		</tr>
		<tr>
		<th class='text-center'>내용</th>
		<!--onKeyUp / onKeyDown으로 글 작성시 키를 누르면 실시간으로 글자수를 제한하는 함수 생성-->
		<td>
		<textarea name='content' id='content' style='resize:none; width:95%; height:300px;' onKeyUp='checkLength(this);'
				  onKeyDown='checkLength(this);'></textarea>
		</td>
		</tr>
	</table>
	<div class='text-right'>
	<!--취소 버튼 누르면 목록으로 돌아가기-->
	<input type='button' class="btn btn-default btn-sm" value='취소' id='btnlist' />
	<!--쓰기 버튼 누르면 post방식으로 데이터 전송하기-->
	<input type='button' class="btn btn-default btn-sm" value='쓰기' id='btninsert' />
	</div>
	</form>
	</div>
</body>
</html>