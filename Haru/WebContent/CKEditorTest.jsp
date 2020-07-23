<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	function divget(){
		var writediv = document.getElementById('t');
		writediv.innerHTML = CKEDITOR.instances.ckeditor.getData();
	}
</script>
</head>
<body>
	<textarea rows="" cols="" id="ckeditor"></textarea>
<script type="text/javascript">
CKEDITOR.replace('ckeditor',{filebrowserUploadUrl:'${pageContext.request.contextPath}/ckeditorImg.do'});
CKEDITOR.instances.ckeditor.getData();
</script>
<div id="t"></div>
<br><input type="button" value="버튼" onclick="divget();">
</body>
</html>