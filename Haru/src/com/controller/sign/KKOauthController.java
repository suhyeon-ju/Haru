package com.controller.sign;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kakaoOauth.dto.KKOauthDto;

import common.utilc;

@WebServlet("/kkoauth.do")
public class KKOauthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public KKOauthController() {super();}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		utilc uc = new utilc();
		//code 를 받아 KKOauthDto 를 리턴합니다.
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String code = request.getParameter("code");
		if(code==null) {
			System.out.println("null !!");
//			dispatch("sign.do?command=kakao", request, response);
			send(request, response);
			return;
		}else {
			System.out.println(code);
			KKOauthDto dto = null;
			BufferedWriter bw = null;
			BufferedReader br = null;
			try {
				URL url = new URL("https://kauth.kakao.com/oauth/token");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				StringBuilder sb = new StringBuilder();
				sb.append("grant_type=authorization_code");
				sb.append("&client_id=f321498efdca3bbaa89bdab055ec38fe");
				sb.append("&redirect_uri="+uc.host+"kkoauth.do");
				sb.append("&code="+code);
				bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
				bw.write(sb.toString());
				bw.flush();
				
				int responseCode = conn.getResponseCode();
	            System.out.println("responseCode : "+responseCode);
	            
	            if(responseCode!=200) {send(request, response);return;}
				
	            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            
	            String result = "",line;
	            
	            while((line = br.readLine()) != null) {
	            	result += line;
	            }
	            
	            System.out.println("response body : "+result);

	            JsonParser parser = new JsonParser();
	            JsonElement element = parser.parse(result);
	            String access_token = null;
	            try {
	            	access_token = element.getAsJsonObject().get("access_token").getAsString();
	            }catch(Exception e) {}
	            if(access_token==null) {send(request, response);return;}
	            System.out.println(access_token);
	            
	            URL url2 = new URL("https://kapi.kakao.com/v2/user/me");
	            HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
	            conn2.setRequestMethod("POST");
	            conn2.setRequestProperty("Authorization", "Bearer " + access_token);
	            responseCode = conn.getResponseCode();
	            System.out.println(responseCode);
	            if(responseCode!=200) {send(request, response);return;}
	            
	            try {br.close();}catch(Exception e) {}
	            String result2="";
	            br = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
	            while((line = br.readLine())!=null) {
	            	result2+=line;
	            }
	            System.out.println("response2 body : "+result2);
	            bw.close();
				br.close();
	            JsonParser parser2 = new JsonParser();
	            JsonElement element2 = parser.parse(result2);
	            
	            JsonObject properties = element2.getAsJsonObject().get("properties").getAsJsonObject();
	            long idkey = -1L;String nickname,profileImg=nickname=null;
	            try {
	            	idkey = element2.getAsJsonObject().get("id").getAsLong();
	            }catch(Exception e) {e.printStackTrace();}
	            
	            try {
	            	nickname = properties.getAsJsonObject().get("nickname").getAsString();
	            }catch(Exception e) {e.printStackTrace();}
	            
	            try {
	            	profileImg = properties.getAsJsonObject().get("profile_image").getAsString();
	            }catch(Exception e) {e.printStackTrace();}
	            
	            
	            
	            if(idkey!=-1 && nickname !=null) {
	            	dto = new KKOauthDto();
	            	dto.setIdkey(idkey);
	            	dto.setNickname(nickname);
	            	if(profileImg!=null) {
	            		dto.setProfileImg(profileImg);
	            	}
//	            	request.setAttribute("kkoauth", dto);
//	            	System.out.println(dto);
//	            	System.out.println("THIS");
	            	HttpSession session = request.getSession();
	        		session.setMaxInactiveInterval(-1);
	        		session.setAttribute("kkoauth", dto);
	        		response.sendRedirect("sign.do?command=kakao");
//	    			dispatch("sign.do?command=kakao", request, response);
	    			return;
	    			}
	            }catch(Exception e) {
				for(int i=0;i<20;i++) {
					System.out.println();
				}
				e.printStackTrace();
				send(request, response);
				return;
				}
//			System.out.println("THIS2");
			send(request, response);
		}
	}
	
	private void send(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatch("sign.do?command=kakao", request, response);
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