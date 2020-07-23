<%@page import="com.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html charset=UTF-8"); %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>student_profile</title>
 <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Assan 3</title>    
          
       <!-- 제이쿼리 적용 -->
       <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
       <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) -->
       <script src="http://code.jquery.com/jquery.js"></script>
       <!-- 모든 합쳐진 플러그인을 포함하거나 (아래) 필요한 각각의 파일들을 포함하세요 -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
          <link href="resource/css/footer.css" rel="stylesheet">
          <link rel="stylesheet" href="resource/css/footer_main.css">
      <link rel="stylesheet" href="resource/css/header_main.css">
        <link href="https://fonts.googleapis.com/css?family=Black+Han+Sans&amp;display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.13.0/css/all.css">
       <!--header  끝 -->
        <!-- Bootstrap-->
        <link href="CSS/dashboard/lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!--Common Plugins CSS -->
        <link href="CSS/dashboard/css/plugins/plugins.css" rel="stylesheet">

        <link href="CSS/classic/css/plugins.css" rel="stylesheet">
        <link href="CSS/classic/css/style.css" rel="stylesheet">
        <!--fonts-->
        <link href="CSS/dashboard/lib/line-icons/line-icons.css" rel="stylesheet">
        <link href="CSS/dashboard/lib/font-awesome/css/fontawesome-all.min.css" rel="stylesheet">
        <link href="CSS/dashboard/css/style.css" rel="stylesheet">
        <!-- Plugins profile form CSS -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
      <!-- 제이쿼리 적용 -->
      <!-- header 시작 -->
           <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="profile/classic/css/footer.css">
        <link href="https://fonts.googleapis.com/css?family=Black+Han+Sans&amp;display=swap" rel="stylesheet">
        <link rel="stylesheet" href="resource/css/header_main.css">
        <link href="resource/css/footer.css" rel="stylesheet">
       <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.13.0/css/all.css"><!-- 마우스 이미지 -->
       <!-- header 끝 -->
<style type="text/css">
   #profileUpload{
      display: none;
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
   }

</style>       
<script type="text/javascript">


   $(function(){
	   $(window).keypress(function(event) { 
		   // 13은 enter다. 엔터를 쳐서 화면이 전환되는 것을 방지.
	       if (event.keyCode == 13){
	    	   event.preventDefault();
	    	   return false;

	       }    
	   });
	   
	   
      $("#nickname").focusout(function(){
            if($("#nickname").val()==null || $("#nickname").val()==""){
           return;
        }
        var nickname = "nickname="+encodeURIComponent($("#nickname").val()); //주소에 데이터를 보낼때 변환해주는 역할
        var nickchk = document.getElementById("nickchk");
        $.ajax({
          url:"sign.do?command=nickcheck&"+nickname,
          dataType: "json", //서버에서 받을 데이터 형식 지정
          success:function(msg){
           if(msg.code == 1){
              $("#chknick").html("변경 불가능한 닉네임입니다.").addClass("text-danger").removeClass("text-success");
              nickchk.value = 1;
           }else{
              $("#chknick").html("변경 가능한 닉네임입니다.").removeClass("text-danger").addClass("text-success");
              nickchk.value = 0; 
           }
          },
          error:function(){
             alert("실패");
          }
        });
     });
   
      $("#UploadBtn").click(function(e) {
         e.preventDefault();
         $("#profileUpload").click();
      });
      
      
      var upload = document.querySelector('#profileUpload');
    
       upload.addEventListener('change',function (e) {
           var get_file = e.target.files;

           var image = document.getElementById("profileImg");
    
           /* FileReader 객체 생성 */
           var reader = new FileReader();
    
           /* reader 시작시 함수 구현 */
           reader.onload = (function (aImg) {
               console.log(1);
    
               return function (e) {
                   console.log(3);
                   /* base64 인코딩 된 스트링 데이터 */
//                    aImg.src = e.target.result;
                   image.src = e.target.result;
               }
           })(image)
    
           if(get_file){
               /* 
                   get_file[0] 을 읽어서 read 행위가 종료되면 loadend 이벤트가 트리거 되고 
                   onload 에 설정했던 return 으로 넘어간다.
                   이와 함게 base64 인코딩 된 스트링 데이터가 result 속성에 담겨진다.
               */
               reader.readAsDataURL(get_file[0]);
               console.log(2);
           }

       });

   });
   function passwordCheckk(){
      var password = document.getElementById("password").value;
      var passwordCheck = document.getElementById("passwordCheck").value;
      
      /* if(password != null && password != ""){
         if(password != passwordCheck){
            alert("비밀번호를 다시 한번 입력해주세요");
            
         }
      } */
      if(passwordCheck =="" && password=="" && passwordCheck == null && password == null){
         alert("수정되었습니다.");
      }else if(password != passwordCheck){
            alert("비밀번호가 일치하지 않습니다.")
      }else{
      
      }
      
   }
   

</script>
            
