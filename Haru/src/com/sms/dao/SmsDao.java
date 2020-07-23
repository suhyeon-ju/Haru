package com.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.sms.dto.SmsDto;
import static common.JDBCTemplate.*;

/**
 * Sms 테이블에 CRUD 하기 위한 클래스 입니다. (UPDATE 필요 없어서 없습니다) 새 인증 요청시 기존 정보 삭제 후 재등록
 * @author SJ
 */
public class SmsDao {
	/** 기본 생성자 */
	public SmsDao() {super();}

	/**
	 * 모든 인증정보를 요청합니다.
	 * @return List 모든 인증정보를 리턴
	 */
	public List<SmsDto> selectAll() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<SmsDto> res = new ArrayList<SmsDto>();
		String sql = "SELECT * FROM TB_SMS";
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				SmsDto dto = new SmsDto(
					rs.getString(1),
					rs.getInt(2)
				);
				res.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs,pstm,con);
		}
		return res;
	}
	/**
	 * phone으로 인증정보를 요청합니다.
	 * @param phone 전화번호
	 * @return SmsDto 인증정보
	 */
	public SmsDto selectOne(String phone) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		SmsDto res = null;
		String sql = "SELECT * FROM TB_SMS WHERE phone=?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, phone);
			rs = pstm.executeQuery();
			while(rs.next()) {
				res = new SmsDto(
					rs.getString(1),
					rs.getInt(2)
				);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs,pstm,con);
		}
		return res;
	}
	/**
	 * 인증 정보를 추가합니다.
	 * @param dto 전화번호/인증번호가 담긴 DTO
	 * @return 0:실패 / 1:성공
	 */
	public int insert(SmsDto dto) {
		int del = delete(dto.getPhone()); //새 인증 생성 전 혹시 모를 인증정보 삭제
		if (dto.getPhone()==null) {return 0;}
		if (dto.getPhone().equals("")) {return 0;}
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "INSERT INTO TB_SMS VALUES(?, ?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getPhone());
			pstm.setInt(2, dto.getRankey());
			res = pstm.executeUpdate();
			if(res>0) {
				commit(con);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm,con);
		}
		return res;
	}
	/**
	 * 인증정보를 삭제합니다.
	 * @param phone 전화번호/인증번호가 담긴 DTO
	 * @return 0:없거나 실패/ 1:성공
	 */
	public int delete(String phone) {
		if(phone==null) {return 0;}
		if(phone.equals("")) {return 0;}
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "DELETE FROM TB_SMS WHERE PHONE=?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1,phone);
			res = pstm.executeUpdate();
			
			if(res>0) {
				commit(con);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm,con);
		}
		return res;
	}
	/**
	 * 인증정보를 수정합니다. 하지만 insert 발생시 기존 인증 기록을 delete 하기 때문에 사용할 필요 없습니다.
	 * @param dto 전화번호/인증번호가 담긴 DTO
	 * @return 0:없거나 실패/ 1:성공
	 */
	public int update(SmsDto dto) {
		if (dto.getPhone()!=null) {return 0;}
		if (dto.getPhone().equals("")) {return 0;}
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "UPDATE TB_SMS SET RANKEY=? WHERE PHONE=?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, dto.getRankey());
			pstm.setString(2,dto.getPhone());
			res = pstm.executeUpdate();
			
			if(res>0) {
				commit(con);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm,con);
		}
		return res;
	}
}
