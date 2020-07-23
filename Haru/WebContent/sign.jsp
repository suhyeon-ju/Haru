<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<%@page import="com.kakaoOauth.dto.KKOauthDto"%>
<%@page import="com.member.dto.MemberDto"%>
<%
	KKOauthDto kakao = (KKOauthDto) request.getAttribute("kkoauth");
%>
<%
	MemberDto member = (MemberDto) session.getAttribute("member");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
<!-- 제이쿼리 적용 -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) -->
<script src="http://code.jquery.com/jquery.js"></script>
<!-- 모든 합쳐진 플러그인을 포함하거나 (아래) 필요한 각각의 파일들을 포함하세요 -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.13.0/css/all.css">
<link rel="stylesheet" href="resource/css/header_main.css">
<link
	href="https://fonts.googleapis.com/css?family=Black+Han+Sans&amp;display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="resource/css/footer_main.css">
<link href="resource/css/custom.css" rel="stylesheet">

<style type="text/css">
.navbar-right ul li a {
	background-color: white;
}

.logotitlee {
	font-size: 50px;
	line-height: 54px;
	font-weight: 300;
	padding-top: 8px;
	color: black;
	text-decoration: none;
}
</style>

<script type="text/javascript">
	var sendnum_bool = false;
	$(function() {
		$("#sendnum").click(function() { //버튼
			if (sendnum_bool == false) { //요청
				$("#inputnum").show();
				sendnum_bool = true;
				$.ajax({
					url : "sign.do?command=sms&phone=1",
					dataType : "json",
					success : function(msg) {
						if (msg.code == 1) {
							alert("발송되었습니다.");
						} else {
							alert("발송 실패");
						}
					},
					error : function() {
						alert("실패");
					}
				});
			} else { //확인
				$.ajax({
					url : "sign.do?command=smschk&phone=1&number=1",
					dataType : "json",
					success : function(msg) {
						if (msg.code == 1) {
							alert("인증번호 확인");
						} else {
							alert("다시 확인해주세요!");
						}
					},
					error : function() {
						alert("실패");
					}
				});
			}
		});
		$("#nickname").focusout(
				function() {
					if ($("#nickname").val() == null
							|| $("#nickname").val() == "") {
						return;
					}
					var nickname = "nickname="
							+ encodeURIComponent($("#nickname").val()); //주소에 데이터를 보낼때 변환해주는 역할
					$
							.ajax({
								url : "sign.do?command=nickcheck&" + nickname,
								dataType : "json", //서버에서 받을 데이터 형식 지정
								success : function(msg) {
									if (msg.code == 1) {
										$("#chknick").html("생성 불가능한 닉네임입니다.")
												.addClass("text-danger");
									} else {
										$("#chknick").html("멋진 닉네임이네요!!")
												.removeClass("text-danger")
												.addClass("text-success");
									}
								},
								error : function() {
									alert("실패");
								}
							});
				});
		$("#id").focusout(
				function() {
					if ($("#id").val() == null || $("#id").val() == "") {
						return;
					}
					var id = "id=" + encodeURIComponent($("#id").val()); //주소에 데이터를 보낼때 변환해주는 역할
					$.ajax({
						url : "sign.do?command=idcheck&" + id,
						dataType : "json", //서버에서 받을 데이터 형식 지정
						success : function(msg) {
							if (msg.code == 1) {
								$("#chkid").html("생성 불가능한 아이디입니다.").addClass(
										"text-danger");
							} else {
								$("#chkid").html("사용 가능한 아이디 입니다.")
										.removeClass("text-danger").addClass(
												"text-success");
							}
						},
						error : function() {
							alert("실패");
						}
					});
				});
		$("#YN").val('N');
		$("#chk").change(function() {
			//체크박스 값에 따라 히든값 변경
			if ($("#chk").is(":checked")) {
				$("#YN").val('Y');
			} else {
				$("#YN").val('N');
			}
		});
		//모달을 전역변수로 선언
		var modalContents = $(".modal-contents");
		var modal = $("#defaultModal");

		$('.onlyAlphabetAndNumber').keyup(function(event) {
			if (!(event.keyCode >= 37 && event.keyCode <= 40)) {
				var inputVal = $(this).val();
				$(this).val($(this).val().replace(/[^_a-z0-9]/gi, '')); //_(underscore), 영어, 숫자만 가능
			}
		});

		$(".onlyHangul").keyup(function(event) {
			if (!(event.keyCode >= 37 && event.keyCode <= 40)) {
				var inputVal = $(this).val();
				$(this).val(inputVal.replace(/[a-z0-9]/gi, ''));
			}
		});

		$(".onlyNumber").keyup(function(event) {
			if (!(event.keyCode >= 37 && event.keyCode <= 40)) {
				var inputVal = $(this).val();
				$(this).val(inputVal.replace(/[^0-9]/gi, ''));
			}
		});

		//------- 검사하여 상태를 class에 적용
		$('#id').keyup(function(event) {

			var divId = $('#divId');

			if ($('#id').val() == "") {
				divId.removeClass("has-success");
				divId.addClass("has-error");
			} else {
				divId.removeClass("has-error");
				divId.addClass("has-success");
			}
		});

		$('#password').keyup(function(event) {

			var divPassword = $('#divPassword');

			if ($('#password').val() == "") {
				divPassword.removeClass("has-success");
				divPassword.addClass("has-error");
			} else {
				divPassword.removeClass("has-error");
				divPassword.addClass("has-success");
			}
		});

		$('#passwordCheck').keyup(function(event) {

			var passwordCheck = $('#passwordCheck').val();
			var password = $('#password').val();
			var divPasswordCheck = $('#divPasswordCheck');

			if ((passwordCheck == "") || (password != passwordCheck)) {
				divPasswordCheck.removeClass("has-success");
				divPasswordCheck.addClass("has-error");
			} else {
				divPasswordCheck.removeClass("has-error");
				divPasswordCheck.addClass("has-success");
			}
		});

		$('#name').keyup(function(event) {

			var divName = $('#divName');

			if ($.trim($('#name').val()) == "") {
				divName.removeClass("has-success");
				divName.addClass("has-error");
			} else {
				divName.removeClass("has-error");
				divName.addClass("has-success");
			}
		});

		$('#nickname').keyup(function(event) {

			var divNickname = $('#divNickname');

			if ($.trim($('#nickname').val()) == "") {
				divNickname.removeClass("has-success");
				divNickname.addClass("has-error");
			} else {
				divNickname.removeClass("has-error");
				divNickname.addClass("has-success");
			}
		});

		$('#birth').keyup(function(event) {

			var divBirth = $('#divBirth');

			if ($.trim($('#divBirth').val()) == "") {
				divBirth.removeClass("has-success");
				divBirth.addClass("has-error");
			} else {
				divBirth.removeClass("has-error");
				divBirth.addClass("has-success");
			}
		});

		$('#email').keyup(function(event) {

			var divEmail = $('#divEmail');

			if ($.trim($('#email').val()) == "") {
				divEmail.removeClass("has-success");
				divEmail.addClass("has-error");
			} else {
				divEmail.removeClass("has-error");
				divEmail.addClass("has-success");
			}
		});

		$('#phoneNumber').keyup(function(event) {

			var divPhoneNumber = $('#divPhoneNumber');

			if ($.trim($('#phoneNumber').val()) == "") {
				divPhoneNumber.removeClass("has-success");
				divPhoneNumber.addClass("has-error");
			} else {
				divPhoneNumber.removeClass("has-error");
				divPhoneNumber.addClass("has-success");
			}
		});

		//강사 이미지
		$('#tutor-image').keyup(function(event) {

			var divTutorImage = $('#tutor-image');

			if ($.trim($('#phoneNumber').val()) == "") {
				divPhoneNumber.removeClass("has-success");
				divPhoneNumber.addClass("has-error");
			} else {
				divPhoneNumber.removeClass("has-error");
				divPhoneNumber.addClass("has-success");
			}
		});

		$('#tutor-profile').keyup(function(event) {

			var divTutorProfile = $('#divTutorProfile');

			if ($.trim($('#tutor-profile').val()) == "") {
				divTutorProfile.removeClass("has-success");
				divTutorProfile.addClass("has-error");
			} else {
				divTutorProfile.removeClass("has-error");
				divTutorProfile.addClass("has-success");
			}
		});

		$('#tutor-kakao').keyup(function(event) {

			var divTutorKakao = $('#divTutorKakao');

			if ($.trim($('#tutor-kakao').val()) == "") {
				divTutorKakao.removeClass("has-success");
				divTutorKakao.addClass("has-error");
			} else {
				divTutorKakao.removeClass("has-error");
				divTutorKakao.addClass("has-success");
			}
		});

		//------- validation 검사
		$("form").submit(
				function(event) {

					var provision = $('#provision');
					var memberInfo = $('#memberInfo');
					var divId = $('#divId');
					var divPassword = $('#divPassword');
					var divPasswordCheck = $('#divPasswordCheck');
					var divName = $('#divName');
					var divNickname = $('#divNickname');
					var divBirth = $('#divBirth');
					var divEmail = $('#divEmail');
					var divPhoneNumber = $('#divPhoneNumber');
					var divTutorImage = $('#divTutorImage');
					var divTutorProfile = $('#divTutorcareer');
					var divTutorKakao = $('#divTutorKakao');
					var divTutorCareer = $('#divTutorCareer')

					//회원가입약관
					if ($('#provisionYn:checked').val() == "N") {
						modalContents.text("회원가입약관에 동의하여 주시기 바랍니다."); //모달 메시지 입력
						modal.modal('show'); //모달 띄우기

						provision.removeClass("has-success");
						provision.addClass("has-error");
						$('#provisionYn').focus();
						return false;
					} else {
						provision.removeClass("has-error");
						provision.addClass("has-success");
					}

					//개인정보취급방침
					if ($('#memberInfoYn:checked').val() == "N") {
						modalContents.text("개인정보취급방침에 동의하여 주시기 바랍니다.");
						modal.modal('show');

						memberInfo.removeClass("has-success");
						memberInfo.addClass("has-error");
						$('#memberInfoYn').focus();
						return false;
					} else {
						memberInfo.removeClass("has-error");
						memberInfo.addClass("has-success");
					}

					//아이디 검사
					if ($('#id').val() == "") {
						modalContents.text("아이디를 입력하여 주시기 바랍니다.");
						modal.modal('show');

						divId.removeClass("has-success");
						divId.addClass("has-error");
						$('#id').focus();
						return false;
					} else {
						divId.removeClass("has-error");
						divId.addClass("has-success");
					}

					//패스워드 검사
					if ($('#password').val() == "") {
						modalContents.text("패스워드를 입력하여 주시기 바랍니다.");
						modal.modal('show');

						divPassword.removeClass("has-success");
						divPassword.addClass("has-error");
						$('#password').focus();
						return false;
					} else {
						divPassword.removeClass("has-error");
						divPassword.addClass("has-success");
					}

					//패스워드 확인
					if ($('#passwordCheck').val() == "") {
						modalContents.text("패스워드 확인을 입력하여 주시기 바랍니다.");
						modal.modal('show');

						divPasswordCheck.removeClass("has-success");
						divPasswordCheck.addClass("has-error");
						$('#passwordCheck').focus();
						return false;
					} else {
						divPasswordCheck.removeClass("has-error");
						divPasswordCheck.addClass("has-success");
					}

					//패스워드 비교
					if ($('#password').val() != $('#passwordCheck').val()
							|| $('#passwordCheck').val() == "") {
						modalContents.text("패스워드가 일치하지 않습니다.");
						modal.modal('show');

						divPasswordCheck.removeClass("has-success");
						divPasswordCheck.addClass("has-error");
						$('#passwordCheck').focus();
						return false;
					} else {
						divPasswordCheck.removeClass("has-error");
						divPasswordCheck.addClass("has-success");
					}

					//이름
					if ($('#name').val() == "") {
						modalContents.text("이름을 입력하여 주시기 바랍니다.");
						modal.modal('show');

						divName.removeClass("has-success");
						divName.addClass("has-error");
						$('#name').focus();
						return false;
					} else {
						divName.removeClass("has-error");
						divName.addClass("has-success");
					}

					//별명
					if ($('#nickname').val() == "") {
						modalContents.text("별명을 입력하여 주시기 바랍니다.");
						modal.modal('show');

						divNickname.removeClass("has-success");
						divNickname.addClass("has-error");
						$('#nickname').focus();
						return false;
					} else {
						divNickname.removeClass("has-error");
						divNickname.addClass("has-success");
					}

					//생일
					if ($('#birth').val() == "") {
						modalContents.text("생일을 입력하여 주시기 바랍니다.");
						modal.modal('show');

						divNickname.removeClass("has-success");
						divNickname.addClass("has-error");
						$('#birth').focus();
						return false;
					} else {
						divNickname.removeClass("has-error");
						divNickname.addClass("has-success");
					}

					//이메일
					if ($('#email').val() == "") {
						modalContents.text("이메일을 입력하여 주시기 바랍니다.");
						modal.modal('show');

						divEmail.removeClass("has-success");
						divEmail.addClass("has-error");
						$('#email').focus();
						return false;
					} else {
						divEmail.removeClass("has-error");
						divEmail.addClass("has-success");
					}

					//휴대폰 번호
					if ($('#phoneNumber').val() == "") {
						modalContents.text("휴대폰 번호를 입력하여 주시기 바랍니다.");
						modal.modal('show');

						divPhoneNumber.removeClass("has-success");
						divPhoneNumber.addClass("has-error");
						$('#phoneNumber').focus();
						return false;
					} else {
						divPhoneNumber.removeClass("has-error");
						divPhoneNumber.addClass("has-success");
					}

					if ($('#chk').is(':checked') != true) {
						return true;
					}

					if ($('#tutor-image').val() == "") {
						modalContents.text("강사 이미지를 업로드해주세요");
						modal.modal('show');

						divTutorImage.removeClass("has-success");
						divTutorImage.addClass("has-error");
						$('#tutor-image').focus();
						return false;
					} else {
						divTutorImage.removeClass("has-error");
						divTutorImage.addClass("has-success");
					}

					//상태메세지가 비었을 경우
					if ($('#tutor-profile').val() == "") {
						modalContents.text("상태메세지를 입력해주세요");
						modal.modal('show');

						divTutorProfile.removeClass("has-success");
						divTutorProfile.addClass("has-error");
						$('#tutor-profile').focus();
						return false;
					} else {
						divTutorProfile.removeClass("has-error");
						divTutorProfile.addClass("has-success");
					}

					//오픈채팅 링크가 비었을 경우
					if ($('#tutor-kakao').val() == "") {
						modalContents.text("오픈채팅 주소를 입력해주세요");
						modal.modal('show');

						divTutorKakao.removeClass("has-success");
						divTutorKakao.addClass("has-error");
						$('#tutor-kakao').focus();
						return false;
					} else {
						divTutorKakao.removeClass("has-error");
						divTutorKakao.addClass("has-success");
					}
					//경력 소개가 비었을 경우
					if ($('#tutor-career').val() == "") {
						modalContents.text("경력 소개란을 입력해주세요");
						modal.modal('show');

						divTutorCareer.removeClass("has-success");
						divTutorCareer.addClass("has-error");
						$('#tutor-career').focus();
						return false;
					} else {
						divTutorCareer.removeClass("has-error");
						divTutorCareer.addClass("has-success");
					}

				});

	});

	$(document).ready(function() {
		$("input:checkbox").on('click', function() {
			if ($(this).prop('checked')) {
				$('#chk-content').show();
			} else {
				$('#chk-content').hide();
			}
		});
	});
