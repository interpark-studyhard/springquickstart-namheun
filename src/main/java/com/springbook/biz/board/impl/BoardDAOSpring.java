package com.springbook.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;
 
@Repository
public class BoardDAOSpring {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String BOARD_INSERT = "insert into board(seq, title, writer, content) values (100, ?, ?, ?)";
	//private final String BOARD_INSERT = "insert into board(seq, title, writer, content) values ((select nvl(max(seq), 0)+1 from board), ? , ?, ?)";
	private final String BOARD_UPDATE = "update board set title=?, content=? where seq = ?";
	private final String BOARD_DELETE = "delete board where seq = ?";
	private final String BOARD_GET = "select * from board where seq = ?";
	private final String BOARD_LIST = "select * from board order by seq desc";
	
	
	//글 등록
	/* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardService#inserBoard(com.springbook.biz.board.BoardVO)
	 */
	public void insertBoard(BoardVO vo){
		System.out.println("===> JDBC로 INSERT BOARD() 기능처리");
		jdbcTemplate.update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent());
	}
	//글 수정
	   
	  /* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardService#updateBoard(com.springbook.biz.board.BoardVO)
	 */
	public void updateBoard(BoardVO vo){
		  System.out.println("===> JDBC로 updateBoard() 기능 처리");
		  jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
	  }
	
	  //글 삭제
	  
	  /* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardService#deleteBoard(com.springbook.biz.board.BoardVO)
	 */
	public void deleteBoard(BoardVO vo){
		  System.out.println("===> JDBC로 deleteBoard() 기능 처리");
		  
		  jdbcTemplate.update(BOARD_DELETE,vo.getSeq());
	  }
	  
	  //글 상세조회
	  /* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardService#selectBoard(com.springbook.biz.board.BoardVO)
	 */
	public BoardVO selectBoard(BoardVO vo){
		  System.out.println("===> JDBC로 selectBoard() 기능 처리");
		  Object[] args = {vo.getSeq()};
		  
		  return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
	  }
	  
	  //글 목록 조회
	  /* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardService#getBoardList(com.springbook.biz.board.BoardVO)
	 */
		public List<BoardVO> getBoardList(BoardVO vo){
		  System.out.println("===> JDBC로 getBoardList() 기능 처리");
		  
		  return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
		  
	}
}





class BoardRowMapper implements RowMapper<BoardVO> {

	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		BoardVO board = new BoardVO();
		board.setSeq(rs.getInt("SEQ"));
		board.setTitle(rs.getString("TITLE"));
		board.setWriter(rs.getString("WRITER"));
		
		board.setContent(rs.getString("CONTENT"));
		board.setRegDate(rs.getDate("REGDATE"));
		board.setCnt(rs.getInt("CNT"));
		return board;
	}
}
