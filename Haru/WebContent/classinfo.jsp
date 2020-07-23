<%@page import="com.member.dto.MemberDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.classdate.dto.ClassDateDto"%>
<%@page import="com.classImage.dto.ClassImgDto"%>
<%@page import="com.classreview.dto.ClassReviewDto"%>
<%@page import="com.classinfo.dto.ClassInfoDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<%
	ClassInfoDto info = (ClassInfoDto) request.getAttribute("classinfo");
List<ClassImgDto> banner = (List<ClassImgDto>) request.getAttribute("classbanner");
List<ClassDateDto> date = (List<ClassDateDto>) request.getAttribute("classdate");
List<ClassReviewDto> review = (List<ClassReviewDto>) request.getAttribute("review");
MemberDto memberdto = (MemberDto) session.getAttribute("member");
if (memberdto == null) {
	//response.sendRedirect("sign.do?redirect=classinfo.do?command=detailclass&classpk="+info.getClasspk());return;
}
if(banner==null || date==null || info == null || review == null){response.sendRedirect("main.do"); return;}
%>
<!DOCTYPE html>
<html lang="ko">
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>강의 상세페이지</title>
<!-- header 시작 -->
    	<link rel="stylesheet" href="profile/classic/css/footer.css">
        <link href="https://fonts.googleapis.com/css?family=Black+Han+Sans&amp;display=swap" rel="stylesheet">
        <link rel="stylesheet" href="resource/css/header_main.css">
        <link href="resource/css/footer.css" rel="stylesheet">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.13.0/css/all.css"><!-- 마우스 이미지 -->
<!-- header 끝 -->

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link href="classinfopage/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous">
	
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous">
	
</script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous">
	
</script>

<script>
	$('.carousel').carousel({
		interval : 2000
	})
	//기본 5초
</script>

<style type="text/css">
.sidediv {
	position: fixed;
	left: 65%;
	bottom: 150px;
}

.classimage {
	display: block;
	margin: 0px auto;
}

.header_wrap {
	margin: 0 auto;
	width: 100%;
	max-width: 1100px;
	min-width: 600px;
}

.header_left {
	float: left;
}

.header_right {
	float: right;
}