</script>
<title>Document</title>
</head>
<body id="body">
	<nav class="navbar navbar-expand-lg" style="background-color: white"
		id="main-nav" style="padding-top: 250px;">
		<div class="container">
			<div class="navbar-left">
				<h2 class="logotitlee"
					style="text-align: center; font-family: 'Black Han Sans', sans-serif;">
					<a class="navbar-brand " href="main.do">HARU </a>
				</h2>
			</div>
			<div class="navbar-right">
				<ul class="nav navbar-nav">
					<li class="nav-item topLi" style="margin: 20PX;"><a href="#"
						class="menulink" style="color: black;"></a>
						<ul class="submenu" id="topul">

						</ul></li>
					<li class="nav-item topLi" style="margin: 20PX;"><a
						href="board.jsp" class="menulink"
						style="color: black; background-color: white;">FAQ</a>
						<ul class="submenu" id="topul">

						</ul></li>
					<li class="nav-item topLi" style="margin: 20PX;"><a
						href="board.jsp" class="menulink"
						style="color: black; background-color: white;">마이페이지</a>
						<ul class="submenu" id="topul">

						</ul></li>
					<li class="nav-item topLi" style="margin: 13PX;"><a
						class="menulink"
						href="<%=(member == null) ? "sign.do" : "sign.do?command=signout"%>"
						style="background-color: white;">
							<button type="button" class="btn btn-outline">
								&nbsp; &nbsp;<%=(member == null) ? "로그인" : "로그아웃"%>&nbsp;&nbsp;&nbsp;
							</button>
					</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark"
		style="display: none; height: 50px; padding: 20px; border: 1px solid lightgray">
		<!-- id="ftco-navbar" -->
	</nav>
	<hr>


	<div class=container>
		<form class="form-horizontal" role="form" method="post"
			action="sign.do" enctype="multipart/form-data">
			<input type="hidden" name="command" value="signup">
			<div class="form-group">
				<label for="provision" class="col-lg-2 control-label">회원가입약관</label>
				<div class="col-lg-12" id="provision">
					<textarea class="form-control" rows="8" style="resize: none"
						readonly="readonly">
