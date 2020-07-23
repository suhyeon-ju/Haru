package com.controller.profile;

import com.classinfo.dao.ClassInfoDao;
import com.pay.biz.PayBiz;

public class countVo {
	private int total_class_num = 0;
	private int total_student = 0;
	private int total_allstudent = 0;
	private int total_pay =0;
	
	public int getmulstudent() {
		
		double res = 0;
		double st = total_student;
		double allst = total_allstudent;
		
		res = st/allst;
		return (int)(res*100);
	}
	
	public int getTotal_class_num() {
		return total_class_num;
	}
	public void setTotal_class_num(int total_class_num) {
		this.total_class_num = total_class_num;
	}
	public int getTotal_student() {
		return total_student;
	}
	public void setTotal_student(int total_student) {
		this.total_student = total_student;
	}
	public int getTotal_allstudent() {
		return total_allstudent;
	}
	public void setTotal_allstudent(int total_allstudent) {
		this.total_allstudent = total_allstudent;
	}
	public int getTotal_pay() {
		return total_pay;
	}
	public void setTotal_pay(int total_pay) {
		this.total_pay = total_pay;
	}
	
	
}
