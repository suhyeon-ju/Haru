package com.controller.profile;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.classinfo.dto.ClassInfoDto;
import com.member.dto.MemberDto;

public class profileDao {
	// 누적 총 매출
	public int total_pay(String id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select sum(b.pay_price) from tb_classinfo a, tb_pay b where a.classpk = b.classpk and a.id = ? and b.pay_true='Y'";
		int res = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			rs = pstm.executeQuery();

			while (rs.next()) {
				res += rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}
		return res;
	}

//	총 강좌수
	public List<ClassInfoDto> selectAll(String id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ClassInfoDto> res = new ArrayList<ClassInfoDto>();
		String sql = "SELECT * FROM tb_classinfo WHERE id=? ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			while (rs.next()) {
				ClassInfoDto dto = new ClassInfoDto(rs.getInt("classpk"), rs.getString("id"), rs.getString("title"),
						rs.getString("content"), rs.getInt("price"), rs.getInt("allstudent"), rs.getString("loc"),
						rs.getString("category"), rs.getString("keyword"), rs.getInt("rank"), rs.getInt("rankcnt"),
						rs.getString("pubImg"), rs.getString("cellurl"));
				res.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}
		return res;
	}
//	select count(*) from tb_classinfo  a, tb_class_member b where a.classpk = b.classpk and a.id = 'testmem3';

//     내 강의를 듣는 총 학생수
	public int countStudent(String id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		String sql = "select count(*) from tb_classinfo  a, tb_class_member b where a.classpk = b.classpk and a.id = ? and b.chk_class='Y'";
//		select count(*) from tb_classinfo  a, tb_class_member b where a.classpk = b.classpk and a.id = 'testmem3' and b.chk_class='Y';
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			rs = pstm.executeQuery();

			while (rs.next()) {
				res += rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}
		return res;
	}

//	강의별 수강인원
	public int eachCountStudent(ClassInfoDto pk) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		String sql = "select count(*) from tb_member a, tb_class_member b where a.id = b.id and classpk = ?";