제 1장 총칙

제 1 조(목적)

본 약관은 국가공간정보포털 웹사이트(이하 "국가공간정보포털")가 제공하는 모든 서비스(이하 "서비스")의 이용조건 및 절차, 회원과 국가공간정보포털의 권리, 의무, 책임사항과 기타 필요한 사항을 규정함을 목적으로 합니다.

제 2 조(약관의 효력과 변경)

1. 국가공간정보포털은 이용자가 본 약관 내용에 동의하는 경우, 국가공간정보포털의 서비스 제공 행위 및 회원의 서비스 사용 행위에 본 약관이 우선적으로 적용됩니다.
2. 국가공간정보포털은 약관을 개정할 경우, 적용일자 및 개정사유를 명시하여 현행약관과 함께 국가공간정보포털의 초기화면에 그 적용일 7일 이전부터 적용 전일까지 공지합니다. 단, 회원에 불리하게 약관내용을 변경하는 경우에는 최소한 30일 이상의 사전 유예기간을 두고 공지합니다. 이 경우 국가공간정보포털은 개정 전 내용과 개정 후 내용을 명확하게 비교하여 회원이 알기 쉽도록 표시합니다.
3. 변경된 약관은 국가공간정보포털 홈페이지에 공지하거나 e-mail을 통해 회원에게 공지하며, 약관의 부칙에 명시된 날부터 그 효력이 발생됩니다. 회원이 변경된 약관에 동의하지 않는 경우, 회원은 본인의 회원등록을 취소(회원탈퇴)할 수 있으며, 변경된 약관의 효력 발생일로부터 7일 이내에 거부의사를 표시하지 아니하고 서비스를 계속 사용할 경우는 약관 변경에 대한 동의로 간주됩니다.

