package com.member.dto;

import java.sql.Date;
import java.util.Calendar;
/** tb_member 테이블의 데이터를 담기 위한 DTO
 */
public class MemberDto {
	private String id;
	private String pwd;
	private String name;
	private String nickname;
	private Date birth;
	private String gender;
	private String email;
	private String phone;
	private String isclass;
	private String profileImg;
	private String pmessage;
	private String openchat;
	private String career;
	private String idcard;
	private String license;
	public MemberDto() {
		super();
	}
	public MemberDto(String id, String pwd, String name, String nickname, Date birth, String gender, String email,
			String phone, String isclass, String profileImg, String pmessage, String openchat, String career,
			String idcard, String license) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.nickname = nickname;
		this.birth = birth;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.isclass = isclass;
		this.profileImg = profileImg;
		this.pmessage = pmessage;
		this.openchat = openchat;
		this.career = career;
		this.idcard = idcard;
		this.license = license;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id.toLowerCase();
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		if(phone.indexOf('-')==-1) {
			if(phone.length()==10 || phone.length()==11) {
				String temp = phone.substring(0,3)+"-";
				temp+=phone.substring(3,phone.length()-4)+"-";
				temp+=phone.substring(phone.length()-4);
				this.phone = temp;
				System.out.println(temp);
				return;
			}
		}
		System.out.println(phone);
		this.phone = phone;
	}
	public String getIsclass() {
		return isclass;
	}
	public void setIsclass(String isclass) {
		this.isclass = isclass;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getPmessage() {
		return pmessage;
	}
	public void setPmessage(String pmessage) {
		this.pmessage = pmessage;
	}
	public String getOpenchat() {
		return openchat;
	}
	public void setOpenchat(String openchat) {
		this.openchat = openchat;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	
	//Custom
	public void setBirth(int yy,int MM,int dd) {
		Calendar cal = Calendar.getInstance();
		cal.set(yy, MM-1, dd);
		Date d = new Date(cal.getTimeInMillis()); 
		setBirth(d);
	}
	public void setBirth(String date) {
		if(date==null) {return;}
		String[] arr = date.split("-");
		setBirth(
			Integer.parseInt(arr[0]),
			Integer.parseInt(arr[1]),
			Integer.parseInt(arr[2])
		);
	}
	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pwd=" + pwd + ", name=" + name + ", nickname=" + nickname + ", birth=" + birth
				+ ", gender=" + gender + ", email=" + email + ", phone=" + phone + ", isclass=" + isclass
				+ ", profileImg=" + profileImg + ", pmessage=" + pmessage + ", openchat=" + openchat + ", career="
				+ career + ", idcard=" + idcard + ", license=" + license + "]";
	}
}
