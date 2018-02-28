<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap css -->
<link href='<c:url value="/resources/bootstrap/css/bootstrap.min.css" />' rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- Bootstrap js -->
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function(){
		$("#btnlist").click(function(){
	    	location.href="${path}/gongjiboard/board/list?curPage="+${curPage};
	    });
	    
		$("#btnupdate").click(function(){
	        var title = $("#title").val();
	        var content = $("#content").val();
	        if(title == ""){
	            document.data.title.focus();
	            return;
	        }
	        if(content == ""){
	            document.data.content.focus();
	            return;
	        }
	        // 폼에 입력한 데이터를 서버로 전송
	        document.data.submit();
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
</head>
<body>
	<h1 class='text-center'>게시판</h1>
	<br>
	<div style='width:800px; margin:auto;'>
	<form name='data' method='post' action='${path}/gongjiboard/board/update.data'>
	<table class='table table-bordered table-sm' width='800px'>
	<tr>
	<th class='text-center' width='70px'>번호</th>
	<td><input type='text' name='id' id='id' value='${data.id}' readonly /></td>
	</tr>
	<tr>
	<th class='text-center' width='70px'>제목</th>
	<td><input type='text' name='title' id='title' value='${data.title}' maxlength='40' style='width:710px;' required /></td>
	</tr>
	<tr>
	<th class='text-center' width='70px'>일자</th>
	<td>${data.date}</td>
	</tr>
	<tr>
	<th class='text-center' width='70px'>내용</th>
	<td>
	<textarea name='content' id='content' style='resize:none; width:710px; height:300px;' onKeyUp='checkLength(this);'
				  onKeyDown='checkLength(this);'>${data.content}</textarea>
	</td>
	</tr>
	</table>
	<div class='text-right'>
		<input type='button' class="btn btn-default btn-sm" value='취소' id='btnlist' />
		<input type='button' class="btn btn-default btn-sm" value='수정' id='btnupdate' />
	</div>
	</form>
	</div>
</body>
</html>