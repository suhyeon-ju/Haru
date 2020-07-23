package com.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static common.JDBCTemplate.*;
import com.member.dto.MemberDto;
import com.util.UtilTemp;
/**
 * 멤버 테이블에 CRUD 하는 클래스입니다.
 * @author SH
 */
public class MemberDao {
   /**
    * 기본 생성자
    */
   public MemberDao() {
      super();
   }
   /**
    * 기본키 값을 사용해 member 정보를 요청합니다.
    * @param id member 의 기본키
    * @return MemberDto를 리턴합니다.
    */
   public MemberDto selectOne(String id) {
      Connection con = getConnection();
      PreparedStatement pstm = null;
      ResultSet rs = null;
      MemberDto res = null;
      String sql = "SELECT * FROM tb_member WHERE id=?";
      try {
         pstm = con.prepareStatement(sql);
         pstm.setString(1, id);
         rs = pstm.executeQuery();
         if (rs.next()) {
            res = new MemberDto(
               rs.getString("id"),
               rs.getString("pwd"),
               rs.getString("name"),
               rs.getString("nickname"),
               rs.getDate("birth"),
               rs.getString("gender"),
               rs.getString("email"),
               rs.getString("phone"),
               rs.getString("isclass"),
               rs.getString("profileImg"),
               rs.getString("pmessage"),
               rs.getString("openchat"),
               rs.getString("career"),
               rs.getString("idcard"),
               rs.getString("license")
            );
         }
      }catch(SQLException e) {
         e.printStackTrace();
      }finally {
         close(rs,pstm,con);
      }
      return res;
   }
   public MemberDto selectOne(String id,String pw) {
      Connection con = getConnection();
      PreparedStatement pstm = null;
      ResultSet rs = null;
      MemberDto res = null;
      String sql = "SELECT * FROM tb_member WHERE id=? AND pwd=?";
      try {
         pstm = con.prepareStatement(sql);
         pstm.setString(1, id.toLowerCase());
         pstm.setString(2, pw);
         rs = pstm.executeQuery();
         if (rs.next()) {
            res = new MemberDto(
               rs.getString("id"),
               rs.getString("pwd"),
               rs.getString("name"),
               rs.getString("nickname"),
               rs.getDate("birth"),
               rs.getString("gender"),
               rs.getString("email"),
               rs.getString("phone"),
               rs.getString("isclass"),
               rs.getString("profileImg"),
               rs.getString("pmessage"),
               rs.getString("openchat"),
               rs.getString("career"),
               rs.getString("idcard"),
               rs.getString("license")
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
    * 멤버 테이블에 정보를 추가합니다.
    * @param dto 멤버 정보가 담긴 DTO
    * @return 실패:0 / 성공:1
    */
   public int insertMember(MemberDto dto) {
         
         String sql="INSERT INTO tb_member VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
         Connection con = getConnection();
         PreparedStatement pstm = null;
         int res = 0;
         
         try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, dto.getId());
            pstm.setString(2, dto.getPwd());
            pstm.setString(3, dto.getName());
            pstm.setString(4, dto.getNickname());
            pstm.setDate(5, dto.getBirth());
            pstm.setString(6, dto.getGender());
            pstm.setString(7, dto.getEmail());
            pstm.setString(8, dto.getPhone());
            pstm.setString(9, dto.getIsclass());
            pstm.setString(10, dto.getProfileImg());
            pstm.setString(11, dto.getPmessage());
            pstm.setString(12, dto.getOpenchat());
            pstm.setString(13, dto.getCareer());
            pstm.setString(14, dto.getIdcard());
            pstm.setString(15, dto.getLicense());
            
            res = pstm.executeUpdate();

            if(res>0) {
               con.commit();
               
            }
            
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         } finally {
            close(pstm);
            close(con);
         }
         
         return res;
         
      }
      /**
       * 모든 회원 정보를 조회합니다.
       * @return 모든 회원정보 List
       */
      public List<MemberDto> selectAll(){
         
//         String sql = "SELECT * FROM tb_member WHERE isclass='Y'";
         String sql = "SELECT * FROM tb_member";
         Connection con = getConnection();
         Statement stmt = null;
         ResultSet rs = null;
         List<MemberDto> res = new ArrayList<MemberDto>();
         
         try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next()) {
               MemberDto dto = new MemberDto();
               dto.setId(rs.getString(1));
               dto.setPwd(rs.getString(2));
               dto.setName(rs.getString(3));
               dto.setNickname(rs.getString(4));
               dto.setBirth(rs.getDate(5));
               dto.setGender(rs.getString(6));
               dto.setEmail(rs.getString(7));
               dto.setPhone(rs.getString(8));
               dto.setIsclass(rs.getString(9));
               dto.setProfileImg(rs.getString(10));
               dto.setPmessage(rs.getString(11));
               dto.setOpenchat(rs.getString(12));
               dto.setCareer(rs.getString(13));
               dto.setIdcard(rs.getString(14));
               dto.setLicense(rs.getString(15));
         
               res.add(dto);
            }
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            close(rs);
            close(stmt);
            close(con);
         }
         return res;
      }

