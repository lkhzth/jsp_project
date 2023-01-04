package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import common.ConnectionUtil;
import domain.BoardVO;
import domain.MemberVO;
import domain.ReserveVO;
import domain.MemberVO.MemberGrade;

public class ReserveDAO {
	
	private DataSource dataSource;
	
	public ReserveDAO() {
		dataSource = ConnectionUtil.getDataSource();
	}

	// 예약글리스트
	public List<ReserveVO> reserveList() {
		List<ReserveVO> list = new ArrayList<>();
		String query = "SELECT * FROM reserve_TB ORDER BY mno DESC";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
		){
			while (rs.next()) {
				ReserveVO vo = ReserveVO.builder()
						.mno(rs.getInt("mno"))
						.title(rs.getString("title"))
						.content(rs.getString("content"))
						.phone(rs.getString("phone"))
						.writer(rs.getString("writer"))
						.writeDate(rs.getDate("writeDate"))
						.reserveReplyCount(rs.getInt("reserveReplyCount")).build();
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 예약글상세
	public ReserveVO reserveAll(int mno) {
		ReserveVO vo = null;
		String query = "SELECT * FROM reserve_TB where mno=?";
		
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);	
		){
			pstmt.setInt(1, mno);
			try (ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					vo = ReserveVO.builder()
							.mno(rs.getInt("mno"))
							.title(rs.getString("title"))
							.content(rs.getString("content"))
							.phone(rs.getString("phone"))
							.writer(rs.getString("writer"))
							.writeDate(rs.getDate("writeDate")).build();
				}
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	// 예약글추가
	public void reserveWrite(ReserveVO vo) {
		String query = "INSERT INTO reserve_TB(mno, title, content, phone, writer) VALUES(mno_seq.NEXTVAL, ?, ?, ?, ?)";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getWriter());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 폰번호 체크
	public boolean phoneCheck(String phone) {
		boolean result = false;
		ReserveVO vo = new ReserveVO();
		String query = "SELECT DECODE(COUNT(phone),1,'TRUE','FALSE') AS RESULT FROM reserve_TB WHERE phone=?";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
			){
				pstmt.setString(1, vo.getPhone());
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
	
	
}
