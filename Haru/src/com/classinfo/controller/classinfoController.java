package com.classinfo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.classImage.dao.ClassImgDao;
import com.classImage.dto.ClassImgDto;
import com.classdate.dao.ClassDateDao;
import com.classdate.dto.ClassDateDto;
import com.classinfo.dao.ClassInfoDao;
import com.classinfo.dto.ClassInfoDto;
import com.classreview.dao.ClassReviewDao;
import com.classreview.dto.ClassReviewDto;
import com.member.dao.MemberDao;
import com.member.dto.MemberDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.pay.biz.PayBiz;
import com.pay.dao.PayDao;
import com.util.UtilTemp;

import common.utilc;

@WebServlet("/classinfo.do")
public class classinfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public classinfoController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		utilc uc = new utilc().encoding(request, response).syo("classinfo 컨트롤러");
		MultipartRequest multi = uc.getMultipart(request);
		String command = uc.getCmd(request, multi); // command를 같이 받음
		
		MemberDto member = uc.getmember(request); //session에서 member 가져옴
		int classpk = 0;
		
		ClassInfoDao infodao = new ClassInfoDao();
		ClassDateDao datedao = new ClassDateDao();
		ClassImgDao imgdao = new ClassImgDao();
		ClassReviewDao reviewdao = new ClassReviewDao();
		
		if(multi==null) {
			//일반 POST, GET 요청 처리코드 시작
			if(command==null) { //command 없을때
				uc.jsResponse("잘못된 요청입니다", "main.do", response); return;
			}
			else if(uc.chk(command, "detailclass")) { // 클래스 조회
				classpk = uc.getClasspk(request);
				ClassInfoDto infodto = infodao.selectOne(classpk);
				List<ClassDateDto> datedto = datedao.finddate(classpk);
				List<ClassImgDto> banimg = imgdao.getList(classpk);
				List<ClassReviewDto> reviewdto = reviewdao.selectAll(classpk);
				request.setAttribute("classinfo", infodto);
				request.setAttribute("review", reviewdto);
				request.setAttribute("classbanner", banimg);
				request.setAttribute("classdate", datedto);
				dispatch("classinfo.jsp", request, response); return;
				//classinfo.jsp에서 classpk 에 해당하는 게시물 없을경우 main으로 이동함
			}
			else if(uc.chk(command, "insertreivew")) { // 리뷰 작성
				classpk = uc.getClasspk(request);
				if(member==null) {
					jsResponse("로그인이 필요합니다", "sign.do?redirect=classinfo.do?command=detailclass&classpk="+classpk, response);
					return;
				}
				int strp = 0;
				try { strp = Integer.parseInt(uc.get(request, "strp"));
				}catch(Exception e) {uc.syo("ERR : strp를 int로 실패");}
				String content = uc.get(request, "reviewcontent");
				ClassReviewDto dto = new ClassReviewDto();
				dto.setId(member.getId());
				dto.setContent(uc.nulChange(content, "."));
				dto.setStrp(strp);
				dto.setNickname(member.getNickname());
				dto.setClasspk(classpk);
				int res = reviewdao.insert(dto);
				if(res>0) {
					infodao.updaterank(classpk);
					response.sendRedirect("classinfo.do?command=detailclass&classpk="+classpk);
					return;
				}else {
					jsResponse("리뷰 내용을 입력해주세요", "classinfo.do?command=detailclass&classpk="+classpk, response);
				}
			}
			else if(uc.chk(command, "delete")) { //클래스 삭제
				classpk = uc.getClasspk(request);
				if(member==null) {
					jsResponse("로그인이 필요합니다", "sign.do?redirect=classinfo.do?command=detailclass&classpk="+classpk, response);
					return;
				}
				ClassInfoDto info = infodao.selectOne(classpk);
				if(uc.chk(info.getId(), member.getId())) { //클래스랑 요청보낸 맴버랑 같다면
				new PayBiz().calcelClassAllPay(classpk);
				int res = infodao.delete(classpk);
				if(res>0) {
					jsResponse("클래스 삭제 성공", "main.do", response);
					return;
				}else {
					jsResponse("클래스 삭제 실패", "classinfo.do?command=detailclass&classpk="+classpk, response);
					return;
				}
				
				}else { //클래스를 개설한 아이디랑 요청보낸 멤버랑 다르다면
					jsResponse("잘못된 요청입니다.", "classinfo.do?command=detailclass&classpk="+classpk, response);
					return;
				}
			}
			else if(uc.chk(command, "classmodify")) { //강의 수정 페이지 요청
				classpk = uc.getClasspk(request);
				ClassInfoDto infodto = infodao.selectOne(classpk);
				List<ClassDateDto> datedto = datedao.finddate(classpk);
				List<ClassImgDto> banimg = imgdao.getList(classpk);
				request.setAttribute("classinfo", infodto);
				request.setAttribute("classbanner", banimg);
				request.setAttribute("classdate", datedto);
				dispatch("classmodify.jsp", request, response);
				return;
			}
			//일반 POST, GET 요청 처리코드 마침
		}else {
			// Multi 요청 처리코드 시작
			if(command==null) { //command 없을때
				uc.jsResponse("잘못된 요청입니다", "main.do", response); return;
			}else if(uc.chk(command, "register")) { // 강의 등록
				if(member==null) {
					uc.jsResponse("로그인해주세요!", "sign.do?redirect=classregister.jsp", response); return;}
				
				String sumnail = uc.imgfile(request, multi, "sumnail"); //대표이미지
				
				String banimg[] = new String[3]; //배너 받을 갯수
				for (int i=0;i<banimg.length;i++) {
					banimg[i] = uc.imgfile(request, multi, "bannimg"+(i+1));
				}
				
				String days[][] = new String[4][3]; //날짜 받을 갯수
				for (int i=0;i<days.length;i++) {
					days[i][0] = uc.get(multi, "day"+(i+1)); //날짜
					days[i][1] = uc.get(multi, "starttime"+(i+1)); //시작시간
					days[i][2] = uc.get(multi, "endtime"+(i+1)); //종료시간
					System.out.println(days[0]+","+days[1]+","+days[2]);
				}
				
				if(uc.nulchk(sumnail,banimg[0],days[0][0])) { // Null Check
					uc.jsResponse("썸네일, 배너,날짜를 하나이상 등록해주세요", "classregister.jsp", response); return;
				}
				
				int key1 = uc.parseInt(uc.get(multi, "key1"));
				int key2 = uc.parseInt(uc.get(multi, "key2"));
				String key3 = uc.get(multi, "key3");
				String title = uc.nulChange(uc.get(multi, "title"),"제목");
				String content = uc.nulChange(uc.get(multi, "content"), "내용");
				int price = uc.parseInt(uc.nulChange(uc.get(multi, "price"), "1000"));
				int allstudent = uc.parseInt(uc.nulChange(uc.get(multi, "allstudent"), "30"));
				String loc = uc.nulChange(uc.get(multi, "loc"), "1");
				String classtype = uc.nulChange(uc.get(multi, "classtype"), "oneday");
				String keyword = uc.nulChange(uc.get(multi, "keyword"), "#태그");
				
				classpk = infodao.makeclasspk(); //classpk 가져옴
				uc.syo("classpk: "+classpk);
				ClassInfoDto infodto = new ClassInfoDto(); //classinfo DTO에 Set
				infodto.setId(member.getId());
				infodto.setTitle(title);
				infodto.setContent(content);
				infodto.setPrice(price);
				infodto.setAllstudent(allstudent);
				infodto.setLoc(key3+" "+loc);
				infodto.setClasstype(classtype);
				infodto.setKeyword(keyword);
				infodto.setPubImg("./"+uc.upload+"/"+sumnail);
				infodto.setClasspk(classpk);
				
				int res = infodao.InsertClass(infodto, key1,key2);
				
				ClassImgDto imgdto = new ClassImgDto(); //배너 insert
				imgdto.setClasspk(classpk);
				for(String tmp : banimg) {
					if(uc.nulchk(tmp)) {continue;}
					imgdto.setImage_url("./"+uc.upload+"/"+tmp);
					imgdto.setImage_name(tmp);
					res += imgdao.inSertImageData(imgdto);
					uc.syo("insert image : "+tmp + ", classpk("+classpk+")");
				}

				UtilTemp utmp = new UtilTemp(); //날짜 insert
				ClassDateDto datedto = new ClassDateDto();
				datedto.setClasspk(classpk);
				System.out.println(classpk);
				for (String[] tmp : days) {
					if(uc.nulchk(tmp[0])) {continue;}
					if(uc.nulchk(tmp[1])) {tmp[1]="00:00";}
					if(uc.nulchk(tmp[2])) {tmp[2]="00:00";}
					datedto.setClassday(utmp.StringToDate(tmp[0]));
					datedto.setStr_time(utmp.StringTimeToDate(tmp[0], tmp[1]));
					datedto.setEnd_time(utmp.StringTimeToDate(tmp[0],tmp[2]));
					res += datedao.insert_daytime(datedto);
					System.out.println("days : "+res);
				}

				if(res>0) { //classinfo 등록 실패하면 배너,날짜 등록도 실패함 PK제약조건
				jsResponse("강의 등록 완료", "classinfo.do?command=detailclass&classpk="+classpk, response);return;
				}else {
				jsResponse("강의 등록 실패", "main.do", response);return;
				}
			}
			else if(uc.chk(command, "modify")){ //강의 수정
				classpk = uc.parseInt(uc.get(multi, "classpk"));
				String sumnailorgin = uc.get(multi, "sumnailorgin"); //원본 이미지
				String sumnail = uc.imgfile(request, multi, "sumnail"); //수정파일
				if(uc.nulchk(sumnail)) {sumnail = sumnailorgin;} //Null이면 
				else {sumnail = "./"+uc.upload+"/"+sumnail;} //기존 이미지로 변경
				
				String title = uc.get(multi, "title");
				String content = uc.get(multi, "content");
				String classtype = uc.get(multi, "classpk");
				String keyword = uc.get(multi,"keyword");
				
				ClassInfoDto infodto = new ClassInfoDto();
				infodto.setClasspk(classpk);
				infodto.setTitle(title);
				infodto.setContent(content);
				infodto.setClasstype(classtype);
				infodto.setKeyword(keyword);
				infodto.setPubImg(sumnail);
				int res = infodao.updateClass(infodto);
				System.out.println(infodto);
				
				ClassImgDto imgdto = new ClassImgDto(); //배너 수정
				imgdto.setClasspk(classpk);
				List<ClassImgDto> banimg = imgdao.selectImg(classpk);
				List<ClassImgDto> banimg_insert = new ArrayList<ClassImgDto>();
				String[] bannimg = new String[3];
				for (int i=0;i<bannimg.length;i++) {
					bannimg[i] = uc.imgfile(request, multi, "bannimg"+i);					
					if(banimg.size()>i) {
						imgdto = banimg.get(i);
						if(bannimg[i]!=null) {
							imgdto.setImage_name(bannimg[i]);
							imgdto.setImage_url("./"+uc.upload+"/"+bannimg[i]);
							res += imgdao.updateImageData(imgdto);
						}
						banimg.set(i, imgdto);
					}else {
						if(bannimg[i]!=null) {
							imgdto.setClasspk(classpk);
							imgdto.setImage_name(bannimg[i]);
							imgdto.setImage_url("./"+uc.upload+"/"+bannimg[i]);
							res += imgdao.inSertImageData(imgdto);
						}
					}
				}
				jsResponse("강의 수정 완료", "classinfo.do?command=detailclass&classpk="+classpk, response);return;
			}
			// Multi 요청 처리코드 마침
		}
		
	}
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		//
		String s = "<script type='text/javascript'>alert('" + msg + "');" + "location.href='/Haru/" + url
				+ "';</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
	}

}
