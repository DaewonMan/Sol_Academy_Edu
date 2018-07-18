<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/validCheck.js"></script>
<link rel="stylesheet" href="resources/css/login.css">
<script type="text/javascript" src="resources/js/member.js"></script>
<script type="text/javascript" src="resources/js/address.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	$(function(){
		connectAddressSearchEvent();
		connectIdCheckEvent();
		
		$(".joinCttTd").each(function(e, c){
			var child = $(c).children();
			child.focus(function(){
				$(this).css("border", "1px solid #9E9E9E");
			});
			child.focusout(function(){
				$(this).css("border", "1px solid #E0E0E0");
			});
		});
		$(".joinCttTd2").each(function(e, c){
			var child = $(c).children();
			child.focus(function(){
				$(this).css("border", "1px solid #9E9E9E");
			});
			child.focusout(function(){
				$(this).css("border", "1px solid #E0E0E0");
			});
		});
		
		/*가입하기 border*/
		$("#joinTb").focusin(function(){
		     $(this).css("border", "3px solid #795548");
		});
		$("#joinTb").focusout(function(){
			$(this).css("border", "1px solid #E0E0E0");
		});
	});
</script>
</head>
<body>
	<form action="welcome" onsubmit="return joinCheck();" method="post" enctype="multipart/form-data" name="joinForm">
		<table id="joinTb">
			<tr>
				<td colspan="2" class="joinTitleTd" align="center">
					wonsta
				</td>
			</tr>
			<tr>
				<td class="joinCttTd">
					<input placeholder="ID(최대10자)" name="wm_id" id="joinID">
				</td>
				<td align="center">
					<span id="joinIDResult">ID 입력</span>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="joinCttTd">
					<input type="password" placeholder="Password(소대문자,숫자조합)" name="wm_pw">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="joinCttTd">
					<input type="password" placeholder="Password Check" name="wm_pwChk">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="joinCttTd">
					<input placeholder="Name" name="wm_name">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="joinCttTd2">
						<select name="wm_y" class="joinSelect">
							<c:forEach var="y" begin="${curYear - 50 }" end="${curYear }">
								<option>${y }</option>
							</c:forEach>
						</select>년&nbsp;&nbsp;
						<select name="wm_m" class="joinSelect2">
							<c:forEach var="m" begin="1" end="12">
								<option>${m }</option>
							</c:forEach>
						</select>월&nbsp;&nbsp;
						<select name="wm_d" class="joinSelect3">
							<c:forEach var="d" begin="1" end="31">
								<option>${d }</option>
							</c:forEach>
						</select>일
				</td>
			</tr>
			<tr>
				<td class="joinCttTd">
					<input readonly="readonly" id="add1" placeholder="우편번호" name="wm_add1">
				</td>
				<td align="center">
					<span id="addrSearch">우편번호검색</span>
				</td>
			</tr>
			<tr>
				<td class="joinCttTd" colspan="2">
					<input readonly="readonly" id="add2" placeholder="주소" name="wm_add2">
				</td>
			</tr>
			<tr>
				<td class="joinCttTd" colspan="2">
					<input placeholder="상세주소" name="wm_add3">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="joinCttTd3">
					<input type="file" name="wm_img">
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2" class="joinBttTd">
					<input type="submit" value="가입하기 ♡">
				</td>
			</tr>
		</table>
	
	</form>
</body>
</html>