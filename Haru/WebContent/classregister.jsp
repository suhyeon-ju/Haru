<%@page import="com.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<%
	MemberDto memberdto = (MemberDto) session.getAttribute("member");
if (memberdto == null) {
	response.sendRedirect("sign.do?redirect=classregister.jsp");
}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>강의등록페이지</title>

<link href="classregisterpage/css/plugins.css" rel="stylesheet">
    <link rel="stylesheet" href="resource/css/header_main.css">
    <link rel="stylesheet" href="resource/css/footer_main.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.13.0/css/all.css">
    <link rel="stylesheet" type="text/css" href="resource/css/style.css" >
    <link rel="stylesheet" type="text/css" href="resource/css/style_main.css" >
    <link href="https://fonts.googleapis.com/css?family=Black+Han+Sans&amp;display=swap" rel="stylesheet">
	

<!-- CKEditor-->

<script type="text/javascript" src="./ckeditor/ckeditor.js"></script>


<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.0.min.js
    " charset="euc-kr"></script>

<!-- 다중 이미지 업로드-->
<script type="text/javascript">
	//강의 카테고리 선택 스크립트
	$(function() {
		$('#cate').change(function() {
			var num = this.value;
			for (var i = 1; i < 7; i++) {
				$('#subcategory' + i).addClass('hiide');// 전체숨김
			}
			$('#subcategory' + num).removeClass('hiide'); //숨김용 클래스빼주는거(.hdiien{display:none;})
		});
		for (var i = 1; i < 7; i++) {
			$('#subcategory' + i).change(function() {
				/* 		alert(this.value); */
				$('#key2').val(this.value);
			});
		}

		//지역 카테고리 선택 스크립트
		$('#region').change(function() {
			var num = this.value;
			for (var i = 0; i < 8; i++) {
				$('#regionSubLayer' + i).addClass('hiide');// 전체숨김
			}
			$('#regionSubLayer' + num).removeClass('hiide'); //숨김용 클래스빼주는거(.hdiien{display:none;})

		});

		for (var i = 0; i < 8; i++) {
			$('#regionSubLayer' + i).change(function() {
				/* 	alert(this.value);  */
				$('#key3').val(this.value);

			});
		}
	});

	//날짜 선택 스크립트
	/* function makedate() {
		var cnt = 0;
		$('#datebutton').before('<div>' + $('#timetable').html() + '</div>');
	} */

	//파일 폼 만들기
	/* function makefileform() {

		$('#cntbanner').val()
		$('#banneraddbtn').before('<div>' + $('#bannerimg').html() + '</div>');
	} */
</script>

</head>
<style type="text/css">
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

.col-md-12 {
	margin-top: 100px;
}

.hiide {
	display: none;
}

