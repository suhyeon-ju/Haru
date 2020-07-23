package com.classdate.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import static common.JDBCTemplate.*;

import com.classdate.dto.ClassDateDto;

public class ClassDateDao {
	public List<ClassDateDto> selectAll() {
		String sql = "SELECT * FROM tb_classdate";
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ClassDateDto> list = new ArrayList<ClassDateDto>();
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				ClassDateDto dto = new ClassDateDto();
				
				list.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs,pstm,con);
		}
	return list;
	}
	
	public List<ClassDateDto> selectAll(int classpk){
		String sql = "SELECT * FROM tb_classdate WHERE classpk=?";
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ClassDateDto> list = new ArrayList<ClassDateDto>();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, classpk);
			rs = pstm.executeQuery();
			while(rs.next()) {
				ClassDateDto dto = new ClassDateDto();
				dto.setClassday(rs.getDate("classday"));
				dto.setStr_time(rs.getDate("str_time"));
				dto.setEnd_time(rs.getDate("end_time"));
				list.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs,pstm,con);
		}
	return list;
	}
	
	public List<ClassDateDto> select_paypage(int classpk){
		String sql = "SELECT * FROM tb_classdate WHERE classpk=?";
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ClassDateDto> list = new ArrayList<ClassDateDto>();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, classpk);
			rs = pstm.executeQuery();
			while(rs.next()) {
				ClassDateDto dto = new ClassDateDto();
				dto.setClassdatepk(rs.getInt("classdatepk"));
				dto.setClassday(rs.getDate("classday"));
				dto.setStr_time(new Date(rs.getTimestamp("str_time").getTime()));
				dto.setEnd_time(new Date(rs.getTimestamp("end_time").getTime()));
				list.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs,pstm,con);
		}
	return list;
	}

	public int insert_daytime(ClassDateDto dto) {
		String sql = "INSERT INTO tb_classdate VALUES(classdate_seq.NEXTVAL,?,?,?,?)";
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, dto.getClasspk());
			pstm.setDate(2, dto.getClassday());
			pstm.setTimestamp(3, new Timestamp(dto.getStr_time().getTime()));
			pstm.setTimestamp(4, new Timestamp(dto.getEnd_time().getTime()));
			res = pstm.executeUpdate();
			if (res > 0) {
				con.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}
		return res;
	}

	public void delete_daytime(int classpk) {
		String sql = "DELETE FROM tb_classdate WHERE CLASSPK=?";
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, classpk);
			res = pstm.executeUpdate();
			if (res > 0) {
				con.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}
	}

	public List<ClassDateDto> finddate(int classpk) {
		String sql = "SELECT * FROM tb_classdate WHERE CLASSPK = ?";
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ClassDateDto> res = new ArrayList<ClassDateDto>();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, classpk);
			rs = pstm.executeQuery();
			while (rs.next()) {
				ClassDateDto dto = new ClassDateDto();
				dto.setClassday(rs.getDate(3));
				dto.setStr_time(new Date(rs.getTimestamp(4).getTime()));
				dto.setEnd_time(new Date(rs.getTimestamp(5).getTime()));
				res.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
