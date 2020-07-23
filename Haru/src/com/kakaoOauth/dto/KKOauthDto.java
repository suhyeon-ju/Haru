package com.kakaoOauth.dto;
/** KAKAO_Oauth_RESTAPI의 데이터를 담기 위한 DTO
 */
public class KKOauthDto {
	private long idkey=-1;
	private String nickname=null;
	private String profileImg=null;
	public KKOauthDto() {
		super();
	}
	public KKOauthDto(long idkey, String nickname, String profileImg) {
		super();
		this.idkey = idkey;
		this.nickname = nickname;
		this.profileImg = profileImg;
	}
	public long getIdkey() {
		return idkey;
	}
	public void setIdkey(long idkey) {
		this.idkey = idkey;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getId() {
		if(idkey==-1) {return null;}
		return "kko_"+idkey;
	}
	public String getpw() {return getPwd();}
	public String getPwd() {
		if(idkey==-1) {return null;}
		int sd = (int)(idkey%9);
		int pt[] = new int[] {};
		switch(sd) {
		case 1:
			pt = new int[]{51,13,17,9,11,2};break;
		case 2:
			pt = new int[]{71,6,53,16,22,7};break;
		case 3:
			pt = new int[]{1,55,87,28,10,3};break;
		case 4:
			pt = new int[]{98,13,78,97,2,8};break;
		case 5:
			pt = new int[]{85,12,92,18,13,5};break;
		case 6:
			pt = new int[]{5,8,68,3,31,51};break;
		case 7:
			pt = new int[]{9,54,6,65,3,68};break;
		case 8:
			pt = new int[]{2,9,10,86,3,15};break;
		default:
			pt = new int[]{10,8,74,12,89,2};break;
		}
		String temp1 = "pw";
		String temp2 = "7JWI64WV7ZWY7IS47JqU";
		String res = "";
		for (int i=0;i<12;i++) {
			if(i%2==0) {
				res+=temp1;
			}else {
				res+=pt[(i%6)];
				res+=(char)(65+(pt[(i%6)]%20));
			}
		}
		res+=temp2;
		return res;
	}
	@Override
	public String toString() {
		return "KKOauthDto [idkey=" + idkey + ", nickname=" + nickname + ", profileImg=" + profileImg + "]";
	}
	
}
