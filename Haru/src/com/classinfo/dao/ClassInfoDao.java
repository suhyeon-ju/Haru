package com.classinfo.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.classinfo.dto.ClassInfoDto;
import static common.JDBCTemplate.*;

/**
 * 클래스 정보 테이블에 CRUD 하기 위한 클래스 (미완성)
 */
public class ClassInfoDao {

	private String category;

	/**
	 * 기본 생성자
	 */
	public ClassInfoDao() {
		super();
	}

	/**
	 * 모든 클래스리스트를 요청합니다. 최신순
	 * 
	 * @return 모든 클래스 리스트 (리스트가 비어있으면 클래스 없음)
	 */
	public List<ClassInfoDto> selectAll() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ClassInfoDto> res = new ArrayList<ClassInfoDto>();
		String sql = "SELECT * FROM (SELECT * FROM tb_classinfo ORDER BY classpk DESC) WHERE ROWNUM <=9 ";
//		String sql = "SELECT * FROM tb_classinfo ORDER BY classpk DESC";
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				ClassInfoDto dto = new ClassInfoDto(rs.getInt("classpk"), rs.getString("id"), rs.getString("title"),
						rs.getString("content"), rs.getInt("price"), rs.getInt("allstudent"), rs.getString("loc"),
						rs.getString("category"), rs.getString("classtype"), rs.getString("keyword"), rs.getInt("rank"),
						rs.getInt("rankcnt"), rs.getString("pubImg"), rs.getString("cellurl"));
				res.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}
		return res;
	}

	public ClassInfoDto selectOne(int classpk) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ClassInfoDto res = null;
		String sql = "SELECT * FROM tb_classinfo WHERE classpk=? ORDER BY classpk DESC";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, classpk);
			rs = pstm.executeQuery();
			if (rs.next()) {
				res = new ClassInfoDto(rs.getInt("classpk"), rs.getString("id"), rs.getString("title"),
						rs.getString("content"), rs.getInt("price"), rs.getInt("allstudent"), rs.getString("loc"),
						rs.getString("category"), rs.getString("classtype"), rs.getString("keyword"), rs.getInt("rank"),
						rs.getInt("rankcnt"), rs.getString("pubImg"), rs.getString("cellurl"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}
		return res;
	}

	public List<ClassInfoDto> selectCategory(int key) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		List<ClassInfoDto> res = new ArrayList<>();

		String category = "";

		switch (key) {

		case 1:
			category = "popular";		

			break;
		case 2:
			category = "design";

			break;
		case 3:
			category = "music";

			break;
		case 4:
			category = "video";
			
			break;
		case 5:
			category = "language";
			break;
		case 6:
			category = "lifeStyle";
			
			break;
		case 7:
			category = "nearloc";
			break;
		default:
			category = "null";
			break;

		}
		String sql = "SELECT * FROM (SELECT * FROM TB_CLASSINFO WHERE category LIKE ?) WHERE ROWNUM <=12";
		// String sql ="SELECT * FROM (SELECT * FROM TB_CLASSINFO WHERE category LIKE ?)
		// WHERE ROWNUM <=12";
//		String sql = "SELECT * FROM TB_ClASSINFO WHERE category = ? ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, category + "%");
			rs = pstm.executeQuery();
			while (rs.next()) {

				ClassInfoDto dto = new ClassInfoDto();
				dto.setClasspk(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPrice(rs.getInt(5));
				dto.setAllstudent(rs.getInt(6));
				dto.setLoc(rs.getString(7));
				dto.setCategory(rs.getString(8));
				dto.setClasstype(rs.getString(9));
				dto.setKeyword(rs.getString(10));
				dto.setRank(rs.getInt(11));
				dto.setRankcnt(rs.getInt(12));
				dto.setPubImg(rs.getString(13));
				dto.setCellurl(rs.getString(14));

				res.add(dto);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}

		return res;
	}

	public List<ClassInfoDto> selectOne(String keyword) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ClassInfoDto> res = new ArrayList<>();
		String sql = "SELECT * FROM tb_CLASSINFO WHERE UPPER(TITLE) LIKE" + " UPPER('%" + keyword + "%')"
				+ "OR CONTENT LIKE" + "'%" + keyword + "%'" + "OR KEYWORD LIKE" + "'%" + keyword + "%'";
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				ClassInfoDto dto = new ClassInfoDto();
				dto.setClasspk(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPrice(rs.getInt(5));
				dto.setAllstudent(rs.getInt(6));
				dto.setLoc(rs.getString(7));
				dto.setCategory(rs.getString(8));
				dto.setClasstype(rs.getString(9));
				dto.setKeyword(rs.getString(10));
				dto.setRank(rs.getInt(11));
				dto.setRankcnt(rs.getInt(12));
				dto.setPubImg(rs.getString(13));
				dto.setCellurl(rs.getString(14));

				res.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}
		return res;

	}

	public List<ClassInfoDto> selectList(int[] param) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ClassInfoDto> res = new ArrayList<>();

		String sql = "SELECT * FROM TB_CLASSINFO C JOIN TB_CLASSDATE D ON (C.CLASSPK=D.CLASSDATEPK) AND D.STR_TIME=? AND D.CLASSDAY =? AND C.CLASSTYPE=? WHERE C.LOC=?";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, param[1]);
			pstm.setInt(2, param[0]);
			pstm.setInt(3, param[2]);
