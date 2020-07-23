package com.classmember.dto;
/** tb_class_member 테이블의 데이터를 담기 위한 DTO
 */
public class ClassMemberDto {
	private String id;
	private int classpk;
	private String chk_class;
	private String chk_wish;
	public ClassMemberDto() {
		super();
	}
	public ClassMemberDto(String id, int classpk, String chk_class, String chk_wish) {
		super();
		this.id = id;
		this.classpk = classpk;
		this.chk_class = chk_class;
		this.chk_wish = chk_wish;
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
	public String getChk_class() {
		return chk_class;
	}
	public void setChk_class(String chk_class) {
		this.chk_class = chk_class;
	}
	public String getChk_wish() {
		return chk_wish;
	}
	public void setChk_wish(String chk_wish) {
		this.chk_wish = chk_wish;
	}
	@Override
	public String toString() {
		return "ClassMemberDto [id=" + id + ", classpk=" + classpk + ", chk_class=" + chk_class + ", chk_wish="
				+ chk_wish + "]";
	}
	
}
