package com.classreview.dao;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.classreview.dto.ClassReviewDto;
/**
 * 클래스 리뷰 테이블에 CRUD 하기 위한 클래스
 * @author GY
 *
 */
public class ClassReviewDao {
	/**
	 * 모든 클래스 리뷰를 조회
	 * @return 모든 클래스 리뷰 List
	 */
	public List<ClassReviewDto> selectAll(int classpk) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ClassReviewDto> res = new ArrayList<>();
		String sql = "SELECT * FROM tb_class_review WHERE CLASSPK = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, classpk);
			rs = pstm.executeQuery();
			while (rs.next()) {
				ClassReviewDto dto = new ClassReviewDto();
				dto.setCmdpk(rs.getInt(1));
				dto.setContent(rs.getString(2));
				dto.setStrp(rs.getInt(3));
				dto.setId(rs.getString(4));
				dto.setClasspk(rs.getInt(5));
				dto.setRegdate(rs.getDate(6));
				res.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	/**
	 * classpk (클래스 고유키) 로 리뷰 리스트를 요청합니다.
	 * @param pk 클래스 고유키 classpk
	 * @return 클래스의 리뷰 리스트
	 */
	public List<ClassReviewDto> selectOne(int pk) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM tb_class_review WHERE CLASSPK = ? ";
		List<ClassReviewDto> res = new ArrayList<>();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, pk);
			rs = pstm.executeQuery();
			while (rs.next()) {
				ClassReviewDto dto = new ClassReviewDto();
				dto.setContent(rs.getString(2));
				dto.setStrp(rs.getInt(3));
				dto.setId(rs.getString(4));
				dto.setClasspk(rs.getInt(5));
				dto.setRegdate(rs.getDate(6));
				res.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
/**
 * 클래스를 기준으로? 리뷰 정보를 삭제
 * @param pk 클래스 고유키
 * @return 성공여부 0:실패 /1:성공
 */
	public int deleteClass(int pk) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "DELETE FROM tb_class_review WHERE CLASSPK = ? ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, pk);
			res = pstm.executeUpdate();
			if (res > 0) {
				con.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	/**
	 * 클래스 리뷰 고유키로 리뷰를 삭제
	 * @param cmdpk 클래스리뷰 고유키
	 * @return 성공여부 0:실패 /1:성공
	 */
	public int delete(int cmdpk) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "DELETE FROM tb_class_review WHERE CMDPK=?";
		return res;
	}
	/**
	 * 클래스 리뷰 고유키로 리뷰를 수정
	 * @param dto 클래스 리뷰 정보 DTO
	 * @return 성공여부 0:실패 /1:성공
	 */
	public int update(ClassReviewDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		String sql = "UPDATE tb_class_review SET CONTENT = ?, STRP = ? WHERE CMDPK = ?";
		int res = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getContent());
			pstm.setInt(2, dto.getStrp());
			pstm.setInt(3, dto.getCmdpk());
			res = pstm.executeUpdate();
			if (res > 0) {
				con.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	/**
	 * 클래스 리뷰를 추가
	 * @param dto 클래스 리뷰 정보가 담긴 DTO
	 * @return 성공여부 0:실패 /1:성공
	 */
	public int insert(ClassReviewDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		String sql = "INSERT INTO tb_class_review VALUES(review_cmdpk.NEXTVAL,?,?,?,?,SYSDATE)";
		int res = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getContent());
			pstm.setInt(2, dto.getStrp());
			pstm.setString(3, dto.getId());
			pstm.setInt(4, dto.getClasspk());
			res = pstm.executeUpdate();
			if (res > 0) {
				con.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	public List<ClassReviewDto> mainselectAll() {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ClassReviewDto> res = new ArrayList<>();
		String sql = "SELECT * FROM (SELECT r.cmdpk, r.content, r.strp, r.id, classpk, r.regdate, b.pubimg FROM tb_class_review r join tb_classinfo b using(classpk) order by cmdpk desc) WHERE ROWNUM <=5";
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				ClassReviewDto dto = new ClassReviewDto();
				dto.setCmdpk(rs.getInt(1));
				dto.setContent(rs.getString(2));
				dto.setStrp(rs.getInt(3));
				dto.setId(rs.getString(4));
				dto.setClasspk(rs.getInt(5));
				dto.setRegdate(rs.getDate(6));
				dto.setPubimg(rs.getString(7));
				res.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
}