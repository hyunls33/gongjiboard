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
<script>
	var nowPage = 0;
	$(document).ready(function(){
        listReply2(nowPage); //댓글 목록 불러오기(json 리턴방식)
        
        //댓글 쓰기 버튼 클릭 이벤트 (ajax로 처리)
        $("#btnReply").click(function(){
            var replytext=$("#replytext").val();
            var replyer=$("#replyer").val();
            if ((replytext != "") && (replyer != "")) { //값이 있을 경우에만 실행
            	$("#replytext").val("");                //textarea초기화하기(내용)
            	$("#replyer").val("");                  //input초기화하기(작성자)
                var bno="${dto.id}"
                var param="replytext="+replytext+"&id="+bno+"&replyer="+replyer;
                $.ajax({                
                    type: "post",
                    url: "${path}/gongjiboard/board/reply/insert",
                    data: param,
                    success: function(){                //작업이 성공시에만 실행함
                        alert("댓글이 등록되었습니다.");       //등록 알림
                        listReply2(nowPage);                   //목록 새로 불러오기
                    }
                });
            }
        });

        //목록으로 돌아가기
		$("#btnlist").click(function(){
	    	location.href="${path}/gongjiboard/board/list?curPage="+${curPage};
	    });
	    
        //수정하기
		$("#btnupdate").click(function(){
			location.href="${path}/gongjiboard/board/update/"+${dto.id};
	    });
		
        //삭제하기
	    $("#btndelete").click(function(){
            if(confirm("삭제하시겠습니까?")){
            	location.href="${path}/gongjiboard/board/delete?id="+${dto.id};
            }
        });
	});
	
    //RestController방식 (Json)
    //댓글 목록 조회 (json)
    function listReply2(check){
    	if (check == 0) {      //페이지 수가 0일 경우
    		nowPage = 1;       //페이지 1로 지정
    	} else {               //아닌경우
    		nowPage = check;   //해당 페이지로 현재페이지 지정
    	}
        $.ajax({
            type: "get",
            //contentType: "application/json", ==> 생략가능(RestController이기때문에 가능)
            url: "${path}/gongjiboard/board/reply/listJson?id=${dto.id}&curPage="+nowPage,
            success: function(result){//작업이 성공시에만 실행함
                var output = "<table class='table' style='width:800px;'>";//변수에 결과를 테이블형식으로 저장하기
                for(var i in result.list){//hashmap으로 넘어온 값 중에 list인 값만 사용
                    output += "<tr>";
                    output += "<td class='td_"+result.list[i].rno+"'>";
                    output += "<span id='replyer_"+result.list[i].rno+"'>"+result.list[i].replyer+"</span>";
                    output += "(<span id='regdate_"+result.list[i].rno+"'>"+result.list[i].regdate+"</span>)";
                    output += "<div style='float:right;'>";
                    output += "<input type='button' class='btn btn-default btn-sm' value='수정' onclick='replyUpdate("+result.list[i].rno+")' /> ";
                    output += "<input type='button' class='btn btn-default btn-sm' value='삭제' onclick='replyDelete("+result.list[i].rno+")' />";
                    output += "</div><br>";
                    output += "<div class='replyin_"+result.list[i].rno+"'>"+result.list[i].replytext+"</div></td>";
                    output += "<tr>";
                }
                output += "</table>";
                $("#listReply").html(output);//id가 listReply인 div안에 결과를 테이블로 출력하기 
                
                printReplyPage(result.page);//hashmap으로 넘어온 값 중에 page인 값만 보내기(페이징 처리하기위해서)
            }
        });
    }
    
    //댓글 페이징 처리
    function printReplyPage(page) {
    	var p = "";

		//맨 첫페이지로 돌아가기
    	if (page.curPage > 1) {
    		p += '<li class="page-item"><a class="page-link" onclick="listReply2(1)">«</a></li>';
    	}
		//이전 블록으로 돌아가기
    	if (page.curBlock > 1) {
    		p += "<li class='page-item'><a class='page-link' onclick='listReply2("+page.prevPage+")'>‹</a></li>";
    	}

		//페이지 숫자 출력
    	for (var i = page.blockStart; i <= page.blockEnd; i++) {
    		if (i != nowPage) {
    			p += "<li class='page-item'><a class='page-link' onclick='listReply2("+i+");'>"+i+"</a></li>";
    		} else {
    			p += "<li class='page-item active'><span class='page-link'>"+i+"</span></li>";
    		}
    	}
    	
		//다음 블록으로 넘어가기
    	if (page.curBlock < page.totBlock) {
    		p += "<li class='page-item'><a class='page-link' onclick='listReply2("+page.nextPage+")'>›</a></li>";
    	}
		//맨 마지막페이지로 돌아가기
		if (page.curPage < page.totPage) {
			p += "<li class='page-item'><a class='page-link' onclick='listReply2("+page.totPage+")'>»</a></li>";
    	}
		
    	$("#printReplyList").html(p);//id가 printReplyList인 div안에 댓글 페이징 출력하기 
    }
	
  	//댓글 수정 버튼 클릭 함수(수정 가능한 입력창으로 전환하기)
    function replyUpdate(rno){
  		var replyer = $("#replyer_"+rno).text();//작성자명 가져오기(div안의 값 가져오기)
  		var regdate = $("#regdate_"+rno).text();//작성날짜 가져오기(div안의 값 가져오기)
  		var replytext = $(".replyin_"+rno).text();//댓글 내용 가져오기(div안의 값 가져오기)
    	var a ="";
  		a += "<span id='replyer_"+rno+"'>"+replyer+"</span>";
  		a += "(<span id='regdate_"+rno+"'>"+regdate+"</span>)";
  		a += "<div style='float:right;'>";
  		a += "<input type='button' class='btn btn-default btn-sm' value='확인' onclick='replyUpdateProc("+rno+")' /> ";
   	 	a += "<input type='button' class='btn btn-default btn-sm' value='취소' onclick='replyUpdateCancel()' />";
  		a += "</div><br>";
   	 	a += "<textarea class='form-control' id='replytext_"+rno+"' style='resize:none;'>"+replytext+"</textarea>";
   	    
   	    $(".td_"+rno).html(a);
    }
 	
 	//댓글 수정 처리 함수
    function replyUpdateProc(rno){
    	var replytext = $('#replytext_'+rno).val();//textarea에 입력된 값 가져오기
    	if (replytext != "") {
    		$.ajax({
                url : '${path}/gongjiboard/board/reply/update',
                type : 'post',
                data : {'replytext' : replytext, 'rno' : rno},
                success : function(data){
                	listReply2(nowPage); //댓글 수정후 목록 출력
                }
            });
    	}
    }
 	
 	//댓글 수정 취소시에는 새로고침하기
    function replyUpdateCancel() {
    	listReply2(nowPage);//다시 목록 새로고침
    }
  	
    //댓글 삭제 버튼 클릭 함수
    function replyDelete(rno){
    	var param="rno="+rno;
        $.ajax({
            url : '${path}/gongjiboard/board/reply/delete',
            type : 'post',
            data: param,
            success : function(){
            	listReply2(nowPage); //댓글 삭제후 목록 출력
            }
        });
    }

