<%@page import="com.classdate.dto.ClassDateDto"%>
<%@page import="java.util.List"%>
<%@page import="com.member.dto.MemberDto"%>
<%@page import="com.classinfo.dto.ClassInfoDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%
	String memberid = (String)request.getAttribute("memberid");
	if(memberid==null){response.sendRedirect("pay.do");return;}
	ClassInfoDto info = (ClassInfoDto)request.getAttribute("classinfo");
	List<ClassDateDto> date = (List<ClassDateDto>)request.getAttribute("classdate");
	MemberDto tinfo = (MemberDto) request.getAttribute("classtea");
	MemberDto member = (MemberDto) session.getAttribute("member");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
   <!-- 메타 태그 시작 -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <!-- 메타 태그 마침 -->
    <!-- 외부 파일 링크 시작 -->
    <!-- JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
    <!-- CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
    <!-- 외부 파일 링크 마침 -->
    
    <!-- 내부 파일 링크 시작 -->
    <!-- 외부 파일보다 재정의가 우선 적용되어야 하기 때문에 아래 작성 -->
    <link rel="stylesheet" href="payment_page/payment_style.css">
    <!-- 내부 파일 링크 마침 -->
    <!-- 헤더,풋터관련 css -->
    <link rel="stylesheet" href="resource/css/header_main.css">
    <link rel="stylesheet" href="resource/css/footer_main.css">
    <link href="https://fonts.googleapis.com/css?family=Black+Han+Sans&amp;display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.13.0/css/all.css">
    <link rel="stylesheet" type="text/css" href="resource/css/style.css" >
    <link rel="stylesheet" type="text/css" href="resource/css/style_main.css" >
    <!-- 헤더,풋터관련 css 마침-->
    <title>결제</title>
    <style type="text/css">
    .step1.left .pimg{
     background-image: url(<%=tinfo.getProfileImg()%>);
     }
        
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
    </style>
    <script type="text/javascript">
    
    
    $(function(){
    	var IMP = window.IMP;
    	IMP.init('imp54718779');
    	var msg;
    	var pub_pay_uid;
    	
    	$('#btn_step1').click(function(){
    		if($('input:radio[name=rdo_day]').is(':checked')){
    		$('#div_step1').addClass('hiddendiv');
    		$('#div_step2').removeClass('hiddendiv');
    		$('#stepslijs1').removeClass('on');
    		$('#stepslijs2').addClass('on');
    		}else{
    			alert('원하는 수업 일정을 선택해 주세요!');
    		}
    	});
    	$('#btn_step2').click(function(){
    		if($('input:radio[name=rdo_pay]').is(':checked')){
    			var pay_method_val = $('input[name=rdo_pay]:checked').val();
    			var member_id_val = '<%=memberid%>';
    			var price_val = '<%=info.getPrice()%>';
    			var classdatepk = $('input[name=rdo_day]:checked').val();
    			var payurl = "pay.do?command=uid";
    			var uidkey = "";
    			payurl += "&datepk="+classdatepk;
    			payurl += "&method="+pay_method_val;
    			
    			$.ajax({
    				url:payurl,
    				dataType: "json",
    				success:function(msg){
    					if(msg.code==1){
    						uidkey = msg.uid;
    						pub_pay_uid = msg.uid;
    						IMP.request_pay({
    		    	            pg : 'html5_inicis',
    		    	            pay_method : pay_method_val,
    		    	            merchant_uid : 'merchant_' + msg.uid,
    		    	            name : 'HARU:<%=info.getTitle()%>',
    		    	            amount : price_val,
    		    	            buyer_email : '',
    		    	            buyer_name : member_id_val,
    		    	            buyer_tel : '',
    		    	            buyer_addr : ''
    		    	        }, function(rsp) {
    		    	            if ( rsp.success ) {
    		    					$.ajax({
    		    						url:"pay.do?command=chkuid&key="+uidkey,
    		    						dataType: "json",
    		    						success:function(msg){
    		    							if(msg.code==1){
    		    								alert('결제 성공!');
        		    				    		$('#div_step2').addClass('hiddendiv');
        		    				    		$('#div_step3').removeClass('hiddendiv');
        		    				    		$('#stepslijs2').removeClass('on');
        		    				    		$('#stepslijs3').addClass('on');
        		    				    		$('#lsarrtep_uid').html('결제 고유 번호 : '+uidkey);
        		    				    		$('#lsarrtep_price').html('결제 금액 : '+price_val);
        		    				    		$('#lsarrtep_method').html('결제 수단 : '+pay_method_val);
        		    				    		$('#lsarrtep_time').html('결제 일자 : '+msg.time);
    		    							}else{
    		    								msg = '결제에 실패하였습니다.';
    		    								msg+= 'REST API 조회 실패 또는 변조';
    		    								alert(msg);
    		    							}
    		    						},
    		    						error:function(){
    		    							msg = '결제에 실패하였습니다.';
    		    							msg += '에러내용 : 성공여부 조회 실패';
    		    							alert(msg);
    		    						}
    		    					});
    		    	            } else {
    		    	                msg = '결제에 실패하였습니다.';
    		    	                msg += '에러내용 : ' + rsp.error_msg;
    		    	                alert(msg);
    		    	            }
    		    	        });
    					}else{
    						msg = '결제에 실패하였습니다.';
    						msg += '에러내용 : DB등록 실패';
    						alert(msg);
    					}
    				},
    				error:function(){
    					msg = '결제에 실패하였습니다.';
    					msg += '에러내용 : AJAX 통신 연결 실패';
    					alert(msg);
    				}
    			});
    		}else{
    			alert('결제 수단을 선택해 주세요.');
    		}
    	})
    	$('#btn_step3').click(function(){
    		location.href='classinfo.do?command=detailclass&classpk='+<%=info.getClasspk()%>;
    	});
    });
    </script>


    
