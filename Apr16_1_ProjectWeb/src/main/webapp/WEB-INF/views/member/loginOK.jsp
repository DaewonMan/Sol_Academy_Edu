<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>${waveMember.wm_name }입니다 ★ (@${waveMember.wm_id })</title>
<link rel="shortcut icon" href="resources/img/tabimg.ico" type="image/x-icon">
<link rel="stylesheet" href="resources/css/index.css">
<link rel="stylesheet" href="resources/css/login.css">
<link rel="stylesheet" href="resources/css/follow.css">

<link rel="stylesheet" href="resources/css/query.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="resources/js/jquery.bxslider.min.js"></script>

<script type="text/javascript" src="resources/js/validCheck.js"></script>
<script type="text/javascript" src="resources/js/member.js"></script>
<script type="text/javascript" src="resources/js/follow.js"></script>
<script type="text/javascript" src="resources/js/post.js"></script>
<script type="text/javascript">
	$(function(){
		var myId = '${loginMember.wm_id }';
		var yourId = '${waveMember.wm_id }';
		var waverOpen = "${waveMember.wm_open}";
		var firstLoc = 0;
		
		var mainImg = $("<img></img>").attr("src", "resources/img/wonsta.jpg").css("width", "35px");
		var mainImgA = $("<a></a>").attr("href", "home.go").css("transition", ".5s ease").css("opacity", "0");
		mainImgA.append(mainImg);
		$(".cttHeadTd").append(mainImgA);
		mainImgA.hide();
		
		$(window).scroll(function(){
			var wScrollTop = $(window).scrollTop(); // 스크롤 상단의 좌표
			
			if(wScrollTop == 0) {
				$(".homeImg").show();
				$(".homeImg").css("opacity", "1");
				mainImgA.css("opacity", "0");
				mainImgA.hide();
				
				$(".cttHeadTd").attr("align", "left");
				$("#cttHead").css("height", "100px");
				$("#mainHead").css("height", "100px");
				
				$("#searchInfo").css("opacity", "1");
			} else {
				$("#searchIdDiv").hide();
				$("#triDiv").hide();
				
				mainImgA.show();
				mainImgA.css("opacity", "1");
				$(".homeImg").css("opacity", "0");
				$(".homeImg").hide();
				
				$(".cttHeadTd").attr("align", "center");
				$("#cttHead").css("height", "70px");
				$("#mainHead").css("height", "70px");
				
				$("#searchInfo").css("opacity", "0");
				$("#searchInfo").val("");
			}
		});
		
		$("#bottomMenuDv").click(function(){
			addPostGo();
		});
		// 하단 메뉴
		$("#bottomMenuDv").mouseenter(function(){
			$("#bottomMenuDv").css("height", "120px");
			
			$("#bottomMenuDv").css("background-position", "0 0");
					
			$("#addPostDiv").css("color","white");
			
		});
		$("#bottomMenuDv").mouseleave(function(){
			$("#bottomMenuDv").css("height", "50px");
			$("#bottomMenuDv").css("background-color", "##BCAAA4");
			$("#bottomMenuDv").css("background-image", "linear-gradient(to left, transparent, transparent 50%, #0D47A1 50%, #0D47A1)");
			$("#bottomMenuDv").css("background-position", "100% 0");
			$("#bottomMenuDv").css("background-size", "200% 100%");
			$("#addPostDiv").css("color","#333333");
		});
		
		// 총 게시물 수
		$.ajax({
			url: "post.count",
			data: {wp_id: yourId},
			success: function(xml){
				//$("table").empty(); // 반복문 돌기전에 테이블안에 있는거 지우기
				$("#postCntSpan").empty();
				var cnt = 0;
				$(xml).find("post").each(function(e){
					cnt = e + 1;
				});
				$("#postCntSpan").text(cnt);
			}
		});
		
		// 검색 input에 대한 포커스
		$('#searchInfo').focus(function(){
			$(this).css('text-align', 'left');
			$('#searchIdDiv').css("opacity", "1");
			$('#triDiv').css("opacity", "1");
		});
		
		
		$('#searchInfo').blur(function(){
			$(this).css('text-align', 'center');
			$('#searchIdDiv').css("opacity", "0");
			$('#triDiv').css("opacity", "0");
		});
		
		// 검색 내용 출력
		searchIdEvent(myId);
		
		// 팔로워 수
		followerCnt(yourId);
		
		// 팔로우 수
		followCnt(yourId);
		
		// 팔로워 수 클릭 시
		searchFollowerEvent(yourId, myId);
		
		// 팔로우 수 클릭 시
		searchFollowEvent(yourId, myId);
		
		// 다른 멤버의  게시물 페이지로 갔을 때 공개 여부에 따른 화면 표시
		if(yourId != myId && waverOpen == 'close') {
			followOrNot2(yourId, myId);
		}
		
		// 팔로우 신청 알람 아이콘 클릭 시
		waitersForFollow(myId);
	});
</script>
</head>
<body id="loBd">
	<table id="mainTb">
		<tr id="mainTbFistTr">
			<td align="center" id="mainHead">
				<table id="cttHead">
					<tr>
						<td class="cttHeadTd" align="left">
							<a href="home.go" class="homeImg"><img src="resources/img/wonsta.jpg">&nbsp;|&nbsp;wonsta</a>
						</td>
						<td class="cttHeadTd2" align="center">
							<input id="searchInfo" placeholder="검  색">
						</td>
						<td align="right" class="cttHeadTd2">
							<div align="right" id="cttHeadDiv2">
								${loginMember.wm_name }님 환영!							
							</div>
							<div align="right" id="cttHeadDiv">
								<button onclick="logoutDo();">로그아웃</button>
								<button onclick="updateDo();">정보수정</button>
								<button onclick="deleteDo();">회원삭제</button>							
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center" id="mainTitle">
				<jsp:include page="${memberTitle }"></jsp:include>
			</td>
		</tr>
		<tr>
			<td class="mainCttTd" align="center" valign="top">
				<jsp:include page="../${contentArea }"></jsp:include>
			</td>
		</tr>
	</table>
	<c:if test="${loginMember.wm_id == waveMember.wm_id }">
		<div id="bottomMenuDv" align="center">
			<div id="addPostDiv">게시물 추가하기</div>
		</div>	
	</c:if>
	<c:if test="${postDetail != null}">
		<div id="showPostDiv">
			<jsp:include page="../${postDetail }"></jsp:include>
		</div>
	</c:if>
</body>
</html>