package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import common.ConnectionUtil;
import domain.ReplyVO;

public class ReplyDao {

	private DataSource dataSource;
	
	public ReplyDao() {
		dataSource = ConnectionUtil.getDataSource();
	}
	
	public List<ReplyVO> replyList(int bno) {
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		String query = "SELECT * FROM REPLY_TB WHERE bno=?";
		
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setInt(1, bno);
			try(ResultSet rs = pstmt.executeQuery();) {
				while(rs.next()) {
					ReplyVO vo = ReplyVO.builder()
							.rno(rs.getInt("rno"))
							.bno(rs.getInt("bno"))
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
	
	public ReplyVO find(int rno) {
		String query = "SELECT * FROM REPLY_TB WHERE rno=?";
		ReplyVO vo = null;
		
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setInt(1, rno);
			try (ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					vo = ReplyVO.builder()
							.rno(rs.getInt("rno"))
							.bno(rs.getInt("bno"))
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
	
	public void insert(ReplyVO vo) {
		String query = "insert into REPLY_TB(rno, bno,reply,writer) values(seq_reply.nextval,?,?,?)";
		String query2 = "update BOARD_TB set replyCount=replyCount+1 where bno=?";
		String query3 = "update BOARD_TB set replyCount = (select count(bno) from REPLY_TB where REPLY_TB.bno = BOARD_TB.bno)"; 
		
		try (Connection conn = dataSource.getConnection();){
			try (
					PreparedStatement pstmt = conn.prepareStatement(query);
					PreparedStatement pstmt2 = conn.prepareStatement(query2);
					PreparedStatement pstmt3 = conn.prepareStatement(query3);
				){
				conn.setAutoCommit(false);
				pstmt.setInt(1, vo.getBno());
				pstmt.setString(2, vo.getReply());
				pstmt.setString(3, vo.getWriter());
				pstmt.executeUpdate();
				
				pstmt2.setInt(1, vo.getBno());
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
	
	public void delete(int bno, int rno) {
		String query = "delete from REPLY_TB where rno=?";
		String query2 = "update BOARD_TB set replyCount=replyCount-1 where bno=?";

		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				PreparedStatement pstmt2 = conn.prepareStatement(query2);
			){
			pstmt.setInt(1, rno);
			pstmt.executeUpdate();
			
			pstmt2.setInt(1, bno);
			pstmt2.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
