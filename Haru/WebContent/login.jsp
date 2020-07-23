<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<%
	String redirect = (String) request.getAttribute("redirect");
%>
<%
	redirect = (redirect == null) ? "main.do" : redirect;
%>
<%
	String classpk = (String) request.getAttribute("classpk");
%>
<%
	classpk = (classpk == null) ? "" : classpk;
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인-하루</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
<!-- 제이쿼리 적용 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resource/css/bootstrap.css">
<link rel="stylesheet" href="resource/js/bootstrap.min.js">
<link rel="stylesheet" href="resource/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="resource/css/login.css">
<link
	href="https://fonts.googleapis.com/css?family=Black+Han+Sans&amp;display=swap"
	rel="stylesheet">
<script type="text/javascript">
	function check() {
		if (login.id.value == "") {
			alert("아이디를 입력하세요");
			return false;
		} else if (login.pw.value == "") {
			alert("비밀번호를 입력하세요");
			return false;
		} else
			return true;
	}
	var div_form_login = '';
	var div_form_id = '';
	var div_form_pw = '';
	$(function() {
		div_form_login = $('#div_login_form');
		div_form_id = $('#div_search_id');
		div_form_pw = $('#div_search_pw');
	});
	function loginform() {
		div_form_id.addClass('div_hide');
		div_form_pw.addClass('div_hide');
		div_form_login.removeClass('div_hide');
	}
	function idform() {
		div_form_login.addClass('div_hide');
		div_form_pw.addClass('div_hide');
		div_form_id.removeClass('div_hide');
	}
	function pwform() {

	}
</script>
<style type="text/css">
.div_hide {
	display: none;
}
</style>
</head>
<body>
	<!-- LOGIN 시작 -->
	<div class="login_wrap">
		<div class="text">
			<p class="title1">
				<span class="titletxt">대한민국 NO.1</span> 원데이 클래스 예약 플랫폼
			</p>
			<h2 class="logintitle"
				style="text-align: center; font-family: 'Black Han Sans', sans-serif;">HARU</h2>
		</div>
		<div class="login-form">
			<form action="sign.do?command=signin" method="POST" id="login"
				onsubmit="return check();">
				<input type="hidden" name="redirect" value="<%=redirect%>">
				<input type="hidden" name="classpk" value="<%=classpk%>">
				<div id="div_login_form" class="">
					<div class="login-form-row">
						<div class="form-label">아이디</div>
						<div class="form-input">
							<input type="text" name="loginid" placeholder="아이디를 입력해 주세요."
								id="id">
						</div>
					</div>
					<div class="login-form-row">
						<div class="form-label">비밀번호</div>
						<div class="form-input">
							<input type="password" name="loginpw"
								placeholder="비밀번호를 입력해 주세요." id="pw">
						</div>
					</div>
					<div class="login-form-row">
						<input type="submit" value="일반 로그인" class="btn submitbtn">
					</div>
					<div class="login-form-row kakao">
						<a
							href="https://kauth.kakao.com/oauth/authorize?client_id=f321498efdca3bbaa89bdab055ec38fe&redirect_uri=http://sclass.iptime.org:8787/Haru/kkoauth.do&response_type=code">
							<img src="resource/images/kakao_login_btn_medium_wide.png">
						</a>
					</div>
					<div class="login-form-row bottom-link" style="padding-top: 100px;">
						<p>
							<a href="sign.jsp">회원가입 </a>
						</p>
						<p>
							<a href="#" onclick="idform();">아이디 찾기 </a>
						</p>
						<p>
							<a href="#" onclick="pwform();">비밀번호 찾기</a>
						</p>
					</div>
				</div>
				<!--  아이디 찾기  -->
				<div id="div_search_id" class="div_hide">
					<div class="login-form-row">
						<div class="form-label">이름</div>
						<div class="form-input">
							<input type="text" name="loginid" placeholder="아이디를 입력해 주세요.">
						</div>
					</div>
					<div class="login-form-row">
						<div class="form-label">닉네임</div>
						<div class="form-input">
							<input type="password" name="loginpw"
								placeholder="비밀번호를 입력해 주세요.">
						</div>
					</div>
					<div class="login-form-row">
						<input type="button" value="일반 로그인" class="btn submitbtn"
							onclick="loginform();">
					</div>
					<div class="login-form-row kakao">
						<a
							href="https://kauth.kakao.com/oauth/authorize?client_id=f321498efdca3bbaa89bdab055ec38fe&redirect_uri=http://sclass.iptime.org:8787/Haru/kkoauth.do&response_type=code">
							<img src="resource/images/kakao_login_btn_medium_wide.png">
						</a>
					</div>
					<div class="login-form-row bottom-link" style="padding-top: 100px;">
						<p>
							<a href="sign.jsp">회원가입 </a>
						</p>
						<p>
							<a href="#">아이디 찾기 </a>
						</p>
						<p>
							<a href="#">비밀번호 찾기</a>
						</p>
					</div>
				</div>
				<!--  비밀번호 찾기  -->
				<div id="div_search_pw" class="div_hide">
					<div class="login-form-row">
						<div class="form-label">아이디</div>
						<div class="form-input">
							<input type="text" name="loginid" placeholder="아이디를 입력해 주세요.">
						</div>
					</div>
					<div class="login-form-row">
						<div class="form-label">비밀번호</div>
						<div class="form-input">
							<input type="password" name="loginpw"
								placeholder="비밀번호를 입력해 주세요.">
						</div>
					</div>
					<div class="login-form-row">
						<input type="submit" value="일반 로그인" class="btn submitbtn">
					</div>
					<div class="login-form-row kakao">
						<a
							href="https://kauth.kakao.com/oauth/authorize?client_id=f321498efdca3bbaa89bdab055ec38fe&redirect_uri=http://sclass.iptime.org:8787/Haru/kkoauth.do&response_type=code">
							<img src="resource/images/kakao_login_btn_medium_wide.png">
						</a>
					</div>
					<div class="login-form-row bottom-link" style="padding-top: 100px;">
						<p>
							<a href="sign.jsp">회원가입 </a>
						</p>
						<p>
							<a href="#">아이디 찾기 </a>
						</p>
						<p>
							<a href="#">비밀번호 찾기</a>
						</p>
					</div>
				</div>
		</div>
		<address class="foot" style="padding: 40px;">Copyright ©
			HARU Corp.All Rights Reserved.</address>
		</form>
	</div>
	</div>
	<!-- LOGIN 마침 -->
</body>
</html>