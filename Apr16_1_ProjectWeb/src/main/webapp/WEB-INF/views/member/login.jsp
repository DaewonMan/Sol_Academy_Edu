<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/validCheck.js"></script>
<script type="text/javascript" src="resources/js/member.js"></script>
<link rel="stylesheet" href="resources/css/login.css">
<script type="text/javascript">
	$(function(){
		
		$("#loginIdInput").focus(function(){
			$("#loginIdInput").css("border", "1px solid #9E9E9E");
		});
		$("#loginIdInput").focusout(function(){
			$("#loginIdInput").css("border", "1px solid #E0E0E0");
		});
		
		$("#loginPwInput").focus(function(){
			$("#loginPwInput").css("border", "1px solid #9E9E9E");
		});
		$("#loginPwInput").focusout(function(){
			$("#loginPwInput").css("border", "1px solid #E0E0E0");
		});
		
		/*로그인 border*/
		$("#loginTb").focusin(function(){
		     $(this).css("border", "3px solid #795548");
		});
		$("#loginTb").focusout(function(){
			$(this).css("border", "1px solid #E0E0E0");
		});
		
		/* 로그인 정보 체크 관련 */
		
		// 비밀번호를 입력하면 아이디와 비밀번호정보를 db와 비교
		$("#loginPwInput").keyup(function(){
			var id = $("#loginIdInput").val();
			var pw = $("#loginPwInput").val()
			
			$.ajax({
				url : "member.info.check",
				data : {
					wm_id : id,
					wm_pw : pw
				},
				success : function(xml) {
					var ok = $(xml).find("member").length;
					
					if (ok == 0) {
						$("#loginForm").attr("onsubmit", "return false;");
					} else if(ok > 0) {
						$("#loginForm").attr("onsubmit", "return loginCheck();");
					}
				}
			});				
			
		});
		// 회원정보와 불일치시 처리
		$("#loginBtnInput").click(function(){
			var checkInfo = $("#loginForm").attr("onsubmit");
			
			if (checkInfo == "return false;") {
				alert("일치하는 회원 정보가 없습니다!!");
				$("#loginIdInput").val("");
				$("#loginPwInput").val("");
				$("#loginIdInput").focus();	
			}
		});
		
	});
</script>
</head>
<body id="logJoinBd">
	<form id="loginForm" name="loginForm" action="login.go" onsubmit="return false;" method="post">
		<table id="loginTb">
			<tr>
				<td id="loginTitldTd" align="center">
					wonsta
				</td>
			</tr>
			<tr>
				<td align="center" class="loginInputTd">
					<input id="loginIdInput" placeholder="아이디" name="wm_id">
				</td>
			</tr>
			<tr>
				<td align="center" class="loginInputTd">
					<input id="loginPwInput" type="password" placeholder="비밀번호" name="wm_pw">
				</td>
			</tr>
			<tr>
				<td align="center" class="loginInputTd">
					<input id="loginBtnInput" type="submit" value="로 그 인">
				</td>
			</tr>
			<tr>
				<td id="loginBottomTd" align="center" valign="top"><span id="autologinFont">자동 로그인</span> <input type="checkbox" name="wm_autologin"></td>			
			</tr>
		</table>
	
	</form>
	<table id="logToJoinTb">
		<tr>
			<td class="wantJoinTd">
				계정이 없으신가요?
			</td>
			<td class="wantJoinTd">
				<a href="join.go">가입하기</a>
			</td>
		</tr>
	</table>
</body>
</html>