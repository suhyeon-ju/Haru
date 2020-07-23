package com.board.dto;

import java.sql.Date;
import java.util.Calendar;
/** tb_board 테이블의 데이터를 담기 위한 DTO
 */
/**
 * @author SJ97T
 *
 */
public class BoardDto {
	private int bbs_no;
	private String id;
	private String title;
	private String content;
	private Date regdate;
	private String nickname; //JOIN 전용 필드
	public BoardDto() {
		super();
	}
	public BoardDto(int bbs_no, String id, String title, String content, Date regdate) {
		super();
		this.bbs_no = bbs_no;
		this.id = id;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}
	public int getBbs_no() {
		return bbs_no;
	}
	public void setBbs_no(int bbs_no) {
		this.bbs_no = bbs_no;
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
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "BoardDto [bbs_no=" + bbs_no + ", id=" + id + ", title=" + title + ", content=" + content + ", regdate="
				+ regdate + "]";
	}
	//JOIN 전용
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
}
