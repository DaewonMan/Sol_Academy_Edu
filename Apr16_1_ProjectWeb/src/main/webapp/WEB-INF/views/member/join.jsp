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
		
		/*�����ϱ� border*/
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
					<input placeholder="ID(�ִ�10��)" name="wm_id" id="joinID">
				</td>
				<td align="center">
					<span id="joinIDResult">ID �Է�</span>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="joinCttTd">
					<input type="password" placeholder="Password(�Ҵ빮��,��������)" name="wm_pw">
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
						</select>��&nbsp;&nbsp;
						<select name="wm_m" class="joinSelect2">
							<c:forEach var="m" begin="1" end="12">
								<option>${m }</option>
							</c:forEach>
						</select>��&nbsp;&nbsp;
						<select name="wm_d" class="joinSelect3">
							<c:forEach var="d" begin="1" end="31">
								<option>${d }</option>
							</c:forEach>
						</select>��
				</td>
			</tr>
			<tr>
				<td class="joinCttTd">
					<input readonly="readonly" id="add1" placeholder="�����ȣ" name="wm_add1">
				</td>
				<td align="center">
					<span id="addrSearch">�����ȣ�˻�</span>
				</td>
			</tr>
			<tr>
				<td class="joinCttTd" colspan="2">
					<input readonly="readonly" id="add2" placeholder="�ּ�" name="wm_add2">
				</td>
			</tr>
			<tr>
				<td class="joinCttTd" colspan="2">
					<input placeholder="���ּ�" name="wm_add3">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="joinCttTd3">
					<input type="file" name="wm_img">
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2" class="joinBttTd">
					<input type="submit" value="�����ϱ� ��">
				</td>
			</tr>
		</table>
	
	</form>
</body>
</html>