제 3 조(약관 외 준칙)

본 약관에 명시되지 않은 사항은 전기통신기본법, 전기통신사업법, 정보통신윤리위원회심의규정, 정보통신 윤리강령, 프로그램보호법 및 기타 관련 법령의 규정에 의합니다.

제 4 조(용어의 정의)

본 약관에서 사용하는 용어의 정의는 다음과 같습니다.
1. 이용자 : 본 약관에 따라 국가공간정보포털이 제공하는 서비스를 받는 자
2. 가입 : 국가공간정보포털이 제공하는 신청서 양식에 해당 정보를 기입하고, 본 약관에 동의하여 서비스 이용계약을 완료시키는 행위
3. 회원 : 국가공간정보포털에 개인 정보를 제공하여 회원 등록을 한 자로서 국가공간정보포털이 제공하는 서비스를 이용할 수 있는 자.
4. 계정(ID) : 회원의 식별과 회원의 서비스 이용을 위하여 회원이 선정하고 국가공간정보포털에서 부여하는 문자와 숫자의 조합
5. 비밀번호 : 회원과 계정이 일치하는지를 확인하고 통신상의 자신의 비밀보호를 위하여 회원 자신이 선정한 문자와 숫자의 조합
6. 탈퇴 : 회원이 이용계약을 종료시키는 행위
7. 본 약관에서 정의하지 않은 용어는 개별서비스에 대한 별도 약관 및 이용규정에서 정의합니다.


제 2장 서비스 제공 및 이용

제 5 조 (이용계약의 성립)

1. 이용계약은 이용자가 온라인으로 국가공간정보포털에서 제공하는 소정의 가입신청 양식에서 요구하는 사항을 기록하여 가입을 완료하는 것으로 성립됩니다.
2. 국가공간정보포털은 다음 각 호에 해당하는 이용계약에 대하여는 가입을 취소할 수 있습니다.
   1) 다른 사람의 명의를 사용하여 신청하였을 때
   2) 이용계약 신청서의 내용을 허위로 기재하였거나 신청하였을 때
   3) 다른 사람의 국가공간정보포털 서비스 이용을 방해하거나 그 정보를 도용하는 등의 행위를 하였을 때
   4) 국가공간정보포털을 이용하여 법령과 본 약관이 금지하는 행위를 하는 경우
   5) 기타 국가공간정보포털이 정한 이용신청요건이 미비 되었을 때

제 6 조 (회원정보 사용에 대한 동의)

