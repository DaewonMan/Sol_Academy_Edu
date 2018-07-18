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
		var postNum = ${postDetails.wp_no }; // �Խù� ��ȣ
		var likeCnt = 0; // ���ƿ� ����
		var mId = '${loginMember.wm_id }';
		var likeBool = "";
		
		// ���� ��
		checkRepleCnt(postNum);		
		
		// �Խù� ������ �� ���ƿ� ����; likeCnt�� 0�� ��
		$.ajax({
			url: "like.update",
			data: {wp_no: postNum, wp_like: likeCnt, wlb_id: mId, wlb_pno: postNum, wlb_bool: likeCnt},
			success: function(xml){
				
				// ��Ʈ����
				likeBool = $(xml).find("likebool").find("wlb_bool").text();
				if(likeBool != ""){
					likeBool *= 1;						
				}
				if(likeBool == 1) {
					$(".likeBtn").text("��");
					$(".likeBtn").css("color", "#D50000");
				} else {
					$(".likeBtn").text("��");
					$(".likeBtn").css("color", "black");
				}					
			}
		});
		
		// ���ƿ� ����
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
		
		// ��� �߰��ϱ�
		$("#repleArea").keyup(function(e){
			//alert(e.keyCode); 13�� ����
			if(e.keyCode == 13) {
				$("#repleBtn").trigger("click");
			}
		});
		
		// �Խù� ���� �̹���
		$("#trashImg").mouseenter(function(){
			$(this).attr("src","resources/img/trash2.png");
		});
		$("#trashImg").mouseleave(function(){
			$(this).attr("src","resources/img/trash1.jpg");			
		});
		
		// ���ƿ� ��ư
		$(".likeBtn").click(function(){
			var hrt = $(this).text();
			if(hrt == "��") {
				$(this).text("��");
				$(this).css("color", "#D50000");
				likeCnt = 1;
			} else {
				$(this).text("��");
				$(this).css("color", "black");
				likeCnt = -1;
			}
			// ���ƿ� ��ư ���� �� ���ƿ� ����; likeCnt�� 1 or -1�� ��
			$.ajax({
				url: "like.update",
				data: {wp_no: postNum, wp_like: likeCnt, wlb_id: mId, wlb_pno: postNum, wlb_bool: likeCnt},
				success: function(){
					// ���ƿ� ����
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
		
		// ���ƿ� Ŭ���� ũ�� ��ȭ
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
							<span class="likeBtn">��</span>&emsp;&emsp;&emsp;<span class="commentBtn">��</span><br>
							���ƿ� <span class="likeCnt"></span>��&nbsp;��� <span class="commentCnt"></span>��
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
								<textarea id="repleArea" name="wr_reple" placeholder="��� �ޱ�..." ></textarea>	
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