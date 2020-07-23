package com.classinfo.dto;
import java.sql.Date;
/** tb_classinfo 테이블의 데이터를 담기 위한 DTO
 */
public class ClassInfoDto {
	private int classpk;
	private String id = null;
	private String title = null;
	private String content = null;
	private int price;
	private int allstudent;
	private String loc = null;
	private String category = null;
	private String classtype = null;
	private String keyword = null;
	private int rank;
	private int rankcnt;
	private String pubImg = null;
	private String cellurl = null;
	
	private String nickname = null; //JOIN 전용 필드 (강사 닉네임)
	private int nowstudent=0; //JOIN 전용 필드 (현 수강생 수)
	
	private int profit = 0;
	private double course_rate =0;
	private double progress_rate = 0;
	
	
	
	
	
	
	public ClassInfoDto(String title, String loc, int nowstudent, int profit, double course_rate,
			double progress_rate) {
		super();
		this.title = title;
		this.loc = loc;
		this.nowstudent = nowstudent;
		this.profit = profit;
		this.course_rate = course_rate;
		this.progress_rate = progress_rate;
	}
	public ClassInfoDto(int classpk, String id, String title, String content, int price, int allstudent, String loc,
			String category, String keyword, int rank, int rankcnt, String pubImg, String cellurl) {
		super();
		this.classpk = classpk;
		this.id = id;
		this.title = title;
		this.content = content;
		this.price = price;
		this.allstudent = allstudent;
		this.loc = loc;
		this.category = category;
		this.keyword = keyword;
		this.rank = rank;
		this.rankcnt = rankcnt;
		this.pubImg = pubImg;
		this.cellurl = cellurl;
	}
	public int getProfit() {
		return profit;
	}
	public void setProfit(int profit) {
		this.profit = profit;
	}
	public double getCourse_rate() {
		return course_rate;
	}
	public void setCourse_rate(double course_rate) {
		this.course_rate = course_rate;
	}
	public double getProgress_rate() {
		return progress_rate;
	}
	public void setProgress_rate(double progress_rate) {
		this.progress_rate = progress_rate;
	}
	public ClassInfoDto(int classpk, String id, String title, String content, int price, int allstudent, String loc,
			String category, String keyword, int rank, int rankcnt, String pubImg, String cellurl, int nowstudent) {
		super();
		this.classpk = classpk;
		this.id = id;
		this.title = title;
		this.content = content;
		this.price = price;
		this.allstudent = allstudent;
		this.loc = loc;
		this.category = category;
		this.keyword = keyword;
		this.rank = rank;
		this.rankcnt = rankcnt;
		this.pubImg = pubImg;
		this.cellurl = cellurl;
		this.nowstudent = nowstudent;
	}
	public ClassInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClassInfoDto(int classpk, String id, String title, String content, int price, int allstudent, String loc,
			String category, String classtype, String keyword, int rank, int rankcnt, String pubImg, String cellurl) {
		super();
		this.classpk = classpk;
		this.id = id;
		this.title = title;
		this.content = content;
		this.price = price;
		this.allstudent = allstudent;
		this.loc = loc;
		this.category = category;
		this.classtype = classtype;
		this.keyword = keyword;
		this.rank = rank;
		this.rankcnt = rankcnt;
		this.pubImg = pubImg;
		this.cellurl = cellurl;
	}
	
	
	//
	
	public int getClasspk() {
		return classpk;
	}
	public void setClasspk(int classpk) {
		this.classpk = classpk;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAllstudent() {
		return allstudent;
	}
	public void setAllstudent(int allstudent) {
		this.allstudent = allstudent;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getClasstype() {
		return classtype;
	}
	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getRankcnt() {
		return rankcnt;
	}
	public void setRankcnt(int rankcnt) {
		this.rankcnt = rankcnt;
	}
	public String getPubImg() {
		return pubImg;
	}
	public void setPubImg(String pubImg) {
		this.pubImg = pubImg;
	}
	public String getCellurl() {
		return cellurl;
	}
	public void setCellurl(String cellurl) {
		this.cellurl = cellurl;
	}
	@Override
	public String toString() {
		return "ClassInfoDto [classpk=" + classpk + ", id=" + id + ", title=" + title + ", content=" + content
				+ ", price=" + price + ", allstudent=" + allstudent + ", loc=" + loc + ", category=" + category
				+ ", classtype=" + classtype + ", keyword=" + keyword + ", rank=" + rank + ", rankcnt=" + rankcnt
				+ ", pubImg=" + pubImg + ", cellurl=" + cellurl + ", nickname=" + nickname + ", nowstudent="
				+ nowstudent + "]";
	}
	public ClassInfoDto(int classpk, String id, String title, String content, int price, int allstudent, String loc,
			String category, String classtype, String keyword, int rank, int rankcnt, String pubImg, String cellurl,
			String nickname, int nowstudent) {
		super();
		this.classpk = classpk;
		this.id = id;
		this.title = title;
		this.content = content;
		this.price = price;
		this.allstudent = allstudent;
		this.loc = loc;
		this.category = category;
		this.classtype = classtype;
		this.keyword = keyword;
		this.rank = rank;
		this.rankcnt = rankcnt;
		this.pubImg = pubImg;
		this.cellurl = cellurl;
		this.nickname = nickname;
		this.nowstudent = nowstudent;
	}
	// JOIN 전용
	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getNowstudent() {
		return nowstudent;
	}
	public void setNowstudent(int nowstudent) {
		this.nowstudent = nowstudent;
	}
	// Custom
	public double mulrank() { //별점
		return rank/rankcnt;
	}
	public String goloc() {
		String goloc = this.loc;
		goloc = goloc.substring(goloc.indexOf(" ") + 1); //가장 앞에 숫자 자르기
		if (goloc.indexOf(" ") == -1) {
			int length = goloc.length();
			if(length>=15) {length=15;}
			goloc = goloc.substring(0, length);
		} else {
			goloc = goloc.substring(goloc.indexOf(" ") + 1);
			goloc = goloc.substring(0, goloc.indexOf(" ") + 1);
		}
		return goloc;
	}
}
