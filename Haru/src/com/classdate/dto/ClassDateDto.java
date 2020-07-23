package com.classdate.dto;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/** tb_ 테이블의 데이터를 담기 위한 DTO
 */
public class ClassDateDto {
	private int classdatepk;
	private int classpk;
	private Date classday;
	private Date str_time;
	private Date end_time;
	
	public ClassDateDto() {
		super();
	}
	public ClassDateDto(int classdatepk, int classpk, Date classday, Date str_time, Date end_time) {
		super();
		this.classdatepk = classdatepk;
		this.classpk = classpk;
		this.classday = classday;
		this.str_time = str_time;
		this.end_time = end_time;
	}
	public int getClassdatepk() {
		return classdatepk;
	}
	public void setClassdatepk(int classdatepk) {
		this.classdatepk = classdatepk;
	}
	public int getClasspk() {
		return classpk;
	}
	public void setClasspk(int classpk) {
		this.classpk = classpk;
	}
	public Date getClassday() {
		return classday;
	}
	public void setClassday(Date classday) {
		this.classday = classday;
	}
	public Date getStr_time() {
		return str_time;
	}
	public void setStr_time(Date str_time) {
		this.str_time = str_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	@Override
	public String toString() {
		return "ClassDateDto [classdatepk=" + classdatepk + ", classpk=" + classpk + ", classday=" + classday
				+ ", str_time=" + str_time + ", end_time=" + end_time + "]";
	}
	
	//Custom
	public void setClassday(int yy,int MM, int dd) {
		setClassday(returnDate(yy,MM,dd));
	}
	public void setClassday(String date) {
		setClassday(returnDate(date));
	}
	
	public String print() {
		String date="";
		SimpleDateFormat format = new SimpleDateFormat("yy.MM.dd");
		date+=format.format(classday)+" (";
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(classday);
		int dw = cal.get(Calendar.DAY_OF_WEEK);
		switch(dw) {
		case 1: date+="일";
			break;
		case 2: date+="월";
			break;
		case 3: date+="화";
			break;
		case 4: date+="수";
			break;
		case 5: date+="목";
			break;
		case 6: date+="금";
			break;
		case 7: date+="토";
			break;
		}
		date+=") ";
		SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
		date+=format2.format(str_time)+"~";
		date+=format2.format(end_time);
		return date;
	}
	public String printTime() {
		String date="";
		Calendar cal = Calendar.getInstance();
		cal.setTime(classday);
		SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
		date+=format2.format(str_time)+"~";
		date+=format2.format(end_time);
		return date;
	}
	
	public String printTimestartime() {
		String date="";
		Calendar cal = Calendar.getInstance();
		cal.setTime(classday);
		SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
		date+=format2.format(str_time);
		return date;
	}
	public String printTimeendtime() {
		String date="";
		Calendar cal = Calendar.getInstance();
		cal.setTime(classday);
		SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
		date+=format2.format(end_time);
		return date;
	}
	private Date returnDate(int yy,int MM,int dd) {
		Calendar cal = Calendar.getInstance();
		cal.set(yy,MM-1,dd);
		Date d = new Date(cal.getTimeInMillis());
		return d;
	}
	private Date returnDate(String Str) {
		if(Str==null) {return null;}
		String arr[] = Str.split("-");
		Date d = returnDate(
			Integer.parseInt(arr[0]),
			Integer.parseInt(arr[1]),
			Integer.parseInt(arr[2])
		);
		return d;
	}
}
