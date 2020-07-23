//package com.controller.sign;
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.dao.member.MemberDao;
//import com.dto.member.MemberDto;
//import com.oreilly.servlet.MultipartRequest;
//
//import common.utilc;
//
//@WebServlet("/ex")
//public class Sign2 extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//    public Sign2() {}
//    
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
//	{doPost(request,response);} //Post에서 한번에 처리
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		// 준비 및 command 받기
//		utilc uc = new utilc();
//		uc.encoding(request, response);
//		MultipartRequest multi = uc.getMultipart(request);
//		String command = uc.getCmd(request,multi);
//
//		if(multi==null) {
//			// 일반 POST, GET 요청 처리
//			if(command==null) {
//				uc.dispatch("login.jsp", request, response); return;
//			}else if(uc.chk(command, "signup")) {
//				uc.dispatch("signup.jsp", request, response); return;
//			}
//		}else {
//			// Mutl 요청 처리
//			if(command==null) {
//				uc.jsResponse("잘못된 요청입니다", "main.do", response);
//			}else if(uc.chk(command, "signup")){
//				// 회원가입 페이지에서 요청이 오면 처리하는 부분
//				System.out.println(command);
//				String id = uc.get(multi, "id");
//				String pw = uc.get(multi, "pw");
//				String pwd = uc.get(multi, "pwd");
//				String name = uc.get(multi, "name");
//				String nickname = uc.get(multi, "nickname");
//				String birth = uc.get(multi, "birth");
//				String email = uc.get(multi, "email");
//				String phone = uc.get(multi, "phone");
//				String gender = uc.get(multi, "gender");
//				String grant = uc.get(multi, "grant");
//				String pImg = uc.imgfile(request,multi,"profileImg");
//				String pMsg = uc.get(multi, "profileMsg");
//				String pChat = uc.get(multi, "profilechat");
//				String pCarrer = uc.get(multi, "profilecarrer");
//				//필수 필요한 값 중에 누락된 값이 있는지 확인
//				if(uc.nulchk(id,pw,name,nickname,birth,email,phone,gender)) {
//					uc.jsResponse("다시 입력해 주세요 : 값 누락", "sign.do?command=signup", response);
//					return;
//				}else if(uc.chk(grant,"teacher") && uc.nulchk(pImg,pChat)) {
//					uc.jsResponse("다시 입력해 주세요 : 강사 필수 정보 누락", "sign.do?command=signup", response);
//					return;
//				}else if(uc.nulchk(pMsg,pCarrer)) {
//					pMsg = uc.nulChange(pMsg, "상태메세지가 없습니다");
//					pCarrer = uc.nulChange(pCarrer, "경력 사항이 없습니다.");
//				}
//				if(uc.chk(grant, "teacher")) {grant="Y";}else {grant="N";}
//				MemberDto member = new MemberDto(id,pw,name,nickname,uc.toDate(birth),email,phone,gender,grant);
//				if(uc.chk(grant, "Y")) {
//					member.setpImg(pImg);
//					member.setpChat(pChat);
//					member.setpMsg(pMsg);
//					member.setpCarrer(pCarrer);
//				}
//				if(new MemberDao().insert(member)>0) {
//					
//				}
//				
//				
//			}
//		}
//
//	}
//}
