package com.controller.sign;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.classmember.dao.ClassMemberDao;
import com.kakaoOauth.dto.KKOauthDto;
import com.member.dao.MemberDao;
import com.member.dto.MemberDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.pay.biz.PayBiz;
import com.sms.dto.SmsDto;
import com.sms.send.SendSMS;

/**
 * Servlet implementation class Signcontroller
 */
@WebServlet("/sign.do")
public class Signcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Signcontroller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(-1);
		String command = request.getParameter("command");
		System.out.println("command=["+command+"]");
		MemberDao dao = new MemberDao();
		MultipartRequest multi = null;
		int sizeLimit = 10 * 1024 * 1024; // 10메가
		String savePath = request.getRealPath("/upload"); // 파일이 업로드될 톰캣 폴더의 webcontent기준
		try {
	         multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
	         
	         if(chk(multi.getParameter("command"), "signup")){
	        	 String id = multi.getParameter("id");
	             String pw = multi.getParameter("pw");
	             String name = multi.getParameter("name");
	             String nickname = multi.getParameter("nickname");
	             String birth = multi.getParameter("birth");
	             String gender = multi.getParameter("gender");
	             String email = multi.getParameter("email");
	             String phone = multi.getParameter("phoneNumber");
	             String chk = multi.getParameter("YN");
	             String tutorimage = "./upload/" + multi.getFilesystemName("tutor-image");
	             String tutorprofile = multi.getParameter("tutor-profile");
	             String tutorkakao = multi.getParameter("tutor-kakao");
	             String tutorcareer = multi.getParameter("tutor-career");

	             MemberDto dto = new MemberDto();
	             dto.setId(id);
	             dto.setPwd(pw);
	             dto.setName(name);
	             dto.setNickname(nickname);
	             String[] splitbirth = birth.split("-");
	             int intbirth[] = {
	                Integer.parseInt(splitbirth[0]),
	                Integer.parseInt(splitbirth[1]),
	                Integer.parseInt(splitbirth[2])
	             };
	             dto.setBirth(intbirth[0], intbirth[1],intbirth[2]);
	             dto.setGender(gender);
	             dto.setEmail(email);
	             dto.setPhone(phone);
	             dto.setIsclass(chk);
	             if(chk.equals("Y")) {
	                dto.setProfileImg(tutorimage);
	                dto.setPmessage(tutorprofile);
	                dto.setOpenchat(tutorkakao);
	                dto.setCareer(tutorcareer);
	             }
	             int success = dao.insertMember(dto);
	             if(success>0) {
	            	 jsResponse("가입을 환영합니다", "main.do", response);
	            	 return;
	             }else {
	            	 jsResponse("가입에 실패했습니다", "sign.do", response);
	            	 return;
	             }
	         }
	      }catch (Exception e) {
	         
	      }
		
		if(command==null) {
			MemberDto dto = (MemberDto) session.getAttribute("member");
			String redirect = request.getParameter("redirect");
			String classpk = request.getParameter("classpk");
			if (classpk == null) {classpk="";}
			if(dto!=null){
				jsResponse("이미 로그인 상태입니다.", "main.do", response);
				return;
				}
			request.setAttribute("classpk", classpk);
			request.setAttribute("redirect", redirect);
			dispatch("login.jsp", request, response);
			return;}
		if(chk(command,"signup")) { //가입
			jsResponse("잘못된 요청입니다", "main.do", response);
		}else if(chk(command,"signin")) { //로그인
			String id = request.getParameter("loginid");
			String pw = request.getParameter("loginpw");
			String redirect = request.getParameter("redirect");
			String classpk = request.getParameter("classpk");
			if(classpk!=null && !classpk.equals("")) {
				redirect+="&classpk="+classpk;
			}
			System.out.println("redirect : "+redirect);
			session.invalidate();
			session=request.getSession(true);
			if(nulchk(id,pw)) {
				jsResponse("잘못된 값입니다!", "sign.do", response);
				return;
			}else {
				MemberDto dto = dao.selectOne(id,pw);
				if(dto!=null) {
					session.setAttribute("member", dto);
					if(redirect==null) {redirect="main.do";}
					if(redirect.equals("")) {redirect="main.do";}
					jsResponse("로그인 성공!", redirect, response);
				}else {
					jsResponse("아이디,비밀번호를 확인해 주세요!", "sign.do", response);
				}
			}
		}else if(chk(command,"signout")) { //로그아웃
			MemberDto dto = (MemberDto) session.getAttribute("member");
			if (dto!=null) {
				session.removeAttribute("member");
				session.invalidate();
				jsResponse("로그아웃 되었습니다!", "main.do", response);
				return;
			}else {
				jsResponse("로그인 상태가 아닙니다", "main.do", response);
			}
		}else if(chk(command,"kakao")) { //카카오
			KKOauthDto kko =
			(KKOauthDto)session.getAttribute("kkoauth");
			session.invalidate();
			session=request.getSession(true);
			System.out.println(kko);
			if(kko==null){jsResponse("카카오로그인 실패!", "sign.do", response);return;}
			else {
				String id = kko.getId();
				String pw = kko.getpw();
				if(nulchk(id,pw)) {
					jsResponse("잘못된 값입니다!", "sign.do", response);
					return;
				}else {
					MemberDto dto = dao.selectOne(id,pw);
					if(dto!=null) {
						session.setAttribute("member", dto);
						jsResponse("로그인 성공!", "main.do", response);
					}else {
						request.setAttribute("kkoauth", kko);
						dispatch("sign.jsp", request, response);
//						jsResponse("회원가입을 진행해 주세요", "sign.jsp", response);
					}
				}
			}
			
		}else if(chk(command,"sms")) { //문자발송 (AJAX)
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			int code = -1;
			String phone = request.getParameter("phone");
			if(!nulchk(phone)) {
				code = new SendSMS(new SmsDto(phone)).sendSMS();
			}
			obj.put("code", code);
			out.println(obj.toJSONString());
			return;
		}else if(chk(command,"smschk")) { //문자확인 (AJAX)
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			int code = -1;
			String phone = request.getParameter("phone");
			String number = request.getParameter("number");
			if(!nulchk(phone,number)) {
				code = new SendSMS(new SmsDto(phone,number)).chkSMS();
			}
			obj.put("code", code);
			out.println(obj.toJSONString());
			return;
			
			}else if(chk(command,"idcheck")) { //아이디 중복 검사 (AJAX)
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			int code = -1;
			String id = request.getParameter("id");
			if(id==null) {
				obj.put("code",1);
				out.println(obj.toJSONString());
				return;
			}
			MemberDto dto = dao.selectOne(id.toLowerCase());
			if(dto==null){code=0;}else {code=1;}
			obj.put("code", code);
			out.println(obj.toJSONString());
			System.out.println("servlet에서 보내는 데이터 "+obj.toJSONString());
			
		}else if(chk(command,"nickcheck")) { //닉네임 중복 검사 (AJAX)
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			int code = -1;
			String nickname = request.getParameter("nickname");
			if(nickname==null) {
				obj.put("code", 1);
				out.println(obj.toJSONString());
				return;
			}
			MemberDto dto = dao.nicknameSelect(nickname);
			if(dto==null) {code=0;}else {code=1;}
			obj.put("code",code);
			out.println(obj.toJSONString());
			System.out.println("데이터 "+obj.toJSONString());
			return;
		}else if(chk(command,"memberout")) {
			String pwd = request.getParameter("loginpw");
			if(pwd==null) {
				jsResponse("잘못된 요청입니다.", "main.do", response);
				return;
			}
			MemberDto member = (MemberDto)session.getAttribute("member");
			if(member==null) {
				jsResponse("로그인이 필요합니다.", "main.do", response);
			}
			String id = member.getId();
			MemberDao memberdao = new MemberDao();
			if(id.indexOf("kko_")!=-1) {
				pwd = member.getPwd();
			}
			MemberDto memberchk = dao.selectOne(id, pwd);
			if(memberchk==null) {
				System.out.println("pwd : "+pwd);
				jsResponse("비밀번호를 다시 확인해주세요", "memberout.jsp", response);
				return;
			}else {
				if(id.equals(memberchk.getId())) {
					PayBiz paybiz = new PayBiz();
					paybiz.calcelMemberAllPay(id);
				}
			}
			ClassMemberDao classmemberdao = new ClassMemberDao();
			classmemberdao.delete(id);
			dao.delete(id,pwd);
			memberchk = dao.selectOne(id,pwd);
			System.out.println(memberchk+",id:"+id+",pw:"+pwd);
			if(memberchk==null) {
				session.invalidate();
				session=request.getSession(true);
				jsResponse("탈퇴되었습니다.", "main.do", response);
				return;
			}else {
				jsResponse("탈퇴실패", "memberout.jsp", response);
				return;
			}
		}else if(chk(command,"error")) {
			jsResponse("잘못된 요청입니다.", "main.do", response);
			return;
		}

		//해당하는 command 가 없을때
		else {
			jsResponse("잘못된 요청입니다.", "main.do", response);
			return;
		}
	}
	// ---
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