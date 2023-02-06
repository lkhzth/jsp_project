package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import common.ConnectionUtil;
import domain.ReserveReplyVO;

public class ReserveReplyDao {

private DataSource dataSource;

	public ReserveReplyDao() {
		dataSource = ConnectionUtil.getDataSource();
	}
	
	public List<ReserveReplyVO> replyList(int mno) {
		List<ReserveReplyVO> list = new ArrayList<ReserveReplyVO>();
		String query = "SELECT * FROM REPLY_RESERVE_TB WHERE mno=?";
		
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setInt(1, mno);
			try(ResultSet rs = pstmt.executeQuery();) {
				while(rs.next()) {
					ReserveReplyVO vo = ReserveReplyVO.builder()
							.rno(rs.getInt("rno"))
							.mno(rs.getInt("mno"))
							.reply(rs.getString("reply"))
							.writer(rs.getString("writer"))
							.replyDate(rs.getDate("replyDate"))
							.modifyDate(rs.getDate("modifyDate")).build();
					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ReserveReplyVO find(int rno) {
		String query = "SELECT * FROM REPLY_RESERVE_TB WHERE rno=?";
		ReserveReplyVO vo = null;
		
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setInt(1, rno);
			try (ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					vo = ReserveReplyVO.builder()
							.rno(rs.getInt("rno"))
							.mno(rs.getInt("mno"))
							.reply(rs.getString("reply"))
							.writer(rs.getString("writer"))
							.replyDate(rs.getDate("replyDate"))
							.modifyDate(rs.getDate("modifyDate")).build();
				}
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	
	public void insert(ReserveReplyVO vo) {
		String query = "insert into REPLY_RESERVE_TB(rno, mno,reply,writer) values(reply_seq.nextval,?,?,?)";
		String query2 = "update reserve_TB set reserveReplyCount=reserveReplyCount+1 where mno=?";
		String query3 = "update reserve_TB set reserveReplyCount = (select count(mno) from REPLY_RESERVE_TB where REPLY_RESERVE_TB.mno = reserve_TB.mno)";
		
		try (Connection conn = dataSource.getConnection();){
			
			try (
					PreparedStatement pstmt = conn.prepareStatement(query);
					PreparedStatement pstmt2 = conn.prepareStatement(query2);
					PreparedStatement pstmt3 = conn.prepareStatement(query3);
				){
				conn.setAutoCommit(false);
				pstmt.setInt(1, vo.getMno());
				pstmt.setString(2, vo.getReply());
				pstmt.setString(3, vo.getWriter());
				pstmt.executeUpdate();
				
				pstmt2.setInt(1, vo.getMno());
				pstmt2.executeUpdate();
				
				pstmt3.executeUpdate();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void delete(int mno, int rno) {
		String query = "delete from REPLY_RESERVE_TB where rno=?";
		String query2 = "update reserve_TB set reserveReplyCount=reserveReplyCount-1 where mno=?";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			PreparedStatement pstmt2 = conn.prepareStatement(query2);
		){
			pstmt.setInt(1, rno);
			pstmt.executeUpdate();
			
			pstmt2.setInt(1, mno);
			pstmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
