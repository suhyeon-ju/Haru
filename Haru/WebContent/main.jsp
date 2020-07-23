<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html; charset=UTF-8");%>
<%@page import="com.classinfo.dto.ClassInfoDto" %>
<%@page import="com.classreview.dto.ClassReviewDto" %>
<%@page import="com.member.dto.MemberDto"%>
<%@page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
   List<ClassInfoDto> list = (List<ClassInfoDto>)request.getAttribute("list");
   List<ClassReviewDto> review=(List<ClassReviewDto>)request.getAttribute("review");
   MemberDto member = (MemberDto) session.getAttribute("member");
   if(list==null || review==null){response.sendRedirect("main.do"); return;}
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
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.13.0/css/all.css">
    <link rel="stylesheet" type="text/css" href="resource/css/style.css" >
    <link rel="stylesheet" type="text/css" href="resource/css/style_main.css" >
    <link href="https://fonts.googleapis.com/css?family=Black+Han+Sans&amp;display=swap" rel="stylesheet">
    
    <script type="text/javascript">
        $(function(){
                //$('#menuList').hide();로 범위를 찾음
              $('.all_category').removeClass('on');
              
              $('#allBtn').click(function(){
                 $('.all_category').addClass('on');                          
              });
              
              $(".close_btn").click(function(){
                 
                 $('.all_category').removeClass('on');   
              });             
        });
    </script>
    <style type="text/css">
      .navbar-right ul li a{
       background-color:white;
    }
    .logotitlee{
    font-size: 50px;
    line-height: 54px;
    font-weight: 300;
    padding-top: 8px;
    color:black;
    text-decoration:none;
    }
    
    
.star {
   display: inline-block;
   width: 20px;
   height: 60px;
   cursor: pointer;
}

