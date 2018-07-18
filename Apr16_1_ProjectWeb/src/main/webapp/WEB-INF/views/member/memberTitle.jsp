<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/follow.js"></script>
<script type="text/javascript" src="resources/js/post.js"></script>
<script type="text/javascript">
	function followBtnOper(yourId, myId, yesOrNo, waverOpen) {
		
		if(yesOrNo == "팔로우") {
			// 비공개면 승인받아야함
			if(waverOpen == 'close') {
				followWait(yourId, myId);
			} else {
				followDo(yourId, myId);
			}
		} else if(yesOrNo == "승인중") {
			unFollowWaitDo(yourId, myId);
		} else {
			unfollowDo(yourId, myId);
		}
		
		// 팔로우, 팔로잉 버튼 누를 시 공개여부 즉각 반응
		//if(waverOpen == 'close') {
			//followOrNot2(yourId, myId);
		//}
	}
	
	$(function(){
		var myId = '${loginMember.wm_id }';
		var yourId = '${waveMember.wm_id }';
		var waverOpen = "${waveMember.wm_open}";
		
		// 팔로우 신청을 여부
		if(followWaitOrNot(yourId, myId) == 0) {
			// 팔로우 여부
			followOrNot(yourId, myId);				
		}
		
		// 팔로우 하기
		$('#followBnt').click(function(){
			var yesOrNo = $(this).text();
			followBtnOper(yourId, myId, yesOrNo, waverOpen);
		});
		
		// 언팔로우 하기
		$('#followingBnt').click(function(){
			var yesOrNo = $(this).text();
			followBtnOper(yourId, myId, yesOrNo, waverOpen);
		});
		
		// 승인중 취소하기
		$('#followWaitBnt').click(function(){
			var yesOrNo = $(this).text();
			followBtnOper(yourId, myId, yesOrNo, waverOpen);
		});
	});
</script>
</head>
<body>
	<table id="cttTitle">
		<tr>
			<td align="center" class="logOkImgTd">
				<img src="resources/img/${waveMember.wm_img }">
			</td>
			<td align="center">
				<table id="profileTb">
					<tr>
						<td colspan="2">${waveMember.wm_id }</td>
						<td><button id="followBnt">팔로우</button></td>
					</tr>
					<tr>
						<td>게시물: <span id="postCntSpan"></span></td>
						<td id="followerTd" style="cursor: pointer;">팔로워: <span id="followerCntSpan"></span></td>
						<td id="followTd" style="cursor: pointer;">팔로우: <span id="followCntSpan"></span></td>
					</tr>
					<tr>
						<td colspan="3">${waveMember.wm_name }</td>
					</tr>
				</table>
			</td>
			<td align="center"></td>
		</tr>
	</table>
</body>
</html>