   /**
    * 아이디를 입력하면 비밀번호를 리턴
    * @param id 아이디
    * @return 아이디와 일치하는 비밀번호
    */
   public String loginChk(String id) {
         
         String sql = "SELECT pwd FROM tb_member WHERE id=?";
         
         Connection con = getConnection();
         PreparedStatement pstm = null;
         ResultSet rs = null;
         String res = null;
         
         try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            
            while(rs.next()) {
               res = rs.getString(1);
            }
               
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            close(rs);
            close(pstm);
            close(con);
         }
         return res;
      }
   
   	public int delete(String id,String pwd) {
   		if(id==null || pwd==null) {return 0;}
   		String sql = "DELETE FROM tb_member where id=? AND pwd=?";
   		Connection con = getConnection();
   		PreparedStatement pstm = null;
   		int res = 0;
   		try {
   			pstm = con.prepareStatement(sql);
   			pstm.setString(1, id);
   			pstm.setString(2, pwd);
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
     * 아이디와 일치하는 회원정보를 삭제합니다.
     * @param id 회원 아이디
     * @return 0:실패 또는 없음/ 1:성공
     */
    public int delete(String id) {
         String sql = "DELETE FROM tb_member WHERE id=?";
         
         Connection con = getConnection();
         PreparedStatement pstm = null;
         int res = 0;
         
         try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, id);
            res = pstm.executeUpdate();
            
            if(res>0) {
               commit(con);
            }
            
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            close(pstm);
            close(con);   
      
         }
      
         return res;
      }
    /**
     * 회원정보를 수정합니다.
     * @param dto 회원정보가 담긴 DTO
     * @return 0:없거나 실패 / 1:성공
     */
    