1. 회원의 개인정보는 「공공기관의개인정보보호에관한법률」에 의해 보호됩니다.
2. 국가공간정보포털의 회원 정보는 다음과 같이 사용, 관리, 보호됩니다.
   1) 개인정보의 사용 : 국가공간정보포털는 서비스 제공과 관련해서 수집된 회원의 신상정보를 본인의 승낙 없이 제3자에게 누설, 배포하지 않습 니다. 단, 전기통신기본법 등 법률의 규정에 의해 국가기관의 요구가 있는 경우, 범죄에 대한 수사상의 목적이 있거나 정보통신윤리위원회의 요청이 있는 경우 또는 기타 관계법령에서 정한 절차에 따른 요청이 있는 경우, 이용자 스스로 개인정보를 공개한 경우에는 그러 하지 않습니다.
   2) 개인정보의 관리 : 회원은 개인정보의 보호 및 관리를 위하여 서비스의 개인정보관리에서 수시로 회원의 개인정보를 수정/삭제할 수 있습니다.
   3) 개인정보의 보호 : 회원의 개인정보는 오직 본인만이 열람/수정/삭제 할 수 있으며, 이는 전적으로 회원의 계정과 비밀번호에 의해 관리되고 있습니다. 따라서 타인에게 본인의 계정과 비밀번호를 알려주어서는 안되며, 작업 종료시에는 반드시 로그아웃해 주시기 바랍니다.
3. 회원이 본 약관에 따라 이용신청을 하는 것은, 국가공간정보포털의 신청서에 기재된 회원정보를 수집, 이용하는 것에 동의하는 것으로 간주 됩니다.

제 7 조 (사용자의 정보 보안)

1. 이용자는 국가공간정보포털 서비스 가입 절차를 완료하는 순간부터 본인이 입력한 정보의 비밀을 유지할 책임이 있으며, 회원이 고의나 중대한 실수로 회원의 계정과 비밀번호를 사용하여 발생한 피해에 대한 책임은 회원 본인에게 있습니다.
2. 계정과 비밀번호에 관한 모든 관리의 책임은 회원에게 있으며, 회원의 계정이나 비밀번호가 부정하게 사용되었다는 사실을 발견한 경우에는 즉시 국가공간정보포털에 신고하여야 합니다. 신고를 하지 않음으로 인한 모든 책임은 회원 본인에게 있습니다.
3. 이용자는 국가공간정보포털 서비스의 사용 종료시마다 정확히 접속을 종료해야 하며, 정확히 종료하지 아니함으로써 제3자가 이용자에 관한 정보를 이용하게 되는 등의 결과로 인해 발생하는 손해 및 손실에 대하여 국가공간정보포털은 책임을 부담하지 아니합니다.

제 8 조 (서비스의 변경)

1. 당 사이트는 귀하가 서비스를 이용하여 기대하는 손익이나 서비스를 통하여 얻은 자료로 인한 손해에 관하여 책임을 지지 않으며, 회원이 본 서비스에 게재한 정보, 자료, 사실의 신뢰도, 정확성 등 내용에 관하여는 책임을 지지 않습니다.
2. 당 사이트는 서비스 이용과 관련하여 가입자에게 발생한 손해 중 가입자의 고의,과실에 의한 손해에 대하여 책임을 부담하지 아니합니다.

제 9 조 (이용기간 및 자격의 정지 및 상실)

1. 국가공간정보포털 회원이용기간은 조직통폐합에 따른 불가항력을 제외하고 회원신청에서 탈퇴까지로 합니다.
2. 국가공간정보포털은 이용자가 본 약관에 명시된 내용을 위배하는 행동을 한 경우, 이용자격을 일시적으로 정지하고 30일 이내에 시정하도록 이용자에게 요구할 수 있으며, 이후 동일한 행위를 2회 이상 반복할 경우에 30일간의 소명기회를 부여한 후 이용자격을 상실시킬 수 있습니다.
3. 국가공간정보포털 회원이 신청 후 12개월이상 장시간 이용하지 않은 회원은 휴면아이디로 분류하여, 자격 정지 및 상실이 가능합니다.

제 10 조 (계약해제, 해지 등)

1. 회원은 언제든지 서비스 초기화면의 마이페이지 또는 정보수정 메뉴 등을 통하여 이용계약 해지 신청을 할 수 있으며, 국가공간정보포털은 관련법 등이 정하는 바에 따라 이를 즉시 처리하여야 합니다.
2. 회원이 계약을 해지할 경우, 관련법 및 개인정보취급방침에 따라 국가공간정보포털이 회원정보를 보유하는 경우를 제외하고는 해지 즉시 회원의 모든 데이터는 소멸됩니다.
3. 회원이 계약을 해지하는 경우, 회원이 작성한 게시물 중 블로그 등과 같이 본인 계정에 등록된 게시물 일체는 삭제됩니다. 단, 타인에 의해 스크랩 되어 재게시되거나, 공용게시판에 등록된 게시물 등은 삭제되지 않으니 사전에 삭제 후 탈퇴하시기 바랍니다.

제 11 조 (게시물의 저작권)

