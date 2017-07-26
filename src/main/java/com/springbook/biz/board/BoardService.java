package com.springbook.biz.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.biz.common.LogAdvice;

public interface BoardService {
	
	

	//글 등록
	void insertBoard(BoardVO vo);
	//글 수정
	void updateBoard(BoardVO vo);

	void deleteBoard(BoardVO vo);

	//글 상세조회
	BoardVO selectBoard(BoardVO vo);

	//글 목록 조회
	List<BoardVO> getBoardList(BoardVO vo);

}
