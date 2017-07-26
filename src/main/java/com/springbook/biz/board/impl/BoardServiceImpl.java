package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.LogAdvice;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	//private BoardDAO boardDAO;
	private BoardDAOSpring boardDAO;
	private LogAdvice log;
	

	public BoardServiceImpl() {
		log = new LogAdvice();
	}

	@Override
	public void insertBoard(BoardVO vo) {
		log.printLog();
		boardDAO.insertBoard(vo);
		boardDAO.insertBoard(vo);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoard(BoardVO vo) {
		log.printLog();
		boardDAO.updateBoard(vo);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		log.printLog();
		boardDAO.deleteBoard(vo);
		// TODO Auto-generated method stub
		
	}

	@Override
	public BoardVO selectBoard(BoardVO vo) {
		log.printLog();
	
		// TODO Auto-generated method stub
		return 	boardDAO.selectBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		log.printLog();
		
		// TODO Auto-generated method stub
		return boardDAO.getBoardList(vo);
	}

}
