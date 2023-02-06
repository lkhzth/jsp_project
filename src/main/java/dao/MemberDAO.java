package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import common.ConnectionUtil;
import domain.MemberVO;
import domain.MemberVO.MemberGrade;

public class MemberDAO {
	
	private DataSource dataSource;
	
	public MemberDAO() {
		dataSource = ConnectionUtil.getDataSource();
	}

	// 회원가입
	public void insertMember(MemberVO vo) {
		String query = "INSERT INTO member_tb(mno, id, pwd, name, email, phone) VALUES(mno_seq.NEXTVAL, ?, ?, ?, ?, ?)";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getPhone());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 로그인 체크
	public boolean loingCheck(MemberVO vo) {
		boolean result = false;
		String query = "SELECT DECODE(COUNT(*),1,'TRUE','FALSE') AS RESULT FROM member_tb WHERE id=? AND pwd=?";
		
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
			){
				pstmt.setString(1, vo.getId());
				pstmt.setString(2, vo.getPwd());
				try (ResultSet rs = pstmt.executeQuery();){
					if(rs.next()) {
						result = Boolean.parseBoolean(rs.getString("RESULT"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return result;
	}

	// 회원등급 조회
	public MemberGrade findMemberGradeById(String id) {
		MemberGrade grade = null;
		String query = "select grade from member_tb where id=?";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);	
		){
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					grade = MemberGrade.valueOf(rs.getString("grade"));
				}
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grade;
	}
}