</head>
<body>
    
	<!-- 헤더 영역 시작  -->
	
    <nav class="navbar navbar-expand-lg" style="background-color: white" id="main-nav" style="padding-top: 250px;">
        <div class="container">
            <div class= "navbar-left">
                <h2 class="logotitlee" style="text-align:center;font-family: 'Black Han Sans', sans-serif; ">
                <a style="font-weight:900; font-size:55px;" class="navbar-brand " href="main.do">HARU
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
    <!-- 헤더 영역 마침  -->
    
    
    
<div class="wrap">
    
    

<div style="height:50px;"></div>
    <div class="wrap_cont">
        <div class="title_box col-sm-12">
            <h3 class="fleft">수업신청</h3>
            <div class="steps fright">
            <div class="fright">
                <div class="title_box">
                    <div class="steps">
                        <li class="on fleft" id="stepslijs1"><b>01</b>일정/장소</li>
                        <img src="payment_page/pay_icon_next.png">
                        <li class="fleft" id="stepslijs2"><b>02</b>신청서 작성</li>
                        <img src="payment_page/pay_icon_next.png">
                        <li class="fleft" id="stepslijs3"><b>03</b>결제</li>
                    </div>
                </div>
            </div>
        </div>
        </div>
    <div class="clearfix uline upad"></div>
    <h3 class="class_title"><%=info.getTitle() %></h3>
    <div class="content step1" id="step1_wrap">
        <div class="step1 left">
            <div class="pimg"></div>
            <div class="pmessage">
                <p><%=tinfo.getPmessage() %></p>
            </div>
        </div>
        <div class="step1 right" id="div_step1">
            <div class="conwrap">
                <div class="contitle">원하시는 수업일정을 선택해주세요.</div>
            
            <div class="conchk">
                <% for(ClassDateDto day : date){%>
                <div class="con-row">
                    <label><input type="radio" name="rdo_day" value="<%=day.getClassdatepk()%>">
                    <%=day.print() %></label>
                </div>
                <% } %>
                <!-- <div class="con-row">
                    <label><input type="radio" name="radio1">
                    01.02(월) 00:00~00:00</label>
                </div>-->
               
            </div>
            </div>
            <button class="nextbtn-step1" id="btn_step1">다음으로 진행하기</button>
            
        </div>
        
        <div class="step1 right hiddendiv" id="div_step2">
            <div class="conwrap">
                <div class="contitle">결제 수단을 선택해 주세요.</div>
            
            <div class="conchk">
                <div class="con-row">
                    <label><input type="radio" name="rdo_pay" value="card">
                    신용카드</label>
                </div>
                <div class="con-row">
                    <label><input type="radio" name="rdo_pay" value="trans">
                    실시간 계좌이체</label>
                </div>
                <div class="con-row">
                    <label><input type="radio" name="rdo_pay" value="phone">
                    휴대폰 소액결제</label>
                </div>
                <div class="con-row">
                    <label><input type="radio" name="rdo_pay" value="vbank">
                    무통장 입금</label>
                </div>
                
            </div>
            </div>
            <button class="nextbtn-step1" id="btn_step2">다음으로 진행하기</button>
        </div>
        
        <div class="step1 right hiddendiv" id="div_step3">
            <div class="conwrap">
                <div class="contitle">결제 완료</div>
            
            <div class="conchk">
                <div class="con-row" id="lsarrtep_uid">
                    결제 고유 번호 : ##_결제_고유_번호_##
                </div>
                <div class="con-row" id="lsarrtep_price">
                    결제 금액 : ##_결제_금액_##
                </div>
                <div class="con-row" id="lsarrtep_method">
                    결제 수단 : ##_결제_수단_##
                </div>
                <div class="con-row" id="lsarrtep_time">
                    결제 일자 : yyyy년 mm월 dd일 HH시Mm분
                </div>
            </div>
            </div>
            <button class="nextbtn-step1" id="btn_step3">다음으로 진행하기</button>
        </div>            
    </div>
    </div>
    <div class="hcdiv"></div>
</div>

    <!-- 바닥 영역  시작 -->
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
    <!-- 바닥 영역 마침  -->
    
</body>
</html>