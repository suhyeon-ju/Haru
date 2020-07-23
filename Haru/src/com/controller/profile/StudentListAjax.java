package com.controller.profile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.member.dao.MemberDao;
import com.member.dto.MemberDto;
@WebServlet("/StudentListAjax")
public class StudentListAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public StudentListAjax() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		System.out.println(" 컨트롤러 들어옴");
		
		int pk = Integer.parseInt(request.getParameter("pk"));
		System.out.println("값 받아옴 " + pk);
		
//		String name = callStudentList(pk).get(0).getName();
//		String phone = callStudentList(pk).get(0).getPhone();
//		String gender = callStudentList(pk).get(0).getGender();
//		
//		이 데이터를 student로 넘겨줄 것이다. 
//		자바에서 json을처리하기 위한 json라이브러리(json_simple.jar)가 필요함 lib폴더에 넣어주자.
//		내부적으로 map과 list 사용
//		대표적인 클래스 : JSONObject->Json을 추상화한 클래스, java.util.HashMap을 상속받는다.
//		JSONObject obj = new JSONObject();
//		데이터를 추가하고 싶을 때 사용했던 매소드 put을 사용함
//		obj.put("name",name);
//		obj.put("phone",phone);
//		obj.put("gender",gender);
//		{"name":name, "phone":phone, "gender":gender} 형식으로 데이터가 추가된다.
		
		JSONObject obj = new JSONObject();
		List<MemberDto> list = callStudentList(pk);
		int cnt =1, code=list.size();
		obj.put("code", code);
//		JSONObject member = new JSONObject();
		//이름 전화번호 성별
		
		JSONObject member = new JSONObject();
		JSONObject progressinfo = new JSONObject();
		
		profileDao Dao = new profileDao();

		
		progressinfo.put("startdate", Dao.startdate(pk));
		progressinfo.put("enddate", Dao.enddate(pk));
		
		progressinfo.put("progressrate", Dao.selectpercent(pk));
		
		
		for(MemberDto dto : list) {
			System.out.println(dto);
			JSONObject tmp = new JSONObject();
			tmp.put("name", dto.getName());
			tmp.put("phone",dto.getPhone());
			tmp.put("gender",dto.getGender());
			
			member.put(cnt++, tmp);
			
		}
		
		obj.put("member",member);
		obj.put("progressinfo",progressinfo);
		
//		obj.put("member", member.toJSONString());
		
		PrintWriter out = response.getWriter();
		out.println(obj.toJSONString());
		
		System.out.println(obj.toJSONString());
	}
	private List<MemberDto> callStudentList(int classpk) {
		profileDao Dao = new profileDao();
		List<MemberDto> list = Dao.memeberList(classpk);
		
		return list;
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