//		select count(*) from tb_member a, tb_class_member b where a.id = b.id and classpk=6;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, pk.getClasspk());
			rs = pstm.executeQuery();

			while (rs.next()) {
				res += rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}
		return res;
	}

	// 최대 수강 가능 인원
	public int countAllStudent(String id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		String sql = "select sum(allstudent) from tb_classinfo where id=?";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			while (rs.next()) {
				res += rs.getInt(1);// rs는 result이고 getInt(1)로 값을 가져옴
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}
		return res;
	}

	public List<String[]> monthList(String id) {

		return null;
	}

	public List<ClassInfoDto> Classlistinfo(String id) {
		System.out.println("sql 넣는다");
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ClassInfoDto> res = new ArrayList<ClassInfoDto>();
		String sql = "select title, cm.cnt*price profit, cm.cnt nowstudent, ROUND(cm.cnt/allstudent*100 , 1) course_rate, progress_rate, loc,  ci.classpk  "
				+ "            from tb_classinfo ci,  " + "               (select a.classpk, NVL(cnt,0) cnt "
				+ "               from tb_classinfo a left outer join  (select classpk, count(*) cnt  "
				+ "                                                from  tb_class_member group by classpk order by classpk asc) b  on a.classpk=b.classpk ) cm , "
				+ "               ( select A.classpk, ROUND(pastDate/totalDate*100 ,1)  progress_rate "
				+ "               from ( select ci.classpk, count(*) totalDate"
				+ "                  from tb_classinfo ci, tb_classdate cd "
				+ "                  where ci.classpk=cd.classpk " + "                  group by ci.classpk ) A,  "
				+ "              ( select a.classpk, NVL(pastDate,0) pastDate "
				+ "               from tb_classinfo a left outer join       "
				+ "                  (select ci.classpk, count(*) pastDate "
				+ "                 from tb_classinfo ci, tb_classdate cd "
				+ "                  where ci.classpk=cd.classpk and sysdate>classday "
				+ "                  group by ci.classpk) b  on a.classpk=b.classpk) B  "
				+ "                  where A.classpk=B.classpk ) tb_progress "
				+ "            where ci.classpk=cm.classpk and ci.classpk=tb_progress.classpk and id=? ";
		System.out.println("sql 준비");
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			rs = pstm.executeQuery();

			while (rs.next()) {
				ClassInfoDto dto = new ClassInfoDto();
				dto.setTitle(rs.getString(1));
				dto.setProfit(rs.getInt(2));
				dto.setNowstudent(rs.getInt(3));
				dto.setCourse_rate(rs.getDouble(4));
				dto.setProgress_rate(rs.getDouble(5));
				String address = rs.getString(6);
				dto.setLoc(goloc(address));
				/*
				 * address = address.substring(0, address.indexOf(" ", address.indexOf(" ") +
				 * 1)); dto.setLoc(address);
				 */
				dto.setClasspk(rs.getInt(7));

				System.out.println("infoselect :" + dto);

				res.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}
		return res;
	}
	public String goloc(String loc) {
		
		String goloc = loc;
		goloc = goloc.substring(goloc.indexOf(" ") + 1);
		if (goloc.indexOf(" ") == -1) {
			int length = goloc.length();
			if(length>=15) {length=15;}
			goloc = goloc.substring(0, length);
		} else {
			goloc = goloc.substring(goloc.indexOf(" ") + 1);
			goloc = goloc.substring(0, goloc.indexOf(" ") + 1);
		}
		return goloc;
	}

	public List<MemberDto> memeberList(int classpk) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MemberDto> res = new ArrayList<MemberDto>();
		String sql = "select name,phone,gender " + " from tb_member m, tb_class_member cm "
				+ " where m.id = cm.id and chk_class='Y' and classpk=?";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, classpk);
			rs = pstm.executeQuery();

			while (rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setName(rs.getString(1));
				dto.setPhone(rs.getString(2));
				dto.setGender(rs.getString(3));

				res.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}
		return res;
	}

	public int selectpercent(int classpk) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		String sql = "select pastDate/totalDate*100 progressrate " + "from ( select ci.classpk, count(*) totalDate "
				+ "   from tb_classinfo ci, tb_classdate cd" + "   where ci.classpk=cd.classpk"
				+ "   group by ci.classpk ) A, " + "       ( select ci.classpk, count(*) pastDate "
				+ "   from tb_classinfo ci, tb_classdate cd " + "   where ci.classpk=cd.classpk and sysdate>classday "
				+ "   group by ci.classpk ) B  " + "	where A.classpk=B.classpk and A.classpk=? ";

		try {

			pstm = con.prepareStatement(sql);
			pstm.setInt(1, classpk);
			rs = pstm.executeQuery();
			while (rs.next()) {
				res += rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}
		return res;
	}

	public String startdate(int classpk) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String res = "";
		String sql = "select classday from tb_classdate where classpk= ? order by classday asc ";

		try {

			pstm = con.prepareStatement(sql);
			pstm.setInt(1, classpk);
			rs = pstm.executeQuery();
			if (rs.next()) {
				res += rs.getDate(1);
				System.out.println("시작일" + res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}
		return res;
	}

	public String enddate(int classpk) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String res = "";
		String sql = "select classday from tb_classdate where classpk=? order by classday desc ";

		try {

			pstm = con.prepareStatement(sql);
			pstm.setInt(1, classpk);
			rs = pstm.executeQuery();
			if (rs.next()) {
				res += rs.getDate(1);
				System.out.println("종료일" + res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}
		return res;
	}

	public List<Integer> monthprofit(String id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Integer> res = new ArrayList<Integer>();
		String sql = "select sum(p.pay_price) from TB_PAY p, TB_CLASSINFO c  "
				+ "where p.classpk=c.classpk and c.id= ? and extract(month from p.pay_time)= ? and extract(year from p.pay_time)=extract(year from sysdate)";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			for (int i = 1; i <= 12; i++) {
				pstm.setInt(2, i);
				rs = pstm.executeQuery();
				if (rs.next()) {
					res.add(rs.getInt(1));
				} else {
					res.add(0);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}
		return res;
	}

	public List<chartDto> eachmonthprofit(String id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<chartDto> res = new ArrayList<chartDto>();
		String sql = "select A.title, extract(month from pay_time) month, classpk, sum(pay_price) sumprice "
				+ " from (select ci.title,ci.id teacherId, p.id memberId, p.classpk, p.pay_time, p.pay_price "
				+ "          from tb_classinfo ci, " + "			        ( select id, classpk, pay_time, pay_price "
				+ "			          from tb_pay "
				+ "			          where months_between(sysdate ,pay_time)<=2 and sysdate>=pay_time) p "
				+ "where ci.classpk=p.classpk and ci.id= ? ) A "
				+ "group by extract(month from pay_time), classpk, title " + "order by month asc, classpk asc";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			while (rs.next()) {
				chartDto dto = new chartDto(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
				res.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}
		return res;
	}

	// ---------------------------------------
	public chartDto makemonthdata() {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		chartDto res = new chartDto();
		String sql = "select extract(month from sysdate-(INTERVAL '2' MONTH)) firstday,extract(month from sysdate-(INTERVAL '1' MONTH)) secondday,"
				+ " extract(month from sysdate) thirdday from dual";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				res = new chartDto(rs.getInt(1), rs.getInt(2), rs.getInt(3));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt, con);
		}
		return res;
	}

	public List<String[]> tmp(String id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<String[]> res = new ArrayList<String[]>();
		String sql = "select classpk, title , extract(month from pay_time) Month, (count(*)*price) price from tb_pay left outer join tb_classinfo using(classpk) where pay_true='Y' and months_between(sysdate ,pay_time)<=2 and tb_classinfo.id=? group by classpk,title, extract(month from pay_time) ,price order by extract(month from pay_time)";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			rs = pstm.executeQuery();

			while (rs.next()) {
				String strtmp[] = { Integer.toString(rs.getInt(1)), rs.getString(2), "" + rs.getInt(3),
						"" + rs.getInt(4) };
				res.add(strtmp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}
		return res;
	}

	public String eachchart(String id) {
		String res = "";
		List<String[]> list = tmp(id);
//			list = new ArrayList<String[]>(); 값 없을때
		System.out.println(list.size());

		Map<Integer, String> title = new HashMap<>();
		Map<Integer, String> month = new HashMap<>();
		Map<Integer, String> classpk = new HashMap<>();

		String result = "";
		String trash = "'Month'";

		for (String[] obj : list) {
			title.put(Integer.parseInt(obj[0]), obj[1]);
			month.put(Integer.parseInt(obj[2]), obj[2]);
			classpk.put(Integer.parseInt(obj[0]), obj[1]);

			for (String str : obj) {
				System.out.print(str + ",");
			}
			System.out.println();
		}
//			System.out.println(title.size());

		Iterator<Integer> iteratorTitle = title.keySet().iterator();
		Iterator<Integer> iteratorMonth = month.keySet().iterator();
		Iterator<Integer> iteratorPk = classpk.keySet().iterator();
		while (iteratorTitle.hasNext()) {
			int key = iteratorTitle.next();
//				System.out.println(key+","+title.get(key));
			trash += ",'" + title.get(key) + "'";
		}
//			System.out.println(trash);
		if (title.size() != 0)
			result += "[" + trash + "]"; // 맨 윗줄 타이틀 완료!!
		System.out.println("완료!!!!!!" + result);

//			while(iteratorMonth.hasNext()) {
//				int key = iteratorMonth.next(); //달
//				System.out.println(key);
//				trash = "'"+key+"'";
//				iteratorPk = classpk.keySet().iterator(); 
//				while(iteratorPk.hasNext()) {
//					int pk=iteratorPk.next();  //pk클래스 고유키
//					System.out.println("pk ="+pk);
//					for(String[] price : list ) {
//						int monchk = Integer.parseInt(price[2]);
//						if( monchk ==  pk) {
//							System.out.println(monchk+":"+ pk);
//							
//							
//						}
//					}
//				}
//				result += ",["+trash+"]";
//			}
		iteratorMonth = month.keySet().iterator();
		while (iteratorMonth.hasNext()) {
			int key = iteratorMonth.next();
			trash = "'" + key + "'";

			iteratorPk = classpk.keySet().iterator();
			System.out.println(key + "," + month.get(key));
			while (iteratorPk.hasNext()) {
//					  System.out.println(iteratorPk.next());
				int pk = iteratorPk.next();
//					  System.out.println(pk);
				boolean bool = false;
				for (String[] cell : list) {
					if (Integer.toString(pk).equals(cell[0]) && Integer.toString(key).equals(cell[2])) {
						bool = true;
						trash += "," + Integer.parseInt(cell[3]);
						break;
					}
				}
				System.out.println(bool);
				trash += (!bool) ? "," + 0 : "";
			}
			result += ",[" + trash + "]";

		}
		if (title.size() == 0) {
			result += "['Month','강의 없음'],['월',0]";

		}
		System.out.println("최종출력" + result);

		return result;
	}

}
