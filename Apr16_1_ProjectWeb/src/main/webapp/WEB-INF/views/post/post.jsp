<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		$(".postUploadInputTd").each(function(e, c){
			var child = $(c).children();
			child.focus(function(){
				$(this).css("border", "1px solid #9E9E9E");
			});
			child.focusout(function(){
				$(this).css("border", "1px solid #E0E0E0");
			});
		});
	
		/*게시물 올리기 border*/
		$("#postUploadTb").focusin(function(){
		     $(this).css("border", "3px solid #795548");
		});
		$("#postUploadTb").focusout(function(){
			$(this).css("border", "1px solid #E0E0E0");
		});
		
	});
</script>
</head>
<body>
	<form action="post.do" onsubmit="return postCheck();" method="post" enctype="multipart/form-data" name="postForm">
		<table id="postUploadTb">
			<tr>
				<td colspan="2" align="center" id="postUploadTitleTd">게시물 올리기!</td>
			</tr>
			<tr>
				<td class="postUploadTd">제목 : </td>
				<td class="postUploadInputTd" align="center">
					<input placeholder="문구 입력" name="wp_title">
				</td>
			</tr>
			<tr>
				<td class="postUploadTd">해쉬태그 : </td>
				<td class="postUploadInputTd" align="center">
					<input placeholder="해쉬태그 입력" name="wp_hash">
				</td>
			</tr>
			<tr>
				<td class="postUploadTd">사진 : </td>
				<td class="postUploadInputTd2" align="center">
					<input type="file" name="wp_img">
				</td>
			</tr>
			<tr>
				<td id="postUploadBtnTd" colspan="2" align="center">
					<input type="submit" value="올 리 기!">
				</td>
			</tr>
		</table>
	
	</form>
</body>
</html>