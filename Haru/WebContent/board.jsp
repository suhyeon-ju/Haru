<%@page import="com.board.dto.BoardDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.member.dto.MemberDto"%>
<%	
     MemberDto member = (MemberDto) session.getAttribute("member");
	List<BoardDto> articleList = (List<BoardDto>) request.getAttribute("articleList");
	if( articleList == null ){
		response.sendRedirect("board.do?command=list");
	}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="resource/css/custom.css" rel="stylesheet">
    <link rel="stylesheet" href="resource/css/header_main.css">
    <link rel="stylesheet" href="resource/css/footer_main.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"> 
    <link href="https://fonts.googleapis.com/css2?family=East+Sea+Dokdo&family=Lobster&family=Shadows+Into+Light&family=Sunflower:wght@500;700&family=Yeon+Sung&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.13.0/css/all.css">
    <link rel="stylesheet" type="text/css" href="resource/css/style.css" >
    <link rel="stylesheet" type="text/css" href="resource/css/style_main.css" >
    <link href="https://fonts.googleapis.com/css?family=Black+Han+Sans&amp;display=swap" rel="stylesheet">
    <style type="text/css">
     .board_table thead tr th{
  	text-align:center;}
    </style>
</head>

<body>
  <body id="body">
    <div style="position: fixed; bottom: 190px; right: 24px; z-index: 1;">
        <a href="#body" class="top"><img src="" /></a>
     </div>
     <div style="position: fixed; bottom: 105px; right: 24px; z-index: 1;">
        <a href="#fh5co-footer"><img src="" /></a>
     </div>
    <nav class="navbar navbar-expand-lg" style="background-color: white" id="main-nav" style="padding-top: 250px;">
        <div class="container">
            <div class= "navbar-left">
               <h2 class="logotitlee" style="text-align:center;font-family: 'Black Han Sans', sans-serif; ">
                <a class="navbar-brand " href="main.do">HARU
                </a></h2>
            </div>
            <div class="navbar-right">
                <ul class="nav navbar-nav">
                   
                    <li class="nav-item topLi" style="margin: 20PX;"><a href="board.jsp" class="menulink" style="color: black; background-color:white;">FAQ</a>
                     
                    </li>
                    <li class="nav-item topLi" style="margin: 20PX;"><a class= "menulink" href="profile.do?command=mypage" style="color:black; background-color:white;">마이페이지</a>
                        <ul class="submenu" id="topul"> 
  
                            <li><a class="submenuLink" id="topli"></a></li>                           
                        </ul>
                    </li>
                    <li class="nav-item topLi" style="margin: 13PX;">
                    <a class= "menulink" href="<%=(member==null)?"sign.do":"sign.do?command=signout" %>"style="background-color:white;" >
                    	<button type="button" class="btn btn-outline-secondary">&nbsp; &nbsp;<%=(member==null)?"로그인":"로그아웃" %>&nbsp;&nbsp;&nbsp;</button>
                    </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <nav
   class="navbar navbar-expand-lg navbar-dark bg-dark"
   style="display: none; height: 50px; padding: 20px; border: 1px solid lightgray">
   <!-- id="ftco-navbar" -->
  </nav>
  <div class="board_wrap">
    <div class="board_banner">
     <div class="img">
        <div class="img_content">
          <div class="img_txt" style="padding-bottom: 35px;">
            <h3  style="font-weight:bolder; font-family: 'East Sea Dokdo', cursive; font-size: 70px;">FAQ</h3>
            <h6  style="font-weight:bolder; font-family: 'East Sea Dokdo', cursive; font-size: 30px;">자주 묻는 질문들</h6>
         </div>
        </div>
        <div class="img-cover"></div>
     </div>  
  </div>
  <jsp:useBean id="dto" class="com.board.dto.BoardDto" scope="request"></jsp:useBean>
  <div style="width: 100%; height:100px;"></div>
  <div class="board_table">
    
    <table class="table table-hover">
      <thead>
        <tr style="text-align:center; background-color:#f1f1f1;">
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">날짜</th>
        </tr>
      </thead>
      <tbody>
        <c:choose>
        	<c:when test="${empty articleList }">
	        	<tr>
	        	<td colspan="4" style="text-align:center;">작성된 글이 없습니다.</td>
	        	</tr>
        	</c:when>
        	<c:otherwise>
        		<c:forEach items="${articleList }" var="dto">
        			<tr style="text-align:center;'">
        				<td>${dto.bbs_no}</td>
       					<td><a style="color:black;" href="board.do?command=boarddetail&bbs_no=${dto.bbs_no }">${dto.title }</a></td>
        				<td>관리자</td>
        				<td>${dto.regdate}</td>
        			</tr>
        		</c:forEach>
        	</c:otherwise>	
        </c:choose>
      </tbody>
    </table>
    
	<!-- pageNum : 넘어온 값이 있다면 pageNum으로 set 하고 아니라면 1로 설정한다  -->
	<!-- 만약 컨트롤러를 통해 넘어오지 않으면 자동으로 컨트롤러로 리다이렉트 되어 1페이지로 표시한다 -->
	<!-- pageSize : 페이지별 출력되는 게시물 수로 10으로 입력되어 있다  -->
	<!-- startPage : 블럭의 페이지 시작 / block마다 시작위치값이다 예)[1],2,3,4,5 / [6],7,8,9,10 -->
	<!-- endPage : 블럭의 페이지 종료 / block마다 끝나는 위치값이다 예) 1,2,3,4,[5] / 6,7,8,9,[10] -->
	<!-- blockSize : 5로 설정되어 있으며 한번에 표시되는 페이지수이다. 예) 5일때 1,2,3,4,5까지 표시되고 다음 블럭에서 6,7,8,9,10 -->
	<!-- totalPage : 게시물에 대한 총 페이지수 -->
    <nav class="blog-pagination justify-content-center d-flex" style="text-align:center;">
           <ul class="pagination">
              <c:if test="${startPage>blockSize }"> <!-- 시작페이지가 블럭크기(5)보다 클경우 즉 1~5페이지가 아니라면 -->
                 <li class="page-item"> <!-- 첫번째 페이지로 이동하는 링크 추가 -->
                    <a href="board.do?command=list&pageNum=1" class="page-link">
                      &laquo;
                    </a>
                 </li>
                 <li class="page-item">
                      <a href="board.do?command=list&pageNum=${startPage-blockSize }" class="page-link" aria-label="Previous">
                   	&lt;
                      </a>
                 </li>
              </c:if>
               <c:forEach var="i" begin="${startPage }" end="${endPage }"> <!-- startPage부터 endPage까지 반복하며 i에 순차적으로 입력된다. -->
                  <c:if test="${pageNum ne i }"> <!-- ne:not equal (!=) --> <!-- i가 현재페이지가 아니라면 링크  -->
                     <li class="page-item">
                        <a href="board.do?command=list&pageNum=${i }" class="page-link">${i }</a> <!-- 같은 블럭에 있는 다른 페이지의 링크 -->
                     </li>
                  </c:if>
                  <c:if test="${pageNum eq i }"> <!-- eq:equal (==) --> <!-- i가 현재페이지라면 페이지번호만 출력(스타일 추가) -->
                     <li class="page-item">
                        <span class="page-link" style="background: lightblue;">${i }</span>
                     </li>
                  </c:if>
               </c:forEach>
               <c:if test="${endPage ne totalPage }"> <!-- ne:not equal(!=) --><!-- 블럭의 마지막 페이지와 게시물 페이지수가 다를경우 다음 블럭에 있는 startPage로 연결된 링크를 추가 -->
               <li class="page-item">
                  <a href="board.do?command=list&pageNum=${startPage+blockSize }" class="page-link" aria-label="Next">
                            	&gt;
                           </a>
                        </li>
                    
                        <li class="page-item"> <!-- 가장 마지막  페이지로 이동하는 링크 추가 -->
                           <a href="board.do?command=list&pageNum=${totalPage }" class="page-link">
               						&raquo;
                           </a>
                       </li>
                     </c:if>
                </ul>
          </nav>
       <div class="board_btn" > 
         <button type="button" class="btn btn-outline-primary" style="outline: none; background-color:rgb(129, 129, 129); color:white;" onclick="location.href='board.do?command=writeform'">글쓰기</button>
       </div>  
     </div>                 
  </div>
  <div style="width:100%; height:100px"></div>
  <footer id="fh5co-footer" class="fh5co-bg" role="contentinfo">
    <div class="overlay"></div>
		<div class="container">
			<div class="row row-pb-md">
				<div class="col-md-4 fh5co-widget">
					<div class="intro">
					<h3>HARU</h3>
					<ul style="padding: 10px 0px;">
						<li style="color:white;" ><i class="fas fa-map-marked-alt" style="color:white;padding-right:10px; "></i>서울 강남구 테헤란로14길 6 남도빌딩</li>
						<li style="color:white;"><i class="fas fa-home" style="color:white; padding:15px 10px 15px 0px;"></i>실제로 운영되지 않는 사이트 입니다.</li>
						<li style="color:white;"><i class="far fa-comment-alt" style="color:white; padding:15px 10px 15px 0px;"></i>때때로 손에서 일을 놓고 휴식을 취해야 한다. 잠시 일에서 벗어나 거리를 두고 보면 자기 삶의 조화로운 균형이 어떻게 깨져 있는지 분명히 보인다.</li>
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
                <div>
    			   <div class="col-md-4" style="text-align:center;">
                       <div>
                            <h3>HARU가 참조한 사이트</h3>
                        </div>
						<ul class="fh5co-footer-links">
							<button style="background-color: #9BDAF2;cursor: pointer; width:250px; height:50px; margin-bottom:20px;"><a href="https://taling.me/" style="color: white; text-decoration:none;">탈잉</a></button>
							<button style="background-color: #9BDAF2;color: white;cursor: pointer; width:250px; height:50px;"><a href="https://booking.naver.com/" style="color: white; text-decoration:none;">네이버 예약</a></button>
						</ul>
					</div>					
				</div>
			</div>

			<div class="row copyright">
				<div class="col-md-12 text-center">
					<p>
						<h5 class="block" style="color:#f1f1f1;">&copy; 2020 | All Rights Reserved.</h5> 
						<h5 class="block" style="color:#f1f1f1;">HARU.com</h5>
					</p>
				</div>
			</div>

		</div>
</footer>
</body>
</body>
</html> 