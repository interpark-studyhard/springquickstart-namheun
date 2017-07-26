package com.springbook.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {
public static void main(String[] args) {
	
	//������ �����̳� ����
	AbstractApplicationContext  container = new GenericXmlApplicationContext("applicationContext.xml");
	
	//Spring �����̳ʷκ��� BoardServiceImple ��ü�� LookUp�Ѵ�.
	BoardService boardService = (BoardService) container.getBean("boardService");
	
	//�� ��� ��� �׽�Ʈ
	BoardVO vo = new BoardVO();
	vo.setTitle("�ӽ� ����");
	vo.setWriter("ȫ�浿");
	vo.setContent("�ӽó���/..................)");
	//boardService.insertBoard(vo);
	
	//�� ��� �˻� ��� �׽�Ʈ
	List<BoardVO> boardList = boardService.getBoardList(vo);
	
	for(BoardVO board : boardList){
			System.out.println("--->> "+board.toString());
	}
	
	//Spring �����̳� ����
	container.close();

	}
}
