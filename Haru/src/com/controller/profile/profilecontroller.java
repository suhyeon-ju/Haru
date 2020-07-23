package com.controller.profile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.classinfo.dto.ClassInfoDto;
import com.member.dao.MemberDao;
import com.member.dto.MemberDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * /profile.do는 form의 action과 같아아한다.
 */
@WebServlet("/profile.do")
public class profilecontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public profilecontroller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(-1);
		
		int sizeLimit = 10 * 1024 * 1024; // 10메가
		String savePath = request.getRealPath("/upload"); // 파일이 업로드될 톰캣 폴더의 webcontent기준
		try {	
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());;
		
		String command = multi.getParameter("command");
		System.out.println("["+command+"]");
		
		MemberDao dao = new MemberDao();

		if(command.equals("student_profile")){
			MemberDto memberDto = (MemberDto)session.getAttribute("member");
			
			if(memberDto == null) {
				jsResponse("로그인이 필요합니다.", "Haru/login.jsp", response);
			}else {				
				String nickname = multi.getParameter("nickname");
				String birth = multi.getParameter("birth");
				String profileImg = "./upload/" + multi.getFilesystemName("profileImg");
				if(profileImg==null || profileImg.equals("./upload/null")) {
					profileImg = multi.getParameter("basicphoto");
					System.out.println(profileImg);
				}
				
				String nickchk = multi.getParameter("nickchk");
				int res = 0;
				if(nickchk.equals("1")) {
					jsResponse("닉네임이 중복되었습니다", "student_profile.jsp", response);
					return;
				}
				
				String pw = multi.getParameter("pw");
				String pwchk = multi.getParameter("pwchk");

			
				if(pw.equals(pwchk)) {
					System.out.println(profileImg);
					System.out.println(nickname);
					System.out.println("birth="+birth);
					System.out.println("pw="+pw);
					
					memberDto.setNickname(nickname);
					memberDto.setBirth(birth);
					memberDto.setProfileImg(profileImg);
					memberDto.setPwd(pw);

					if(pw != null && !pw.equals("")) {
						res = dao.st_pw_update(memberDto);
					}else {
						res = dao.st_update(memberDto);
					}
					
				}
				
				System.out.println(res);
				if(res>0) {
	            	 jsResponse("수정되었습니다.", "student_profile.jsp", response);
	            }else {
	            	 jsResponse("수정에 실패했습니다", "student_profile.jsp", response);
	            }
			}
		}

		if (command.equals("teacher_profile")) {
			System.out.println("티처!!!!!!!!!!!!!!!!!");
			

			MemberDto memberDto = (MemberDto) session.getAttribute("member");
			if (memberDto == null) {
				jsResponse("로그인이 필요합니다.", "Haru/login.jsp", response);
			} else {
				String profileImg = "./upload/" + multi.getFilesystemName("profileImg");
				System.out.println(profileImg);
				if(profileImg==null || profileImg.equals("./upload/null")) {
					profileImg = multi.getParameter("basicphoto");
					System.out.println(profileImg);
				}
				System.out.println(profileImg);
				String nickname = multi.getParameter("nickname");
				String birth = multi.getParameter("birth");
				String email = multi.getParameter("email");
				String openchat = multi.getParameter("openchat");
				String phone = multi.getParameter("phone");
				String pmessage = multi.getParameter("pmessage");
				String career = multi.getParameter("career");
				String pw = multi.getParameter("pw");
				String pwchk = multi.getParameter("pwchk");
				
				String nickchk = multi.getParameter("nickchk");
				System.out.println("nickchk"+nickchk);
				System.out.println(profileImg);
				int res = 0;
				if(nickchk.equals("1")) {
					jsResponse("닉네임이 중복되었습니다", "teacher_profile.jsp", response);
					return;
				}
				
				
				if (pw.equals(pwchk)) {
					System.out.println(profileImg);
					System.out.println(nickname);
					System.out.println("birth=" + birth);
					System.out.println(email);
					System.out.println(openchat);
					System.out.println(phone);
					System.out.println(pmessage);
					System.out.println(career);
					System.out.println(pw);

					memberDto.setProfileImg(profileImg);
					memberDto.setNickname(nickname);
					memberDto.setBirth(birth);
					memberDto.setEmail(email);
					memberDto.setOpenchat(openchat);
					memberDto.setPhone(phone);
					memberDto.setPmessage(pmessage);
					memberDto.setCareer(career);
					memberDto.setPwd(pw);

					if (pw != null && !pw.equals("")) {
						res = dao.te_pw_update(memberDto);
					} else {
						res = dao.te_update(memberDto);
					}
				}
//					dispatch("teacher_profile.jsp",request,response);
				System.out.println(res);
				if (res > 0) {
					jsResponse("수정되었습니다.", "teacher_profile.jsp", response);
				} else {
					jsResponse("수정에 실패했습니다", "teacher_profile.jsp", response);
				}
			}
		}

	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	String command = request.getParameter("command");
	System.out.println(command);
	if(command==null) {command="";}
	if (command.equals("teacher_profile_main")) {
		System.out.println("티처 메인 프로필!!!!!!!!!!!!!!!!!");
		int res = 0; // classpk받을 변수
		MemberDto memberDto = (MemberDto) session.getAttribute("member");

		if (memberDto == null) {
			jsResponse("로그인이 필요합니다.", "sign.do?redirect=profile.do?command=teacher_profile_main", response);
			return;
		} else {
			System.out.println("컨트롤러 확인");
			String id = memberDto.getId();
			profileDao Dao = new profileDao();

			countVo vo = new countVo();

			vo.setTotal_allstudent(Dao.countAllStudent(id));// 최대 수용 인원
			vo.setTotal_class_num(Dao.selectAll(id).size());// 총 강좌수
			vo.setTotal_pay(Dao.total_pay(id)); // 이상없음
			vo.setTotal_student(Dao.countStudent(id));// 총 수강생 수

			List<ClassInfoDto> list = Dao.selectAll(id);
			List<ClassInfoDto> list1 = Dao.Classlistinfo(id);
			List<chartDto> monthlylist = Dao.eachmonthprofit(id);
			chartDto fixmonth = Dao.makemonthdata();
			String eachchart = Dao.eachchart(id);
			
			

			int Moncnt = 1; 
			String arr_month="";
			for(int a : new profileDao().monthprofit(id)) {
				arr_month +=(Moncnt!=1?",":"")+"['";
				arr_month+=(Moncnt%2!=0?Moncnt+"월":"")+"',"+a+"]";
				Moncnt++;
			}
			
			
			
			System.out.println(fixmonth);
			request.setAttribute("fixmonth", fixmonth );
			request.setAttribute("list", list);
			request.setAttribute("list1", list1);
			request.setAttribute("vo", vo);
			request.setAttribute("arr_month", arr_month);
			request.setAttribute("monthlylist", monthlylist);
			request.setAttribute("eachchart", eachchart);
			
			dispatch("teacher_profile_main.jsp", request, response);

			return;
		}
	}else if(command.equals("mypage")){
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		if (memberDto == null) {
			jsResponse("로그인이 필요합니다.", "sign.do?redirect=profile.do?command=mypage", response);
			return;
		}
		String isclass = memberDto.getIsclass();
		if(isclass==null) {isclass="";}
		if(isclass.equals("N")) {
			response.sendRedirect("student_profile.jsp");
			return;
		}else {
			response.sendRedirect("profile.do?command=teacher_profile_main");
			return;
		}
	}else if(command.equals("mypageprofile")) {
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		if(memberDto == null) {
			jsResponse("로그인이 필요합니다.", "sign.do?redirect=profile.do?command=mypageprofile", response);
			return;
		}
		String isclass = memberDto.getIsclass();
		if(isclass==null) {isclass="";}
		if(isclass.equals("N")) {
			response.sendRedirect("student_profile.jsp");
		}else {
			response.sendRedirect("teacher_profile.jsp");
		}
	}
	}

	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request ,response 를 원하는 곳으로 보내는
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		//
		String s = "<script type='text/javascript'>alert('" + msg + "');" + "location.href='" + url + "';</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
	}
}