</script>
</head>
<body>
	<h1 class='text-center'>게시판</h1>
	<br>
	<div style='width:800px; margin:auto;'>
		<table class='table table-bordered table-sm' width='800px'>
			<tr>
				<th class='text-center' width='70px'>번호</th>
				<td>${dto.id}</td>
			</tr>
			<tr>
				<th class='text-center' width='70px'>제목</th>
				<td>${dto.title}</td>
			</tr>
			<tr>
				<th class='text-center' width='70px'>조회수</th>
				<td>${dto.viewcnt}</td>
			</tr>
			<tr>
				<th class='text-center' width='70px'>일자</th>
				<td>${dto.date}</td>
			</tr>
			<tr>
				<th class='text-center' width='70px'>내용</th>
				<td><textarea readonly='readonly' style='resize:none; width:710px; height:300px;'>${dto.content}</textarea></td>
			</tr>
		</table>
		<div class='text-right'>
			<input type='button' class="btn btn-default btn-sm" value='목록' id='btnlist' />
			<input type='button' class="btn btn-default btn-sm" value='수정' id='btnupdate' />
			<input type='button' class="btn btn-default btn-sm" value='삭제' id='btndelete' />
		</div>
		
		<!-- 댓글 부분 -->
		<br>
		<input type='text' id='replyer' style='width:800px;' placeholder="작성자이름" /><br>
		<textarea rows="3" cols="80" style='width:800px; resize:none;' id="replytext" placeholder="댓글을 작성해주세요"></textarea>
		<br><br>
		<button type="button" class="btn btn-default btn-sm" style='float:right;' id="btnReply">댓글 작성</button>
		<br><br>
		
		<!-- 댓글 목록 출력할 위치 -->
		<div id="listReply"></div>
		<!-- 댓글 페이징 출력할 위치 -->
		<ul id='printReplyList' class='pagination pagination-sm justify-content-center'></ul>
	</div>
</body>
</html>