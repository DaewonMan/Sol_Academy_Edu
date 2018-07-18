<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/follow.js"></script>
<script type="text/javascript">
	$(function(){
		var mId = "${loginMember.wm_id}";
	
		countWaitForMe(mId);
		
		$("#postOpen").click(function(){
			var oOrC = $(this).text();
			var openOption = "";
			
			if(oOrC == "전체공개") { /// 여기서부터 수정
				alert("비공개로 바꿉니까 ?");
				openOption = "close";
			} else {
				alert("전체공개로 바꿉니까 ?");
				openOption = "open";				
			}
			
			$.ajax({
				url : "update.member.open",
				data : {
					wm_id : mId,
					wm_open: openOption
				},
				success : function(ok) {
					if(ok == "OK") {
						if(oOrC == "전체공개") {
							$("#postOpen").attr("id", "postClose").text("비공개");
						} else {
							$("#postClose").attr("id", "postOpen").text("전체공개");
						}
					}
				}
			});
			
		});
		
		$("#postClose").click(function(){
			var oOrC = $(this).text();
			var openOption = "";
			
			if(oOrC == "전체공개") { /// 여기서부터 수정
				alert("비공개로 바꿉니까 ?");
				openOption = "close";
			} else {
				alert("전체공개로 바꿉니까 ?");
				openOption = "open";				
			}
			
			$.ajax({
				url : "update.member.open",
				data : {
					wm_id : mId,
					wm_open: openOption
				},
				success : function(ok) {
					if(ok == "OK") {
						if(oOrC == "전체공개") {
							$("#postOpen").attr("id", "postClose").text("비공개");
						} else {
							$("#postClose").attr("id", "postOpen").text("전체공개");
						}
					}
				}
			});
		});
	});
</script>
</head>
<body>
	<table id="cttTitle">
		<tr>
			<td align="center" class="logOkImgTd">
				<img src="resources/img/${loginMember.wm_img }">
			</td>
			<td align="center">
				<table id="profileTb">
					<tr>
						<td>${loginMember.wm_id }</td>
						<td align="right">
							<c:if test="${waveMember.wm_open == 'open' }">
								<button id="postOpen">전체공개</button>
							</c:if>
							<c:if test="${waveMember.wm_open == 'close' }">
								<button id="postClose">비공개</button>							
							</c:if>													
						</td>
						<td>
							<button id="memberInfoUpdate" onclick="updateDo();">정보수정</button>
						</td>
					</tr>
					<tr>
						<td>게시물: <span id="postCntSpan"></span></td>
						<td id="followerTd" style="cursor: pointer;">팔로워: <span id="followerCntSpan"></span></td>
						<td id="followTd" style="cursor: pointer;">팔로우: <span id="followCntSpan"></span></td>
					</tr>
					<tr>
						<td colspan="3">${loginMember.wm_name }</td>
					</tr>
				</table>
			</td>
			<td align="left" valign="top">
				<div id="alarmWaiter" style="visibility: hidden; cursor: pointer;">
					<img src="resources/img/followme.png" style="width: 80px; padding-top: 10px;">
				</div>						
			</td>
		</tr>
	</table>
</body>
</html>