<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>${waveMember.wm_name }입니다 ★ (@${waveMember.wm_id })</title>
<link rel="stylesheet" href="resources/css/login.css">

<link rel="stylesheet" href="resources/css/query.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="resources/js/jquery.bxslider.min.js"></script>

<script type="text/javascript" src="resources/js/validCheck.js"></script>
<script type="text/javascript" src="resources/js/member.js"></script>
<script type="text/javascript">
	$(function(){
		$(".updateCttTd").each(function(e, c){
			var child = $(c).children();
			child.focus(function(){
				$(this).css("border", "1px solid #9E9E9E");
			});
			child.focusout(function(){
				$(this).css("border", "1px solid #E0E0E0");
			});
		});
		$(".updateCttTd2").each(function(e, c){
			var child = $(c).children();
			child.focus(function(){
				$(this).css("border", "1px solid #9E9E9E");
			});
			child.focusout(function(){
				$(this).css("border", "1px solid #E0E0E0");
			});
		});
		
		/*수정하기 border*/
		$("#updateTb").focusin(function(){
		     $(this).css("border", "3px solid #795548");
		});
		$("#updateTb").focusout(function(){
			$(this).css("border", "1px solid #E0E0E0");
		});
	});
</script>
</head>
<body id="logJoinBd">
	<form action="update.member.go" onsubmit="return updateCheck();" method="post" enctype="multipart/form-data" name="joinForm">
		<table id="updateTb">
			<tr>
				<td colspan="2" class="updateTitleTd" align="center">
					수 정 하 기
				</td>
			</tr>
			<tr>
				<td colspan="2" class="updateCttTd" align="center">
					<input placeholder="ID" name="wm_id" value="${sessionScope.loginMember.wm_id }" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="updateCttTd" align="center">
					<input type="password" placeholder="Password" name="wm_pw">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="updateCttTd" align="center">
					<input type="password" placeholder="Password Check" name="wm_pwChk">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="updateCttTd" align="center">
					<input placeholder="Name" name="wm_name" value="${sessionScope.loginMember.wm_name }">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="updateCttTd" align="center">
						<select name="wm_y" class="updateDateSelect">
							<c:forEach var="y" begin="${curYear - 50 }" end="${curYear }">
								<option>${y }</option>
							</c:forEach>
						</select>년&nbsp;&nbsp;
						<select name="wm_m" class="updateDateSelect">
							<c:forEach var="m" begin="1" end="12">
								<option>${m }</option>
							</c:forEach>
						</select>월&nbsp;&nbsp;
						<select name="wm_d" class="updateDateSelect">
							<c:forEach var="d" begin="1" end="31">
								<option>${d }</option>
							</c:forEach>
						</select>일
					</td>
			</tr>
			<tr>
				<td class="updateCttTd2" align="right">
					<input placeholder="우편번호" name="wm_add1" value="${sessionScope.loginMember.wm_add1 }">
				</td>
				<td align="center" class="postNumSeachBtn">
					<span style="cursor: pointer;">우편번호검색</span>
				</td>
			</tr>
			<tr>
				<td class="updateCttTd" colspan="2" align="center">
					<input placeholder="주소" name="wm_add2" value="${sessionScope.loginMember.wm_add2 }">
				</td>
			</tr>
			<tr>
				<td class="updateCttTd" colspan="2" align="center">
					<input placeholder="상세주고" name="wm_add3" value="${sessionScope.loginMember.wm_add3 }">
				</td>
			</tr>
			<tr>
				<td class="updateImgTd" align="center">
					<img src="resources/img/${sessionScope.loginMember.wm_img}">
				</td>
				<td class="updateImgTd2" align="center">
					<input type="file" name="wm_img">
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2" class="updateBttTd">
					<input type="submit" value="Update">
				</td>
			</tr>
		</table>
	
	</form>
</body>
</html>