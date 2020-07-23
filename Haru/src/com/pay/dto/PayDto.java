package com.pay.dto;
import java.sql.Date;
import java.text.SimpleDateFormat;
/** tb_pay 테이블의 데이터를 담기 위한 DTO
 */
public class PayDto {
	private String id;
	private int classpk;
	private int classdatepk;
	private long pay_uid;
	private String pay_name;
	private String pay_method;
	private int pay_price;
	private String pay_true;
	private Date pay_time;
	public PayDto() {
		super();
	}
	public PayDto(long pay_uid,String id,int classpk,String pay_name,String pay_method,int pay_price,String pay_true,Date pay_time) {
		this(id, classpk, pay_uid, pay_name, pay_method, pay_price, pay_true, pay_time);
	}
	public PayDto(String id, int classpk, long pay_uid, String pay_name, String pay_method, 
			int pay_price, String pay_true, Date pay_time) {
		super();
		this.id = id;
		this.classpk = classpk;
		this.pay_uid = pay_uid;
		this.pay_name = pay_name;
		this.pay_method = pay_method;
		this.pay_price = pay_price;
		this.pay_true = pay_true;
		this.pay_time = pay_time;
	}
	public PayDto(String id, int classpk, int classdatepk, long pay_uid, String pay_name, String pay_method,
			int pay_price, String pay_true, Date pay_time) {
		super();
		this.id = id;
		this.classpk = classpk;
		this.classdatepk = classdatepk;
		this.pay_uid = pay_uid;
		this.pay_name = pay_name;
		this.pay_method = pay_method;
		this.pay_price = pay_price;
		this.pay_true = pay_true;
		this.pay_time = pay_time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getClasspk() {
		return classpk;
	}
	public void setClasspk(int classpk) {
		this.classpk = classpk;
	}
	public int getClassdatepk() {
		return classdatepk;
	}
	public void setClassdatepk(int classdatepk) {
		this.classdatepk = classdatepk;
	}
	public long getPay_uid() {
		return pay_uid;
	}
	public void setPay_uid(long pay_uid) {
		this.pay_uid = pay_uid;
	}
	public String getPay_name() {
		return pay_name;
	}
	public void setPay_name(String pay_name) {
		this.pay_name = pay_name;
	}
	public String getPay_method() {
		return pay_method;
	}
	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}
	public int getPay_price() {
		return pay_price;
	}
	public void setPay_price(int pay_price) {
		this.pay_price = pay_price;
	}
	public String getPay_true() {
		return pay_true;
	}
	public void setPay_true(String pay_true) {
		this.pay_true = pay_true;
	}
	public Date getPay_time() {
		return pay_time;
	}
	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}
	@Override
	public String toString() {
		return "PayDto [id=" + id + ", classpk=" + classpk + ", classdatepk=" + classdatepk + ", pay_uid=" + pay_uid
				+ ", pay_name=" + pay_name + ", pay_method=" + pay_method + ", pay_price=" + pay_price + ", pay_true="
				+ pay_true + ", pay_time=" + pay_time + "]";
	}
	
	//Custom
	public String printTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
		Date date = getPay_time();
		return format.format(date);
	}
	
}
