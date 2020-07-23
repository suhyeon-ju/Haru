package com.excel.download;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.json.simple.JSONObject;

import com.classinfo.dao.ClassInfoDao;
import com.classinfo.dto.ClassInfoDto;

@WebServlet("/excel.do")
// 엑셀 컨트롤러 (AJAX 요청)
public class excelcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public excelcontroller() {super();}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		int code = -1,classpk=0;
		String m = request.getParameter("m");
		String Str_classpk = request.getParameter("pk");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		String url = "";
		if(!nulchk(m,Str_classpk)){
			code-=1;
			try {classpk=Integer.parseInt(Str_classpk);code-=1;}
			catch(java.lang.NumberFormatException e) {}
			ClassInfoDao classinfodao = new ClassInfoDao();
			ClassInfoDto classinfo = classinfodao.selectOne(classpk);
			if(classinfo!=null) {
				code-=1;
				Excel_Class excel = new Excel_Class(request.getRealPath("/upload"),classpk);
			if(chk(m,"set")) {
				code-=2;
				if(excel.resetURL()>0) {
					code-=1;
					code-=excel.newCellThread();
				}
				if(code==-8) {
					code = 1;
				}
			}else if(chk(m,"get")) {
				code-=1;
				String cellurl = classinfodao.selectOne(classpk).getCellurl();
				System.out.println("cellurl : "+cellurl);
				if(!nulchk(cellurl)) {
					code=1;
					url=cellurl;
				}
			}
			}
		}
		obj.put("code",code);
		obj.put("url", url);
		out.println(obj.toJSONString());
		System.out.println("데이터 "+obj.toJSONString());
		return;
/* 에러코드 정의
 * 1 : 성공
 * -1: 값 누락 (NULL)
 * -2: classpk 가 int가 아님 (Exception)
 * -3: classpk에 해당하는 클래스가 없음 (NULL)
 * -4: m(커멘드)와 일치하는 조건이 없음
 * -5: 컬럼에 주소가 없음 (NULL)
 * -6: 커럼 초기화 쿼리 실패 (Exception)
 * -7: Thread 예외(Exception)
 */
	}
	private boolean chk(String command,String text) {
		if(nulchk(command,text)) {return false;}
		return command.equals(text);
	}
	private boolean nulchk(String...arr) {
		boolean res = false;
		for (int i=0;i<arr.length;i++) {
			if(arr[i]==null) {
				return !res;
			}else if(arr[i].isEmpty()) {
				return !res;
			}else if(arr[i].trim().equals("")) {
				return !res;
			}
		}
		return res;
	}
	private void dispatch
	(String url, HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//request ,response 를 원하는 곳으로 보내는
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		//
		String s = "<script type='text/javascript'>alert('"+msg+"');"
				+ "location.href='/Haru/"+url+"';</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
	}
}