<% MemberDto member = (MemberDto)session.getAttribute("member"); %>      
</head>
<body>
<div class="header_wrap top15" style="height:100px; padding-bottom:15px; margin-left: 166px; margin-top: 20px; ">
<nav class="navbar navbar-expand-lg" style="background-color: white" id="main-nav">
        <div class="container-main">
            <div class="navbar-left">
                <h2 class="logotitlee" style="text-align:center;font-family: 'Black Han Sans', sans-serif; ">
                <a class="navbar-brand " href="main.do"     style="padding-right: 453px; padding-top: 27px;" >HARU
                </a></h2>
            </div>
            <div class="navbar-right" style="margin-left: 56px;" >
                <ul class="nav navbar-nav">
                    <li class="nav-item topLi" style="margin: 20PX; margin-right: 41px"><a href="#" class="menulink" style="color: black; b"></a>
                        <ul class="submenu" id="topul"> 

                        </ul>
                    </li>
                    <li class="nav-item topLi" style="margin: 20PX;"><a href="board.jsp" class="menulink" style="color: black; background-color:white;
                       padding-top: 29px;">FAQ</a>
                        <ul class="submenu" id="topul"> 
                 
                        </ul>
                    </li>
                    <li class="nav-item topLi" style="margin: 20PX;"><a href="profile.do?command=mypage" class="menulink" style="color: black; background-color:white;
                       padding-top: 29px;">마이페이지</a>
                        <ul class="submenu" id="topul"> 
                          
                        </ul>
                    </li>
                    <li class="nav-item topLi" style="margin: 13PX;">
                    <a class="menulink" href="sign.do?command=signout" style="background-color:white; padding-top: 29px; padding-left: 31px">
                       <button type="button" class="btn btn-outline" style=" border:0;font-size: 14px !important; background-color:white;
                          padding-left: 0px; padding-right: 0px;">&nbsp; &nbsp;<%=(member==null)?"로그인":"로그아웃" %>&nbsp;&nbsp;&nbsp;</button>
                    </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    </div>


   <div class="container mb40">
        <hr>
      <form action="profile.do" method="post" enctype="multipart/form-data"> 
           <input type="hidden" id="command" name="command"  value="student_profile">
            <input type="hidden" name="xxxx" id="xxxx" value="xxxx" />
           <h4>내 프로필</h4>
            <hr class="mb20">
            <div class="row">
                <div class="col-md-12 ">
                    <div class="fancy-title mb30">   
                    </div><!--title-->
                    <div class="form-group row" id="preview">
                            <img src="<%=member.getProfileImg() %>" id="profileImg" alt="" class="img-fluid shadow-sm avatar100 ml-auto mr-auto d-block rounded-circle">
                    </div>
                    <div class="form-group row">       
                      <button id="UploadBtn" class="ml-auto mr-auto rounded-circle" style="background-color: pink;"><i class="fas fa-camera" ></i></button>
                      <input type="file" id="profileUpload" name="profileImg">
                      <input type="hidden" name="basicphoto" value="<%=member.getProfileImg() %>">
                    </div>
                </div>
            </div>
            <hr>
            <div class="row" >
                <div class="col-md-12">
                    <div class="row">
                        <label for="example-text-input" class="col-2 col-form-label">id</label>
                        <label for="example-text-input" class="col-2 col-form-label"><%=member.getId() %></label>
                    </div>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <label for="example-text-input" class="col-2 col-form-label">이름</label>
                        <label for="example-text-input" class="col-2 col-form-label"><%=member.getName() %></label>
                    </div>
                </div>
            </div>
            <hr>
            <!--**********************************************  -->
         
         
            <div class="row">
                <div class="col-md-12 ">
                    <div class="row">
                        <label for="example-text-input" class="col-2 col-form-label">닉네임</label>
                       	           <input type="hidden" id="nickchk" name="nickchk" value="0">
                        <div class="col-10">
                            <input class="form-control" type="text" value="<%=member.getNickname() %>" id="nickname" name="nickname">
                        </div>
                        <div class="col-lg-8" id="chknick">
                     </div>
                    </div>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-md-12 ">
                    <div class="row">
                        <label for="example-text-input" class="col-2 col-form-label">생년월일</label>
                        <div class="col-10">
                            <!-- <input class="form-control" type="text" value="도둑놈" id="example-text-input"> -->
                            <input class="form-control col-3" type="date" value="<%=member.getBirth()%>" name="birth">
                        </div>
                    </div>
                </div>
            </div>
            <hr class="mb50">
            <h4>비밀번호 수정</h4> 
            <hr>
            <div class="row mb30">
                <div class="col-md-12 mb10">
                    <div class="row">
                        <label for="example-text-input" class="col-2 col-form-label">비밀번호</label>
                        <div class="col-10 mb10">
                            <input class="form-control" type="password" placeholder="비밀번호를 입력하세요" id="password" name="pw">
                        </div>
                        <label for="example-text-input" class="col-2 col-form-label" style="padding-top: 30px;">비밀번호 확인</label>
                        <div class="col-10" style="padding-top: 25px;">
                            <input class="form-control" type="password" placeholder="비밀번호를 다시 입력해주세요" id="passwordCheck" name="pwchk">
                        </div>
                    </div>
                    
                </div>
            </div>
            
              <div class="row">
                <div class="col-md-12 ">
                    <div class="row" style="padding-top: 50px;width:100%;text-align:center;display:block;">
                  		<input type="submit" class="btn btn-secondary" value="수정"
                						onclick="passwordCheckk();">
                		<input type="button" class="btn btn-secondary" value="회원탈퇴" 
                  						onclick="location.href='memberout.jsp'">
                    </div>
                </div>
            </div>
               <div style="width:100%; height:80px;"></div>
         </form>   
      </div>
         <!--수정 완료 -->
        <!-- 프로필 수정 끝 -->
      
     
       <footer id="fh5co-footer" class="fh5co-bg" role="contentinfo">
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
                     <button style="background-color: #9BDAF2;cursor: pointer; width:250px; height:50px; margin-bottom:20px;"><a href="https://taling.me/" style="color: white; text-decoration:none;">탈잉</a></button>
                     <button style="background-color: #9BDAF2;color: white;cursor: pointer; width:250px; height:50px;"><a href="https://booking.naver.com/" style="color: white; text-decoration:none;">네이버 예약</a></button>               
                  </ul>
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
</html>