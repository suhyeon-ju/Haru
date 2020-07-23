package com.pay.biz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 *  아임포트에 REST API를 요청하도록 하는 클래스
 * @author SJ
 */
public class Pay_REST {
	private long pay_uid=-1;
	public String Status_con(String Str) {
		if(Str.equals("ready")) {return "미결제";}
		if(Str.equals("paid")) {return "결제완료";}
		if(Str.equals("cancelled")) {return "결제취소";}
		if(Str.equals("failed")) {return "결제실패";}
		return Str;
	}
	
	public String[] parse_StatusArr(long pay_uid) {
		String Str = RESTAPI_payment_find_Json(pay_uid);
		if (Str==null) {return null;}
		String[] res = null;
		System.out.println(Str);
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(Str);
		try {
			int code = element.getAsJsonObject().get("code").getAsInt();
			if (code!=0) {return null;}
			JsonObject response = element.getAsJsonObject().get("response").getAsJsonObject();
			res = new String[] {
				response.get("amount").getAsString(),
				response.get("pay_method").getAsString(),
				response.get("status").getAsString()
			};
		}catch(Exception e) {e.printStackTrace();return res;}
		return res;
	}
	public String parse_cancel(long pay_uid) {
		String Str = RESTAPI_payment_cancel_Json(pay_uid);
		if (Str==null) {return null;}
		String res = null;
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(Str);
		try {
			int code = element.getAsJsonObject().get("code").getAsInt();
			if(code!=0) {return "code:err";}
			JsonObject response = element.getAsJsonObject().get("response").getAsJsonObject();
			res = response.get("status").getAsString();
		}catch(Exception e) {e.printStackTrace();return res;}
		return res;
	}
	public String parse_Status(long pay_uid) {
		String Str = RESTAPI_payment_find_Json(pay_uid);
		if(Str==null) {return null;}
		String res = null;
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(Str);
		try {
			JsonObject response = element.getAsJsonObject().get("response").getAsJsonObject();
			res = response.get("status").getAsString();
		}catch(Exception e) {return res;}
		return res;
	}
	private String parse_accessToken() {
		String Str = RESTAPI_authenticate_Json();
		if (Str==null) {return null;}
		String res = null;
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(Str);
		try {
			JsonObject response = element.getAsJsonObject().get("response").getAsJsonObject();
			res = response.get("access_token").getAsString();
		}catch(Exception e) {return res;}
		return res;
	}
	private String RESTAPI_payment_cancel_Json(long pay_uid) {
		String accessToken = parse_accessToken();
		String request_url="https://api.iamport.kr/payments/cancel";
		String res = null;
		if(accessToken==null) {return null;}
		BufferedWriter bw = null;
		BufferedReader br = null;
		try {
			URL url = new URL(request_url);
			HttpURLConnection conn;
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization"," Bearer "+accessToken);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			bw = getConnWriter(conn);
			bw.write("merchant_uid=merchant_"+pay_uid);
			bw.flush();
			int responseCode = conn.getResponseCode();
			if (responseCode!=200) {return null;}
			br = getConnReader(conn);
			String line=res="";
			while ((line=br.readLine())!=null) {
				res+=line;
			}
		}catch(Exception e) {e.printStackTrace();return null;}
		finally {
			close(bw);
			close(br);
		}
		return res;
	}
	private String RESTAPI_payment_find_Json(long pay_uid) {
		String accessToken = parse_accessToken();
		String request_url="https://api.iamport.kr/payments/find/merchant_"+pay_uid+"/?sorting=-updated";
		String res = null;
		
		BufferedReader br = null;
		try {
			URL url = new URL(request_url);
			HttpURLConnection conn;
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization"," Bearer "+accessToken);
			conn.setRequestMethod("GET");
			int responseCode = conn.getResponseCode();
			if (responseCode!=200) {return null;}
			br = getConnReader(conn);
			String line=res="";
			while ((line=br.readLine()) != null) {
				res+=line;
			}
		}catch (Exception e) {e.printStackTrace();return null;}
		finally {
			close(br);
		}
		try {
			
		}catch(Exception e) {e.printStackTrace();return null;}
		return res;
	}
	
	private String RESTAPI_authenticate_Json() {
		String key="5413393357627422";
		String key_secret="6hb2u1sZuznQyId7uOlbgBoL69WVXEV4V76p8zpD8NuHvHPZwbbMMAyTKvOH9KL5SSu0rUEAH8idP7cl";
		String request_url="https://api.iamport.kr/users/getToken";
		String res=null;
		
		BufferedWriter bw = null;
		BufferedReader br = null;
		try {
			URL url = new URL(request_url);
			HttpURLConnection conn;
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			bw = getConnWriter(conn);
			if (bw==null) {return null;}
			bw.write("imp_key="+key+"&imp_secret="+key_secret);
			bw.flush();
			int responseCode = conn.getResponseCode();
			if (responseCode!=200) {return null;}
			br = getConnReader(conn);
			if (br==null) {return null;}
			String line=res="";
			while ((line = br.readLine()) != null) {
				res+=line;
			}
		}catch (Exception e) {e.printStackTrace();return null;}
		finally {
			close(bw);
			close(br);
		}
		return res;
	}
	private BufferedReader getConnReader(HttpURLConnection conn) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		}catch(Exception e) {e.printStackTrace();}
		return br;
	}
	private BufferedWriter getConnWriter(HttpURLConnection conn) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		}catch(Exception e) {e.printStackTrace();}
		return bw;
	}
	private void close(BufferedReader br) {
		if (br==null) {return;}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void close(BufferedWriter bw) {
		if (bw==null) {return;}
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
