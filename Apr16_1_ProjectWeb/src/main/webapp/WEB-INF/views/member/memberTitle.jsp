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
		
		if(yesOrNo == "�ȷο�") {
			// ������� ���ι޾ƾ���
			if(waverOpen == 'close') {
				followWait(yourId, myId);
			} else {
				followDo(yourId, myId);
			}
		} else if(yesOrNo == "������") {
			unFollowWaitDo(yourId, myId);
		} else {
			unfollowDo(yourId, myId);
		}
		
		// �ȷο�, �ȷ��� ��ư ���� �� �������� �ﰢ ����
		//if(waverOpen == 'close') {
			//followOrNot2(yourId, myId);
		//}
	}
	
	$(function(){
		var myId = '${loginMember.wm_id }';
		var yourId = '${waveMember.wm_id }';
		var waverOpen = "${waveMember.wm_open}";
		
		// �ȷο� ��û�� ����
		if(followWaitOrNot(yourId, myId) == 0) {
			// �ȷο� ����
			followOrNot(yourId, myId);				
		}
		
		// �ȷο� �ϱ�
		$('#followBnt').click(function(){
			var yesOrNo = $(this).text();
			followBtnOper(yourId, myId, yesOrNo, waverOpen);
		});
		
		// ���ȷο� �ϱ�
		$('#followingBnt').click(function(){
			var yesOrNo = $(this).text();
			followBtnOper(yourId, myId, yesOrNo, waverOpen);
		});
		
		// ������ ����ϱ�
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
						<td><button id="followBnt">�ȷο�</button></td>
					</tr>
					<tr>
						<td>�Խù�: <span id="postCntSpan"></span></td>
						<td id="followerTd" style="cursor: pointer;">�ȷο�: <span id="followerCntSpan"></span></td>
						<td id="followTd" style="cursor: pointer;">�ȷο�: <span id="followCntSpan"></span></td>
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