<%@page import="com.springbook.biz.board.BoardVO"%>
<%@page import="com.springbook.biz.board.impl.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    
    
<%

	//����� �Է� ���� ����
 	String seq = request.getParameter("seq");
 	
 	BoardVO vo = new BoardVO();
	vo.setSeq(Integer.parseInt(seq));

	BoardDAO boardDao = new BoardDAO();
	boardDao.deleteBoard(vo);
	
	//ȭ�� �׺���̼�
	response.sendRedirect("getBoardList.jsp");


%>