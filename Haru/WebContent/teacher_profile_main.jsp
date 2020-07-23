<%@page import="com.controller.profile.chartDto"%>
<%@page import="com.classinfo.dto.ClassInfoDto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.controller.profile.countVo"%>
<%@page import="com.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html charset=UTF-8"); %>

<% countVo vo = (countVo)request.getAttribute("vo");
	if(vo==null){
		response.sendRedirect("profile.do?command=teacher_profile_main");
		return ;
	}
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>teacher_profile_main</title>
    
    <!--차트 부트스트랩 CDN  -->
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <!-- Bootstrap-->
    <link href="CSS/dashboard/lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!--Common Plugins CSS -->
    <link href="CSS/dashboard/css/plugins/plugins.css" rel="stylesheet">
    <link href="profile/classic/css/plugins.css" rel="stylesheet">
    <link href="profile/classic/css/style.css" rel="stylesheet">
    <!--fonts-->
    <link href="CSS/dashboard/lib/line-icons/line-icons.css" rel="stylesheet">
    <link href="CSS/dashboard/lib/font-awesome/css/fontawesome-all.min.css" rel="stylesheet">
    <link href="CSS/dashboard/css/style.css" rel="stylesheet">
    <!-- 간단프로필 css -->
    <link href="profile/classic/css/realchartpage.css" type="text/css" rel="stylesheet">
    
    <!-- jQuery first, then Tether, then Bootstrap JS. -->
    <script src="profile/classic/js/plugins.js"></script>
    <!-- 제이쿼리 적용 -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.0.min.js"></script>

    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.13.0/css/all.css"><!-- 마우스 이미지 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="profile/classic/css/footer.css">
        <link href="https://fonts.googleapis.com/css?family=Black+Han+Sans&amp;display=swap" rel="stylesheet">
        <link rel="stylesheet" href="resource/css/header_main.css">
<!-- 수정 시작 -->
<% MemberDto member = (MemberDto)session.getAttribute("member"); %>      

