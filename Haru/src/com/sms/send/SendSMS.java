package com.sms.send;

import com.sms.dao.SmsDao;
import com.sms.dto.SmsDto;

/**
 * 문자 발송 클래스 입니다.
 *
 */
public class SendSMS {
	boolean mode_chkT = false;
	boolean mode_sendT = true;
	
	private SmsDto temp = null;
	public SendSMS() {super();}
	public SendSMS(SmsDto dto) {this.temp = dto;}
	
	public int chkSMS(SmsDto dto) {this.temp = dto;return chkSMS();}
	public int chkSMS() {
		int res = 0;
		if(temp==null) {return 0;}
		if(mode_chkT) {return 1;}
		SmsDao smsdao = new SmsDao();
		temp.setPhone(temp.getPhone(), true);
		SmsDto select = smsdao.selectOne(temp.getPhone());
		if(select!=null) {
			if(select.getRankey()==temp.getRankey()) {res=1;}	
		}
		return res;
	}
	private boolean filterNum(String phone) {
		String table[] = {"*"};
		if(phone==null) {return false;}
		for(String k : table) {
			if(phone.equals(k)) {
				return true;
			}
		}
		return false;
	}
	
	public int sendSMS(String phone) {return sendSMS(new SmsDto(phone));}
	public int sendSMS(SmsDto dto) {this.temp = dto;return sendSMS();}
	public int sendSMS() {
		int res = 0;
		if(temp==null) {return 0;}
		SmsDao smsdao = new SmsDao();
		if(mode_sendT) {
			temp.setRankey(888888);
			res = smsdao.insert(temp);
			if(res>0) {res=1;}
			return res;
		}
		String phone = temp.getPhone();
		if(filterNum(phone)) {
			
		}else {
			return 2;
		}
		
		return res;
	}
}
