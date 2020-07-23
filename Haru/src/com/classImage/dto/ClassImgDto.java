package com.classImage.dto;
/** tb_class_image 테이블의 데이터를 담기 위한 DTO
 */
public class ClassImgDto {
	private int banner_no;
	private int classpk;
	private String image_name;
	private String image_url;
	public ClassImgDto() {
		super();
	}
	public ClassImgDto(int banner_no, int classpk, String image_name, String image_url) {
		super();
		this.banner_no = banner_no;
		this.classpk = classpk;
		this.image_name = image_name;
		this.image_url = image_url;
	}
	public int getBanner_no() {
		return banner_no;
	}
	public void setBanner_no(int banner_no) {
		this.banner_no = banner_no;
	}
	public int getClasspk() {
		return classpk;
	}
	public void setClasspk(int classpk) {
		this.classpk = classpk;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	@Override
	public String toString() {
		return image_url;
	}
	
}