.star_left {
   background: url(http://gahyun.wooga.kr/main/img/testImg/star.png)
      no-repeat 0 0;
   background-size: 40px;
   margin-right: -3px;
}

.star_right {
   background: url(http://gahyun.wooga.kr/main/img/testImg/star.png)
      no-repeat -20px 0;
   background-size: 40px;
   margin-left: -3px;
}
.star.on {
    background-image: url(http://gahyun.wooga.kr/main/img/testImg/star_on.png);
}
    </style>

</head>
<body>
<!--  <body id="body">-->
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
                    <li class="nav-item topLi" style="margin: 20PX;"><a href="#" class="menulink" style="color: black; b"></a>
                        <ul class="submenu" id="topul"> 

                        </ul>
                    </li>
                    <li class="nav-item topLi" style="margin: 20PX;"><a href="board.jsp"  class="menulink" style="color: black; background-color:white;" >FAQ</a>
                        <ul class="submenu" id="topul"> 
                 
                        </ul>
                    </li>
                    <li class="nav-item topLi" style="margin: 20PX;"><a href="profile.do?command=mypage" class= "menulink" style="color: black; background-color:white;">마이페이지</a>
                        <ul class="submenu" id="topul"> 
                          
                        </ul>
                    </li>
                    <li class="nav-item topLi" style="margin: 13PX;">
                    <a class= "menulink" href="<%=(member==null)?"sign.do":"sign.do?command=signout" %>"style="background-color:white;" >
                       <button type="button" class="btn btn-outline">&nbsp; &nbsp;<%=(member==null)?"로그인":"로그아웃" %>&nbsp;&nbsp;&nbsp;</button>
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
        <div class="banner_wrap">
            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                  <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                  <li data-target="#myCarousel" data-slide-to="1"></li>
                  <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>
      
                <!-- Wrapper for slides -->
                <div class="carousel-inner" >
                  <div class="item active">
                    <!-- <img src="image/img03.jpg" alt="Los Angeles" style="width:100%;"> -->
                    <img src="resource/images/bannerr3.jpg">
                  </div>
            
                  <div class="item">
                    <!-- <img src="image/img01.jpg" althttp://numbuzin.com/="Chicago" style="width:100%;"> -->
                    <img src="resource/images/bannerr2.jpg">
                  </div>
                
                  <div class="item">
                    <!-- <img src="image/img01.jpg" alt="New york" style="width:100%;"> -->
                    <img src="resource/images/back08.jpg">
                  </div>
                </div>
            
                <!-- Left and right controls -->
                <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                  <span class="glyphicon glyphicon-chevron-left"></span>
                  <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">
                  <span class="glyphicon glyphicon-chevron-right"></span>
                  <span class="sr-only">Next</span>
                </a>
              </div>
        </div>
        <div class="category_wrap" >
          <div class="category">
            <div class="c1" onclick="location.href='main.do?command=category&key=1'"><img src="resource/images/like07.png" alt=""><div style="margin-top: 15px;">인기수업</div></div>
            <div class="c2" onclick="location.href='main.do?command=category&key=2'"><img src="resource/images/design01.png"alt=""><div style="margin-top: 15px;">디자인</div></div>
            <div class="c3" onclick="location.href='main.do?command=category&key=3'"><img src="resource/images/guitar05.png" alt=""><div style="margin-top: 15px;">음악</div></div>
            <div class="c4" onclick="location.href='main.do?command=category&key=4'"><img src="resource/images/video04.png"alt=""><div style="margin-top: 15px;">영상</div></div>
            <div class="c5" onclick="location.href='main.do?command=category&key=5'"><img src="resource/images/language01.png" alt=""><div style="margin-top: 15px;">외국어</div></div>
            <div class="c6" onclick="location.href='main.do?command=category&key=6'"><img src="resource/images/cook07.png" alt=""><div style="margin-top: 15px;">라이프</div></div>
            <div class="c7" onclick="location.href='main.do?command=category&key=7'"><img src="resource/images/location02.png" alt=""><div style="margin-top: 15px;">내 근처</div></div>
            <div class="c8" onclick="" id="allBtn" ><img src="resource/images/list02.png" alt="" style="width: 30px;height: 25px; margin-top: 15px;"></div>
          </div>
        </div>
        <div class="all_category on" >
            <h2 class="ti">전체 서비스</h2>
                <div class="dt">
                    <div class="dtc">
                        <div class="container_allc">
                            <button class="close_btn"><img src="https://front-img.taling.me/Content/app3/images/btn_close.png" alt="닫기"></button>
                                <div id="menuList">
                                    <table>
                                        <tbody>
                                            <tr>   
                                                <th><a href="" style="color: #ccc; ">인기수업</a></th>   
                                                    <td> 
                                                        <a href="">튜터전자책</a>   
                                                        <a href="">투잡</a>   
                                                        <a href="">퍼스널컬러</a>   
                                                        <a href="">엑셀</a>   
                                                        <a href="">댄스</a>   
                                                        <a href="">탈잉이벤트</a>   
                                                        <a href="">메이크업</a>   
                                                        <a href="">프리미어</a>   
                                                        <a href="">포토샵</a>   
                                                        <a href="">방송댄스</a>   
                                                        <a href="">보컬</a>   
                                                        <a href="">PT</a>   
                                                        <a href="">에프터이펙트</a>   
                                                        <a href="">WEB개발</a>
                                                        <a href="">영어회화</a>   
                                                        <a href="">핸드메이드</a>   
                                                        <a href="">마케팅</a>
                                                    </td>
                                            </tr>
                                            <tr>   
                                                <th><a href="" style="color: #ccc;">디자인</a></th>   
                                                    <td>    
                                                        <a href="">포토샵</a>   
                                                        <a href="">일러스트레이터</a>   
                                                        <a href="">제품디자인</a>   
                                                        <a href="">건축</a>   
                                                        <a href="">UX/UI디자인</a>   
                                                        <a href="">편집디자인</a>   
                                                        <a href="">라이트룸</a>   
                                                        <a href="">애니메이션</a>
                                                    </td>
                                            </tr>
                                            <tr>   
                                                <th><a href="" style="color: #ccc;">음악</a></th>   
                                                    <td>    
                                                        <a href="">보컬</a>   
                                                        <a href="">피아노</a>   
                                                        <a href="">미디작곡</a>   
                                                        <a href="">기타연주</a>   
                                                        <a href="">드럼</a>   
                                                        <a href="">국악</a>   
                                                        <a href="">랩</a>   
                                                        <a href="">디제잉</a>   
                                                        <a href="">바이올린</a>   
                                                        <a href="">오케스트라</a>   
                                                        <a href="">성악</a>   
                                                        <a href="">우쿨렐레</a>   
                                                        <a href="">첼로</a>   
                                                    </td>
                                            </tr>
                                            <tr>   
                                                <th><a href="" style="color: #ccc;">영상</a></th>   
                                                    <td>    
                                                        <a href="">프리미어</a>   
                                                        <a href="">영상기획/촬영</a>   
                                                        <a href="">영상(기타)</a>   
                                                        <a href="">에프터이펙트</a>   
                                                        <a href="">파이널컷</a>   
                                                        <a href="">여행영상</a>   
                                                        <a href="">시포디</a>   
                                                        <a href="">VJ</a>   
                                                    </td>
                                            </tr>
                                            <tr>   
                                                <th><a href="" style="color: #ccc;">외국어</a></th>   
                                                    <td>    
                                                        <a href="">영어회화</a>   
                                                        <a href="">중국어</a>   
                                                        <a href="">일본어</a>   
                                                        <a href="">토익/토플</a>   
                                                        <a href="">외국어(기타)</a>   
                                                        <a href="">스페인어</a>   
                                                        <a href="">프랑스어</a>   
                                                        <a href="">비즈니스</a>   
                                                        <a href="">독일어</a>   
                                                        <a href="">여행 외국어</a>   
                                                        <a href="">오픽/토스</a>   
                                                        <a href="">Writing</a>   
                                                        <a href="">언어교환</a>   
                                                    </td>
                                            </tr>
                                            
                                            <tr>   
                                                <th><a href="" style="color: #ccc;">라이프스타일</a></th>   
                                                    <td>    
                                                        <a href="">핸드메이드</a>   
                                                        <a href="">사진</a>   
                                                        <a href="">플라워</a>   
                                                        <a href="">요리/베이킹</a>   
                                                        <a href="">연기</a>   
                                                        <a href="">커피/차</a>   
                                                        <a href="">여행/투어</a>   
                                                        <a href="">사주/타로</a>   
                                                        <a href="">반려동물</a>   
                                                        <a href="">술</a>   
                                                        <a href="">가죽공예</a>   
                                                        <a href="">마술</a>   
                                                        <a href="">도예/물레</a>   
                                                        <a href="">집꾸미기</a>   
                                                        <a href="">바둑</a>   
                                                    </td>
                                            </tr>        
                                        </tbody>
                                 </table>
                            </div>
                         </div>
                    </div>
                </div>
        </div>
        
        <div style="width:100%; height:10px;"></div>
        <div class="search_wrap">
          <div class="modal-body">
            <div class="jsx-2460799870 bar-layout">
              <h1 id="title_txt" style="padding-left:0px;">SEARCH</h1>
              <!-- <p class="jsx-2460799870">
                어떤 <span class="jsx-2460799870">클래스</span>를 원하시나요?
              </p> -->
              <div style="width: 100%; height: 25px;"></div>
              <form action="" method="post" id="searchform" >
               <input type="hidden" name="command" value="search">
                <div class="jsx-2460799870 search-box" >
                  <div class="jsx-2460799870 search-bar">
                    <input type="text" id="searchInput" name="keyword" placeholder="어떤 클래스를 찾으시나요?" 
                      class="search-bar" /> <input type="hidden" name="page"
                      value="1" /> <span><a
                      href="javascript:searchform.submit();"><img
                        src="resource/images/search02.png"></a></span>
                  </div>
                </div>
              </form>
            </div>
            <div style="width:100%; height:40px;"></div>
                <div class="jsx-2533767983 keyword-link" style="display:inline-block; width:100%;padding-right: 150px; text-align: center;">
                    
                </div>
          </div>
            <div style="width: 100%; height:90px;"></div>  
        </div>
          <jsp:useBean id="dto" class="com.classinfo.dto.ClassInfoDto" scope="request"></jsp:useBean>
        <div class="content_wrap">
          <div class="class_title" style="padding-bottom:30px;"><h1>이번달 강의</h1></div>
          <div class="container">

            <div class="row">
                <div class="row">
                <c:choose>
                 <c:when test="${empty list }">
                    <h4 style="text-align:center;'">등록된 게시글이 없습니다.</h4>
                 </c:when>
               <c:otherwise>
                 <c:forEach var="dto" items="${list }">
                  <div class="col-lg-4 col-md-4 mb-4">
                    <div class="card h-100">
                      <a href="./classinfo.do?command=detailclass&classpk=${dto.classpk}"><img class="card-img-top col-lg-12 col-md-12 mb-12 " src="${dto.pubImg}" alt="" style="padding:0px; height:200px;"></a>
                      <div class="card-body">
                        <h4 class="card-title">
                          <a href="./classinfo.do?command=detailclass&classpk=${dto.classpk}">${dto.title }</a>
                        </h4>
                      <span>￦</span><fmt:formatNumber value="${dto.price }" pattern="#,###" />
                      </div>
                      <div class="card-footer">
                      
                      <fmt:parseNumber var="mulrank" integerOnly="true" value="${dto.rankcnt!=0?(dto.rank/dto.rankcnt):0}" />
                      <small  style="font-size:12pt;"class="text-muted">${mulrank/2}
                      
                     <div class="star-box">
                     <c:forEach var="i" begin="${1 }" end="${10 }">
                          <c:if test="${(i%2) ne 0}"><span class="star star_left <c:if test="${i<=mulrank}">on</c:if>"></span></c:if>
                        <c:if test="${(i%2) eq 0}"><span class="star star_right <c:if test="${i<=mulrank}">on</c:if>"></span></c:if>
                     </c:forEach>
 
                      </div>
                      </small>                     
                      </div>
                    </div>
                  </div>
                  </c:forEach>
               </c:otherwise>
              </c:choose>
              </div>
            </div>
                  
          </div>
          
        <div style="width:100%; height:40px;"></div>
         <jsp:useBean id="re" class="com.classreview.dto.ClassReviewDto" scope="request"></jsp:useBean>
        <div class="review_wrap">
          <div class="class_title" style="padding-bottom:30px;"><h1>실시간 클래스 리뷰</h1></div>
          <div id="exampleSlider">
            <div class="MS-content">
                
                <c:choose>
                   <c:when test="${empty review }">
                   <h4 style="text-align:center;'">등록된 리뷰가 없습니다.</h4>
                   </c:when>
                   <c:otherwise>
                   <c:forEach var="dto" items="${review }">
                   <div class="item">
                    <div class="col-lg-12 col-md-12 mb-12">
                    <div class="card h-100">
                      <a href="./classinfo.do?command=detailclass&classpk=${dto.classpk }" style="border:0;text-decoration:none;color:#000;">
                      <div class="card-body" style="float:none; clear:both; display:block; text-align:center;">
                      		<img src="${dto.pubimg }" style="width:100%; height:150px;"/>
                      		
                        <br> 
                     <c:set value="${(dto.strp) }" var="star"></c:set><br>
                       <small  style="font-size:12pt;"class="text-muted">${dto.strp/2}
                     <div class="star-box">
                         <c:forEach var="i" begin="${1 }" end="${10 }">
                          <c:if test="${(i%2) ne 0}"><span class="star star_left <c:if test="${i<=dto.strp}">on</c:if>"></span></c:if>
                        <c:if test="${(i%2) eq 0}"><span class="star star_right <c:if test="${i<=dto.strp}">on</c:if>"></span></c:if>
                     </c:forEach>
                      </small>    
                      </div>
                      <br>
                   
                        <p class="card-text" style="font-size:16px;">${dto.content }</p>
                      </div>
                      <div class="card-footer"></div>                    
                    </div>
                  </div>
                 </div>
                    </c:forEach>
                    </c:otherwise>
                    </c:choose> 
                    </div>
                </div>
            </div>
        </div>
     
         <!-- Include jQuery -->
         <script src="./js/jquery-2.2.4.min.js"></script>
     
         <!-- Include Multislider -->
         <script src="./js/multislider.min.js"></script>
     
         <!-- Initialize element with Multislider -->
         <script type="text/javascript"> 
         $('#exampleSlider').multislider({
             interval: 2700,
             slideAll: false,
             duration: 1500
         });
         </script>

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
  
<!--</body>-->
</body>
</html> 