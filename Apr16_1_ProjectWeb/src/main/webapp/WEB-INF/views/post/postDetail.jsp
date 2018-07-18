<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/post.css">
<script type="text/javascript" src="resources/js/validCheck.js"></script>
<script type="text/javascript" src="resources/js/post.js"></script>
<script type="text/javascript">
	$(function(){
		var postNum = ${postDetails.wp_no }; // 게시물 번호
		var likeCnt = 0; // 좋아요 여부
		var mId = '${loginMember.wm_id }';
		var likeBool = "";
		
		// 리플 수
		checkRepleCnt(postNum);		
		
		// 게시물 눌렀을 때 좋아요 갯수; likeCnt는 0이 들어감
		$.ajax({
			url: "like.update",
			data: {wp_no: postNum, wp_like: likeCnt, wlb_id: mId, wlb_pno: postNum, wlb_bool: likeCnt},
			success: function(xml){
				
				// 하트여부
				likeBool = $(xml).find("likebool").find("wlb_bool").text();
				if(likeBool != ""){
					likeBool *= 1;						
				}
				if(likeBool == 1) {
					$(".likeBtn").text("♥");
					$(".likeBtn").css("color", "#D50000");
				} else {
					$(".likeBtn").text("♡");
					$(".likeBtn").css("color", "black");
				}					
			}
		});
		
		// 좋아요 갯수
		$.ajax({
			url: "like.cnt",
			data: {wp_no: postNum},
			success: function(xml){
				$(".likeCnt").empty();
				var cnt = $(xml).find("post").find("wp_like").text();
				$(".likeCnt").text(cnt);
			}
		});
		/*============================================================*/
		
		// 댓글 추가하기
		$("#repleArea").keyup(function(e){
			//alert(e.keyCode); 13이 엔터
			if(e.keyCode == 13) {
				$("#repleBtn").trigger("click");
			}
		});
		
		// 게시물 삭제 이미지
		$("#trashImg").mouseenter(function(){
			$(this).attr("src","resources/img/trash2.png");
		});
		$("#trashImg").mouseleave(function(){
			$(this).attr("src","resources/img/trash1.jpg");			
		});
		
		// 좋아요 버튼
		$(".likeBtn").click(function(){
			var hrt = $(this).text();
			if(hrt == "♡") {
				$(this).text("♥");
				$(this).css("color", "#D50000");
				likeCnt = 1;
			} else {
				$(this).text("♡");
				$(this).css("color", "black");
				likeCnt = -1;
			}
			// 좋아요 버튼 누른 후 좋아요 갯수; likeCnt는 1 or -1이 들어감
			$.ajax({
				url: "like.update",
				data: {wp_no: postNum, wp_like: likeCnt, wlb_id: mId, wlb_pno: postNum, wlb_bool: likeCnt},
				success: function(){
					// 좋아요 갯수
					$.ajax({
						url: "like.cnt",
						data: {wp_no: postNum},
						success: function(xml){
							$(".likeCnt").empty();
							var cnt = $(xml).find("post").find("wp_like").text();
							$(".likeCnt").text(cnt);
						}
					});
				}
			});
		});
		
		// 좋아요 클릭시 크기 변화
		$(".likeBtn").mousedown(function(){
			$(this).css("font-size", "40pt");
		});
		$(".likeBtn").mouseup(function(){
			$(this).css("font-size", "30pt");
		});
		
	});
</script>
</head>
<body id="postDetailBD">
	
	<table class="df">
		<tr>
			<td colspan="2" align="right" class="postDetailbtnTd">
				<button onclick="goToMemberDisplay('${waveMember.wm_id}', '${loginMember.wm_id }');">x</button>			
			</td>
		</tr>
		<tr>
			<td class="postDetailImgTd" align="center">
				<img src="resources/img/${postDetails.wp_img }">
			</td>
			<td valign="top" align="center" class="postDetailDescTd">
				<table id="postDetailDescTb">
					<tr class="DescTitleTr">
						<td align="center">
							<img src="resources/img/${waveMember.wm_img }">						
						</td>
						<td class="DescIdTd">
							${postDetails.wp_id }
						</td>
						<td class="DeletePostTd" align="right">
							<c:if test="${loginMember.wm_id == waveMember.wm_id }">
								<img id="trashImg" src="resources/img/trash1.jpg" onclick="postDeleteDo(${postDetails.wp_no });">							
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="3" align="right" class="DescDateTd">
							<fmt:formatDate value="${postDetails.wp_date }" pattern="yyyy-MM-dd"/>
							
						</td>
					</tr>
					<tr>
						<td colspan="3" class="DescTitleTd" align="center">
							${postDetails.wp_title }
						</td>
					</tr>
					<tr>
						<td colspan="3" class="DescHashTd" align="center">
							${postDetails.wp_hash }
						</td>
					</tr>
					<tr>
						<td colspan="3" class="DescLikeTd">
							<span class="likeBtn">♡</span>&emsp;&emsp;&emsp;<span class="commentBtn">☆</span><br>
							좋아요 <span class="likeCnt"></span>개&nbsp;댓글 <span class="commentCnt"></span>개
						</td>
					</tr>
					<tr>
						<td colspan="3" valign="top" class="DescRShowTd">
							<div class="DescRShowDiv">
								<c:forEach var="r" items="${reples}">
									<div class="eachReples${r.wr_no }">
										<span class="DescRShowId">${r.wr_id} : </span><span class="DescRShowRep">${r.wr_reple}</span>
										<c:if test="${r.wr_id ==  loginMember.wm_id}"><span class="repleXBtn" onclick="repleDeleteDo(${r.wr_no}, ${r.wr_rno });">&nbsp;&nbsp;&nbsp;&nbsp;x</span></c:if><p>									
									</div>
								</c:forEach>														
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="3" class="DescRepleTd">
							<form action="post.reple.do">
								<textarea id="repleArea" name="wr_reple" placeholder="댓글 달기..." ></textarea>	
								<input name="wr_rno" value="${postDetails.wp_no }" style="display:none;">
								<input id="repleBtn" type="submit" style="display:none;">				
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
</body>
</html>