<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>
<%@page import="com.member.dto.MemberDto"%>
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
    <link href="https://fonts.googleapis.com/css2?family=East+Sea+Dokdo&family=Lobster&family=Shadows+Into+Light&family=Sunflower:wght@500;700&family=Yeon+Sung&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Black+Han+Sans&amp;display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.13.0/css/all.css">
    <link rel="stylesheet" type="text/css" href="resource/css/style.css" >
    <link rel="stylesheet" type="text/css" href="resource/css/style_main.css" >
    <link href="https://fonts.googleapis.com/css?family=Black+Han+Sans&amp;display=swap" rel="stylesheet">
  
</head>
<%MemberDto member = (MemberDto) session.getAttribute("member"); %>
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
                 
                    <li class="nav-item topLi" style="margin: 20PX;"><a href="board.jsp" class="menulink" style="color:black; background-color:white;">FAQ</a>
                 
                    </li>
                    <li class="nav-item topLi" style="margin: 20PX;"><a class= "menulink" href="profile.do?command=mypage" style="color:black; background-color:white;">마이페이지</a>
                        <ul class="submenu" id="topul"> 
   
                            <li><a class="submenuLink" id="topli">내정보 변경</a></li>
                          
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
               <h3  style="font-weight:bolder; font-family: 'East Sea Dokdo', cursive; font-size: 70px;">F A Q</h3>
            <h6  style="font-weight:bolder; font-family: 'East Sea Dokdo', cursive; font-size: 30px;">자주 묻는 질문들</h6>
         </div>
        </div>
        <div class="img-cover"></div>
     </div>  
  </div>
  <div style="width: 100%; height:80px;"></div>
  <div class="board_table_detail" style="padding-left:350px;">
   <form action="board.do" method="post">
   <input type="hidden" name="bbs_no" value="${dto.bbs_no }">
    <input type="hidden" name="command" value="update">
    <table class="table_deta" border= "1px solid rgb(170, 168, 168);">
          <tr>
              <th style="width:100px;">작성자</th>
              <td><input type="hidden" name="id" value="admin">관리자</td>
          </tr>
          <tr>
            <th>날짜</th>
            <td>SYSDATE</td>
         </tr>
          <tr>
              <th>제목</th>
              <td colspan="2"><input type="text" name="title" size="92" value="${dto.title}"></td>
          </tr>
          <tr>
              <th>내용</th>
              <td colspan="2"><textarea rows="15" cols="94" name="content" style="resize: none;" >${dto.content }</textarea></td>
          </tr>
    </table>
        <div class="board_btn" style="padding: 20px 280px;"> 
         
          <input type="submit" class="btn btn-outline-primary" value="수정완료" style="outline: none; background-color:rgb(129, 129, 129); color:white;">
          <button type="button" class="btn btn-outline-primary" style="outline: none;" onclick="location.href='board.jsp'">취소</button>
          <button type="button" class="btn btn-outline-primary" style="outline: none;" onclick="location.href='board.jsp'">목록</button>
        </div> 
   </form>
        
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