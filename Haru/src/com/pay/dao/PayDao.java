package com.pay.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.classdate.dto.ClassDateDto;
import com.pay.biz.PayBiz;
import com.pay.dto.PayDto;
import static common.JDBCTemplate.*;
/**
 * Pay 테이블에 CRUD 하는 클래스입니다. PayBiz에서 사용하는 목적으로 실제 사용시 PayBiz에서 사용하세요. (생성자로 생성 불가)
 * @author SJ
 *
 */
public final class PayDao { //상속 불가
	// CRUD
	private PayDao() {} //생성 불가
	/**
	 * 생성자를 통한 생성을 막는 대신 PayBiz에서 PayDao를 가져오기 위한 메소드
	 * @param biz PayDao가 필요한 PayBiz를 넘긴다.
	 * @see PayBiz#setPayDao(PayDao)
	 */
	public static void getInstance(PayBiz biz) {
		biz.setPayDao(new PayDao());
	}
	/**
	 * 모든 결제내역을 List에 담아 리턴합니다.
	 * @return 모든 결제내역의 List
	 */
	public List<PayDto> selectAll() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<PayDto> res = new ArrayList<PayDto>();
		String sql = "SELECT * FROM TB_PAY";
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				PayDto dto = new PayDto(
					rs.getString("id"),
					rs.getInt("classpk"),
					rs.getInt("classdatepk"),
					rs.getLong("pay_uid"),
					rs.getString("pay_name"),
					rs.getString("pay_method"),
					rs.getInt("pay_price"),
					rs.getString("pay_true"),
					rs.getDate("pay_time")
				);
				res.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs,pstm,con);
		}
		return res;
	}
	/**
	 * classpk (클래스)를 조건으로 결제내역을 검색합니다.
	 * @param classpk 클래스 기본키 (classpk)
	 * @return List 클래스를 조건으로 일치하는 결제내역 리스트
	 */
	public List<PayDto> selectClasspk(int classpk) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<PayDto> res = new ArrayList<PayDto>();
		String sql = "SELECT * FROM TB_PAY WHERE classpk=?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, classpk);
			rs = pstm.executeQuery();
			while ( rs.next() ) {
				PayDto dto = new PayDto(
						rs.getString("id"),
						rs.getInt("classpk"),
						rs.getInt("classdatepk"),
						rs.getLong("pay_uid"),
						rs.getString("pay_name"),
						rs.getString("pay_method"),
						rs.getInt("pay_price"),
						rs.getString("pay_true"),
						rs.getDate("pay_time")
					);
				res.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs,pstm,con);
		}
		return res;
	}
	/**
	 * id (멤버 아이디)를 조건으로 결제내역을 검색합니다.
	 * @param Memberid 클래스 기본키 (id)
	 * @return List 멤버 아이디를 조건으로 일치하는 결제내역 리스트
	 */
	public List<PayDto> selectMemberid(String Memberid) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<PayDto> res = new ArrayList<PayDto>();
		String sql = "SELECT * FROM TB_PAY WHERE id=?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, Memberid);
			rs = pstm.executeQuery();
			while ( rs.next() ) {
				PayDto dto = new PayDto(
						rs.getString("id"),
						rs.getInt("classpk"),
						rs.getInt("classdatepk"),
						rs.getLong("pay_uid"),
						rs.getString("pay_name"),
						rs.getString("pay_method"),
						rs.getInt("pay_price"),
						rs.getString("pay_true"),
						rs.getDate("pay_time")
					);
				res.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs,pstm,con);
		}
		return res;
	}
	/**
	 * 거래 고유 번호를 조건으로 결제정보을 검색합니다.
	 * @param pay_uid 거래 고유 번호
	 * @return 일치하는 결제정보
	 */
	public PayDto selectOne(long pay_uid) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		PayDto res = null;
		String sql = "SELECT * FROM TB_PAY WHERE pay_uid=?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, pay_uid);
			rs = pstm.executeQuery();
			while ( rs.next() ) {
				res = new PayDto(
						rs.getString("id"),
						rs.getInt("classpk"),
						rs.getInt("classdatepk"),
						rs.getLong("pay_uid"),
						rs.getString("pay_name"),
						rs.getString("pay_method"),
						rs.getInt("pay_price"),
						rs.getString("pay_true"),
						new Date(rs.getTimestamp("pay_time").getTime())
					);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs,pstm,con);
		}
		return res;
	}
	
	public ClassDateDto selectClassDate(int classdate) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ClassDateDto res = null;
		String sql = "SELECT * FROM TB_CLASSDATE WHERE classdatepk=?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, classdate);
			rs = pstm.executeQuery();
			while ( rs.next() ) {
				res = new ClassDateDto();
				res.setClassdatepk(rs.getInt("classdatepk"));
				res.setClasspk(rs.getInt("classpk"));
				res.setClassday(rs.getDate("classday"));
				res.setStr_time(rs.getDate("str_time"));
				res.setEnd_time(rs.getDate("end_time"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs,pstm,con);
		}
		return res;
	}
	
	/**
	 * 테이블에 결제정보를 추가합니다. PayBiz에서 넘겨준 dto로 작업합니다.
	 * @param dto Pay 정보가 담긴 dto
	 * @return 0: 실패/1: 성공
	 */
	public int insert(PayDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "INSERT INTO TB_PAY VALUES(?,?,?,?,?,?,?,?,?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, dto.getPay_uid());
			pstm.setString(2, dto.getId());
			pstm.setInt(3, dto.getClasspk());
			pstm.setInt(4, dto.getClassdatepk());
			pstm.setString(5, dto.getPay_name());
			pstm.setString(6, dto.getPay_method());
			pstm.setInt(7, dto.getPay_price());
			pstm.setString(8, dto.getPay_true());
			pstm.setDate(9, dto.getPay_time());
			res = pstm.executeUpdate();
			
			if(res>0) {commit(con);}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm,con);
		}
		return res;
	}
	public int newinsert(PayDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "INSERT INTO TB_PAY VALUES(?,?,?,?,?,?,?,'N',SYSDATE)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, dto.getPay_uid());
			pstm.setString(2, dto.getId());
			pstm.setInt(3, dto.getClasspk());
			pstm.setInt(4, dto.getClassdatepk());
			pstm.setString(5, dto.getPay_name());
			pstm.setString(6, dto.getPay_method());
			pstm.setInt(7, dto.getPay_price());
			res = pstm.executeUpdate();
			
			if(res>0) {commit(con);}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm,con);
		}
		return res;
	}
	/**
	 * 테이블에 결제정보를 삭제합니다. PayBiz에서 넘겨준 결제고유번호로 작업합니다. cancel 작업도 같이 하기 때문에 Biz에서 호출합니다.
	 * @param pay_uid 결제고유번호
	 * @return 0: 실패 또는 없음 / 1: 성공
	 */
	public int delete(long pay_uid) {
		System.out.println("pay_uid : "+pay_uid);
		Connection con = getConnection();
		PreparedStatement pstm = null;
		String sql = "DELETE FROM TB_PAY WHERE pay_uid=?";
		System.out.println("Query 준비");
		int res = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, pay_uid);
			res = pstm.executeUpdate();
			if(res>0) {commit(con);}
			System.out.println("Query 실행");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm,con);
		}
		return res;
	}
	/**
	 * 테이블에 결제정보를 수정합니다. PayBiz에서 넘겨준 정보로 작업합니다.
	 * @param dto 결제정보가 담긴 DTO (결제수단, 성공여부, 시간)
	 * @return 0: 실패 또는 없음/ 1: 성공
	 */
	public int update(PayDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		String sql = "UPDATE TB_PAY SET PAY_METHOD=?, PAY_TRUE=?, PAY_TIME=? WHERE pay_uid=?";
		int res = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getPay_method());
			pstm.setString(2, dto.getPay_true());
			pstm.setDate(3, dto.getPay_time());
			pstm.setLong(4, dto.getPay_uid());
			res = pstm.executeUpdate();
			if(res>0) {commit(con);}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm,con);
		}
		return res;
	}
	public int update(long pay_uid,boolean update) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		String sql = "UPDATE TB_PAY SET PAY_TRUE=?, pay_time=SYSDATE WHERE pay_uid=?";
		int res = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, (update?"Y":"N"));
			pstm.setLong(2, pay_uid);
			res = pstm.executeUpdate();
			if(res>0) {commit(con);}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm,con);
		}
		return res;
	}
}
