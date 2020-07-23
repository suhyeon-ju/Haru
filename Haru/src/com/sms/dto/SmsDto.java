package com.sms.dto;
/** tb_sms 테이블의 데이터를 담기 위한 DTO
 */
public class SmsDto {
	private String phone = "";
	private int rankey;
	public SmsDto() {
		super();
	}
	public SmsDto(String phone) {this(phone,-1);setPhone(phone,true);}
	public SmsDto(String phone, String rankey) {
		this(phone,Integer.parseInt(rankey));
	}
	public SmsDto(String phone, int rankey) {
		this.phone = phone;
		this.rankey = rankey;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String setPhone(String phone,boolean bool) {
		if(phone==null) {return this.phone;}
		if(bool) {
			String tmp = phone;
			if(tmp.length()==10 || tmp.length()==11) {
				tmp = phone.substring(0,3)+"-";
				tmp+= phone.substring(3,phone.length()-4)+"-";
				tmp+= phone.substring(phone.length()-4);
				this.phone = tmp;
			}
		}else {this.phone=phone;}
		return this.phone;
	}
	public int getRankey() {
		return rankey;
	}
	public void setRankey(int rankey) {
		this.rankey = rankey;
	}
	@Override
	public String toString() {
		return "SmsDto [phone=" + phone + ", rankey=" + rankey + "]";
	}
	
}
