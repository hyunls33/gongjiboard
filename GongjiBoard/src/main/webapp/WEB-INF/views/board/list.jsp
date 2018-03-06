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
<script>
    $(document).ready(function(){
        $("#btnwrite").click(function(){//신규글 작성하기
            // 페이지 주소 변경(이동)
            location.href = "${path}/gongjiboard/board/write";
        });
    });
</script>
<style>
	th { background-color:#d9edf7;}
</style>
</head>
<body>
	<h1 class='text-center'>게시판</h1>
	<br>
	<!--테이블 생성 및 헤더부분 출력-->
	<div style='width:100%; margin:auto;'>
	<table class='table table-hover table-bordered table-sm' width='100%'>
		<thead>
			<tr class='text-center'>
			<th width='50px'>번호</th>
			<th>제목</th>
			<th width='60px'>조회수</th>
			<th width='110px'>등록일</th>
			</tr>
		</thead>
		<tbody>
	        <c:forEach items="${list}" var="gongji">
	        	<!-- 각 줄 클릭시 이동 -->
	            <tr onclick=location.href='${path}/gongjiboard/board/view/${gongji.id}'>
	            	<td id='num' class='text-center'>${gongji.id}</td>
	                <td>${gongji.title}
	                	<!-- ** 댓글이 있으면 게시글 이름 옆에 출력하기 -->
	                    <c:if test="${gongji.recnt > 0}">
	                    	<span style='color: red;'>(${gongji.recnt})</span>
	                    </c:if>
	                </td>
	                <td class='text-center'>${gongji.viewcnt}</td>
	                <td class='text-center'>${gongji.date}</td>
	            </tr>
	        </c:forEach>
        </tbody>
    </table>
	<ul id='pagelist' class='pagination pagination-sm justify-content-center'>
	<!-- 이전 -->
	<c:if test="${page.curPage>1 }">
		<li class="page-item">
			<a class="page-link" href="${path}/gongjiboard/board/list?curPage=1">시작</a>
		</li>
	</c:if>
	 
	<c:if test="${page.curBlock > 1 }">
		<li class="page-item">
			<a class="page-link" href="${path}/gongjiboard/board/list?curPage=${page.prevPage}">이전</a>
		</li>
	</c:if>
	
	<c:forEach var="pageNum" begin="${page.blockStart}" end="${page.blockEnd}">
	<c:choose>
	    <c:when test="${pageNum==page.curPage }">
		    <li class="page-item active">
		    	<span class="page-link">${pageNum}</span>
		    </li>
	    </c:when>
	    <c:otherwise>
	    	<li class="page-item">
				<a class="page-link" href="${path}/gongjiboard/board/list?curPage=${pageNum}">${pageNum}</a>
			</li>
	    </c:otherwise>
	</c:choose>
	</c:forEach>
	 
	<!-- 다음 -->
	<c:if test="${page.curBlock <= page.totBlock}">
		<li class="page-item">
			<a class="page-link" href="${path}/gongjiboard/board/list?curPage=${page.nextPage}">다음</a>
		</li>
	</c:if>
	 
	<!-- 마지막 페이지 -->
	<c:if test="${page.curPage < page.totPage}">
		<li class="page-item">
			<a class="page-link" href="${path}/gongjiboard/board/list?curPage=${page.totPage}">끝</a>
		</li>
	</c:if>
	</ul>
	<div class='text-right'>
	<input type='button' value='신규' class="btn btn-default btn-sm" id='btnwrite' />
	</div>
	</div>
</body>
</html>