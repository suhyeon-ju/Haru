<%@page import="com.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	MemberDto dto = (MemberDto) session.getAttribute("member");
	if(dto==null){response.sendRedirect("./login.jsp");return;}
	// 로그인이 꼭 필요한 페이지에서는 위 2줄을 추가해 회원정보가 없으면 로그인 페이지로 이동
	String g = dto.getIsclass();
%>
이름 : <%=dto.getName() %><br>
아이디 : <%=dto.getId() %><br>
등급 : <%=(g.equals("Y"))?"강사":(g.equals("N"))?"수강생":"관리자" %>
<a href="./sign.do?command=signout">로그아웃</a>
</body>
</html>