.inlinebox {
	display: inline-block;
	margin-bottom: 15px;
	margin-right: 5px;
}
</style>
</head>
<body>
    <nav class="navbar navbar-expand-lg" style="background-color: white" id="main-nav" style="padding-top: 250px;">
        <div class="container">
            <div class= "navbar-left" style="margin-top:30px">
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
                    <a class= "menulink" href="<%=(memberdto==null)?"sign.do":"sign.do?command=signout" %>"style="background-color:white;" >
                    	<button type="button" class="btn btn-outline">&nbsp; &nbsp;<%=(memberdto==null)?"로그인":"로그아웃" %>&nbsp;&nbsp;&nbsp;</button>
                    </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
	<hr>
	<br>
	<br>
	<form action="./classinfo.do" method="post"
		enctype="multipart/form-data">
		<input type="hidden" name="command" value="register">
		<div class="container mb40">
			<h4>썸네일 등록</h4>
			<hr class="mb50">
			<div>
				<input type="file" name="sumnail"> <br> <br> <br>
			</div>

			<h4>배너 이미지 등록</h4>
			<hr class="mb50">
			<div>

				<input type="file" name="bannimg1"> <br> <br> <input
					type="file" name="bannimg2"> <br> <br> <input
					type="file" name="bannimg3"> <br> <br>
				<!-- <input type="button"
					value="이미지 추가" id="banneraddbtn" onclick="makefileform();"> -->
			</div>
			<br> <br>

			<h4>강의 정보 등록</h4>
			<hr class="mb50">
			<div class="row">
				<label for="example-text-input" class="col-2 col-form-label">강의제목</label>
				<label for="example-text-input" class="col-2 col-form-label"><input
					class="form-control" type="text" name="title"></label>
			</div>
			<br> <br>
			<hr class="mb50">
			<div class="row">
				<label for="example-text-input" class="col-2 col-form-label">카테고리</label>
				<div class="col-10">
					<select id="cate" name="key1" class="form-control">
						<option value="1">인기수업</option>
						<option value="2">디자인</option>
						<option value="3">음악</option>
						<option value="4">영상</option>
						<option value="5">외국어</option>
						<option value="6">라이프스타일</option>
					</select>
					<div style="width: 100%; height: 30px;"></div>

					<select class="form-control" id="subcategory1" name="key2">
						<option value="1,2,3,4,5,6,7,8,9,10,11,12,13">서브 카테고리</option>
						<option value="1">투잡</option>
						<option value="2">퍼스널컬러</option>
						<option value="3">엑셀</option>
						<option value="4">댄스</option>
						<option value="5">메이크업</option>
						<option value="6">포토샵</option>
						<option value="7">방송댄스</option>
						<option value="8">보컬</option>
						<option value="9">PT</option>
						<option value="10">WEB개발</option>
						<option value="11">영어회화</option>
						<option value="12">핸드메이드</option>
						<option value="13">마케팅</option>
					</select> <select class="form-control hiide" id="subcategory2" name="key2">
						<option value="1,2,3,4,5,6,7,8">서브 카테고리</option>
						<option value="1">포토샵</option>
						<option value="2">일러스트레이터</option>
						<option value="3">제품디자인</option>
						<option value="4">건축</option>
						<option value="5">UI/UX 디자인</option>
						<option value="6">편집디자인</option>
						<option value="7">라이트룸</option>
						<option value="8">애니메이션</option>
					</select> <select class="form-control hiide" id="subcategory3" name="key2">
						<option value="1,2,3,4,5,6,7,8">서브 카테고리</option>
						<option value="1">보컬</option>
						<option value="2">피아노</option>
						<option value="3">미디작곡</option>
						<option value="4">기타연주</option>
						<option value="5">드럼</option>
						<option value="6">국악</option>
						<option value="7">랩</option>
						<option value="8">디제잉</option>
					</select> <select class="form-control hiide" id="subcategory4" name="key2">
						<option value="1,2,3,4,5,6,7,8">서브 카테고리</option>
						<option value="1">프리미어</option>
						<option value="2">영상기획/촬영</option>
						<option value="3">영상(기타)</option>
						<option value="4">에프터이펙트</option>
						<option value="5">파이널컷</option>
						<option value="6">여행영상</option>
						<option value="7">시포디</option>
						<option value="8">VJ</option>
					</select> <select class="form-control hiide" id="subcategory5" name="key2">
						<option value="1,2,3,4,5,6,7,8">서브 카테고리</option>
						<option value="1">중국어</option>
						<option value="2">일본어</option>
						<option value="3">토익/토플</option>
						<option value="4">외국어(기타)</option>
						<option value="5">스페인어</option>
						<option value="6">프랑스어</option>
						<option value="7">비즈니스</option>
						<option value="8">독일어</option>
					</select> <select class="form-control hiide" id="subcategory6" name="key2">
						<option value="1,2,3,4,5,6,7,8">서브 카테고리</option>
						<option value="1">핸드메이드</option>
						<option value="2">사진</option>
						<option value="3">요리/베이킹</option>
						<option value="4">연기</option>
						<option value="5">커피/차</option>
						<option value="6">여행/투어</option>
						<option value="7">사주/타로</option>
						<option value="8">반려동물</option>
					</select> <input type="hidden" id="key2" name="key2">
				</div>
			</div>
			<br> <br>
			<hr class="mb50">
			<div class="row">
				<label class="col-2 col-form-label">가격</label> <label
					class="col-2 col-form-label"> <input class="form-control"
					type="number" name="price" placeholder="$"></label>
			</div>
			<hr class="mb50">
			<div class="row">
				<label class="col-2 col-form-label">날짜 시간 등록</label>
				<div class="col-10">
					<div id="timetable">
						<input class="form-control col-3 inlinebox" type="date"
							name="day1"> <input class="form-control col-2 inlinebox"
							type="time" name="starttime1"> <input
							class="form-control col-2 inlinebox" type="time" name="endtime1">
						<br> <input class="form-control col-3 inlinebox" type="date"
							name="day2"> <input class="form-control col-2 inlinebox"
							type="time" name="starttime2"> <input
							class="form-control col-2 inlinebox" type="time" name="endtime2">
						<br> <input class="form-control col-3 inlinebox" type="date"
							name="day3"> <input class="form-control col-2 inlinebox"
							type="time" name="starttime3"> <input
							class="form-control col-2 inlinebox" type="time" name="endtime3">
						<br> <input class="form-control col-3 inlinebox" type="date"
							name="day4"> <input class="form-control col-2 inlinebox"
							type="time" name="starttime4"> <input
							class="form-control col-2 inlinebox" type="time" name="endtime4">
					</div>
				</div>
			</div>
			<br> <br>
			<hr class="mb50">
			<div class="row">
				<label for="example-text-input" class="col-2 col-form-label">장소</label>
				<div class="col-10">
					<div>
                  <select id="region" class="form-control">
                     <option value="">지역</option>
                     <option value="0">서울</option>
                     <option value="1">경기</option>
                     <option value="2">인천</option>
                     <option value="3">부산</option>
                     <option value="4">경상,대구,울산</option>
                     <option value="5">대전,충청</option>
                     <option value="6">광주,전라,제주</option>
                     <option value="7">온라인</option>
                  </select>
                  <div style="width: 100%; height: 30px;"></div>
                  <select class="hiide form-control" id="regionSubLayer0"
                     name="key3" onchange="regionSubSelect(this.value)">
                     <option
                        value="1,4,14,2,9,5,11,19,6,7,21,22,10,8,17,15,18,3,16,25,12,24,77,75,23,13">서울 전체
                     </option>
                     <option value="1">강남</option>
                     <option value="4">신촌홍대</option>
                     <option value="14">건대</option>
                     <option value="2">사당</option>
                     <option value="9">잠실</option>
                     <option value="5">종로</option>
                     <option value="11">신림</option>
                     <option value="19">마포</option>
                     <option value="6">영등포</option>
                     <option value="7">성북</option>
                     <option value="21">목동</option>
                     <option value="22">구로</option>
                     <option value="10">왕십리</option>
                     <option value="8">혜화</option>
                     <option value="17">노원</option>
                     <option value="15">용산</option>
                     <option value="18">수유</option>
                     <option value="3">신사</option>
                     <option value="16">충무로</option>
                     <option value="25">미아</option>
                     <option value="12">동작</option>
                     <option value="24">은평</option>
                     <option value="77">강서</option>
                     <option value="75">마곡더랜드타워</option>
                     <option value="23">청량리</option>
                     <option value="13">회기</option>
                  </select> <select class="hiide form-control" id="regionSubLayer1"
                     name="key3" onchange="regionSubSelect(this.value)">
                     <option value="26,30,28,27,29,82">경기 전체</option>
                     <option value="26">분당</option>
                     <option value="30">수원</option>
                     <option value="28">일산</option>
                     <option value="27">서현</option>
                     <option value="29">의정부</option>
                     <option value="82">광명</option>
                  </select> <select class="hiide form-control" id="regionSubLayer2"
                     name="key3" onchange="regionSubSelect(this.value)">
                     <option value="33,32,31,34">인천 전체</option>
                     <option value="33">부평</option>
                     <option value="32">구월동</option>
                     <option value="31">송도</option>
                     <option value="34">계양</option>
                  </select> <select class="hiide form-control" id="regionSubLayer3"
                     name="key3" onchange="regionSubSelect(this.value)">
                     <option value="36,37,39,69,38,89,35,78">부산 전체</option>
                     <option value="36">부산서면</option>
                     <option value="37">부산부경대</option>
                     <option value="39">부산해운대</option>
                     <option value="69">부산동래</option>
                     <option value="38">부산남포</option>
                     <option value="89">수영구</option>
                     <option value="35">부산대</option>
                     <option value="78">광안리</option>
                  </select> <select class="hiide form-control" id="regionSubLayer4"
                     name="key3" onchange="regionSubSelect(this.value)">
                     <option value="48,47,71,90,46,50,45,42,87">경상,대구,울산
                        전체</option>
                     <option value="48">대구중앙</option>
                     <option value="47">대구동성로</option>
                     <option value="71">대구수성구</option>
                     <option value="90">대구경북대</option>
                     <option value="46">창원</option>
                     <option value="50">경산영남대</option>
                     <option value="45">구미</option>
                     <option value="42">울산대</option>
                     <option value="87">성서계대</option>
                  </select> <select class="hiide form-control" id="regionSubLayer5"
                     name="key3" onchange="regionSubSelect(this.value)">
                     <option value="55,74,73,56,52">대전,충청 전체</option>
                     <option value="55">대전역</option>
                     <option value="74">천안</option>
                     <option value="73">둔산</option>
                     <option value="56">청주</option>
                     <option value="52">유성구</option>
                  </select> <select class="hiide form-control" id="regionSubLayer6"
                     name="key3" onchange="regionSubSelect(this.value)">
                     <option value="60,62,63,66,67,68">광주,전라,제주 전체</option>
                     <option value="60">전남대</option>
                     <option value="62">전주</option>
                     <option value="63">제주</option>
                     <option value="66">동명</option>
                     <option value="67">첨단지구</option>
                     <option value="68">상무지구</option>
                  </select> <select class="hiide form-control" id="regionSubLayer7"
                     name="key3" onchange="regionSubSelect(this.value)">
                     <option value="64">온라인 전체</option>
                     <option value="64">온라인</option>
                  </select> <input type="hidden" name="key3" id="key3">
               </div>
					<div style="width: 100%; height: 30px;"></div>
					<input class="form-control" type="text" name="loc"
						placeholder="주소입력 : ">
				</div>
			</div>
			<br> <br>
			<hr class="mb50">
			<div class="row">
				<label for="example-text-input" class="col-2 col-form-label">총
					수강인원 수</label>
				<div class="col-10">
					<input class="form-control" type="number" name="allstudent">
				</div>
			</div>
			<br> <br>
			<hr class="mb50">
			<div class="row">
				<label for="example-text-input" class="col-2 col-form-label">클래스
					타입</label>
				<div class="col-10">
					<select id="classtype" name="classtype" class="form-control">
						<option value="1N1">ONE&ONE</option>
						<option value="group">GROUP</option>
						<option value="oneday">ONEDAY</option>
					</select>
				</div>
			</div>
			<br> <br>
			<hr class="mb50">
			<div class="row">
				<label for="example-text-input" class="col-2 col-form-label">연관
					태그</label>
				<div class="col-10">
					<input class="form-control col-3" type="text" name="keyword"
						placeholder="#">
				</div>
			</div>
			<br> <br>
			<hr class="mb50">
			<h4>강의 내용 등록</h4>
			<hr>

			<textarea rows="" cols="" id="ckeditor" name="content"></textarea>
			<script type="text/javascript">
				CKEDITOR
						.replace(
								'ckeditor',
								{
									filebrowserUploadUrl : '${pageContext.request.contextPath}/ckeditorImg.do'
								});
				CKEDITOR.instances.ckeditor.getData();
			</script>

			<div class="row">
				<div class="col-md-12 ">
					<div class="row">
						<input type="submit" class="btn btn-secondary mr-auto ml-auto"
							value="등록" />
					</div>
					
				</div>
			</div>
			<div style="margin-top : 30px">
			</div>
		</div>
	</form>

</body>

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
</html>