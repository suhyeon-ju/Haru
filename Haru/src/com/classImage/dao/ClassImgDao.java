package com.classImage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.classImage.dto.ClassImgDto;
import static common.JDBCTemplate.*;

/**
 * 이미지 정보 테이블에 CRUD 하기 위한 클래스
 * 
 * @author BH
 */
public class ClassImgDao {

	/**
	 * classimage 테이블에 이미지 정보를 추가합니다.
	 * 
	 * @param dto classImgDto 클래스 이미지 정보
	 */
	public int inSertImageData(ClassImgDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int ress = 0;
		String sql = "INSERT INTO tb_class_image values(class_image_seq.NEXTVAL,?,?,?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, dto.getClasspk());
			pstm.setString(2, dto.getImage_name());
			pstm.setString(3, dto.getImage_url());
			ress = pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		return ress;
	}
	public int updateImageData(ClassImgDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int ress = 0;
		String sql = "UPDATE tb_class_image SET IMAGE_NAME=?, IMAGE_URL=? WHERE BANNER_NO=? ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getImage_name());
			pstm.setString(2, dto.getImage_url());
			pstm.setInt(3, dto.getBanner_no());
			ress = pstm.executeUpdate();
			if(ress>0) {
				con.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		return ress;
	}
	public int deleteImageData(int classpk) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int ress = 0;
		String sql = "DELETE FROM tb_class_image WHERE CLASSPK=? ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, classpk);
			ress = pstm.executeUpdate();
			System.out.println("bannerimage delete 돌았다");
			if(ress>0) {
				con.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		return ress;
	}
	/**
	 * classimage 테이블에 모든 이미지 정보를 가져옵니다 (SELECT ALL)
	 * 
	 * @return 모든 classImgDto를 리스트에 담아 리턴합니다.
	 */
	public List<ClassImgDto> getList() {
		Connection con = getConnection();
		List<ClassImgDto> res = new ArrayList<ClassImgDto>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT image_url FROM tb_class_image WHERE CLASSPK=?";

		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				ClassImgDto dto = new ClassImgDto();
				dto.setBanner_no(rs.getInt(1));
				dto.setClasspk(rs.getInt(2));
				dto.setImage_name(rs.getString(3));
				dto.setImage_url(rs.getString(4));
				res.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * classimage 테이블의 기본키 (banner_no)를 사용해 이미지 정보를 가져옵니다.
	 * 
	 * @param num 테이블의 기본키 (banner_no)
	 * @return banner_no와 일치하는 이미지 정보를 가져옵니다.
	 */
	public ClassImgDto getreadData(int num) {
		ClassImgDto dto = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Connection con = getConnection();
		String sql = "select classpk, image_name, image_url where banner_no = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, num);
			rs = pstm.executeQuery();
			if (rs.next()) {
				dto = new ClassImgDto();
				dto.setBanner_no(rs.getInt(1));
				dto.setClasspk(rs.getInt(2));
				dto.setImage_name(rs.getString(3));
				dto.setImage_url(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		return dto;
	}

	/**
	 * classimage 테이블의 기본키 (banner_no)를 사용해 이미지 정보를 삭제합니다.
	 * 
	 * @param num 테이블의 기본키 (banner_no)
	 * @return 0: 없거나 실패/ 1: 성공
	 */
	public int deleteData(int num) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		String sql = "delete tb_class_image where banner_no = ?";
		int res = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, num);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		return res;
	}

	public List<ClassImgDto> getList(int classpk) {
		Connection con = getConnection();
		List<ClassImgDto> res = new ArrayList<ClassImgDto>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM tb_class_image WHERE CLASSPK =?";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, classpk);
			rs = pstm.executeQuery();

			while (rs.next()) {
				ClassImgDto dto = new ClassImgDto();
				dto.setBanner_no(rs.getInt(1));
				dto.setClasspk(rs.getInt(2));
				dto.setImage_name(rs.getString(3));
				dto.setImage_url(rs.getString(4));
				res.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public int makeclasspk() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		String sql = "SELECT classpk_seq.NEXTVAL FROM DUAL";
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				res=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(res);
		return res;
	}
	public List<ClassImgDto> selectImg(int classpk) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ClassImgDto> res = new ArrayList<ClassImgDto>();
		String sql = "SELECT * FROM tb_class_image WHERE CLASSPK =?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, classpk);
			rs=pstm.executeQuery();
			while(rs.next()) {
				ClassImgDto temp = new ClassImgDto();
				temp.setBanner_no(rs.getInt(1));
				temp.setClasspk(rs.getInt(2));
				temp.setImage_name(rs.getString(3));
				temp.setImage_url(rs.getString(4));
				res.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