<!-- 수정 끝 -->



    <!-- 강의별 막대그래프 -->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    
    <script type="text/javascript">
	
        google.charts.load('current', { 'packages': ['bar'] });
        google.charts.setOnLoadCallback(drawChart);
		
        function drawChart() {
     
         		 
           	 var data = google.visualization.arrayToDataTable([
              	<%=(String)request.getAttribute("eachchart")%>
            ]);
		  
	        
		
            var options = {
                chart: {
                    title: '매출 현황',
                    subtitle: '강의 별 매출',
            
                   
                }
            };

            var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

            chart.draw(data, google.charts.Bar.convertOptions(options));
        }
     
    </script>

     <script type="text/javascript">
        google.charts.load('current', { packages: ['corechart', 'line'] });
        google.charts.setOnLoadCallback(drawBasic);

        function drawBasic() {

            var data = new google.visualization.DataTable();
            data.addColumn('string', '월별');
            data.addColumn('number', '매출');

            data.addRows([
             	<%String arr_month = (String) request.getAttribute("arr_month");%>
            	<%=arr_month%>
            	
               /*  ['1월', 0], ['', 10], ['3월', 20], ['', 17], ['5월', 25], ['', 23],
                ['7월', 22], ['', 35], ['9월', 40], ['', 50], ['11월', 70], ['', 80] */
                
            ]);
			
            
            var options = {
                title: '총매출 ',

                hAxis: {
                    title: '월'
                },
                vAxis: {
                    title: 'Popularity'
                }

            };

            var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

            chart.draw(data, options);
            
            
            
            
        }

    </script>
	
    <script type="text/javascript">  
    var gb_classpk = 0;

   $(function() {
   	$("#member_list tr.chk_js_row").click(function(){     
   		 
   	        var str = ""
   	        var tdArr = new Array();    // 배열 선언
   	            
   	        // 현재 클릭된 Row(<tr>)
   	        $("tr").css("background-color","white");
   	        $(this).css("background-color","silver");
   	           
   	        var classpk = $(this).children().first().find(".pk").html();
   	        gb_classpk = classpk;
   	       
			$.ajax({
					url:"StudentListAjax?pk="+classpk,
    				dataType: "json", 
    				success:function(msg){
    					
    					$('#studentList').html('');
    					var progressinfo = msg.progressinfo;
    					var member = msg.member;
    					$.each(member, function(key, value){
    						
  			    			
  			    				var tr = $("<tr></tr>");
  			    				
  			    			    var td0 = $("<td>"+key+"</td>");
  			    			    var td1 = $("<td>"+value.name+"</td>");
  			    			    var td2 = $("<td>"+value.phone+"</td>");
  			    			    var td3 = $("<td>"+value.gender+"</td>");
  			    			   tr.append(td0);
  			    			   tr.append(td1);
  			    			   tr.append(td2);
  			    			   tr.append(td3);
    						
    						$('#studentList').append(tr);
    						
    					
    					});
					var mprogress = progressinfo.startdate+" / "+progressinfo.enddate+" / "+progressinfo.progressrate+"%";
				
					$('#dateinfo').html(mprogress);
 					
							$('#progress_bar').css("width",progressinfo.progressrate+"%");
							$('#progress_bar').css("aria-valuenow",progressinfo.progressrate);
    				},

    			/* 	error:function(){alert("실패");} */
			});
			
   	        
   	        
   	        
   	});
   });  
    </script>
    
    <script type="text/javascript">  
   $(function() {
   	$("#download").click(function(){     
//   		alert(gb_classpk);
   		
   		if(gb_classpk!=0){
   			$.ajax({
				url:"excel.do?m=set&pk="+gb_classpk,
				dataType: "json", 
				success:function(msg){
					if(msg.code==1){
						alert("파일이 생성 중 입니다.잠시만 기다려주세요");
						setTimeout(function() {
							  $.ajax({
									url:"excel.do?m=get&pk="+gb_classpk,
									dataType: "json", 
									success:function(msg){
								
										if(msg.code==1){
											location.href="./upload/"+msg.url;
										}else{
											alert("실패");
										}
									},
									error:function(){alert("실패");}
									
							  
							  
							  });
							}, 3000);
						
		
					}else{
						alert("실패");
					}
				},
				error:function(){alert("실패");}
   			});
   		}else{
   			alert("클래스를 선택해주세요.");
   		}
   	});
   });  
    </script>
    
    <style type="text/css">
         .container0 {
            display: flex;
            flex-wrap: nowrap;
            text-align: center;
            line-height: 50px;
            font-size: 25px;
        }

        .container2 {
            display: flex;

        }
        .pk{
        	display: none;
        } 
        
/* header */


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
        /* header end */
.downbutton{
    margin-right: 0px;
}
.studentlist-title{
    display: flex;
    float: left;
}
.studentlist-title-botton{
    /* display: flex; */
    display: inline-block;
    float: right;

}

.row {
    margin-right: -15px;
    margin-left: -15px;
}
.header_wrap.top15 {
	margin-top:15px;
}
    </style>
</head>


<body>
<div class="header_wrap top15" style="height:150px; padding-bottom:15px; margin-left: 166px ">
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
                    <a class="menulink" href="sign.do?command=signout" style="background-color:white; padding-top: 29px; padding-left: 0;">
                    	<button type="button" class="btn btn-outline" style=" border:0;font-size: 14px !important; background-color:white;
                    		padding-left: 0px; padding-right: 0px; font-weight:100 !important;color:#337ab7;">&nbsp; &nbsp;<%=(member==null)?"로그인":"로그아웃" %>&nbsp;&nbsp;&nbsp;</button>
                    </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    </div>

    


    <div class="container mb40">
        <hr>
        <h4>프로필</h4>
        <hr class="mb10">
        <div class="row">
            <div class="col-md-12 ">
                <!-- <div class="fancy-title mb30">
                </div> -->
                <!--title-->
                
                    <!-- 요약 정보 -->
                 <!-- 강사 간단 프로필 -->
				<div class="profile-container">
					<div class="row">
						<div class="col-xs-12 col-sm-15">
							<!-- Teacher profile -->
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">Teacher profile</h4>
								</div>
								<div class="panel-body">
									<div class="profile__avatar">
										<img src="<%=member.getProfileImg() %>" alt="...">
									</div>

									<div class="profile__header">
										<h4>
											<%=member.getName() %> <small>강사</small>
										</h4>
										<p class="text-muted"><%=member.getPmessage() %></p>
										<p>
											<input type="button" value="프로필 수정" onClick="location.href='./teacher_profile.jsp'">

										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 요약 정보 끝!! -->
               

            </div>
        </div>
        <hr>
        <h4>통계 자료</h4>
        <hr class="mb20">
        <div class="row">
            <div class="col-md-12 ">
