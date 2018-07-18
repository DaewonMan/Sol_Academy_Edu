<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/post.css">
<script type="text/javascript">
	$(function(){
		
		// 댓글 수
		$(".postTd").mouseenter(function(){
			var ele = $(this).children(); // 하위 a 태그 받기
			var ele2 = ele.children(); // 하위 img div
			var ele3 = ele2.eq(1).children(); // 하위 span들
			var ele4 = ele3.eq(0); // .likeCntSpan
			var ele5 = ele3.eq(1); // .repleCntSpan
			//alert(ele4.text());
			
			var eleNo = ele.attr("href").replace("post.show?wp_id=${waveMember.wm_id}&wp_no=", ""); // a태그의 속성인 href값의 댓글수만 남기고 공백으로
			eleNo *= 1; // 숫자로 변환
			
			// 댓글 갯수
			$.ajax({
				url: "reple.count",
				data: {wr_rno: eleNo},
				success: function(xml){
					ele5.empty();
					var cnt = 0;
					$(xml).find("reple").each(function(e){
						cnt = e + 1;
					});
					ele5.text(cnt);
				}
			});
			
			// 좋아요 갯수
			$.ajax({
				url: "like.cnt",
				data: {wp_no: eleNo},
				success: function(xml){
					ele4.empty();
					var cnt = $(xml).find("post").find("wp_like").text();
					ele4.text(cnt);
				}
			});
		});
		
	});
</script>
</head>
<body id="homeBd">
	<c:choose>
		<c:when test="${posts.size() == 0}">
			<div id="noPostDiv">
				<img src="resources/img/wonsta.jpg"><p>
				<span>게시물 없음</span>
			</div>
		</c:when>
		<c:when test="${posts.size() >= 1}">
			<table id="postTb">
				<c:forEach begin="0" end="${posts.size()-1}" step="3" varStatus="status">
					<tr>
						<td class="postTd" align="center">
							<c:if test="${posts.size()-1 >= status.current}">
								<a href="post.show?wp_id=${posts.get(status.current).wp_id}&wp_no=${posts.get(status.current).wp_no}" class="postATag${posts.get(status.current).wp_no} }">
									<img id="postImg" src="resources/img/${posts.get(status.current).wp_img}">
		    						<div class="middle">♥ <span class="likeCntSpan">0</span>개  ☆ <span class="repleCntSpan">0</span>개</div>
	  							</a>						
							</c:if>
						</td>
						<td class="postTd" align="center">
							<c:if test="${posts.size()-1 >= status.current+1}">
								<a href="post.show?wp_id=${posts.get(status.current+1).wp_id}&wp_no=${posts.get(status.current+1).wp_no}" class="postATag${posts.get(status.current+1).wp_no} }">
									<img id="postImg" src="resources/img/${posts.get(status.current+1).wp_img}">													
	    							<div class="middle">♥ <span class="likeCntSpan">0</span>개  ☆ <span class="repleCntSpan">0</span>개</div>
	  							</a>
							</c:if>
						</td>
						<td class="postTd" align="center">
							<c:if test="${posts.size()-1 >= status.current+2}">
								<a href="post.show?wp_id=${posts.get(status.current+2).wp_id}&wp_no=${posts.get(status.current+2).wp_no}" class="postATag${posts.get(status.current+2).wp_no} }">
									<img id="postImg" src="resources/img/${posts.get(status.current+2).wp_img}">
		    						<div class="middle">♥ <span class="likeCntSpan">0</span>개  ☆ <span class="repleCntSpan">0</span>개</div>
		  						</a>					
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
	</c:choose>
</body>
</html>