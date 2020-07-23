package com.pay.biz;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.classdate.dto.ClassDateDto;
import com.classinfo.dao.ClassInfoDao;
import com.classinfo.dto.ClassInfoDto;
import com.classmember.dao.ClassMemberDao;
import com.classmember.dto.ClassMemberDto;
import com.member.dto.MemberDto;
import com.pay.dao.PayDao;
import com.pay.dto.PayDto;

/**
 * 미완성
 * 
 * @author SJ
 *
 */
public class PayBiz {
	/*
	 * pay_uid NUMBER, id VARCHAR2(100) NOT NULL, classpk NUMBER NOT NULL, pay_name
	 * VARCHAR2(100) NOT NULL, pay_method VARCHAR2(100) DEFAULT 'none' NOT NULL,
	 * pay_price number NOT NULL, pay_true VARCHAR2(100) DEFAULT 'N' NOT NULL,
	 * pay_time DATE,
	 */
	private PayDao dao = null;
	private long pay_uid = -1;
	private String Memberid = null;
	private int classpk = -1;
	private int classdatepk = -1;

	/**
	 * 기본 생성자 (동시에 PayDao도 생성)
	 */
	public PayBiz() {
		PayDao.getInstance(this);
	}

	public PayBiz(long uid) {
		this();
		this.pay_uid = uid;
	}

	/**
	 * PayDao가 자신을 넘겨줄 목적으로만 사용하는 메소드
	 * 
	 * @param dao PayDao가 Biz에게 자신을 넘겨줍니다.
	 */
	public void setPayDao(PayDao dao) {
		this.dao = dao;
	}

	/**
	 * 새로운 결제 정보를 추가합니다. Dao를 호출
	 * 
	 * @return 자신을 리턴합니다.
	 * @see PayDao#insert(com.pay.dto.PayDto)
	 */
	public PayBiz newPay() {
		return this;
	}

	public PayBiz newPay(MemberDto memberdto, int datepk, String method) {
		ClassDateDto temp = null;
		ClassInfoDto temp2 = null;
		if (memberdto == null) {
			System.out.println("NOT MEMBER");
			return null;
		}
		if (method == null) {
			System.out.println("NOT METHOD");
			return null;
		}
		this.pay_uid = randomkey();
		this.Memberid = memberdto.getId();
		this.classdatepk = datepk;
		temp = dao.selectClassDate(classdatepk);
		if (temp == null) {
			System.out.println("NOT CLASSDATE");
			return null;
		} // !!!
		this.classpk = temp.getClasspk();
		temp2 = new ClassInfoDao().selectOne(classpk);
		PayDto dto = new PayDto();
		dto.setPay_uid(pay_uid);
		dto.setId(Memberid);
		dto.setClasspk(classpk);
		dto.setClassdatepk(classdatepk);
		dto.setPay_method(method);
		dto.setPay_name("HARU:" + temp2.getTitle());
		dto.setPay_price(temp2.getPrice());
		int res = dao.newinsert(dto);
		System.out.println(res);
		System.out.println(dto);
		return this;
	}

	public boolean chkPay() {
		if (pay_uid == -1L) {
			return false;
		}
		Pay_REST rest = new Pay_REST();
		String[] apires = rest.parse_StatusArr(pay_uid);
		if (apires == null) {
			return false;
		}
		if (apires.length != 3) {
			return false;
		}
		if (!apires[2].equals("paid")) {
			return false;
		}
		System.out.println(apires[2]);
		PayDto dto = selectOne(pay_uid);
		if (dto == null) {
			return false;
		}
		if (!apires[0].equals(Long.toString(dto.getPay_price()))) {
			return false;
		}
		dao.update(pay_uid, true);
		ClassMemberDao cmember = new ClassMemberDao();
		// MemberDto member = (MemberDto) session.getAttribute("member");
		cmember.autoSet(dto.getId(), dto.getClasspk(), 1, true);
		return true;
	}

	public int update(long pay_uid, boolean update) {
		return dao.update(pay_uid, update);
	}

	public PayDto selectOne(long pay_uid) {
		return dao.selectOne(pay_uid);
	}

	public int cancelPay(long pay_uid) {
		Pay_REST rest = new Pay_REST();
		PayDto dto = selectOne(pay_uid);
		String status = rest.parse_cancel(pay_uid);
		System.out.println("결제번호[" + pay_uid + "]" + rest.Status_con(status));
		if (status.equals("cancelled")) {
			int r = update(pay_uid, false);
			r += new ClassMemberDao().autoSet(dto.getId(), dto.getClassdatepk(), 1, false);
			if (r == 2) {
				return 1;
			}
		}
		return 0;
	}

	public int calcelClassAllPay(int classpk) {
		int res = 0, tmp = 0;
		Pay_REST rest = new Pay_REST();
		List<PayDto> list = dao.selectClasspk(classpk);
		for (PayDto dto : list) {
			System.out.println("dto : " + dto);
			cancelPay(dto.getPay_uid());
			tmp += dao.delete(dto.getPay_uid());
		}
		if (tmp == list.size()) {
			res = 1;
		}

		return res;
	}

	public int calcelMemberAllPay(String id) {
		int res = 0, tmp = 0;
		Pay_REST rest = new Pay_REST();
		List<PayDto> list = dao.selectMemberid(id);
		for (PayDto dto : list) {
			System.out.println("dto : " + dto);
			tmp++;
			cancelPay(dto.getPay_uid());
			tmp += dao.delete(dto.getPay_uid());
		}
		if (tmp == list.size()) {
			res = 1;
		}
		return res;
	}

	private long randomkey() {
		long random = 0;
		PayDto dto = null;
		do {
			random = (long) (Math.random() * 9 + 1);
			for (int i = 0; i < 12; i++) {
				random *= 10;
				random += (long) (Math.random() * 10);
			}
			dto = dao.selectOne(random);
		} while (dto != null);
		return random;
	}

	private boolean nullcheck(String Str) {
		if (Str == null) {
			return true;
		}
		if (Str.equals("")) {
			return true;
		}
		return false;
	}

	public long getPay_uid() {
		return pay_uid;
	}

	public void setPay_uid(long pay_uid) {
		this.pay_uid = pay_uid;
	}

	public String getMemberid() {
		return Memberid;
	}

	public void setMemberid(String memberid) {
		Memberid = memberid;
	}

	public int getClasspk() {
		return classpk;
	}

	public void setClasspk(int classpk) {
		this.classpk = classpk;
	}

	public int getClassdatepk() {
		return classdatepk;
	}

	public void setClassdatepk(int classdatepk) {
		this.classdatepk = classdatepk;
	}

}