<!--             <div class="ml-auto mr-auto "> -->
                <div class="fancy-title mb30">
                </div>
                <!--title-->
                <div class="form-group row">
                    <!-- 요약 정보 -->
                    <div class="row mr-auto ml-auto">
                        <div class="col-lg-6 mb-6 col-md-6 mb-30">
                            <div class="list border1 rounded overflow-hidden">
                                <div class="list-item">
                                    <div
                                        class="list-thumb bg-primary text-primary-light avatar rounded-circle avatar60 shadow-sm">
                                        <i class="fas fa-book"></i>
                                    </div>
                                    <div class="list-body text-right">
                                        <span class="list-title fs-2x"><%=vo.getTotal_class_num() %></span>
                                        <span class="list-content fs14">개설 강좌수</span>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--col-->
                        <div class="col-lg-6 mb-6 col-md-6 mb-30">
                            <div class="list border1 rounded overflow-hidden">
                                <div class="list-item">
                                    <div
                                        class="list-thumb bg-warning-active text-warning-light avatar rounded-circle avatar60 shadow-sm">
                                        <i class="fas fa-child"></i>
                                    </div>
                                    <div class="list-body text-right">
                                        <span class="list-title fs-2x"><%=vo.getTotal_student() %></span>
                                        <span class="list-content  fs14">현재 수강생</span>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--col-->
                        <div class="col-lg-6 mb-6 col-md-6 mb-30">
                            <div class="list border1 rounded overflow-hidden">
                                <div class="list-item">
                                    <div
                                        class="list-thumb bg-danger-active text-danger-light avatar rounded-circle avatar60 shadow-sm">
                                        <i class="icon-Add-Basket"></i>
                                    </div>
                                    <div class="list-body text-right">
                                        <span class="list-title fs-2x"><%=vo.getmulstudent()%>%</span>
                                        <span class="list-content  fs14">평균 수강률</span>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--col-->
                        <div class="col-lg-6 mb-6 col-md-6  mb-30">
                            <div class="list border1 rounded overflow-hidden">
                                <div class="list-item">
                                    <div
                                        class="list-thumb bg-success-active text-success-light avatar rounded-circle avatar60 shadow-sm">
                                        <i class="icon-Money-Bag"></i>
                                    </div>
                                    <div class="list-body text-right">
                                        <span class="list-title fs-2x"><%=vo.getTotal_pay() %></span>
                                        <span class="list-content fs14">총 매출</span>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--col-->
                    </div>
                    <!-- 요약 정보 끝!! -->
                </div>

            </div>
        </div>
        <!-- <hr class="mb50"> -->
        <!-- <h4>막대 차트</h4>  -->

        <hr>
        <div class="row mb30">
            <div class="col-md-12 mb10">
                <div class="row">
                    <div class="container2 ml-auto mr-auto">
                        <div id="chart1">
                            <div id="columnchart_material" class="chart" style="width: 0%; height: 300px; margin: 0;">
                            </div>
                        </div>
                        <!-- <div id="chart2" > 
                             <div id="piechart_3d" class="chart" style="width: 50%; height: 300px; margin: 0;"></div>
                            </div>  -->


                        <!-- 총매출 그래프!!! -->
                        <div class="googlechart" style="display:table; width: 100%; height: 300px;">
                            <div id="chart_div" style="display:table-cell; vertical-align: bottom;"></div>

                        </div>
                        <!-- 총매출 그래프 끝 -->
                    </div>
                </div>
            </div>
        </div>
        <!-- <h4>막대,원 차트</h4> 끝!!!!!!!!!!!!!!!!!!!!!! -->
        <hr>
        <h4>강의 리스트</h4>
        <hr class="mb20">
        <div class="row">
            <div class="col-md-12">
                <div class="row ">
                    <div class=" ml-auto mr-auto " style="margin: 0 30px 0 15px; width:650px;">
                        <table id="member_list" class="table table-bordered ">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">강의명</th>
                                    <th scope="col">매출</th>
                                    <th scope="col">수강인원</th>
                                    <th scope="col">수강룰</th>
                                    <th scope="col">진행률</th>
                                    <th scope="col">수업장소</th>
                                    <th scope="col">&nbsp;</th>
                                </tr>
                            </thead>
                            <tbody>
                            
                            <c:choose>
                            	<c:when test="${empty list1 }">
                            		<tr>
                            			<td colspan="8"  style="text-align:center;">======================등록된 강의가 없습니다.======================</td>
                            		</tr>
                            	</c:when>
                            	<c:otherwise>
                            		<input type="hidden" id='list1' value="${list1} ">
                            		<c:forEach items="${list1 }" var="dto" varStatus="status">
                            			<tr class="chk_js_row">
                            				<th scope="row">${status.count} <span class="pk">${dto.classpk}</span></th>
                            				<td>${dto.title }</td>
                            				<td>${dto.profit }</td>
                            				<td>${dto.nowstudent }</td>
                            				<td>${dto.course_rate }%</td>
                            				<td>${dto.progress_rate }%</td>
                            				<td>${dto.loc }</td>
                            				<td><a href="classinfo.do?command=classmodify&classpk=${dto.classpk}">수정</a></td>
                            				<input type="hidden" >
                            			</tr>
                            		</c:forEach>
                            	</c:otherwise>
                            </c:choose>
                            </tbody>
                        </table>
						<div style="width:100%;padding-left:550px;">
							<a href="classregister.jsp" class="btn btn-sm btn-gradient btn-gradient-secondary mb-2 downbutton studentlist-title-button"
                          		type="button" ><span style="font-size:10pt;">강의 등록</span></a>
						</div>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <h4>강의 정보</h4>
        <hr class="mb20">
        <div class="row">
            <div class="col-md-12">
                <div class="row ">
                    <div class=" ml-auto mr-auto " style="margin: 0 30px 0 15px; width:650px;">
                        <table class="table table-bordered ">
                            <div class="studentlist-title" style="font-size:20px ">수강생 정보</div>
                            <div style="float: right;">
                            <a href="#" class="btn btn-sm btn-gradient btn-gradient-secondary mb-2 downbutton studentlist-title-button"
                            		id="download" type="button" value="download"><span style="font-size:10pt;">download</span><span class="pk">${classpk}</span></a>
                            </div>
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">이름</th>
                                    <th scope="col">연락처</th>
                                    <th scope="col">성별</th>
                                </tr>
                            </thead>
                            <tbody id="studentList">
               


                            </tbody>
                        </table>

                        <!-- 강의별 진행바 -->
                        <div class="progress-bar-title">수업 진행 현황</div>
                        <div class="progress_area">
                            <div class="progress">
                            <div class="progress-bar bg-warning" id="progress_bar" role="progressbar" style="width: 0%" aria-valuenow="0"
                                aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <div id="dateinfo">시작일/종료일/진행률</div>
                        </div> 




                    </div>
                </div>
            </div>
        </div>
        <hr class="mb50">
    </div>
    

    <!--back to top-->
    <a href="#" class="back-to-top hidden-xs-down" id="back-to-top"><i class="ti-angle-up"></i></a>

        
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