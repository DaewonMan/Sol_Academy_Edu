<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="resources/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		// 요청 헤더
		// js로는 못 불러오고
		// DAO에서 불러와야함
		
		// 2. JSONP가 되어 있지 않으면
		// JS로는 외부에 접근이 불가능하니
		// JAVA로 접근
		// 중간 한 단계(proxy)를 거치게
		
		/* $.ajax({
			url : "movie.get",
			data : {q : "starwars"},
			success : function(xml) {
				alert(xml);
			}
		}); */
		$("#b2").click(function(){
			var movieTitle = $("#i2").val();
			// ㅋ -> %2A(UTF-8)
			movieTitle = encodeURIComponent(movieTitle);
			
			$.ajax({
				url : "movie.get",
				data : {q : movieTitle},
				success : function(xml) {
					//alert(xml);
					$("table").empty();
					$(xml).find("item").each(function(i, e){
						var title = $(e).find("title").text();
						var link = $(e).find("link").text();
						var pubDate = $(e).find("pubDate").text();
						var actor = $(e).find("actor").text();
						var userRating = $(e).find("userRating").text();
						var img = $(e).find("image").text();
						
						var i = $("<img>").attr("src", img);
						var iTd = $("<td></td>").append(i);
						
						var tTd = $("<td></td>").html(title); // b태그 나오기때문에 html로 해준다
						var lTd = $("<td></td>").text(link);
						var pTd = $("<td></td>").text(pubDate);
						var aTd = $("<td></td>").text(actor);
						var uTd = $("<td></td>").text(userRating);
						
						var tr = $("<tr></tr>").append(iTd,tTd,lTd,pTd,aTd,uTd);
						var table = $("table").append(tr);
					});
				}
			});
		});
		
		$("#i2").keyup(function(){
			$("#b2").trigger("click");
		});
		
		//===================================================
		$("#b1").click(function(){
			var cityName = $("#i1").val();
			//alert(cityName);
			
			// Cross-Domain AJAX
			// 1. JSONP(Json with Padding)
			// 서버쪽에서 파라메터 하나 지정(callback),
			// callback=asd를 넣어 요청했을때
			// 결과가 asd(....) (JS함수형태)로 나오게 설정
			// 
			
			
			// AJAX
			$.ajax({
				url: "http://api.openweathermap.org/data/2.5/weather",
				data : {q:cityName, appid:"baff8f3c6cbc28a4024e336599de28c4",
					units:"metric", lang:"kr"},
				success : function(json){
					//alert(json);
					// JSON -> String
					//alert(JSON.stringify(json));
					var w = json.weather[0].description;
					var i = json.weather[0].icon;
					i = "https://openweathermap.org/img/w/" + i + ".png";
					var t_max = json.main.temp_max;
					var t_min = json.main.temp_min;
					/*
					alert(w);
					alert(i);
					alert(t_max);
					alert(t_min);
					*/
					$("#img1").attr("src", i);
					$("#h31").text(w);
					$("#h32").text(t_max);
				}
			});
		});
		// =============================================
		$("#b3").click(function(){
			var bTitle = $("#i3").val();
			bTitle = encodeURIComponent(bTitle);
			$.ajax({
				url : "book.get",
				data : {q : bTitle},
				success : function(json){
					var ar = json.documents;
					$("table").empty();
					$.each(ar, function(i, e){
						var author = e.authors;
						var price = e.price;
						var publisher = e.publisher;
						
						var aTd = $("<td></td>");
						// 저자명도 배열이기에 반복문으로 append
						$.each(author, function(j, a){
							$(aTd).append(a);
						});
						
						var pTd = $("<td></td>").text(price);
						var pubTd = $("<td></td>").text(publisher);
						
						var tr = $("<tr></tr>").append(aTd, pTd, pubTd);
						$("table").append(tr);
						
					});
				}
			});
		});
		$("#i3").keyup(function(){
			$("#b3").trigger("click");
		});
		
		
	});
</script>
</head>
<body>
	<h2>날씨검색</h2>
	<input id="i1"><button id="b1">도시명으로 검색</button>
	<hr>
	<img id="img1" src=""><h3 id="h31"></h3><h3 id="h32"></h3>
	<hr>
	<input id="i2">&nbsp;<button id="b2">영화 제목 검색(네이버API)</button>
	<hr>
	<input id="i3">&nbsp;<button id="b3">책 제목 검색(카카오API)</button>
	<table>
	
	</table>
</body>
</html>