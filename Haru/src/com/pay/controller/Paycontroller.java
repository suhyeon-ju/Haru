package com.pay.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.classdate.dao.ClassDateDao;
import com.classdate.dto.ClassDateDto;
import com.classinfo.dao.ClassInfoDao;
import com.classinfo.dto.ClassInfoDto;
import com.member.dao.MemberDao;
import com.member.dto.MemberDto;
import com.pay.biz.PayBiz;
import com.pay.dao.PayDao;
import com.pay.dto.PayDto;

/**
 * Servlet implementation class Paycontroller
 */
@WebServlet("/pay.do")
public class Paycontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Paycontroller() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");// encoding
		
		String command = request.getParameter("command");
		System.out.println("["+command+"]");// 명령 확인
		
		HttpSession session = request.getSession();
		if(command==null) { //커멘드 null 단순히 ajax 실행
			MemberDto dto = (MemberDto) session.getAttribute("member");
			String strclasspk = request.getParameter("classpk");
			if(strclasspk==null) {strclasspk="0";}
			if(dto==null) {jsResponse("잘못된 요청입니다 : 로그인필요", "sign.do?redirect=pay.do?classpk="+strclasspk, response);return;}
			if(strclasspk==null) {jsResponse("잘못된 요청입니다 : 값 누락", "sign.do?redirect=pay.do?classpk="+strclasspk, response);return;}
			int classpk = Integer.parseInt(strclasspk);
			String id = dto.getId();
			ClassInfoDao infodao = new ClassInfoDao();
			ClassInfoDto infodto = infodao.selectOne(classpk);
			if(infodto==null) {jsResponse("클래스를 찾을 수 없습니다!", "main.do", response);return;}
			ClassDateDao datedao = new ClassDateDao();
			List<ClassDateDto> datedto = datedao.select_paypage(classpk);
			MemberDao memberdao = new MemberDao();
			MemberDto teacherinfo = memberdao.selectTeacher(infodto.getId());
			if(teacherinfo==null) {jsResponse("강사정보를 찾을 수 없습니다.", "main.do", response);}
			request.setAttribute("classinfo", infodto);
			request.setAttribute("classdate", datedto);
			request.setAttribute("classtea", teacherinfo);
			request.setAttribute("memberid", dto.getId());
			dispatch("payment.jsp", request, response);
		}else if(chk(command, "uid")) { //AJAX
			MemberDto dto = (MemberDto) session.getAttribute("member");
			String Str_classdatepk = request.getParameter("datepk");
			String pay_met = request.getParameter("method");
			System.out.println("member /"+dto.getId());
			System.out.println("datepk /"+Str_classdatepk);
			System.out.println("method /"+pay_met);
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			int code = -1;
			long uid = -1L;
			if(Str_classdatepk!=null && pay_met!=null) {
				int datepk = Integer.parseInt(Str_classdatepk);
				PayBiz pay = new PayBiz().newPay(dto,datepk,pay_met);
//				PayBiz pay = new PayBiz();
				System.out.println(pay);
				uid = pay.getPay_uid();
				if(uid!=-1L) {
					code=1;
				}
				obj.put("uid", uid);
			}
			obj.put("code", code);
			out.println(obj.toJSONString());
		}else if(chk(command,"chkuid")) { //AJAX
			String Strkey = request.getParameter("key");
			System.out.println("key /"+Strkey);
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			int code = -1;
			long uid = -1L;
			if(Strkey!=null) {
				uid = Long.parseLong(Strkey);
				PayBiz pay = new PayBiz(uid);
//				code = (pay.chkPay()?1:code);
				if(pay.chkPay()) {
					System.out.println("OK");
					code=1;
					PayDto dto = pay.selectOne(uid);
					pay.update(uid, true);
					obj.put("time",dto.printTime());
				}else {
					System.out.println("???");
				}
			}
			obj.put("code", code);
			out.println(obj.toJSONString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private boolean chk(String command,String text) {
		if(command==null) {return false;}
		if(text==null) {return false;}
		return command.equals(text);
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
				+ "location.href='"+url+"';</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
	}
}
