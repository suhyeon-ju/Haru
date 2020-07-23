package com.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 편리
 * @author SJ
 *
 */
public class UtilTemp {
//성공 -2 실패 -3 배열의 모두 성공 여부
	public boolean BatchChk(int[] args) {
		int res=0;
		for (int i=0;i<args.length;i++) {
			if(args[i]==-2) {
				res++;
			}
		}
		return (res==args.length);
	}
	public String nullStr(String Str,String Default) {
		if(Str==null) {return Default;}
		if(Str.equals("")){return Default;}
		if(Str.trim().equals("")){return Default;}
		return Str;
	}
	public Date StringToDate(String str) {
		if(str==null) {return null;}
		String[] arr = str.split("-");
		int day[] = {
			Integer.parseInt(arr[0]),
			Integer.parseInt(arr[1]),
			Integer.parseInt(arr[2])
		};
		return IntToDate(day[0],day[1],day[2]);
	}
	public Date IntToDate(int yy,int MM,int dd) {
		Calendar cal = Calendar.getInstance();
		cal.set(yy, MM-1, dd);
		Date day = new Date(cal.getTimeInMillis());
		return day;
	}
	public Date IntToDate(int yy,int MM,int dd,int hour,int mm) {
		Calendar cal = Calendar.getInstance();
		cal.set(yy,MM-1,dd,hour,mm);
		Date day = new Date(cal.getTimeInMillis());
		return day;
	}
	public Date StringTimeToDate(String day,String time) {
		if(day==null) {return null;}
		if(time==null) {return null;}
		String[] dayArr = day.split("-");
		String[] timeArr = time.split(":");
		int[] table =  {
			Integer.parseInt(dayArr[0]),
			Integer.parseInt(dayArr[1]),
			Integer.parseInt(dayArr[2]),
			Integer.parseInt(timeArr[0]),
			Integer.parseInt(timeArr[1])
		};
		return IntToDate(table[0],table[1],table[2],table[3],table[4]);
	}
	public String DateToString(Date date,String format) {
		if(date==null) {return null;}
		if(format==null) {return null;}
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	public String toString(java.util.Date date) {
		return date.toString();
	}
	public String toString(java.sql.Date date) {
		return new java.util.Date(date.getTime()).toString();
	}
	public String CharToString(char ch) {
		return String.valueOf(ch);
	}
	public String CharToString(char ch[]) {
		return String.valueOf(ch);
	}
	public String IntToString(int num) {
		return Integer.toString(num);
	}
	public int StringToInt(String Str) {
		if(Str==null) {return -1;}
		return Integer.parseInt(Str);
	}
	public Date TimeStampToDate(Timestamp date) {
		Timestamp ts = date;
		Date res = new Date(ts.getTime());
		return res;
	}
	public boolean nulchk(String...arr) {
		boolean res = false;
		for (int i=0;i<arr.length;i++) {
			if(arr[i]==null) {
				return !res;
			}else if(arr[i].isEmpty()) {
				return !res;
			}else if(arr[i].trim().equals("")) {
				return !res;
			}
		}
		return res;
	}
	public boolean chk(String command,String text) {
		return command.equals(text);
	}
	public boolean isStringN(String...f) {
		if(f==null) return false;
		for (int i=0;i<f.length;i++) {
			if(f[i]==null) return false;
			if(!f[i].equals("N")) return false;
		}
		return true;
	}
}
