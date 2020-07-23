package com.board.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import com.board.dto.BoardDto;

import static common.JDBCTemplate.*;

public class BoardDao {
	
	public BoardDao() {
		super();
	}
	
	public List<BoardDto> selectAll(){
		Connection con = getConnection();
		PreparedStatement pstm =null;
		ResultSet rs = null;
		List<BoardDto> res = new ArrayList<BoardDto>();
		String sql = "SELECT * FROM TB_BOARD ORDER BY BBS_NO DESC";
		
		try {
			pstm=con.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()) {
				BoardDto db = new BoardDto(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5));
				res.add(db);
				System.out.println(db);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}		
		return res;		
	}
	
	public BoardDto selectOne(int bbs_no) {
		Connection con =getConnection();
		PreparedStatement pstm =null;
		ResultSet rs = null;
		BoardDto res = null;
	    String sql ="SELECT * FROM TB_BOARD WHERE BBS_NO=?";
	    
	    try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, bbs_no);
			rs=pstm.executeQuery();
			while(rs.next()) {
				res= new BoardDto(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}	       
		return res;
	}
	
	public int insert(BoardDto dto) {
		Connection con =getConnection();
		PreparedStatement pstm =null;
		String sql="INSERT INTO TB_BOARD VALUES(BBS_NO_SEQ.NEXTVAL,?,?,?,SYSDATE)";
		int res =0;
		System.out.println(dto.getId() + " "+dto.getTitle()+" "+dto.getContent());
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, dto.getId());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			res=pstm.executeUpdate();
			
			if (res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}

		return res;
	}
	
	public int update(BoardDto dto) {
		Connection con =getConnection();
		PreparedStatement pstm =null;
		String sql="UPDATE TB_BOARD SET TITLE=?,CONTENT=? WHERE BBS_NO=?";
		System.out.println(dto.getTitle()+dto.getBbs_no()+dto.getContent());
		int res =0;
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getBbs_no());
			res=pstm.executeUpdate();
			
			if (res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}

		return res;	
	}
	
	public int delete(int bbs_no) {
		Connection con= getConnection();
		PreparedStatement pstm =null;
		String sql ="DELETE FROM TB_BOARD WHERE BBS_NO=?";
		int res=0;
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, bbs_no);
			res=pstm.executeUpdate();
			
			if (res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		return res;		
	}

	public int getTotalArticle() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		String sql ="SELECT COUNT(*) FROM TB_BOARD ";
		
		try {
			pstm= con.prepareStatement(sql);
			rs=pstm.executeQuery();
			if(rs.next()) {
				res=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs,pstm,con);
		}
		
		return res;
	}

	public List<BoardDto> getArticleList(int startRow, int endRow) {
		Connection con = getConnection();
		PreparedStatement pstm =null;
		ResultSet rs = null;
		List<BoardDto> res = new ArrayList<BoardDto>();
		String sql ="SELECT * FROM(SELECT TB.*,ROWNUM RN FROM(SELECT * FROM TB_BOARD ORDER BY BBS_NO DESC)TB) WHERE RN BETWEEN ? AND ?";
		
		try {
			pstm= con.prepareStatement(sql);
			pstm.setInt(1,startRow);
			pstm.setInt(2, endRow);
			rs=pstm.executeQuery();
			while(rs.next()) {
				BoardDto dto =new BoardDto();
				dto.setBbs_no(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				res.add(dto);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs,pstm,con);
		}
		return res;
	}

}