1. 이용자가 게시한 게시물의 저작권은 이용자가 소유하며, 국가공간정보포털는 서비스 내에 이를 게시할 수 있는 권리를 갖습니다.
2. 국가공간정보포털은 다음 각 호에 해당하는 게시물이나 자료를 사전통지 없이 삭제하거나 이동 또는 등록 거부를 할 수 있습니다.
   1) 본서비스 약관에 위배되거나 상용 또는 불법, 음란, 저속하다고 판단되는 게시물을 게시한 경우
   2) 다른 회원 또는 제 3자에게 심한 모욕을 주거나 명예를 손상시키는 내용인 경우
   3) 공공질서 및 미풍양속에 위반되는 내용을 유포하거나 링크시키는 경우
   4) 불법복제 또는 해킹을 조장하는 내용인 경우
   5) 영리를 목적으로 하는 광고일 경우
   6) 범죄와 결부된다고 객관적으로 인정되는 내용일 경우
   7) 다른 이용자 또는 제 3자의 저작권 등 기타 권리를 침해하는 내용인 경우
   8) 국가공간정보포털에서 규정한 게시물 원칙에 어긋나거나, 게시판 성격에 부합하지 않는 경우
   9) 기타 관계법령에 위배된다고 판단되는 경우
3. 이용자의 게시물이 타인의 저작권을 침해함으로써 발생하는 민, 형사상의 책임은 전적으로 이용자가 부담하여야 합니다.


제 3장 의무 및 책임


제 12 조 (국가공간정보포털의 의무)

1. 국가공간정보포털은 회원의 개인 신상 정보를 본인의 승낙없이 타인에게 누설, 배포하지 않습니다. 단, 전기통신관련법령 등 관계법령에 의하여 관계 국가기관 등의 요구가 있는 경우에는 그러하지 아니합니다.
2. 국가공간정보포털은 법령과 본 약관이 금지하거나 미풍양속에 반하는 행위를 하지 않으며, 지속적·안정적으로 서비스를 제공하기 위해 노력 할 의무가 있습니다.
3. 국가공간정보포털은 이용자의 귀책사유로 인한 서비스 이용 장애에 대하여 책임을 지지 않습니다.

제 13 조 (회원의 의무)

1. 회원 가입시에 요구되는 정보는 정확하게 기입하여야 합니다. 또한 이미 제공된 회원에 대한 정보가 정확한 정보가 되도록 유지, 갱신하여야 하며, 회원은 자신의 계정 및 비밀번호를 제3자에게 이용하게 해서는 안됩니다.
2. 회원은 국가공간정보포털의 사전 승낙없이 서비스를 이용하여 어떠한 영리행위도 할 수 없으며, 그 영업활동의 결과에 대해 국가공간정보포털은 일절 책임을 지지 않습니다. 또한 회원은 이와 같은 영업활동으로 국가공간정보포털이 손해를 입은 경우 손해배상의무를 지며, 국가공간정보포털은 해당 회원에 대해 서비스 이용제한 및 적법한 절차를 거쳐 손해배상 등을 청구할 수 있습니다.
3. 회원은 국가공간정보포털 서비스 이용과 관련하여 다음 각 호의 행위를 하여서는 안됩니다.
   1) 회원가입 신청 또는 회원정보 변경 시 허위내용을 기재하거나 다른 회원의 비밀번호와 ID를 도용하여 부정 사용하는 행위
   2) 저속, 음란, 모욕적, 위협적이거나 타인의 Privacy를 침해할 수 있는 내용을 전송, 게시, 게재, 전자우편 또는 기타의 방법으로 전송하는 행위
   3) 국가공간정보포털 운영진, 직원 또는 관계자를 사칭하는 행위
   4) 서비스를 통하여 전송된 내용의 출처를 위장하는 행위
   5) 법률, 계약에 의해 이용할 수 없는 내용을 게시, 게재, 전자우편 또는 기타의 방법으로 전송하는 행위
   6) 서버 해킹 및 컴퓨터바이러스 유포, 웹사이트 또는 게시된 정보의 일부분 또는 전체를 임의로 변경하는 행위
   7) 타인의 특허, 상표, 영업비밀, 저작권, 기타 지적재산권을 침해하는 내용을 게시, 게재, 전자우편 또는 기타의 방법으로 전송하는 행위
   8) 국가공간정보포털의 승인을 받지 아니한 광고, 판촉물, 스팸메일, 행운의 편지, 피라미드 조직 기타 다른 형태의 권유를 게시, 게재, 전자우편 또는 기타의 방법으로 전송하는 행위
   9) 다른 사용자의 개인정보를 수집, 저장, 공개하는 행위
   10) 범죄행위를 목적으로 하거나 기타 범죄행위와 관련된 행위
   11) 선량한 풍속, 기타 사회질서를 해하는 행위
   12) 타인의 명예를 훼손하거나 모욕하는 행위
   13) 타인의 지적재산권 등의 권리를 침해하는 행위
   14) 타인의 의사에 반하여 광고성 정보 등 일정한 내용을 지속적으로 전송하는 행위
   15) 서비스의 안정적인 운영에 지장을 주거나 줄 우려가 있는 일체의 행위
   17) 본 약관을 포함하여 기타 국가공간정보포털이 정한 제반 규정 또는 이용 조건을 위반하는 행위
   18) 기타 관계법령에 위배되는 행위


제 4장 기타


제 14 조 (양도금지)

회원이 서비스의 이용권한, 기타 이용계약 상 지위를 타인에게 양도, 증여할 수 없습니다.

제 15조 (면책조항)

1. 국가공간정보포털은 서비스 이용과 관련하여 이용자에게 발생한 손해에 대하여 국가공간정보포털의 중대한 과실, 고의 또는 범죄행위로 인해 발생한 손해를 제외하고 이에 대하여 책임을 부담하지 않으며, 이용자가 본 서비스에 게재한 정보, 자료, 사실의 신뢰도, 정확성 등 내용에 관하여는 책임을 지지 않습니다.
2. 국가공간정보포털은 서비스 이용과 관련하여 이용자에게 발생한 손해 중 이용자의 고의, 실수에 의한 손해에 대하여 책임을 부담하지 아니합니다.
3. 국가공간정보포털은 이용자간 또는 이용자와 제3자간에 서비스를 매개로 하여 물품거래 혹은 금전적 거래 등과 관련하여 어떠한 책임도 부담하지 아니하고, 이용자가 서비스의 이용과 관련하여 기대하는 이익에 관하여 책임을 부담하지 않습니다.

