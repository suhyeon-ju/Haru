<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@page import="com.member.dto.MemberDto"%>
<% MemberDto member = (MemberDto)session.getAttribute("member");
   if(member==null){response.sendRedirect("sign.do?redirect=memberout.jsp");return;}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원탈퇴-하루</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
    <!-- 제이쿼리 적용 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="stylesheet" href="resource/css/bootstrap.css">
    <link rel="stylesheet" href="resource/js/bootstrap.min.js">
    <link rel="stylesheet" href="resource/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="resource/css/login.css">
    <link href="https://fonts.googleapis.com/css?family=Black+Han+Sans&amp;display=swap" rel="stylesheet">
    <script type="text/javascript">
        function check(){
			if(login.pw.value==""){
                alert("비밀번호를 입력하세요");
                return false;	
            }else{
            	
            }
            	return true;
        }
    </script>
</head>
<body>
<!-- LOGIN 시작 -->
<div class="login_wrap">
<div class="text"> <p class="title1">
                     <span class="titletxt">대한민국 NO.1</span> 원데이 클래스 예약 플랫폼
                  </p>
    <h2 class="logintitle" style="text-align:center;font-family: 'Black Han Sans', sans-serif;">HARU</h2></div>
	    <div class="login-form">
        <form action="sign.do?command=memberout" method="POST" id="login" onsubmit="return check();">
            <!--  아이디 찾기  -->
            <div id="div_search_id" class="div_hide">
             <div class="login-form-row">
                <div class="form-label">정말로 HARU를 떠나시겠습니까?<br>
                	회원탈퇴를 클릭 후에는 탈퇴를 취소할수 없습니다..<br>
                	아래 회원탈퇴를 클릭할경우 이에 동의한것으로 처리됩니다. </div>
                
            </div>
            <div class="login-form-row">
                <div class="form-label">비밀번호</div>
                <div class="form-input">
                    <input type="password" name="loginpw" placeholder="비밀번호를 입력해 주세요." id="pw">
                </div>
            </div>
            <div class="login-form-row">
                <input type="submit" value="회원탈퇴" class="btn submitbtn">
            </div>
            <div class="login-form-row">
                <input type="button" value="돌아가기" class="btn submitbtn" onclick="location.href='main.do'">
            </div>
            </div>
            </div>
            <address class="foot" style="padding:40px;">Copyright © HARU Corp.All Rights Reserved.</address>
        </form>
    </div>
</div>
<!-- LOGIN 마침 -->
</body>
</html>