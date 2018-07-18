<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>원스타그램입니다</title>
<link rel="shortcut icon" href="resources/img/tabimg.ico"
	type="image/x-icon">
<link rel="stylesheet" href="resources/css/index.css">
<link rel="stylesheet" href="resources/css/login.css">
<link rel="stylesheet" href="resources/css/query.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="resources/js/jquery.bxslider.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#slider').bxSlider( {
			mode: 'horizontal',
		    speed: 2000,        
		    pager: false,      
		    moveSlides: 1,     
		    slideWidth: 246,
		    slideMargin: 0,
		    auto: true,        
		    autoHover: true,
		    pause: 5000,
		    controls: false
		});
	});
</script>
</head>
<body>
	<div id="firstMainDiv">
		<table id="firstMainTb">
			<tr>
				<td id="firstMainImgTd">
					<div id="firstMainImgDiv">
						<div id="slider">
							<img class="firstMainImg" src="resources/img/wonsta.jpg">
							<img class="firstMainImg" src="resources/img/bench.jpg">
							<img class="firstMainImg" src="resources/img/pado.jpg">
							<img class="firstMainImg" src="resources/img/eunha.jpg">						
						</div>
					</div>
				</td>
				<td align="center"><jsp:include page="${loginArea }"></jsp:include>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>