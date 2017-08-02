package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;
 
@Repository("boardDAO")
public class BoardDAO implements BoardService {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String BOARD_INSERT = "insert into board(seq, title, writer, content) values ((select nvl(max(seq), 0)+1 from board), ? , ?, ?)";
	private final String BOARD_UPDATE = "update board set title=?, content=? where seq = ?";
	private final String BOARD_DELETE = "delete board where seq = ?";
	private final String BOARD_GET = "select * from board where seq = ?";
	private final String BOARD_LIST = "select * from board order by seq desc";
	
	
	//�� ���
	/* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardService#inserBoard(com.springbook.biz.board.BoardVO)
	 */
	@Override
	public void insertBoard(BoardVO vo){
		System.out.println("===> JDBC�� INSERT BOARD() ���ó��");
		
	   try {
		   conn = JDBCUtil.getConnection();
		   stmt = conn.prepareStatement(BOARD_INSERT);
		   stmt.setString(1, vo.getTitle());
		   stmt.setString(2, vo.getWriter());
		   stmt.setString(3, vo.getContent());
		   stmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		JDBCUtil.close(stmt, conn);
	}
	   
	}
	//�� ����
	  /* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardService#updateBoard(com.springbook.biz.board.BoardVO)
	 */
	@Override
	public void updateBoard(BoardVO vo){
		  System.out.println("===> JDBC�� updateBoard() ��� ó��");
		  try {
			conn = JDBCUtil.getConnection();
			  stmt = conn.prepareStatement(BOARD_UPDATE);
			   stmt.setString(1, vo.getTitle());
			   stmt.setString(2, vo.getContent());
			   stmt.setInt(3, vo.getSeq());
			   
			   stmt.executeUpdate();
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
		   
	  }
	
	  //�� ����
	  
	  /* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardService#deleteBoard(com.springbook.biz.board.BoardVO)
	 */
	@Override
	public void deleteBoard(BoardVO vo){
		  System.out.println("===> JDBC�� deleteBoard() ��� ó��");
		  try {
			  conn = JDBCUtil.getConnection();
			  stmt = conn.prepareStatement(BOARD_DELETE);
			  stmt.setInt(1, vo.getSeq());
			   stmt.executeUpdate();
		  } catch (SQLException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }finally {
			  JDBCUtil.close(stmt, conn);
		  }
		  
	  }
	  
	  //�� ����ȸ
	  /* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardService#selectBoard(com.springbook.biz.board.BoardVO)
	 */
	@Override
	public BoardVO selectBoard(BoardVO vo){
		  System.out.println("===> JDBC�� selectBoard() ��� ó��");
		  
		  BoardVO board = null;
		  
		  try {
			  conn = JDBCUtil.getConnection();
			  stmt = conn.prepareStatement(BOARD_GET);
			  stmt.setInt(1, vo.getSeq());
			  rs = stmt.executeQuery();
			  
			  if( rs.next() ) {
				  board = new BoardVO();
				  board.setSeq(rs.getInt("SEQ"));
				  board.setTitle(rs.getString("TITLE"));
				  board.setWriter(rs.getString("WRITER"));
				  board.setContent(rs.getString("CONTENT"));
				  board.setRegDate(rs.getDate("REGDATE"));
				  board.setCnt(rs.getInt("CNT"));
			  }
			  
		  } catch (SQLException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }finally {
			  JDBCUtil.close(stmt, conn);
		  }
		  return board;
	  }
	  
	  //�� ��� ��ȸ
	  /* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardService#getBoardList(com.springbook.biz.board.BoardVO)
	 */
	@Override
	public List<BoardVO> getBoardList(BoardVO vo){
		  System.out.println("===> JDBC�� getBoardList() ��� ó��");
		  
		  List<BoardVO> list = new ArrayList<BoardVO>();
		  
		  try {
			  BoardVO board = null;
			  conn = JDBCUtil.getConnection();
			  stmt = conn.prepareStatement(BOARD_LIST);
			  rs = stmt.executeQuery();
			 while( rs.next() ) {
				  board = new BoardVO();
				  board.setSeq(rs.getInt("SEQ"));
				  board.setTitle(rs.getString("TITLE"));
				  board.setWriter(rs.getString("WRITER"));
				  board.setContent(rs.getString("CONTENT"));
				  board.setRegDate(rs.getDate("REGDATE"));
				  board.setCnt(rs.getInt("CNT"));
				  list.add(board);
			  }
			  
		  } catch (SQLException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }finally {
			  JDBCUtil.close(stmt, conn);
		  }
		  return list;
	  }
	  
}
