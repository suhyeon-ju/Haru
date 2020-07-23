package com.classreview.dto;

import java.sql.Date;
import java.util.Calendar;
/** tb_class_review 테이블의 데이터를 담기 위한 DTO
 */
public class ClassReviewDto {
	private int cmdpk;
	private String content;
	private int strp;
	private String id;
	private int classpk;
	private String nickname;
	private Date regdate;
	private String pubimg;
	public ClassReviewDto() {
		super();
	}
	public ClassReviewDto(int cmdpk, String content, int strp, String id, int classpk, String nickname, Date regdate) {
		super();
		this.cmdpk = cmdpk;
		this.content = content;
		this.strp = strp;
		this.id = id;
		this.classpk = classpk;
		this.nickname = nickname;
		this.regdate = regdate;
	}
	public int getCmdpk() {
		return cmdpk;
	}
	public void setCmdpk(int cmdpk) {
		this.cmdpk = cmdpk;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
		
	}
	public int getStrp() {
		return strp;
	}
	public void setStrp(int strp) {
		this.strp = strp;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	//Custom
	public void setRegdate(int yy,int MM,int dd) {
		Calendar cal = Calendar.getInstance();
		cal.set(yy, MM-1 ,dd);
		Date d = new Date(cal.getTimeInMillis());
		setRegdate(d);
	}
	public void setRegdate(String date) {
		if(date==null) {return;}
		String[] arr = date.split("-");
		setRegdate(
			Integer.parseInt(arr[0]),
			Integer.parseInt(arr[1]),
			Integer.parseInt(arr[2])
		);
	}
	
	public String getPubimg() {
		return pubimg;
	}
	public void setPubimg(String pubimg) {
		this.pubimg = pubimg;
	}
	@Override
	public String toString() {
		return "ClassReviewDto [classpk=" + classpk + ", cmdpk=" + cmdpk + ", content=" + content + ", id=" + id
				+ ", nickname=" + nickname + ", regdate=" + regdate + ", strp=" + strp + "]";
	}
	
}