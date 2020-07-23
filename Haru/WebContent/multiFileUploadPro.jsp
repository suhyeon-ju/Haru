<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"
	import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"
	import="java.util.*" import="java.net.*" import="java.io.*"%>
<%
	String realPath = "";
String savePath = "fileUpload";
String type = "euc-kr";
int maxSize = 5 * 1024 * 1024;

ServletContext context = getServletContext();
realPath = context.getRealPath(savePath);
out.print("the realpath is : " + realPath + "<br />");
ArrayList saveFiles = new ArrayList();
ArrayList origFiles = new ArrayList();

try {
	MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, type, new DefaultFileRenamePolicy());
	Enumeration files = multi.getFileNames();
	while (files.hasMoreElements()) {
		String name = (String) files.nextElement();
		saveFiles.add(multi.getFilesystemName(name));
		origFiles.add(multi.getOriginalFileName(name));
	}
%>
<html>
<head>
<title>다중 파일 업로드 처리</title>
</head>
<body>
	<%
		for (int i = 0; i < saveFiles.size(); i++) {
		String y = (String) saveFiles.get(i);
		String x = request.getContextPath() + "/" + savePath + "/" + URLEncoder.encode(y, "UTF-8");
	%>
	<%=i%>.
	<a href="<%=x%>"><strong><%=origFiles.get(i)%></strong></a>
	<%
		}
	%>
</body>
</html>
<%
	} catch (IOException e) {
	System.out.print(e);
} catch (Exception e) {
	System.out.print(e);
}
%>

