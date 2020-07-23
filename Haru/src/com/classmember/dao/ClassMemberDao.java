package com.classmember.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static common.JDBCTemplate.*;
import com.classmember.dto.ClassMemberDto;
import com.member.dto.MemberDto;
/**
 * (미완성)
 * @author SJ
 */
public class ClassMemberDao {
	
	public List<MemberDto> selectStudent(int classpk) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MemberDto> res = new ArrayList<MemberDto>();
		String sql = "SELECT m.name,m.phone,m.gender,m.nickname  FROM tb_class_member c ,tb_member m where c.id = m.id and c.classpk=? order by m.name";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, classpk);
			rs = pstm.executeQuery();
			while(rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setName(rs.getString("NAME"));
				dto.setPhone(rs.getString("PHONE"));
				dto.setGender(rs.getString("GENDER"));
				dto.setNickname(rs.getString("NICKNAME"));
				res.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm,con);
		}
		return res;
	}
	public ClassMemberDto selectOne(String memberid,int classpk) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ClassMemberDto res = null;
		String sql = "SELECT * FROM TB_CLASS_MEMBER WHERE ID=? AND CLASSPK=?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, memberid);
			pstm.setInt(2, classpk);
			rs = pstm.executeQuery();
			if (rs.next()) {
				res = new ClassMemberDto();
				res.setId(rs.getString("id"));
				res.setClasspk(rs.getInt("classpk"));
				res.setChk_class(rs.getString("chk_class"));
				res.setChk_wish(rs.getString("chk_wish"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm,con);
		}
		return res;
	}
	public int insert(ClassMemberDto memberdto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "INSERT INTO tb_class_member VALUES(?,?,?,?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, memberdto.getId());
			pstm.setInt(2, memberdto.getClasspk());
			pstm.setString(3, memberdto.getChk_class());
			pstm.setString(4, memberdto.getChk_wish());
			res = pstm.executeUpdate();
			if(res>0) {
				commit(con);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(pstm,con);
		}
		return res;
	}
	public int update(ClassMemberDto memberdto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "UPDATE tb_class_member SET chk_class=?, chk_wish=?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, memberdto.getChk_class());
			pstm.setString(2, memberdto.getChk_wish());
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
	public int delete(String memberid,int classpk) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "DELETE FROM tb_class_member WHERE id=?, classpk=?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, memberid);
			pstm.setInt(2, classpk);
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
	public int delete(String memberid) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "DELETE FROM tb_class_member WHERE id=?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, memberid);
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
	
	
	public int autoSet(String memberid,int classpk,int type,boolean b) {
		int res = 0;	String Str_mem,Str_wi;
		ClassMemberDto dto = selectOne(memberid, classpk);
		if(dto==null) {
			Str_mem=(type==1)?(b)?"Y":"N":"N";
			Str_wi=(type==2)?(b)?"Y":"N":"N";
			if(isFalse(Str_mem,Str_wi))
			{return 1;}
			res = insert(new ClassMemberDto(memberid,classpk,Str_mem,Str_wi));
		}else {
			Str_mem=(type==1)?(b)?"Y":"N":dto.getChk_class();
			Str_wi=(type==2)?(b)?"Y":"N":dto.getChk_wish();
			if(isFalse(Str_mem,Str_wi))
			{return delete(dto.getId(), dto.getClasspk());}
			res = update(new ClassMemberDto(memberid,classpk,Str_mem,Str_wi));
		}
		return res;
	}
	public boolean isFalse(String...f) {
		if(f==null) return false;
		for (int i=0;i<f.length;i++) {
			if(f[i]==null) return false;
			if(!f[i].equals("N")) return false;
		}
		return true;
	}
}
