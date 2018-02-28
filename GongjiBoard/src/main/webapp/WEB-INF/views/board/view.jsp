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
	$(document).ready(function(){
        listReply2(); //댓글 목록 불러오기(json 리턴방식)
        
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
                        listReply2();                   //목록 새로 불러오기
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
    function listReply2(){
        $.ajax({
            type: "get",
            //contentType: "application/json", ==> 생략가능(RestController이기때문에 가능)
            url: "${path}/gongjiboard/board/reply/listJson?id=${dto.id}",
            success: function(result){      //작업이 성공시에만 실행함
                console.log(result);
                var output = "<table class='table' style='width:800px;'>";//변수에 결과를 테이블형식으로 저장하기
                for(var i in result){
                    output += "<tr>";
                    output += "<td class='td_"+result[i].rno+"'>";
                    output += "<span id='replyer_"+result[i].rno+"'>"+result[i].replyer+"</span>";
                    output += "(<span id='regdate_"+result[i].rno+"'>"+result[i].regdate+"</span>)";
                    output += "<div style='float:right;'>";
                    output += "<input type='button' class='btn btn-default btn-sm' value='수정' onclick='replyUpdate("+result[i].rno+")' /> ";
                    output += "<input type='button' class='btn btn-default btn-sm' value='삭제' onclick='replyDelete("+result[i].rno+")' />";
                    output += "</div><br>";
                    output += "<div class='replyin_"+result[i].rno+"'>"+result[i].replytext+"</div></td>";
                    output += "<tr>";
                }
                output += "</table>";
                $("#listReply").html(output);//id가 listReply인 div안에 결과를 테이블로 출력하기
            }
        });
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
                	listReply2(); //댓글 수정후 목록 출력
                }
            });
    	}
    }
 	
 	//댓글 수정 취소시에는 새로고침하기
    function replyUpdateCancel() {
    	listReply2();//다시 목록 새로고침
    }
  	
    //댓글 삭제 버튼 클릭 함수
    function replyDelete(rno){
    	var param="rno="+rno;
        $.ajax({
            url : '${path}/gongjiboard/board/reply/delete',
            type : 'post',
            data: param,
            success : function(){
            	listReply2(); //댓글 삭제후 목록 출력
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
				<td>${dto.content}</td>
			</tr>
		</table>
		<div class='text-right'>
			<input type='button' class="btn btn-default btn-sm" value='목록' id='btnlist' />
			<input type='button' class="btn btn-default btn-sm" value='수정' id='btnupdate' />
			<input type='button' class="btn btn-default btn-sm" value='삭제' id='btndelete' />
		</div>
		<!-- **댓글 목록 출력할 위치 -->
		<!-- 댓글 부분 -->
		<br>
		<input type='text' id='replyer' style='width:800px;' placeholder="작성자이름" /><br>
		<textarea rows="3" cols="80" style='width:800px; resize:none;' id="replytext" placeholder="댓글을 작성해주세요"></textarea>
		<br><br>
		<button type="button" class="btn btn-default btn-sm" style='float:right;' id="btnReply">댓글 작성</button>
		<br><br>
		<!-- **댓글 목록 출력할 위치 -->
		<div id="listReply"></div>
	</div>
</body>
</html>