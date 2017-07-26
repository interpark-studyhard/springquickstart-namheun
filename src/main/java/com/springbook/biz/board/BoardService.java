package com.springbook.biz.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.biz.common.LogAdvice;

public interface BoardService {
	
	

	//�� ���
	void insertBoard(BoardVO vo);
	//�� ����
	void updateBoard(BoardVO vo);

	void deleteBoard(BoardVO vo);

	//�� ����ȸ
	BoardVO selectBoard(BoardVO vo);

	//�� ��� ��ȸ
	List<BoardVO> getBoardList(BoardVO vo);

}
