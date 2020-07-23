package common;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.dto.MemberDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class utilc {
	public utilc() {System.out.println("utilc 생성");}
	public static String host = "http://sclass.iptime.org:8787/Haru/";
	public static String upload = "upload";
	public int sizeLimit = 1024 * 1024 * 10;
	
	// UTF-8 인코딩
	public utilc encoding (HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		return this;
	}
	
	public MemberDto getmember(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto) session.getAttribute("member");
		return member;
	}	
	
	//파일 업로드 경로를 가져옴 (사전에 폴더 없을경우 자동 생성 역할)
	public String save_path(HttpServletRequest request) {
		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath+upload;
		System.out.println("savepath : "+savePath);
		File fileDir = new File(savePath);
		if(!fileDir.exists()) {
			fileDir.mkdir();
		}
		return savePath;
	}
	
	public Date toDate(String str) {
		if(nulchk(str)) {return null;}
		if(str.indexOf("-")==-1) {return null;}
		String[] arr = str.split("-");
		int day[] = new int[3];
		try {
			for(int i=0;i<arr.length;i++) {
				day[i] = Integer.parseInt(arr[i]);
			}
		}catch(Exception e) {e.printStackTrace();return null;}
		return toDate(day[0],day[1],day[2]);
	}
	
	public Date toDate(int yy,int MM,int dd) {
		Calendar cal = Calendar.getInstance();
		cal.set(yy,MM-1,dd);
		return new Date(cal.getTimeInMillis());
	}
	
	//문자가 null 또는 공백인지 확인
	public boolean nulchk(String...arr) {
		boolean chk = false;
		for (int i=0;i<arr.length;i++) {
			if(arr[i]==null) {return !chk;}
			if(arr[i].isEmpty()) {return !chk;}
			if(arr[i].trim().equals("")) {return !chk;}
		}
		return chk;
	}
	
	//MultipartRequest 객체를 생성함
	public MultipartRequest getMultipart(HttpServletRequest request) {
		MultipartRequest multi = null;
		 try {
			multi = new MultipartRequest(request, save_path(request), sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			System.out.println("multipart/form-data 요청이 아닙니다");
		}
		return multi;
	}
	
	//Multi에서 파일 이름을 받아옴
	public String file(MultipartRequest multi,String str) {
		if(str==null||multi==null) {return null;}
		return multi.getFilesystemName(str);
	}
	
	//Multi 에서 받은 다음 이미지 파일이 아니라면 삭제해버림
	public String imgfile(HttpServletRequest request, MultipartRequest multi,String str) {
		String fileName = file(multi,str);
		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath+upload;
		System.out.println("savepath : "+savePath);
		if(nulchk(fileName)) {return null;}
		String delName = savePath + File.separator + fileName;
		if(fileName.indexOf(".")<0) {
			System.out.println("삭제 파일 : "+delName);
			delFile(delName);
			return null;
		}else {
			String[] arr = fileName.split("\\."); //정규식으로 인식 방지
			System.out.println(arr[arr.length-1]);
			if(!chkword(arr[arr.length-1],"jpg","bmp","jpeg","png","gif")){
				System.out.println("삭제 파일 : "+delName);
				delFile(delName);
				return null;
			}
		}
		return fileName;
	}
	
	public int parseInt(String str) {
		int res = 0;
		try { res = Integer.parseInt(str);	
		}catch(Exception e) {syo("ERR : 문자열을 int로 실패");}
		return res;
	}
	
	//파일 삭제 처리
	public void delFile(String str) {
		File file = new File(str);
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("파일 삭제 성공");
			}else {
				System.out.println("파일 삭제 실패");
			}
		}else {
			System.out.println("NOT");
		}
	}
		
	// getParameter 줄임
	public String get(MultipartRequest multi,String str) {
		if(str==null||multi==null) {return null;}
		System.out.println(str+" : "+multi.getParameter(str));
		return multi.getParameter(str);
	}
	
	public String get(HttpServletRequest request,String str) {
		if(str==null||request==null) {return null;}
		System.out.println(str+" : "+request.getParameter(str));
		return request.getParameter(str);
	}
	
	public int getClasspk(HttpServletRequest request) {
		int classpk = 0;
		try {
			classpk = Integer.parseInt(get(request,"classpk"));
		}catch(Exception e) {syo("ERR : classpk를 int로 실패");}
		return classpk;
	}
	
	// 서로 같은 문자인지 비교 (사전 null 체크)
	public boolean chk(String command,String text) {
		if(nulchk(command,text)) {return false;}
		return command.equals(text);
	}

	//첫번째 문자열과 같은 문자열이 하나라도 있는지
	public boolean chkword(String word,String...val) {
		if(nulchk(val) || nulchk(word)) {
			return false;
		}
		for(String tmp : val) {
			System.out.println(word+":"+tmp);
			if(word.equals(tmp)) {return true;}
		}
		return false;
	}
	
	// 입력된 문자를 그대로 리턴하지만 만약 null,공백이라면 def값을 리턴
	public String nulChange(String text,String def) {
		if(nulchk(text)) {return def;}
		return text;
	}
	// command 값을 request 에서 가져옴
	public String getCmd(HttpServletRequest request) {
		return request.getParameter("command");
	}
	// command 값을 multipart 에서 가져옴
	public String getCmd(MultipartRequest multi) {
		if(multi==null) {return null;}
		return multi.getParameter("command");
	}
	
	// multi 요청이면 multi 커멘드를 리턴, 아니면 request 커멘드 리턴
	public String getCmd(HttpServletRequest request, MultipartRequest multi) {
		return getCmd(multi,request);
	}

	// multi 요청이면 multi 커멘드를 리턴, 아니면 request 커멘드 리턴
	public String getCmd(MultipartRequest multi,HttpServletRequest request) {
		String command = null;
		if(multi==null) {
			command = getCmd(request);
			System.out.println("POST / GET command : "+command);
			return command;
		}else {
			command = getCmd(multi);
			System.out.println("MultiPart command : "+command);
			return command;
		}
	}
	
	// 출력문 단축
	public utilc syo(String message) {
		System.out.println(message);
		return this;
	}
	
	// request , response를 원하는 곳으로 보냄
	public void dispatch(String url,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	// 자바스크립트로 메세지 출력 후 이동
	public void jsResponse(String msg,String url, HttpServletResponse response) throws IOException {
		String script =
			"<script type='text/javascript'>alert('"+msg+"');location.href='./"+url+"';</script>";
		PrintWriter out = response.getWriter();
		out.print(script);
	}
	
	public void close(PrintWriter out) {
		try {
			out.close();
		}catch(Exception e) {}
	}
	
	public static void main(String[] args) {
		//테스트 코드 작성
		
	}
}