제 16 조 (재판관할)

국가공간정보포털과 이용자간에 발생한 서비스 이용에 관한 분쟁에 대하여는 대한민국 법을 적용하며, 본 분쟁으로 인한 소는 민사소송법상의 관할법원에 제기합니다.
부 칙 1. (시행일) 본 약관은 2016년 1월 1일부터 시행됩니다.
                </textarea>
					<div class="radio">
						<label> <input type="radio" id="provisionYn"
							name="provisionYn" value="Y" autofocus="autofocus" checked>
							동의합니다.
						</label>
					</div>
					<div class="radio">
						<label> <input type="radio" id="provisionYn"
							name="provisionYn" value="N"> 동의하지 않습니다.
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="memberInfo" class="col-lg-2 control-label">개인정보취급방침</label>
				<div class="col-lg-12" id="memberInfo">
					<textarea class="form-control" rows="8" style="resize: none"
						readonly="readonly">
가. 개인정보의 수집 및 이용 목적
① 국가공간정보포털은 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며, 이용 목적이 변경되는 경우에는 개인정보 보호법 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다.
1. 국가공간정보포털 서비스 제공을 위한 회원관리
1) 공간정보 다운로드, 오픈API 신청 및 활용 등 포털 서비스 제공과 서비스 부정이용 방지를 목적으로 개인정보를
   처리합니다.

나. 수집하는 개인정보의 항목
① 국가공간정보포털 회원정보(필수): 이름, 이메일(아이디), 비밀번호

다. 개인정보의 보유 및 이용기간
① 국가공간정보포털은 법령에 따른 개인정보 보유ㆍ이용기간 또는 정보주체로부터 개인정보를 수집 시에 동의 받은 개인정보 보유ㆍ이용기간 내에서 개인정보를 처리ㆍ보유합니다.
1. 국가공간정보포털 회원정보
- 수집근거: 정보주체의 동의
- 보존기간: 회원 탈퇴 요청 전까지(1년 경과 시 재동의)
- 보존근거: 정보주체의 동의

