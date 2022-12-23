package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import common.ConnectionUtil;
import domain.MemberVO;
import domain.ReserveVO;
import domain.MemberVO.MemberGrade;

public class ReserveDAO {
	
	private DataSource dataSource;
	
	public ReserveDAO() {
		dataSource = ConnectionUtil.getDataSource();
	}

	public void reserveWrite(ReserveVO vo) {
		String query = "INSERT INTO reserve_TB(mno, name, phone, content) VALUES(mno_seq.NEXTVAL, ?, ?, ?)";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPhone());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// 등급 조회
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
