package common;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.classinfo.dao.ClassInfoDao;
import com.classinfo.dto.ClassInfoDto;
import com.classmember.dao.ClassMemberDao;
import com.controller.sign.Signcontroller;
import com.pay.biz.PayBiz;
import com.pay.biz.Pay_REST;
import com.sms.dto.SmsDto;
import com.sms.send.SendSMS;
import com.util.UtilTemp;

/**
 * 자바 테스트용 클래스 파일
 */
public class TestClass {
	public static void main(String[] args) {
//		System.out.println(new SendSMS(new SmsDto("01000000000")).sendSMS());
//		System.out.println(new SendSMS(new SmsDto("01000000000",888888)).chkSMS());
//		new PayBiz().cancelPay(1L);
//		System.out.println(0/1);
//		ClassInfoDao dao = new ClassInfoDao();
//		ClassInfoDto dto = dao.selectOne(6);
//		System.out.println(dto.getRank());
//		System.out.println(dto.getRankcnt());
//		System.out.println((int)(dto.getRank()/dto.getRankcnt()));
		System.out.println(new ClassInfoDao().updaterank(6));
	}
}