라. 동의 거부 권리 및 동의 거부에 따른 불이익
위 개인정보의 수집 및 이용에 대한 동의를 거부할 수 있으나, 동의를 거부할 경우 회원 가입이 제한됩니다.

                </textarea>
					<div class="radio">
						<label> <input type="radio" id="memberInfoYn"
							name="memberInfoYn" value="Y" checked> 동의합니다.
						</label>
					</div>
					<div class="radio">
						<label> <input type="radio" id="memberInfoYn"
							name="memberInfoYn" value="N"> 동의하지 않습니다.
						</label>
					</div>
				</div>
			</div>
			<%
				if (kakao == null) {
			%>
			<div class="form-group" id="divId">
				<label for="inputId" class="col-lg-2 control-label">아이디</label>
				<div class="col-lg-12">
					<input type="text" class="form-control onlyAlphabetAndNumber"
						name="id" id="id" data-rule-required="true"
						placeholder="30자이내의 알파벳, 언더스코어(_), 숫자만 입력 가능합니다." maxlength="30">
				</div>
				<div class="col-lg-8" id="chkid"></div>
			</div>
			<div class="form-group" id="divPassword">
				<label for="inputPassword" class="col-lg-2 control-label">패스워드</label>
				<div class="col-lg-12">
					<input type="password" class="form-control" name="pw" id="password"
						name="excludeHangul" data-rule-required="true" placeholder="패스워드"
						maxlength="30">
				</div>
			</div>
			<div class="form-group" id="divPasswordCheck">
				<label for="inputPasswordCheck" class="col-lg-2 control-label">패스워드
					확인</label>
				<div class="col-lg-12">
					<input type="password" class="form-control" id="passwordCheck"
						data-rule-required="true" placeholder="패스워드 확인" maxlength="30">
				</div>
			</div>
			<%
				} else {
			%>
			<input type="hidden" name="id" value="<%=kakao.getId()%>"> <input
				type="hidden" name="pw" value="<%=kakao.getpw()%>">
			<%
				}
			%>
			<div class="form-group" id="divName">
				<label for="inputName" class="col-lg-2 control-label">이름</label>
				<div class="col-lg-12">
					<input type="text" class="form-control onlyHangul" name="name"
						id="name" data-rule-required="true" placeholder="한글만 입력 가능합니다."
						maxlength="15">
				</div>
			</div>

			<div class="form-group" id="divNickname">
				<label for="inputNickname" class="col-lg-2 control-label">별명</label>
				<div class="col-lg-12">
					<input type="text" class="form-control" name="nickname"
						id="nickname" data-rule-required="true" placeholder="별명"
						maxlength="15">
				</div>
				<div class="col-lg-8" id="chknick"></div>
			</div>

			<div class="form-group" id="divBirth">
				<label for="inputBirth" class="col-lg-2 control-label">생년월일</label>
				<div class="col-lg-12">
					<input type="date" class="form-control" id="birth"
						data-rule-required="true" placeholder="생년월일" maxlength="15"
						name="birth">
				</div>
			</div>

			<div class="form-group" id="divEmail">
				<label for="inputEmail" class="col-lg-2 control-label">이메일</label>
				<div class="col-lg-12">
					<input type="email" class="form-control" name="email" id="email"
						data-rule-required="true" placeholder="이메일" maxlength="40">
				</div>
			</div>
			<div class="form-group" id="divPhoneNumber">
				<label for="inputPhoneNumber" class="col-lg-2 control-label">휴대폰
					번호</label>
				<div class="col-lg-12">
					<input type="tel" class="form-control onlyNumber"
						name="phoneNumber" id="phoneNumber" data-rule-required="true"
						placeholder="-를 제외하고 숫자만 입력하세요." maxlength="11">
				</div>

			</div>

			<div class="form-group">
				<label for="inputPhoneNumber" class="col-lg-2 control-label">성별</label>
				<div class="col-lg-12">
					<select class="form-control" name="gender" id="gender">
						<option value="M">남</option>
						<option value="F">여</option>
					</select>
				</div>
			</div>


			<div class="form-check">
				<label for="inputTutor" class="col-lg-2 control-label">강사로
					등록</label> <input type="hidden" id="YN" name="YN"> <input
					type="checkbox" id="chk" name="chk">
			</div>

			<div id="chk-content" style="display: none;">
				<div class="form-group" id="divTutorImage">
					<label for="tutor-image" class="col-lg-2 control-label">강사이미지</label>
					<div class="col-lg-12">
						<input type="file" class="form-control" id="tutor-image"
							name="tutor-image" data-rule-required="true">
					</div>
				</div>

				<div class="form-group" id="divTutorProfile">
					<label for="tutor-profile" class="col-lg-2 control-label">프로필
						상태메세지</label>
					<div class="col-lg-12">
						<input type="text" class="form-control " id="tutor-profile"
							name="tutor-profile" data-rule-required="true">
					</div>
				</div>

				<div class="form-group" id="divTutorKakao">
					<label for="tutor-kakao" class="col-lg-2 control-label">오픈채팅
						링크</label>
					<div class="col-lg-12">
						<input type="text" class="form-control " id="tutor-kakao"
							name="tutor-kakao" data-rule-required="true">
					</div>
				</div>

				<div class="form-group" id="divTutorCareer">
					<label for="tutor-career" class="col-lg-2 control-label">경력
						소개</label>
					<div class="col-lg-12">
						<textarea class="form-control " id="tutor-career"
							name="tutor-career" data-rule-required="true">
                    </textarea>
					</div>
				</div>

			</div>

			<div class="form-group" align="right">
				<div class="col-lg-offset-2 col-lg-12">
					<button type="submit" class="btn btn-primary">Sign in</button>
				</div>
			</div>
	</div>
	</form>

	<!-- 모달창 -->
	<div class="modal fade" id="defaultModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">알림</h4>
				</div>
				<div class="modal-body">
					<p class="modal-contents"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!--// 모달창 -->
	<hr />


	</div>
	<footer id="fh5co-footer" class="fh5co-bg" role="contentinfo"
		style="font-size: 14px;">
		<div class="overlay"></div>
		<div class="container">
			<div class="row row-pb-md">
				<div class="col-md-4 fh5co-widget">
					<div class="intro">
						<h3>HARU</h3>
						<ul style="padding: 10px 0px;">
							<li style="color: white; list-style: none;"><i
								class="fas fa-map-marked-alt"
								style="color: white; padding-right: 10px;"></i>서울 강남구 테헤란로14길 6
								남도빌딩</li>
							<li style="color: white; list-style: none;"><i
								class="fas fa-home"
								style="color: white; padding: 15px 10px 15px 0px;"></i>실제로 운영되지
								않는 사이트 입니다.</li>
							<li style="color: white; list-style: none;"><i
								class="far fa-comment-alt"
								style="color: white; padding: 15px 10px 15px 0px;"></i>때때로 손에서
								일을 놓고 휴식을 취해야 한다. 잠시 일에서 벗어나 거리를 두고 보면 자기 삶의 조화로운 균형이 어떻게 깨져 있는지
								분명히 보인다.</li>
						</ul>
					</div>
				</div>
				<div class="col-md-4">
					<div class="devel" style="text-align: center;">
						<h3>사이트 개발자</h3>
						<p style="color: white;">정승주 박초롱 이병호 주수현 김건영</p>
						<div style="height: 60px"></div>
						<i class="fas fa-laptop fa-2x" style="color: white;"></i> <i
							class="fas fa-mouse fa-2x" style="color: white;"></i>
					</div>
				</div>

				<div class="col-md-4" style="text-align: center;">
					<div>
						<h3>HARU가 참조한 사이트</h3>
					</div>
					<ul class="fh5co-footer-links">
						<button
							style="background-color: #9BDAF2; cursor: pointer; width: 250px; height: 50px; margin-bottom: 20px; border: 0;">
							<a href="https://taling.me/"
								style="color: white; text-decoration: none;">탈잉</a>
						</button>
						<button
							style="background-color: #9BDAF2; color: white; cursor: pointer; width: 250px; height: 50px; border: 0;">
							<a href="https://booking.naver.com/"
								style="color: white; text-decoration: none;">네이버 예약</a>
						</button>
					</ul>
				</div>



			</div>

			<div class="row copyright">
				<div class="col-md-12 text-center">
					<p>
					<h5 class="block" style="color: #f1f1f1; font-size: 14px;">&copy;
						2020 | All Rights Reserved.</h5>
					<h5 class="block" style="color: #f1f1f1; font-size: 14px;">HARU.com</h5>
					</p>
				</div>
			</div>

		</div>
	</footer>
</body>
</html>