//	         pstm.setInt(4, param[3]);

			while (rs.next()) {

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}
		return res;
	}

	public List<ClassInfoDto> customselect(int[] param) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ClassInfoDto> res = new ArrayList<ClassInfoDto>();
		String sql = "";

		System.out.println(param[0] + "," + param[1] + "," + param[2] + "," + param[3]);

		String defQR, typeQR, timeQR, wodQR, locQR;
		defQR = typeQR = timeQR = wodQR = locQR = "";
		// 순서대로 기본쿼리, 타입, 시간, 요일, 지역 선언 및 초기화

		defQR = "SELECT DISTINCT CI.* FROM TB_CLASSDATE CD, TB_CLASSINFO CI WHERE CD.CLASSPK=CI.CLASSPK";

		// 모든 값의 합이 1 이상이라면 조건이 선택되었다고 판단하고 쿼리문 작성 시작
		if ((param[0] + param[1] + param[2] + param[3]) > 0) {
			if (param[3] % 100 == 0) {
				switch (param[3] / 100) {
				case 1:
					System.out.println("서울지역 전체 선택됨" + param[3]);
					defQR += " AND REGEXP_LIKE(LOC, '1 |2 |3 |4 |5 |6 |7 |8 |9 |10 |11 |12 |13 |14 |15 |16 |17 |18 |19 |20 |21 |22 |23 |24 |25 |26 ')";
					System.out.println(defQR);
					break;
				case 2:
					System.out.println("경기지역 전체 선택됨" + param[3]);
					defQR += " AND REGEXP_LIKE(LOC, '27 |28 |29 |30 |31 |32 ')";
					System.out.println(defQR);
					break;
				case 3:
					System.out.println("인천지역 전체 선택됨" + param[3]);
					defQR += " AND REGEXP_LIKE(LOC, '33 |34 |35 |36 ')";
					System.out.println(defQR);
					break;
				case 4:
					System.out.println("부산지역 전체 선택됨" + param[3]);
					defQR += " AND REGEXP_LIKE(LOC, '37 |38 |39 |40 |41 |42 |43 |44 ')";
					System.out.println(defQR);
					break;
				case 5:
					System.out.println("경상,대구,울산 지역 전체 선택됨" + param[3]);
					defQR += " AND REGEXP_LIKE(LOC, '45 |46 |47 |48 |49 |50 |51 |52 |53 ')";
					System.out.println(defQR);
					break;
				case 6:
					System.out.println("대전,충청 지역 전체 선택됨" + param[3]);
					defQR += " AND REGEXP_LIKE(LOC, '54 |55 |56 |57 |58 ')";
					System.out.println(defQR);
					break;
				case 7:
					System.out.println("광주, 전라, 제주 지역 전체 선택됨" + param[3]);
					defQR += " AND REGEXP_LIKE(LOC, '59 |60 |61 |62 |63 |64 ')";
					System.out.println(defQR);
					break;
				case 8:
					System.out.println("온라인 전체 선택됨" + param[3]);
					defQR += " AND CI.LOC LIKE('65 %')";
					System.out.println(defQR);
					break;
				default:
					break;
				}
			}
			if (param[3] < 100 && param[3] > 0) {
				System.out.println("지역 선택됨!" + param[3]);
				locQR += param[3] + " %";
				defQR += " AND CI.LOC LIKE '" + locQR + "'";
				System.out.println(defQR);
			}
			if (param[1] != 0 && param[1] != 3) { // 모두 선택 또는 선택안함
				System.out.println("시간 선택됨!" + param[1]);
				timeQR += "TO_NUMBER(TO_CHAR(CD.str_time,'HH24'))";
				if (param[1] % 2 == 1) {
					timeQR += "<12";
				} else {
					timeQR += ">=12";
				}
				defQR += " AND " + timeQR;
				System.out.println(defQR);
			}
			if (param[2] > 0) {
				System.out.println("수업형태 선택됨!");
				boolean firstQR = true;
				String tableQR[] = { "'1N1'", "'group'", "'oneday'" };
				for (int i = 0; i < 3; i++) {
					if (param[2] % 2 == 1) {
						typeQR += (firstQR ? "" : ",") + tableQR[i];
						firstQR = false;
					}
					if (param[2] == 0)
						break;
					param[2] /= 2;
				}
				defQR += " AND CI.classtype IN (" + typeQR + ")";
				System.out.println(defQR);
			}
			if (param[0] > 0) {
				System.out.println("요일 선택됨!");
				boolean firstQR = true;
				int tableQR[] = { 2, 3, 4, 5, 6, 7, 1 };
				for (int i = 0; i < 7; i++) {
					if (param[0] % 2 == 1) {
						wodQR += (firstQR ? "" : ",") + "'" + tableQR[i] + "'";
						firstQR = false;
					}
					if (param[0] == 0)
						break;
					param[0] /= 2;
				}
				defQR += " AND TO_CHAR(CD.classday,'D') IN (";
				defQR += wodQR;
				defQR += ")";
				System.out.println(defQR);
			}
		}
		sql = defQR; // 생성한 쿼리문을 sql에 입력
		// 모든 합이 0이라면 선택된 값이 없어 기본 쿼리가 실행
		System.out.println(sql);

		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				ClassInfoDto dto = new ClassInfoDto();
				dto.setClasspk(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPrice(rs.getInt(5));
				dto.setAllstudent(rs.getInt(6));
				dto.setLoc(rs.getString(7));
				dto.setCategory(rs.getString(8));
				dto.setClasstype(rs.getString(9));
				dto.setKeyword(rs.getString(10));
				dto.setRank(rs.getInt(11));
				dto.setRankcnt(rs.getInt(12));
				dto.setPubImg(rs.getString(13));
				dto.setCellurl(rs.getString(14));

				res.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}
		return res;
	}

	public int InsertClass(ClassInfoDto dto, int... key) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		String category = "";

		switch (key[0]) {

		case 1:
			category = "popular";
			switch (key[1]) {
	
			default:
			
			}

			break;
		case 2:
			category = "design";
			switch (key[1]) {
			case 1:
				category += " photoshop";
				break;
			case 2:
				category +=" illustrate";
			}

			break;
		case 3:
			category = "music";

			break;
		case 4:
			category = "video";
			switch(key[1]) {
			case 1:
				category +=" premier";
			}
			break;
		case 5:
			category = "language";
			break;
		case 6:
			category = "lifeStyle";
			switch(key[1]) {
			case 1:
				category +=" cooking";
			}
			break;
		case 7:
			category = "nearloc";
			break;
		default:
			category = "null";
			break;

		}

		String loc = dto.getLoc();
		System.out.println(loc);
		String sql = "INSERT INTO tb_classinfo VALUES(?,?,?,?,?,?,?,?,?,?,DEFAULT,DEFAULT,?,NULL)";
		try {

			pstm = con.prepareStatement(sql);
			pstm.setInt(1, dto.getClasspk());
			pstm.setString(2, dto.getId());
			pstm.setString(3, dto.getTitle());
			pstm.setString(4, dto.getContent());
			pstm.setInt(5, dto.getPrice());
			pstm.setInt(6, dto.getAllstudent());
			pstm.setString(7, loc);
			pstm.setString(8, category);
			pstm.setString(9, dto.getClasstype());
			pstm.setString(10, dto.getKeyword());
			pstm.setString(11, dto.getPubImg());
			res = pstm.executeUpdate();

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

	public int resetExcelURL(int classpk) {
		int res = 0;
		String sql = "update tb_classinfo set CELLURL = null where classpk=?";
		Connection con = getConnection();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, classpk);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}
		return res;
	}

	public int setExcelURL(int classpk, String url) {
		int res = 0;
		String sql = "update tb_classinfo set CELLURL = ? where classpk=?";
		Connection con = getConnection();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, url);
			pstm.setInt(2, classpk);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}
		return res;
	}

	public int updateClass(ClassInfoDto dto) {
		int res = 0;
		String sql = "UPDATE tb_classinfo SET TITLE=?, CONTENT=?, CLASSTYPE=?, PUBIMG=? WHERE CLASSPK=?";

		Connection con = getConnection();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setString(3, dto.getClasstype());
			pstm.setString(4, dto.getPubImg());
			pstm.setInt(5, dto.getClasspk());
			res = pstm.executeUpdate();
			if (res > 0) {
				con.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public int updaterank(int classpk) {
		int res = 0;
		String sql = "UPDATE tb_classinfo SET rank=(NVL((select sum(strp) from tb_class_review where classpk=?),0)), rankcnt=(select count(strp) from tb_class_review where classpk=?) where classpk=?";
		Connection con = getConnection();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, classpk);
			pstm.setInt(2, classpk);
			pstm.setInt(3, classpk);
			res = pstm.executeUpdate();
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}
		return res;
	}

	public int delete(int classpk) {
		int res = 0;
		String sql = "DELETE FROM tb_classinfo WHERE CLASSPK=?";

		Connection con = getConnection();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, classpk);
			res = pstm.executeUpdate();
			if (res > 0) {
				con.commit();
			}
			System.out.println("class info delete 돌았다");
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
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(res);
		return res;
	}
}