.dropdown {
	/*  position: block;*/
	display: inline-block;
	padding: 10px;
	margin-right: 40px;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #ffffff;
	min-width: 160px;
	/*  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);*/
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.clearfix {
	float: none;
	clear: both;
}

.dropdown-content a:hover {
	background-color: #ffffff;
}

.dropdown:hover .dropdown-content {
	display: block;
}

.dropdown:hover .dropbtn {
	background-color: #3e8e41;
}

a-stack i.fa-star {
	color: transparent;
	-webkit-text-stroke-width: 1px;
	-webkit-text-stroke-color: orange;
}

.fa-stack i.fa-star-half {
	color: yellow;
	-webkit-text-stroke-width: 1px;
	-webkit-text-stroke-color: orange;
}

.star {
	display: inline-block;
	width: 30px;
	height: 60px;
	cursor: pointer;
}

.star_left {
	background: url(http://gahyun.wooga.kr/main/img/testImg/star.png)
		no-repeat 0 0;
	background-size: 60px;
	margin-right: -3px;
}

.star_right {
	background: url(http://gahyun.wooga.kr/main/img/testImg/star.png)
		no-repeat -30px 0;
	background-size: 60px;
	margin-left: -3px;
}

.star.on {
	background-image:
		url(http://gahyun.wooga.kr/main/img/testImg/star_on.png);
}

.starr {
	display: inline-block;
	width: 10px;
	height: 30px;
}

.starr_left {
	background: url(startbootstrap-blog-post-gh-pages-2/image/star.jpg)
		no-repeat 0 0;
	background-size: 60px;
}

.starr_right {
	background: url(startbootstrap-blog-post-gh-pages-2/image/star.jpg)
		no-repeat -30px 0;
	background-size: 60px;
}

.starr.on {
	background-image:
		url(startbootstrap-blog-post-gh-pages-2/image/star.jpg);
}
.star2 {
	display: inline-block;
	width: 30px;
	height: 50px;
	cursor: pointer;
}

.star2_left {
	background: url(http://gahyun.wooga.kr/main/img/testImg/star.png)
		no-repeat 0 0;
	background-size: 30px;
	margin-right: -20px;
}

.star2_right {
	background: url(http://gahyun.wooga.kr/main/img/testImg/star.png)
		no-repeat -30px 0;
	background-size: 30px;
	margin-left: -20px;
}

.one {
	background-image:
		url(http://gahyun.wooga.kr/main/img/testImg/star_on.png);
}
.nametag, .reviewstar {
	display: inline-block;
	margin-right: 20px;
}

#star_grade a {
	text-decoration: none;
	color: gray;
}

#star_grade a.on {
	color: red;
}
</style>


<!-- Bootstrap core JavaScript -->
<script src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#user_review a').hover(function() {
			$(this).siblings().find("i").removeClass("checked");
			$(this).children("i").addClass("checked");
			$(this).prevAll("a").children("i").addClass("checked");
		});

	});

	$(function() {
	      $("#strp").val(1);
	      $(".star").on('click', function() {
	         var idx = $(this).index();
	         $(".star").removeClass("on");
	         for (var i = 0; i <= idx; i+=2) { //13579 246810
	            $(".star").eq(i).addClass("on");
	            $(".star").eq(i+1).addClass("on");
	         }
	         console.log(idx);
	         $("#strp").val(idx);
	      });
	   });
	

  
<%-- 	$(function(){
		<c:set var="id" value="<%=memberdto.getId()%>"/>
		<c:if test="${id eq '<%=info.getId()%>'}">
		$(".classmodify").show();
		</c:if>
	};  --%>
	
	
</script>


</head>

<body>

	<nav class="navbar navbar-expand-lg" style="background-color: white" id="main-nav" style="padding-top:250px;">
        <div class="container-main">
            <div class= "navbar-left" style="margin-left : 160px">
                <h2 class="logotitlee" style="text-align:center; font-family: 'Black Han Sans', sans-serif; ">
            	<a class="navbar-brand " href="main.do">HARU</a>
          		</h2>
            </div>
            <div class="navbar-right">
                <ul class="nav navbar-nav" style="margin-left : 520px">
                    <li class="nav-item topLi" style="margin: 20PX;"><a href="#" class="menulink" style="color: black;"></a>
                       
                    </li>
                    <li class="nav-item topLi" style="margin: 20PX;"><a href="board.jsp" class="menulink" style="color: black; background-color:white;">FAQ</a>
                       
                    </li>
                    <li class="nav-item topLi" style="margin: 20PX;"><a class= "menulink" href="profile.do?command=mypage" style="color: black; background-color:white;">마이페이지</a>
                        <ul class="submenu" id="topul"> 
                            
                        </ul>
                    </li>
                    <li class="nav-item topLi" style="margin: 13PX;">
                   <a class= "menulink" href="<%=(memberdto==null)?"sign.do":"sign.do?command=signout" %>"style="background-color:white;" >
                    	<button type="button" class="btn btn-outline-secondary">&nbsp; &nbsp;<%=(memberdto==null)?"로그인":"로그아웃" %>&nbsp;&nbsp;&nbsp;</button>
                    </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

	<!-- Page Content -->
	<div class="container">

		<div class="row">

			<!-- Post Content Column -->
			<div class="col-lg-8">

				<!-- Title -->
				<h1 class="mt-4"><%=info.getTitle()%></h1>

				<!-- Author -->
				<p class="lead">
					by <a href="#"><%=info.getId()%></a>
				</p>

				<hr>

				<!-- Date/Time -->
				<p>Posted on January 1, 2019 at 12:00 PM</p>

				<hr>

				Preview Image

				<div id="demo" class="carousel slide" data-ride="carousel">
					<div class="carousel-inner">
						<!-- 슬라이드 쇼 -->
						<%-- <div class="carousel-item active">
							<!--가로-->

							<img class="d-block w-100" src="<%//banner.getImage_url()%>"
								alt="First slide">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="<%//banner.getImage_url()%>"
								alt="Second slide">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="<%//banner.getImage_url()%>"
								alt="Third slide">
						</div> --%>
						<%
							int firstbanner = 0;
						for (ClassImgDto dto : banner) {
						%>
						<div class="carousel-item <%=++firstbanner == 1 ? "active" : ""%>">
							<img class="d-block w-100" src="<%=dto.getImage_url()%>"
								alt="First slide">
						</div>
						<%
							}
						%>

						<!-- / 슬라이드 쇼 끝 -->
						<!-- 왼쪽 오른쪽 화살표 버튼 -->
						<a class="carousel-control-prev" href="#demo" data-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<!-- <span>Previous</span> -->
						</a> <a class="carousel-control-next" href="#demo" data-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<!-- <span>Next</span> -->
						</a>
						<!-- / 화살표 버튼 끝 -->
						<!-- 인디케이터 -->
						<ul class="carousel-indicators">
							<li data-target="#demo" data-slide-to="0" class="active"></li>
							<!--0번부터시작-->
							<li data-target="#demo" data-slide-to="1"></li>
							<li data-target="#demo" data-slide-to="2"></li>
						</ul>
						<!-- 인디케이터 끝 -->
					</div>


					<hr>

					<!-- Post Content -->
					<%=info.getContent()%>

					<hr>

					<!-- Comments Form -->

					<div class="card my-4">
						<h5 class="card-header">리뷰남기기:</h5>
						<div class="card-body">
							<form action="./classinfo.do" method="post">
								<input type="hidden" name="command" value="insertreivew">
								<input type="hidden" name="classpk"
									value="<%=info.getClasspk()%>"> <input type="hidden"
									id="strp" name="strp" value="0">
								<div class="star-box">
									 <span class="star star_left on"></span> <span
										class="star star_right on"></span> <span class="star star_left"></span>
									<span class="star star_right"></span> <span
										class="star star_left"></span> <span class="star star_right"></span>

									<span class="star star_left"></span> <span
										class="star star_right"></span> <span class="star star_left"></span>
									<span class="star star_right"></span> 
								

								</div>
								<div class="form-group">
									<textarea class="form-control" rows="3" name="reviewcontent"></textarea>
								</div>

								<button type="submit" class="btn btn-primary">전송</button>
							</form>
						</div>
					</div>


					<!-- Single Comment -->
					<jsp:useBean id="re" class="com.classreview.dao.ClassReviewDao"
						scope="request"></jsp:useBean>
					<div class="media mb-4">			<jsp:useBean id="list_review" class="com.classreview.dto.ClassReviewDto" scope="request"></jsp:useBean>
						<table class="table table-stripped" id="reviews">
							<thead>
								<tr>
									<th>Rating</th>
									<!-- 평점 -->
									<th>User</th>
									<th>Text</th>
								</tr>
							</thead>
							<c:choose>
        						<c:when test="${empty review }">
        						<tr>
        							<th colspan="3">
        							<h4 style="text-align:center;'">등록된 리뷰가 없습니다.</h4>
        							</th>
       							</tr>
        						</c:when>
        						<c:otherwise>
        						<tbody>
        						<c:forEach var="dto" items="${review }">
        						<tr>
        							<td width=200px>
										<c:forEach var="i" begin="${1 }" end="${10 }">
                   	    					<c:if test="${(i%2) ne 0}"><span class="star2 star2_left <c:if test="${i<=dto.strp}">one</c:if>"></span></c:if>
                     						<c:if test="${(i%2) eq 0}"><span class="star2 star2_right <c:if test="${i<=dto.strp}">one</c:if>"></span></c:if>
                     					</c:forEach>
									</td>
        							<td width=200px>${dto.id }</td>
        							<td>${dto.content }</td>
        						</tr>
        						</c:forEach>
        						</c:otherwise>
        					</c:choose>
        					</tbody>
        					</table>
					</div>
				</div>

			</div>

			<!-- Sidebar Widgets Column -->
			<div class="col-md-4 sidediv">

				<!-- Search Widget -->
				<div class="card my-4">
					<h5 class="card-header">수업등록</h5>
					<div class="card-body">
						<div class="input-group">
							<table class="table">
								<thead>
									<tr>
										<th scope="col">날짜</th>
										<th scope="col">시간</th>
										<th scope="col">장소</th>
									</tr>
								</thead>
								<tbody>
									<%
										for (ClassDateDto day : date) {
									%>
									<tr>
										<th scope="row"><%=day.getClassday().toString()%></th>
										<td><%=day.printTime()%></td>
										<td><%=info.goloc()%></td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
							<!-- <input type="text" class="form-control" placeholder="Search for..."> -->
							<!-- <span class="input-group-btn"> -->
							<button class="btn btn-secondary" type="button"
								onclick="location.href='./pay.do?classpk=<%=info.getClasspk()%>'">강의신청</button>

						</div>
					</div>
				</div>
				<!-- Categories Widget -->
				<div class="card my-4">
					<h5 class="card-header">연관검색어</h5>
					<div class="card-body">
						<div class="row">
							<%-- <div class="col-lg-6">
									<ul class="list-unstyled mb-0">
										<li><a href="#"><%=info.getKeyword()%></a></li>
										<li><a href="#"><%=info.getKeyword()%></a></li>
										<li><a href="#"><%=info.getKeyword()%></a></li>
									</ul>
								</div>
								<div class="col-lg-6">
									<ul class="list-unstyled mb-0">
										<li><a href="#"><%=info.getKeyword()%></a></li>
										<li><a href="#"><%=info.getKeyword()%></a></li>
										<li><a href="#"><%=info.getKeyword()%></a></li>
									</ul>
								</div> --%>
							<%
								String tagarr[] = info.getKeyword().split(" ");
							for (String s : tagarr) {
							%>
							<div class="col-lg-6">
								<ul class="list-unstyled mb-0">
									<li><a href="main.do?command=search&keyword=<%=s%>"><%=s%></a></li>
								</ul>
							</div>
							<%
								}
							%>
						</div>
					</div>
				</div>
				<%-- 	<%
						if (memberdto.getId().equals(info.getId())) {
					%> --%>
				<%
					boolean sidewidget = false;
				if (memberdto != null) {
					if (memberdto.getId() != null) {
						sidewidget = memberdto.getId().equals(info.getId());
					}
				}
				if (sidewidget) {
				%>
				<div class="card my-4 classmodify">
					<h5 class="card-header">강의수정</h5>
					<div class="card-body">
						<input type="button" value="수정"
							onclick="location.href='./classinfo.do?command=classmodify&classpk=<%=info.getClasspk()%>'">
						<input type="button" value="삭제"
							onclick="location.href='./classinfo.do?command=delete&classpk=<%=info.getClasspk()%>'">
					</div>
				</div>
				<%
					}
				%>

			</div>

		</div>

	</div>


	<footer id="fh5co-footer" class="fh5co-bg" role="contentinfo" style="font-size:14px;">
    <div class="overlay"></div>
		<div class="container">
			<div class="row row-pb-md">
				<div class="col-md-4 fh5co-widget">
					<div class="intro">
					<h3>HARU</h3>
					<ul style="padding: 10px 0px;">
						<li style="color:white ;list-style: none;" ><i class="fas fa-map-marked-alt" style="color:white;padding-right:10px; "></i>서울 강남구 테헤란로14길 6 남도빌딩</li>
						<li style="color:white;list-style: none;"><i class="fas fa-home" style="color:white; padding:15px 10px 15px 0px;"></i>실제로 운영되지 않는 사이트 입니다.</li>
						<li style="color:white;list-style: none;"><i class="far fa-comment-alt" style="color:white; padding:15px 10px 15px 0px;"></i>때때로 손에서 일을 놓고 휴식을 취해야 한다. 잠시 일에서 벗어나 거리를 두고 보면 자기 삶의 조화로운 균형이 어떻게 깨져 있는지 분명히 보인다.</li>
					</ul>
					</div>
				</div>
				<div class="col-md-4">
					<div class="devel" style="text-align:center;">
                        <h3>사이트 개발자</h3>
						<p style="color:white;">정승주 박초롱 이병호 주수현 김건영 </p>
						<div style="height:60px"></div>
						<i class="fas fa-laptop fa-2x" style="color:white;"></i>
						<i class="fas fa-mouse fa-2x" style="color:white;"></i>
					</div>
                </div>
             
    					<div class="col-md-4" style="text-align:center;">
                        <div>
                            <h3>HARU가 참조한 사이트</h3>
                        </div>
						<ul class="fh5co-footer-links">
							<button style="background-color: #9BDAF2;cursor: pointer; width:250px; height:50px; margin-bottom:20px; border:0;"><a href="https://taling.me/" style="color: white; text-decoration:none;">탈잉</a></button>
							<button style="background-color: #9BDAF2;color: white;cursor: pointer; width:250px; height:50px; border:0;"><a href="https://booking.naver.com/" style="color: white; text-decoration:none;">네이버 예약</a></button>					
						</ul>
					</div>

					
				
			</div>

			<div class="row copyright">
				<div class="col-md-12 text-center">
					<p>
						<h5 class="block" style="color:#f1f1f1;font-size:14px;">&copy; 2020 | All Rights Reserved.</h5> 
						<h5 class="block" style="color:#f1f1f1;font-size:14px;">HARU.com</h5>
					</p>
				</div>
			</div>

		</div>
</footer>



</body>

</html>