//   전체항목 update용, st_update :학생 프로필 업데이트용 te_update : 강사 프로필 업데이트 용 pw붙은거는  pw포함
    //** 수정 시작 ***
   public int update(MemberDto dto) {
        String sql = "UPDATE tb_member SET pwd=?, name =?,nickname=?,birth=? ,gender=?,email=?,"
              + "phone=?,isclass =?,profileImg=? ,pmessage=?,openchat=? ,career =?,idcard=? ,license=?"
              +"WHERE id=?";
              
        Connection con = getConnection();
        PreparedStatement pstm = null;
        int res = 0;
        
        try {
           pstm = con.prepareStatement(sql);
           
           pstm.setString(1, dto.getPwd());
           pstm.setString(2, dto.getName());
           pstm.setString(3, dto.getNickname());
           pstm.setDate(4, dto.getBirth());
           pstm.setString(5, dto.getGender());
           pstm.setString(6, dto.getEmail());
           pstm.setString(7, dto.getPhone());
           pstm.setString(8, dto.getIsclass());
           pstm.setString(9, dto.getProfileImg());
           pstm.setString(10, dto.getPmessage());
           pstm.setString(11, dto.getOpenchat());
           pstm.setString(12, dto.getCareer());
           pstm.setString(13, dto.getIdcard());
           pstm.setString(14, dto.getLicense());
           pstm.setString(15, dto.getId());
           
           res = pstm.executeUpdate();
           
           if(res>0) {
              con.commit();
           }
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
           close(pstm);
           close(con);
        }
        return res;
     }
   
   public int st_update(MemberDto dto) {
        String sql = "UPDATE tb_member SET nickname=?,birth=?,profileImg=? WHERE id=?";
              
        Connection con = getConnection();
        PreparedStatement pstm = null;
        int res = 0;
        
        try {
           pstm = con.prepareStatement(sql);
           
          
           pstm.setString(1, dto.getNickname());
           pstm.setDate(2, dto.getBirth());
           pstm.setString(3, dto.getProfileImg());
           pstm.setString(4, dto.getId());
           
           res = pstm.executeUpdate();
           
           if(res>0) {
              con.commit();
           }
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
           close(pstm);
           close(con);
        }
        return res;
     }
   
   public int st_pw_update(MemberDto dto) {
        String sql = "UPDATE tb_member SET nickname=?,birth=?,profileImg=?,pwd=? WHERE id=?";
              
        Connection con = getConnection();
        PreparedStatement pstm = null;
        int res = 0;
        
        try {
           pstm = con.prepareStatement(sql);
           
          
           pstm.setString(1, dto.getNickname());
           pstm.setDate(2, dto.getBirth());
           pstm.setString(3, dto.getProfileImg());
           pstm.setString(4, dto.getPwd());
           pstm.setString(5, dto.getId());
           
           res = pstm.executeUpdate();
           
           if(res>0) {
              con.commit();
           }
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
           close(pstm);
           close(con);
        }
        return res;
     }
   
   
   public int te_update(MemberDto dto) {
        String sql = "UPDATE tb_member SET nickname=?,birth=? ,email=?,profileImg=? ,pmessage=?,"
              + "openchat=?,career =? WHERE id=?";
              
        Connection con = getConnection();
        PreparedStatement pstm = null;
        int res = 0;
        
        try {
           pstm = con.prepareStatement(sql);
           
        
           pstm.setString(1, dto.getNickname());
           pstm.setDate(2, dto.getBirth());
           pstm.setString(3, dto.getEmail());
           pstm.setString(4, dto.getProfileImg());
           pstm.setString(5, dto.getPmessage());
           pstm.setString(6, dto.getOpenchat());
           pstm.setString(7, dto.getCareer());
           pstm.setString(8, dto.getId());
           
           res = pstm.executeUpdate();
           
           if(res>0) {
              con.commit();
           }
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
           close(pstm);
           close(con);
        }
        return res;
     }
   public int te_pw_update(MemberDto dto) {
        String sql = "UPDATE tb_member SET nickname=?,birth=? ,email=?,profileImg=? ,pmessage=?,"
              + "openchat=?,career =?,pwd=? WHERE id=?";
              
        Connection con = getConnection();
        PreparedStatement pstm = null;
        int res = 0;
        
        try {
           pstm = con.prepareStatement(sql);
           
        
           pstm.setString(1, dto.getNickname());
           pstm.setDate(2, dto.getBirth());
           pstm.setString(3, dto.getEmail());
           pstm.setString(4, dto.getProfileImg());
           pstm.setString(5, dto.getPmessage());
           pstm.setString(6, dto.getOpenchat());
           pstm.setString(7, dto.getCareer());
           pstm.setString(8, dto.getPwd());
           pstm.setString(9, dto.getId());
           
           res = pstm.executeUpdate();
           
           if(res>0) {
              con.commit();
           }
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
           close(pstm);
           close(con);
        }
        return res;
     }
   //** 수정완료 **
   public MemberDto nicknameSelect(String nickname) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MemberDto res = null;
		String sql = "SELECT * FROM tb_member WHERE nickname=?";
		try {
		   pstm = con.prepareStatement(sql);
		   pstm.setString(1, nickname);
		   rs = pstm.executeQuery();
		   if (rs.next()) {
		      res = new MemberDto(
				 rs.getString("id"),
				 rs.getString("pwd"),
				 rs.getString("name"),
				 rs.getString("nickname"),
				 rs.getDate("birth"),
				 rs.getString("gender"),
				 rs.getString("email"),
				 rs.getString("phone"),
				 rs.getString("isclass"),
				 rs.getString("profileImg"),
				 rs.getString("pmessage"),
				 rs.getString("openchat"),
				 rs.getString("career"),
				 rs.getString("idcard"),
				 rs.getString("license")
		      );
		   }
		}catch(SQLException e) {
		   e.printStackTrace();
		}finally {
		   close(rs,pstm,con);
		}
		return res;
	}
   public MemberDto selectTeacher(String id) {
	   UtilTemp t = new UtilTemp();
	      Connection con = getConnection();
	      PreparedStatement pstm = null;
	      ResultSet rs = null;
	      MemberDto res = null;
	      String sql = "SELECT * FROM tb_member WHERE id=?";
	      try {
	         pstm = con.prepareStatement(sql);
	         pstm.setString(1, id.toLowerCase());
	         rs = pstm.executeQuery();
	         if (rs.next()) {
	            res = new MemberDto(
	               rs.getString("id"),
	               null, //pw
	               rs.getString("name"),
	               rs.getString("nickname"),
	               rs.getDate("birth"),
	               rs.getString("gender"),
	               t.nullStr(rs.getString("email"), ""),
	               t.nullStr(rs.getString("phone"), ""),
	               rs.getString("isclass"),
	               t.nullStr(rs.getString("profileImg"), "./payment_page/payment_cat-1151519_1280.jpg"),
	               t.nullStr(rs.getString("pmessage"), "강사 소개글이 없습니다."),
	               t.nullStr(rs.getString("openchat"), ""),
	               t.nullStr(rs.getString("career"), ""),
	               null,
	               null
	            );
	         }
	      }catch(SQLException e) {
	         e.printStackTrace();
	      }finally {
	         close(rs,pstm,con);
	      }
	      return res;
	   }
   public List<MemberDto> findnickname(String id) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MemberDto> res = null;
		String sql = "SELECT NICKNAME FROM tb_member WHERE ID=?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			while(rs.next()) {
				MemberDto temp = new MemberDto();
				temp.setNickname(rs.getString(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
}